package viewsVersion2;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllersVersion2.GameController;
import modelsVersion2.SymbolEnum;

public class GameGUI {
	private GameController controller;
	private JFrame frame;
	private ActionListener listener;
	private JLabel creditsLabel, winningsLabel, betPerLineLabel, linesPlayedLabel, totalBetLabel;
	private JButton spinButton;
	private JPanel slotHolderPanel, mainPanel;
	private JPanel slotPanel = new JPanel(new GridLayout(3, 5));
	private JPanel[] slotPanelCells = new JPanel[15];
	
	
	public GameGUI(GameController controller) {
		this.controller = controller;
		listener = controller.getActionListener();
		initializeUI();
	}
	
	public void allowVisibility() {
		frame.setVisible(true);
	}
	
	private void initializeUI() {
		frame = new JFrame("Slot Machine");
		frame.setSize(new Dimension(500, 500));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel(new CardLayout());
		
		JPanel slotGamePanel = new JPanel();
		slotGamePanel.setLayout(new BoxLayout(slotGamePanel, BoxLayout.Y_AXIS));
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.GREEN);
		topPanel.setMaximumSize(new Dimension(500, 50));
		
		JLabel titleLabel = new JLabel("Slot Machine");
		Font titleLabelFont = titleLabel.getFont();
		titleLabel.setFont(new Font(titleLabelFont.getFontName(), Font.PLAIN, 30));
		topPanel.add(titleLabel);	
		slotGamePanel.add(topPanel);
		
		JPanel centerPanel = new JPanel();
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
		
		test.add(lepTest);
		centerPanel.add(test);
		
		slotHolderPanel = new JPanel();
		slotHolderPanel.setMaximumSize(new Dimension(220, 145));
		slotHolderPanel.setBackground(Color.BLACK);
		//slotPanel = getResultsSlotPanel(controller.getResultsBoard());
		slotPanel = new JPanel(new GridLayout(3, 5));
		slotPanel.setMaximumSize(new Dimension(210, 145));
		slotPanel.setPreferredSize(new Dimension(210, 145));
		slotPanel.setBackground(Color.WHITE);
		slotPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN, 3), slotPanel.getBorder()));
		
		int counter = 0;
		for(int a = 0; a < 3; a++) {
			for(int b = 0; b < 5; b++) {
				JPanel cell = new JPanel();
				cell.setMaximumSize(new Dimension(40, 40));
				cell.add(new JLabel(controller.getResultsBoard()[a][b].getImage()));
				slotPanelCells[counter] = cell;
				slotPanel.add(cell);
				counter++;				
			}
		}
		
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
		slotGamePanel.add(centerPanel);
		
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
		slotGamePanel.add(chipInformationPanel);
		
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
		slotGamePanel.add(creditsInformationPanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setMaximumSize(new Dimension(500, 50));
		bottomPanel.setBackground(Color.PINK);
		
		spinButton = new JButton("Spin");
		Font spinButtonFont = spinButton.getFont();
		spinButton.setFont(new Font(spinButtonFont.getFontName(), Font.PLAIN, 16));
		spinButton.setToolTipText("Spin the Reels");
		spinButton.setPreferredSize(new Dimension(80, 30));
		spinButton.setActionCommand("Spin");
		spinButton.addActionListener(listener);
		bottomPanel.add(spinButton);
		
		JButton linesPlayedButton = new JButton("Lines to Play");
		Font linesPlayedButtonFont = linesPlayedButton.getFont();
		linesPlayedButton.setFont(new Font(linesPlayedButtonFont.getFontName(), Font.PLAIN, 16));
		linesPlayedButton.setToolTipText("Number of Lines Played");
		linesPlayedButton.setPreferredSize(new Dimension(135, 30));
		linesPlayedButton.setActionCommand("LinesPlayed");
		linesPlayedButton.addActionListener(listener);
		bottomPanel.add(linesPlayedButton);
		
		JButton betPerLineButton = new JButton("Bet Per Line");
		Font betPerLineButtonFont = betPerLineButton.getFont();
		betPerLineButton.setFont(new Font(betPerLineButtonFont.getFontName(), Font.PLAIN, 16));
		betPerLineButton.setToolTipText("Number of Chips Bet Per Line");
		betPerLineButton.setPreferredSize(new Dimension(130, 30));
		betPerLineButton.setActionCommand("BetPerLine");
		betPerLineButton.addActionListener(listener);
		bottomPanel.add(betPerLineButton);
		
		JButton exitButton = new JButton("Exit");
		Font exitButtonFont = exitButton.getFont();
		exitButton.setFont(new Font(exitButtonFont.getFontName(), Font.PLAIN, 16));
		exitButton.setToolTipText("Exit the Game");
		exitButton.setPreferredSize(new Dimension(70, 30));
		exitButton.setActionCommand("Exit");
		exitButton.addActionListener(listener);
		bottomPanel.add(exitButton);
		slotGamePanel.add(bottomPanel);
		
		mainPanel.add(slotGamePanel, "Main");
		
		frame.add(mainPanel);
		System.out.println(slotPanel.getSize());
	}
	
	public JPanel getResultsSlotPanel(SymbolEnum[][] board) {
		ArrayList<JLabel> testList = new ArrayList<JLabel>();
		JPanel resultsPanel = new JPanel(new GridLayout(3, 5));
		resultsPanel.setBackground(Color.WHITE);
		resultsPanel.setMaximumSize(new Dimension(220, 130));
		resultsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.CYAN, 3), resultsPanel.getBorder()));
		
		//GridLayout adds components left to right so must add icons from reelBoard row by row to jList
		for (int c = 0; c < 3; c++) {
			for (int d = 0; d < 5; d++) {
			//ImageIcon image = new ImageIcon(board[c][d].getImage());
			testList.add(new JLabel(board[c][d].getImage()));
			}
		}
		
		for (int f = 0; f < testList.size(); f++) {
			resultsPanel.add(testList.get(f));
		}
		
		return resultsPanel;
	}
	
	public void updateCreditsLabelText(String text) {
		creditsLabel.setText(text);
	}
	
	public void changeSpinButtonEnabledStatus(boolean status) {
		spinButton.setEnabled(status);
	}
	
	public void updateWinningsLabelText(String text) {
		winningsLabel.setText(text);
	}
	
	public void spinReelOne(int indexCounter) {
		slotPanelCells[10].add(new JLabel(controller.getReel(1).getNextElement(indexCounter).getImage()), 0);
		slotPanelCells[5].add(new JLabel(controller.getReel(1).get(indexCounter).getImage()), 0);
		slotPanelCells[0].add(new JLabel(controller.getReel(1).getPreviousElement(indexCounter).getImage()), 0);
		slotPanel.validate();
		slotPanel.repaint();
	}
	
	public void spinReelTwo(int indexCounter) {
		slotPanelCells[11].add(new JLabel(controller.getReel(2).getNextElement(indexCounter).getImage()), 0);
		slotPanelCells[6].add(new JLabel(controller.getReel(2).get(indexCounter).getImage()), 0);
		slotPanelCells[1].add(new JLabel(controller.getReel(2).getPreviousElement(indexCounter).getImage()), 0);
		slotPanel.validate();
		slotPanel.repaint();
	}
	
	public void spinReelThree(int indexCounter) {
		slotPanelCells[12].add(new JLabel(controller.getReel(3).getNextElement(indexCounter).getImage()), 0);
		slotPanelCells[7].add(new JLabel(controller.getReel(3).get(indexCounter).getImage()), 0);
		slotPanelCells[2].add(new JLabel(controller.getReel(3).getPreviousElement(indexCounter).getImage()), 0);
		slotPanel.validate();
		slotPanel.repaint();
	}
	
	public void spinReelFour(int indexCounter) {
		slotPanelCells[13].add(new JLabel(controller.getReel(4).getNextElement(indexCounter).getImage()), 0);
		slotPanelCells[8].add(new JLabel(controller.getReel(4).get(indexCounter).getImage()), 0);
		slotPanelCells[3].add(new JLabel(controller.getReel(4).getPreviousElement(indexCounter).getImage()), 0);
		slotPanel.validate();
		slotPanel.repaint();
	}
	
	public void spinReelFive(int indexCounter) {
		slotPanelCells[14].add(new JLabel(controller.getReel(5).getNextElement(indexCounter).getImage()), 0);
		slotPanelCells[9].add(new JLabel(controller.getReel(5).get(indexCounter).getImage()), 0);
		slotPanelCells[4].add(new JLabel(controller.getReel(5).getPreviousElement(indexCounter).getImage()), 0);
		slotPanel.validate();
		slotPanel.repaint();
	}
	
	public void displayResultSymbols(int reel) {
		slotPanelCells[reel + 9].add(new JLabel(controller.getResultsBoard()[2][reel - 1].getImage()), 0);
		slotPanelCells[reel + 4].add(new JLabel(controller.getResultsBoard()[1][reel - 1].getImage()), 0);
		slotPanelCells[reel - 1].add(new JLabel(controller.getResultsBoard()[0][reel - 1].getImage()), 0);
		slotPanel.validate();
		slotPanel.repaint();
	}
	
	public void updateBetPerLineLabelText(String text) {
		betPerLineLabel.setText(text);
	}
	
	public void updateTotalBetLabelText(String text) {
		totalBetLabel.setText(text);
	}
	
	public void updateLinesPlayedLabelText(String text) {
		linesPlayedLabel.setText(text);
	}
	
	public void showBonusRound() {
		CardLayout cl = (CardLayout)(mainPanel.getLayout());
		cl.show(mainPanel, "Bonus");
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
	
	public String getWinningsLabelText() {
		return winningsLabel.getText();
	}
}
