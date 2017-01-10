package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.BonusRoundController;
import models.SymbolEnum;

public class BonusGUI {
	private BonusRoundController controller;
	private ActionListener listener;
	private JPanel centerPanel, mainPanel;
	private JLabel[][] displayBoard;
	private JLabel winningsLabel, totalWinningsLabel;
	private SymbolEnum[][] gameBoard;
	private JButton returnToSlotsButton;
	
	/*
	 * @param panel JPanel using card layout to add bonus GUI as a card
	 * @param controller reference to bonus controller
	 */
	
	public BonusGUI(JPanel panel, BonusRoundController controller) {
		this.controller = controller;
		listener = controller.getActionListener() ;
		gameBoard = this.controller.getGameBoard();
		panel.add(createBonusGUI(), "Bonus");
	}
	
	private JPanel createBonusGUI()	{
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.GREEN);
		topPanel.setMaximumSize(new Dimension(500, 50));
		
		JLabel titleLabel = new JLabel("Bonus Round");
		Font titleLabelFont = titleLabel.getFont();
		titleLabel.setFont(new Font(titleLabelFont.getFontName(), Font.PLAIN, 30));
		topPanel.add(titleLabel);
		mainPanel.add(topPanel);
		
		centerPanel = new JPanel();
		centerPanel.setBackground(Color.RED);
		centerPanel.setMaximumSize(new Dimension(500, 330));
		
		centerPanel.add(createBonusGameArea());
		mainPanel.add(centerPanel);
		
		JPanel labelsPanel = new JPanel();
		labelsPanel.setMaximumSize(new Dimension(500, 60));
		labelsPanel.setBackground(Color.YELLOW);
		
		winningsLabel = new JLabel("Won: 0");
		Font winningsLabelFont = winningsLabel.getFont();
		winningsLabel.setFont(new Font(winningsLabelFont.getFontName(), Font.PLAIN, 20));
		
		labelsPanel.add(winningsLabel);
		
		totalWinningsLabel = new JLabel("Total Won: 0");
		Font totalWinningsLabelFont = totalWinningsLabel.getFont();
		totalWinningsLabel.setFont(new Font(totalWinningsLabelFont.getFontName(), Font.PLAIN, 20));
		
		labelsPanel.add(totalWinningsLabel);
		mainPanel.add(labelsPanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.MAGENTA);
		bottomPanel.setMaximumSize(new Dimension(500, 60));
		
		returnToSlotsButton = new JButton("Return to Slots");
		returnToSlotsButton.setActionCommand("Return To Slots");
		returnToSlotsButton.setVisible(false);
		returnToSlotsButton.addActionListener(listener);
		bottomPanel.add(returnToSlotsButton);
		
		mainPanel.add(bottomPanel);
		
		return mainPanel;
	}
	
	private JPanel createBonusGameArea() {
		JPanel bonusGameArea = new JPanel(new GridLayout(3, 3, 10, 10));
		bonusGameArea.setMaximumSize(new Dimension(500, 300));
		
		displayBoard = createDisplayBoard();
		
		//adds all JLabel objects to bonus game panel
		for(int a = 0; a < 3; a++) {
			for(int b = 0; b < 3; b++) {
				bonusGameArea.add(displayBoard[a][b]);
			}
		}
		
		return bonusGameArea;
	}
	
	//creates 2-D Array of JLabel objects to display images
	private JLabel[][] createDisplayBoard() {
		JLabel[][] board = new JLabel[3][3];
		
		for(int a = 0; a < 3; a++) {
			for(int b = 0; b < 3; b++) {
				JLabel label = new JLabel();
				label.setMaximumSize(new Dimension(100, 100));
				String labelName = "Position " + a + " " + b;
				label.setName(labelName);
				label.addMouseListener((MouseAdapter) listener);
				label.setIcon(new ImageIcon(gameBoard[a][b].getImage()));
				
				board[a][b] = label;
			}
		}
		
		return board;
	}
	
	public void setGameBoard(SymbolEnum[][] board) {
		gameBoard = board;
	}
	
	public JLabel[][] getDisplayBoard() {
		return displayBoard;
	}
	
	public void updateWinningsLabelText(String text) {
		winningsLabel.setText(text);
	}
	
	public void updateTotalWinningsLabelText(String text) {
		totalWinningsLabel.setText(text);
	}
	
	public void showReturnToSlotsButton() {
		returnToSlotsButton.setVisible(true);
	}
	
	public void resetBonusRoundGUIComponents() {
		//generate new game board for next bonus round
		controller.generateGameBoard();
		gameBoard = controller.getGameBoard();
		
		//reset only components that need it
		mainPanel.remove(centerPanel);
		centerPanel = new JPanel();
		centerPanel.setBackground(Color.RED);
		centerPanel.setMaximumSize(new Dimension(500, 330));
		
		centerPanel.add(createBonusGameArea());
		mainPanel.add(centerPanel, 1);
		mainPanel.validate();
		mainPanel.repaint();
		
		updateWinningsLabelText("Won: 0");
		updateTotalWinningsLabelText("Total Won: 0");
	}
	
}
