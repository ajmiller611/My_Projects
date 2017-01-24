package controllersVersion2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import modelsVersion2.CircularLinkedList;
import modelsVersion2.GameSounds;
import modelsVersion2.SlotGameModel;
import modelsVersion2.SymbolEnum;
import viewsVersion2.GameGUI;
import viewsVersion2.BonusGUI;

public class GameController {
	private static GameController controllerInstance;
	private SlotGameModel gameModel;
	private GameGUI gameView;
	private BonusRoundController bonusController;
	private ActionListener listener;
	private PayoutController payout;
	
	public GameController() {
		//Create game model
		gameModel = new SlotGameModel();
		
		//Set the bonus round controller
		//bonusController = BonusRoundController.getBonusRoundControllerInstance();
		
		//Create event handler for GUI
		listener = new EventHandler();
		
		//Set the payout controller
		//payout = PayoutController.getPayoutControllerInstance();
	}
	
	//Use singleton design pattern to allow only one instance of game controller.
	public static GameController getGameControllerInstance() {
		if(controllerInstance == null) {
			controllerInstance = new GameController();
		}
		return controllerInstance;
	}
	
	public void setBonusRoundController(BonusRoundController controller) {
		bonusController = controller;
	}
	
	public void setPayoutController(PayoutController controller) {
		payout = controller;
	}
	
	public void loadResources() {
		gameModel.createReels();
		
		payout.setupPaylines();
		
		bonusController.setupImages();
		
		//Generate a game board for the initial Bonus round GUI creation to use
		bonusController.generateGameBoard();
		
		//Generate a results board for the initial GUI to use when GUI is created
		gameModel.generateResultsBoard();
	}
	
	public void startApplication() {
		//Create game GUI
		gameView = new GameGUI();
		
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
		//Create bonus GUI and passed needed values
		BonusGUI gui = new BonusGUI(gameView.getMainPanel());
		
		//Give bonus controller the value of the bonus GUI
		bonusController.setBonusGUIView(gui);
		return gui;
	}
	
	public ActionListener getActionListener() {
		return listener;
	}
	
	public CircularLinkedList getReel(int index) {
		return gameModel.getReel(index);
	}
	
	public SymbolEnum[][] getResultsBoard() {
		return gameModel.getResultsBoard();
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
	
	//Get the main panel from game GUI to allow use of card layout
	public JPanel getMainPanelFromGameGUI() {
		return gameView.getMainPanel();
	}
	
	public void addBonusRoundWinnings(int winnings) {
		gameModel.addCredits(winnings);
	}
	
	public void updateWinningsAndCreditsLabelAfterBonusRound(int winnings) {
		//Calculate total winnings of bonus round plus wins from paylines
		int totalWinnings = winnings + Integer.parseInt(gameView.getWinningsLabelText().substring(5));
		gameView.updateWinningsLabelText("Won: " + totalWinnings);
		
		gameView.updateCreditsLabelText("Credits: " + gameModel.getTotalCredits());
	}
	
	class EventHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			if (e.getActionCommand().equals("Spin")) {			
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
					//Disable spin button to prevent another spin to be activated while a current spin is in progress
					gameView.changeSpinButtonEnabledStatus(false);

					//Subtract bet and update credits label
					gameView.updateCreditsLabelText("Credits: " + gameModel.getTotalCreditsAfterBet());

					//Change won label text to make it clearer of winnings from spin to spin
					gameView.updateWinningsLabelText("Won: 0");

					//Create illusion of spinning reels using Swing Timer
					final Timer reelOneTimer = new Timer(100,  new ActionListener() {
						//Counter variable used to stop the timer
						int counter = 0;
						//Pick random position on the reel to start at
						int reelOneIndexCounter = (int) (Math.random() * gameModel.getReel(1).size());
						public void actionPerformed(ActionEvent e) {
							//Tell view to spin reel one
							gameView.spinReelOne(reelOneIndexCounter);
							//Check counter and reset index back to 0 if needed
							reelOneIndexCounter = checkCounter(reelOneIndexCounter);
							counter++;
							if(counter == 10) {
								//Stop timer which stops the spin
								((Timer) e.getSource()).stop();
								//Display the symbols that are the result of the spin
								gameView.displayResultSymbols(1);
							}
						}
					});
						
					final Timer reelTwoTimer = new Timer(100, new ActionListener() {
						int counter = 0;
						int reelTwoIndexCounter = (int) (Math.random() * gameModel.getReel(2).size());
						public void actionPerformed(ActionEvent e) {
							gameView.spinReelTwo(reelTwoIndexCounter);
							reelTwoIndexCounter = checkCounter(reelTwoIndexCounter);
							counter++;
							if(counter == 15) {
								((Timer) e.getSource()).stop();
								gameView.displayResultSymbols(2);
							}
						}
					});
						
					final Timer reelThreeTimer = new Timer(100, new ActionListener() {
						int counter = 0;
						int reelThreeIndexCounter = (int) (Math.random() * gameModel.getReel(3).size());
						public void actionPerformed(ActionEvent e) {
							gameView.spinReelThree(reelThreeIndexCounter);
							reelThreeIndexCounter = checkCounter(reelThreeIndexCounter);
							counter++;
							if(counter == 20) {
								((Timer) e.getSource()).stop();
								gameView.displayResultSymbols(3);
							}
						}
					});
						
					final Timer reelFourTimer = new Timer(100, new ActionListener() {
						int counter = 0;
						int reelFourIndexCounter = (int) (Math.random() * gameModel.getReel(4).size());
						public void actionPerformed(ActionEvent e) {
							gameView.spinReelFour(reelFourIndexCounter);
							reelFourIndexCounter = checkCounter(reelFourIndexCounter);
							counter++;
							if(counter == 25) {
								((Timer) e.getSource()).stop();
								gameView.displayResultSymbols(4);
							}
						}
					});
						
					final Timer reelFiveTimer = new Timer(100, new ActionListener() {
						int counter = 0;
						int reelFiveIndexCounter = (int) (Math.random() * gameModel.getReel(5).size());
						public void actionPerformed(ActionEvent e) {
							gameView.spinReelFive(reelFiveIndexCounter);
							reelFiveIndexCounter = checkCounter(reelFiveIndexCounter);
							counter++;
							if(counter == 30) {
								((Timer) e.getSource()).stop();
								gameView.displayResultSymbols(5);
								
								/*
								 * Check for bonus round win. If true then update labels and enable spin button
								 * after bonus GUI is shown. This makes sure another spin can't be started while
								 * the program is in the process of showing the bonus round GUI.
								 */
								if(checkForBonusRoundWin(gameModel.getResultsBoard())) {
									Timer bonusRoundTimer = new Timer(1000, new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											initiateBonusRound();
											//Tell view to update labels with winnings from spin
											gameView.updateWinningsLabelText("Won: " + Integer.toString(getPayout()));
											gameView.updateCreditsLabelText("Credits: " + Integer.toString(gameModel.getTotalCredits()));
											//Enable spin button now that spin is over
											gameView.changeSpinButtonEnabledStatus(true);
										}
									});
									bonusRoundTimer.setRepeats(false);
									bonusRoundTimer.start();
								} else {
									//Tell view to update labels with winnings from spin
									gameView.updateWinningsLabelText("Won: " + Integer.toString(getPayout()));
									gameView.updateCreditsLabelText("Credits: " + Integer.toString(gameModel.getTotalCredits()));
									//Enable spin button now that spin is over
									gameView.changeSpinButtonEnabledStatus(true);
								}
								//Stop the spinning sound
								GameSounds.pauseSpinningReelsSound();
							}
						}
					});

					reelOneTimer.start();
					reelTwoTimer.start();
					reelThreeTimer.start();
					reelFourTimer.start();
					reelFiveTimer.start();
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
		
		//Method to check the counter for end of reel index
		private int checkCounter(int counter) {
			int newCount = counter;
			//Reset counter back to 0 if at ending index of 6
			if(counter == 6) {
				newCount = 0;
			} else {
				newCount++;
			}
			
			return newCount;
		}
	}
}
