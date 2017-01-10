import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameGUI implements ActionListener {
	private JPanel centerPanel, slotHolderPanel;
	private JPanel slotPanel = new JPanel(new GridLayout(3, 5));
	private JLabel linesPlayedLabel, betPerLineLabel, totalBetLabel;
	private static JLabel creditsLabel, winningsLabel;
	private JButton spinButton;
	private Timer timer;
	private SymbolEnum[][] resultsBoard = SlotGame.getResultsBoard();
	
	
	public GameGUI(JPanel panel) {
		JPanel mainPanel = panel;
		mainPanel.add(createGameGui(), "Main");
	}
	
	private JPanel createGameGui() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.GREEN);
		topPanel.setMaximumSize(new Dimension(500, 50));
		
		JLabel titleLabel = new JLabel("Slot Machine");
		Font titleLabelFont = titleLabel.getFont();
		titleLabel.setFont(new Font(titleLabelFont.getFontName(), Font.PLAIN, 30));
		topPanel.add(titleLabel);	
		mainPanel.add(topPanel);
		
		centerPanel = new JPanel();
		//centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
		centerPanel.setBackground(Color.RED);
		centerPanel.setMaximumSize(new Dimension(500, 300));
		
		centerPanel.add(Box.createRigidArea(new Dimension(500, 60)));
		
		JPanel test = new JPanel();
		test.setMaximumSize(new Dimension(150, 150));
		test.setPreferredSize(new Dimension(150, 150));
		JLabel lepTest = new JLabel();
		
		try {
			BufferedImage lep = ImageIO.read(new File("./leprechaun_drinking_beer.jpg"));
			ImageIcon image = new ImageIcon(lep);
			lepTest.setIcon(image);
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		
		//lepTest.setMaximumSize(new Dimension(100, 200));
		//lepTest.setPreferredSize(new Dimension(100, 200));
		test.add(lepTest);
		centerPanel.add(test);
		
		slotHolderPanel = new JPanel();
		slotHolderPanel.setMaximumSize(new Dimension(220, 130));
		slotHolderPanel.setBackground(Color.BLACK);
		slotPanel = GameGUI.getResultsSlotPanel(resultsBoard);
		slotHolderPanel.add(slotPanel);
		centerPanel.add(slotHolderPanel);
		
		JPanel test2 = new JPanel();
		test2.setMaximumSize(new Dimension(100, 150));
		test2.setPreferredSize(new Dimension(100, 150));
		JLabel lepTest2	= new JLabel();
		
		try {
			BufferedImage lepBeer = ImageIO.read(new File("./leprechaun_test.png"));
			ImageIcon image = new ImageIcon(lepBeer);
			lepTest2.setIcon(image);
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		
		test2.add(lepTest2);
		centerPanel.add(test2);
		mainPanel.add(centerPanel);
		
		JPanel chipInformationPanel = new JPanel();
		chipInformationPanel.setLayout(new BoxLayout(chipInformationPanel, BoxLayout.LINE_AXIS));
		chipInformationPanel.setMaximumSize(new Dimension(500, 50));
		chipInformationPanel.setBackground(Color.MAGENTA);
		
		chipInformationPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		
		linesPlayedLabel = new JLabel("Lines: 1");
		Font linesPlayedLabelFont = linesPlayedLabel.getFont();
		linesPlayedLabel.setFont(new Font(linesPlayedLabelFont.getFontName(), Font.PLAIN, 20));
		chipInformationPanel.add(linesPlayedLabel);
		
		chipInformationPanel.add(Box.createRigidArea(new Dimension(35, 0)));
		
		betPerLineLabel = new JLabel("Bet Per Line: 1");
		Font betPerLineLabelFont = betPerLineLabel.getFont();
		betPerLineLabel.setFont(new Font(betPerLineLabelFont.getFontName(), Font.PLAIN, 20));
		chipInformationPanel.add(betPerLineLabel);
		
		chipInformationPanel.add(Box.createRigidArea(new Dimension(35, 0)));
		
		totalBetLabel = new JLabel("Total Bet: 1");
		Font totalBetLabelFont = totalBetLabel.getFont();
		totalBetLabel.setFont(new Font(totalBetLabelFont.getFontName(), Font.PLAIN, 20));
		chipInformationPanel.add(totalBetLabel);
		mainPanel.add(chipInformationPanel);
		
		JPanel creditsInformationPanel = new JPanel();
		creditsInformationPanel.setLayout(new BoxLayout(creditsInformationPanel, BoxLayout.LINE_AXIS));
		creditsInformationPanel.setMaximumSize(new Dimension(500, 50));
		creditsInformationPanel.setBackground(Color.ORANGE);
		
		creditsInformationPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		
		creditsLabel = new JLabel("Credits: 100");
		Font creditsLabelFont = creditsLabel.getFont();
		creditsLabel.setFont(new Font(creditsLabelFont.getFontName(), Font.PLAIN, 20));
		creditsInformationPanel.add(creditsLabel);
		
		creditsInformationPanel.add(Box.createRigidArea(new Dimension(35, 0)));
		
		winningsLabel = new JLabel("Won: 0");
		Font winningsLabelFont = winningsLabel.getFont();
		winningsLabel.setFont(new Font(winningsLabelFont.getFontName(), Font.PLAIN, 20));
		creditsInformationPanel.add(winningsLabel);
		mainPanel.add(creditsInformationPanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setMaximumSize(new Dimension(500, 50));
		bottomPanel.setBackground(Color.PINK);
		
		spinButton = new JButton("Spin");
		Font spinButtonFont = spinButton.getFont();
		spinButton.setFont(new Font(spinButtonFont.getFontName(), Font.PLAIN, 16));
		spinButton.setToolTipText("Spin the Reels");
		spinButton.setPreferredSize(new Dimension(80, 30));
		spinButton.setActionCommand("Spin");
		spinButton.addActionListener(this);
		bottomPanel.add(spinButton);
		
		JButton linesPlayedButton = new JButton("Lines to Play");
		Font linesPlayedButtonFont = linesPlayedButton.getFont();
		linesPlayedButton.setFont(new Font(linesPlayedButtonFont.getFontName(), Font.PLAIN, 16));
		linesPlayedButton.setToolTipText("Number of Lines Played");
		linesPlayedButton.setPreferredSize(new Dimension(135, 30));
		linesPlayedButton.setActionCommand("LinesPlayed");
		linesPlayedButton.addActionListener(this);
		bottomPanel.add(linesPlayedButton);
		
		JButton betPerLineButton = new JButton("Bet Per Line");
		Font betPerLineButtonFont = betPerLineButton.getFont();
		betPerLineButton.setFont(new Font(betPerLineButtonFont.getFontName(), Font.PLAIN, 16));
		betPerLineButton.setToolTipText("Number of Chips Bet Per Line");
		betPerLineButton.setPreferredSize(new Dimension(130, 30));
		betPerLineButton.setActionCommand("BetPerLine");
		betPerLineButton.addActionListener(this);
		bottomPanel.add(betPerLineButton);
		
		JButton exitButton = new JButton("Exit");
		Font exitButtonFont = exitButton.getFont();
		exitButton.setFont(new Font(exitButtonFont.getFontName(), Font.PLAIN, 16));
		exitButton.setToolTipText("Exit the Game");
		exitButton.setPreferredSize(new Dimension(70, 30));
		exitButton.setActionCommand("Exit");
		exitButton.addActionListener(this);
		bottomPanel.add(exitButton);
		mainPanel.add(bottomPanel);
		
		return mainPanel;
	}
	
	private static JPanel getResultsSlotPanel(SymbolEnum[][] board){
		ArrayList<JLabel> testList = new ArrayList<JLabel>();
		JPanel resultsPanel = new JPanel(new GridLayout(3, 5));
		resultsPanel.setBackground(Color.WHITE);
		resultsPanel.setMaximumSize(new Dimension(220, 130));
		resultsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN, 3), resultsPanel.getBorder()));
		
		//GridLayout adds components left to right so must add icons from reelBoard row by row to jList
		for (int c = 0; c < 3; c++) {
			for (int d = 0; d < 5; d++) {
			ImageIcon image = new ImageIcon(board[c][d].getImage());
			testList.add(new JLabel(image));
			}
		}
		
		for (int f = 0; f < testList.size(); f++) {
			resultsPanel.add(testList.get(f));
		}
		
		return resultsPanel;
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
	
	public static void updateWinningsLabelAfterBonusRound(int winnings) {
		int totalWinnings = winnings + Integer.parseInt(winningsLabel.getText().substring(5));
		winningsLabel.setText("Won: " + totalWinnings);
		
		creditsLabel.setText("Credits: " + SlotGame.getTotalCredits());
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getActionCommand().equals("Spin")){			
			if(SlotGame.getTotalCredits() == 0) {
				//When user runs out of credits, give the user more credits to continue.
				JOptionPane.showMessageDialog(null, "You ran out of credits. Here is 100 more to keep playing.");
				SlotGame.addCredits(100);
				creditsLabel.setText("Credits: " + SlotGame.getTotalCredits());
				
			} else if(SlotGame.getTotalCredits() < SlotGame.getTotalBet()) {
				//Check to make sure user has enough credits to make the bet.
				JOptionPane.showMessageDialog(null, "You do not have enough credits to bet.");
			} else {
				resultsBoard = SlotGame.getResultsBoard();
				
				if(checkForBonusRoundWin(resultsBoard)) {					
					GameSounds.playSpinningReelsSound();
					
					spinButton.setEnabled(false);

					//Subtract bet and update credits label.
					creditsLabel.setText("Credits: " + SlotGame.getTotalCreditsAfterBet());

					//Change won label text to make it clearer of winnings from spin to spin.
					winningsLabel.setText("Won: 0");

					//Create illusion of spinning reels with Swing Timer.
					timer = new Timer(140,  new ActionListener() {
						int counter = 0;
						public void actionPerformed(ActionEvent e) {

							slotHolderPanel.remove(slotPanel);
							slotPanel = GameGUI.getResultsSlotPanel(SlotGame.getBoardForSpin());
							slotHolderPanel.add(slotPanel);
							slotHolderPanel.validate();
							slotHolderPanel.repaint();
							counter++;
							if(counter == 10) {
								timer.stop();
								slotHolderPanel.remove(slotPanel);
								slotPanel = GameGUI.getResultsSlotPanel(resultsBoard);

								slotHolderPanel.add(slotPanel);			
								slotHolderPanel.validate();
								slotHolderPanel.repaint();

								winningsLabel.setText("Won: " + Integer.toString(SlotGame.getPayout()));
								creditsLabel.setText("Credits: " + Integer.toString(SlotGame.getTotalCredits()));
								
								Timer bonusRoundTimer = new Timer(1000, new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										SlotGame.showBonusRound();
									}
								});
								bonusRoundTimer.setRepeats(false);
								bonusRoundTimer.start();
								GameSounds.pauseSpinningReelsSound();
								spinButton.setEnabled(true);

							}
						}
					});

					timer.start();
					
					
				} else {
					GameSounds.playSpinningReelsSound();

					//Disable spin button to prevent another spin to be activated while a current spin is in progress.
					spinButton.setEnabled(false);

					//Subtract bet and update credits label.
					creditsLabel.setText("Credits: " + SlotGame.getTotalCreditsAfterBet());

					//Change won label text to make it clearer of winnings from spin to spin.
					winningsLabel.setText("Won: 0");

					//Create illusion of spinning reels with Swing Timer.
					timer = new Timer(140,  new ActionListener() {
						int counter = 0;
						public void actionPerformed(ActionEvent e) {

							slotHolderPanel.remove(slotPanel);
							slotPanel = GameGUI.getResultsSlotPanel(SlotGame.getBoardForSpin());
							slotHolderPanel.add(slotPanel);
							slotHolderPanel.validate();
							slotHolderPanel.repaint();
							counter++;
							if(counter == 10) {
								timer.stop();
								slotHolderPanel.remove(slotPanel);
								slotPanel = GameGUI.getResultsSlotPanel(resultsBoard);

								slotHolderPanel.add(slotPanel);			
								slotHolderPanel.validate();
								slotHolderPanel.repaint();

								winningsLabel.setText("Won: " + Integer.toString(SlotGame.getPayout()));
								creditsLabel.setText("Credits: " + Integer.toString(SlotGame.getTotalCredits()));
								spinButton.setEnabled(true);
								GameSounds.pauseSpinningReelsSound();

							}
						}
					});

					timer.start();
				}
			}
			
			
			
		}
		else if (e.getActionCommand().equals("BetPerLine")){	
			if(SlotGame.getBetPerLine() == 1) {
				SlotGame.setBetPerLine(10);
			} else if(SlotGame.getBetPerLine() == 100) {
				SlotGame.setBetPerLine(1);
			} else {
				SlotGame.setBetPerLine(SlotGame.getBetPerLine() + 10);
			}
			
			SlotGame.updateTotalBet();
			betPerLineLabel.setText("Bet Per Line: " + SlotGame.getBetPerLine());
			totalBetLabel.setText("Total Bet: " + SlotGame.getTotalBet());
		}
		else if (e.getActionCommand().equals("LinesPlayed")){
			if(SlotGame.getLinesPlayed() == 9) {
				SlotGame.setLinesPlayed(1);
			} else {
				SlotGame.setLinesPlayed(SlotGame.getLinesPlayed() + 1);
			}
			
			SlotGame.updateTotalBet();
			SlotGame.changeStatusOfPaylines();
			linesPlayedLabel.setText("Lines: " + SlotGame.getLinesPlayed());
			totalBetLabel.setText("Total Bet: " + SlotGame.getTotalBet());
		}
		else if (e.getActionCommand().equals("Exit")){
			System.exit(0);
		}
	}
	
}
