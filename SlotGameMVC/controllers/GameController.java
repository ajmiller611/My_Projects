package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import models.GameSounds;
import models.Reels;
import models.SlotGameModel;
import models.SymbolEnum;
import views.*;

public class GameController {
	private SlotGameModel gameModel;
	private GameGUI gameView;
	private BonusRoundController bonusController;
	private ActionListener listener;
	private PayoutController payout;
	private Timer timer;
	
	public GameController() {
		//Create game model
		gameModel = new SlotGameModel();
		
		//Create bonus round controller and pass a reference to game controller
		bonusController = new BonusRoundController(this);
		
		listener = new EventHandler();
		
		//Create payout controller and pass a reference to game controller
		payout = new PayoutController(this);
	}
	
	public void loadResources() {
		Reels.createReels();
		
		payout.setupPaylines();
		
		bonusController.setupImages();
		
		//generate a game board for the initial Bonus round GUI creation to use
		bonusController.generateGameBoard();
		
		//generate a results board for the initial GUI to use when GUI is created
		gameModel.generateResultsBoard();
	}
	
	public void startApplication() {
		//Create game GUI and pass a reference to game controller
		gameView = new GameGUI(this);
		
		/*
		 * Create bonus GUI now.
		 * TODO: Use swing worker thread to create bonus GUI
		 * 		 that way if GUI had more bells and whistles to it
		 * 		 the bonus GUI creation wouldn't take up processing time
		 * 		 on the main thread.
		 */
		createBonusGUI();
		
		//GUI created and ready to show to the user
		gameView.allowVisibility();
		GameSounds.startBackgroundMusic();
	}
	
	public BonusGUI createBonusGUI() {
		//Create bonus GUI and passed needed references
		BonusGUI gui = new BonusGUI(gameView.getMainPanel(), bonusController);
		
		//Give bonus controller the reference to the bonus GUI
		bonusController.setBonusGUIView(gui);
		return gui;
	}
	
	public ActionListener getActionListener() {
		return listener;
	}
	
	public SymbolEnum[][] getResultsBoard() {
		return gameModel.getResultsBoard();
	}
	
	public SymbolEnum[][] getBoardForSpin() {
		Reels reels = new Reels();
		return reels.getReelBoardResult();
	}
	
	private int getPayout() {
		int winnings = payout.calculatePayout(gameModel.getResultsBoard());
		gameModel.addCredits(winnings);
		return winnings;
	}
	
	private boolean checkForBonusRoundWin(SymbolEnum[][] board) {
		boolean bonusRoundWin = false;
		int bonusRoundSymbolCount = 0;
		for(int a = 1; a < 4; a++) {
			for(int b = 0; b < 3; b++) {
				if(board[b][a].equals(SymbolEnum.potOfGoldBonusRound)) {
					bonusRoundSymbolCount++;
				}
			}
		}
		
		if(bonusRoundSymbolCount == 3) {
			bonusRoundWin = true;
		}
		
		return bonusRoundWin;
	}
	
	public int getBetPerLine() {
		return gameModel.getBetPerLine();
	}
	
	public void initiateBonusRound() {
		gameView.showBonusRound();
	}
	
	//Get reference to main panel from game GUI to allow use of card layout
	public JPanel getMainPanelFromGameGUI() {
		return gameView.getMainPanel();
	}
	
	public void addBonusRoundWinnings(int winnings) {
		gameModel.addCredits(winnings);
	}
	
	public void updateWinningsAndCreditsLabelAfterBonusRound(int winnings) {
		//calculate total winnings of bonus round plus wins from paylines
		int totalWinnings = winnings + Integer.parseInt(gameView.getWinningsLabelText().substring(5));
		gameView.updateWinningsLabelText("Won: " + totalWinnings);
		
		gameView.updateCreditsLabelText("Credits: " + gameModel.getTotalCredits());
	}
	
	class EventHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if (e.getActionCommand().equals("Spin")){			
				if(gameModel.getTotalCredits() == 0) {
					//When user runs out of credits, give the user more credits to continue.
					JOptionPane.showMessageDialog(null, "You ran out of credits. Here is 100 more to keep playing.");
					gameModel.addCredits(100);
					gameView.updateCreditsLabelText("Credits: " + gameModel.getTotalCredits());
					
				} else if(gameModel.getTotalCredits() < gameModel.getTotalBet()) {
					//Check to make sure user has enough credits to make the bet.
					JOptionPane.showMessageDialog(null, "You do not have enough credits to bet.");
				} else {
					gameModel.generateResultsBoard();
					
					GameSounds.playSpinningReelsSound();
					
					if(checkForBonusRoundWin(gameModel.getResultsBoard())) {
						//Disable spin button to prevent another spin to be activated while a current spin is in progress
						gameView.changeSpinButtonEnabledStatus(false);

						//Subtract bet and update credits label
						gameView.updateCreditsLabelText("Credits: " + gameModel.getTotalCreditsAfterBet());

						//Change won label text to make it clearer of winnings from spin to spin
						gameView.updateWinningsLabelText("Won: 0");

						//Create illusion of spinning reels with Swing Timer
						timer = new Timer(140,  new ActionListener() {
							int counter = 0;
							public void actionPerformed(ActionEvent e) {

								gameView.updateSlotPanelForSpinAnimation();
								counter++;
								if(counter == 10) {
									timer.stop();
									
									gameView.updateSlotPanelForSpinResult();

									gameView.updateWinningsLabelText("Won: " + Integer.toString(getPayout()));
									gameView.updateCreditsLabelText("Credits: " + Integer.toString(gameModel.getTotalCredits()));
									
									//Timer used to allow user time to recognize they hit the bonus round
									Timer bonusRoundTimer = new Timer(1000, new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											initiateBonusRound();
										}
									});
									bonusRoundTimer.setRepeats(false);
									bonusRoundTimer.start();
									GameSounds.pauseSpinningReelsSound();
									gameView.changeSpinButtonEnabledStatus(true);

								}
							}
						});

						timer.start();
						
					} else {
						//Disable spin button to prevent another spin to be activated while a current spin is in progress
						gameView.changeSpinButtonEnabledStatus(false);

						//Subtract bet and update credits label
						gameView.updateCreditsLabelText("Credits: " + gameModel.getTotalCreditsAfterBet());

						//Change won label text to make it clearer of winnings from spin to spin
						gameView.updateWinningsLabelText("Won: 0");

						//Create illusion of spinning reels with Swing Timer
						timer = new Timer(140,  new ActionListener() {
							int counter = 0;
							public void actionPerformed(ActionEvent e) {

								gameView.updateSlotPanelForSpinAnimation();
								counter++;
								if(counter == 10) {
									timer.stop();
									gameView.updateSlotPanelForSpinResult();

									gameView.updateWinningsLabelText("Won: " + Integer.toString(getPayout()));
									gameView.updateCreditsLabelText("Credits: " + Integer.toString(gameModel.getTotalCredits()));
									gameView.changeSpinButtonEnabledStatus(true);
									GameSounds.pauseSpinningReelsSound();

								}
							}
						});

						timer.start();
					}
				}
			}
			else if (e.getActionCommand().equals("BetPerLine")){	
				if(gameModel.getBetPerLine() == 1) {
					gameModel.setBetPerLine(10);
				} else if(gameModel.getBetPerLine() == 100) {
					gameModel.setBetPerLine(1);
				} else {
					gameModel.setBetPerLine(gameModel.getBetPerLine() + 10);
				}
				
				gameModel.updateTotalBet();
				gameView.updateBetPerLineLabelText("Bet Per Line: " + gameModel.getBetPerLine());
				gameView.updateTotalBetLabelText("Total Bet: " + gameModel.getTotalBet());
			}
			else if (e.getActionCommand().equals("LinesPlayed")){
				if(gameModel.getLinesPlayed() == 9) {
					gameModel.setLinesPlayed(1);
				} else {
					gameModel.setLinesPlayed(gameModel.getLinesPlayed() + 1);
				}
				
				gameModel.updateTotalBet();
				payout.updateActiveLinesPlayed(gameModel.getLinesPlayed());
				gameView.updateLinesPlayedLabelText("Lines: " + gameModel.getLinesPlayed());
				gameView.updateTotalBetLabelText("Total Bet: " + gameModel.getTotalBet());
			}
			else if (e.getActionCommand().equals("Exit")){
				System.exit(0);
			}
		}
		
	}
	
}
