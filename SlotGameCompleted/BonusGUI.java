import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BonusGUI extends MouseAdapter implements ActionListener {
	JPanel panel;
	JLabel positionZeroZero, positionZeroOne, positionZeroTwo, positionOneZero, positionOneOne, positionOneTwo, positionTwoZero, positionTwoOne, positionTwoTwo;
	JLabel[][] displayBoard;
	JLabel winningsLabel, totalWinningsLabel;
	SymbolEnum[][] gameBoard;
	JButton returnToSlotsButton;
	
	public BonusGUI(JPanel panel) {
		gameBoard = BonusRound.getGameBoard();
		this.panel = panel;
		panel.add(createBonusGUI(), "Bonus");
	}
	
	private JPanel createBonusGUI()	{
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.GREEN);
		topPanel.setMaximumSize(new Dimension(500, 50));
		
		JLabel titleLabel = new JLabel("Bonus Round");
		Font titleLabelFont = titleLabel.getFont();
		titleLabel.setFont(new Font(titleLabelFont.getFontName(), Font.PLAIN, 30));
		topPanel.add(titleLabel);
		mainPanel.add(topPanel);
		
		JPanel centerPanel = new JPanel();
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
		returnToSlotsButton.addActionListener(this);
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
				label.addMouseListener(this);
				label.setIcon(new ImageIcon(gameBoard[a][b].getImage()));
				
				board[a][b] = label;
			}
		}
		
		return board;
	}
	
	public static void getGameBoardFromBonusRound() {
		//gameBoard = BonusRound.getGameBoard();
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(BonusRound.getNumberOfSelectedImages() < 3) {
			if(e.getComponent().getName().equals("Position 0 0")) {
				displayBoard[0][0].setIcon(BonusRound.getImageToDisplayAfterSelection());
				BonusRound.addToWinnings(gameBoard[0][0].getValue() * SlotGame.getBetPerLine());
				winningsLabel.setText("Won: " + (gameBoard[0][0].getValue() * SlotGame.getBetPerLine()));
				totalWinningsLabel.setText("Total Won: " + BonusRound.getTotalWinnings());
				displayBoard[0][0].removeMouseListener(this);
				BonusRound.incrementNumberOfSelectedImages();
			
			} else if(e.getComponent().getName().equals("Position 0 1")) {
				displayBoard[0][1].setIcon(BonusRound.getImageToDisplayAfterSelection());
				BonusRound.addToWinnings(gameBoard[0][1].getValue() * SlotGame.getBetPerLine());
				winningsLabel.setText("Won: " + (gameBoard[0][1].getValue() * SlotGame.getBetPerLine()));
				totalWinningsLabel.setText("Total Won: " + BonusRound.getTotalWinnings());
				displayBoard[0][1].removeMouseListener(this);
				BonusRound.incrementNumberOfSelectedImages();
			
			} else if(e.getComponent().getName().equals("Position 0 2")) {
				displayBoard[0][2].setIcon(BonusRound.getImageToDisplayAfterSelection());
				BonusRound.addToWinnings(gameBoard[0][2].getValue() * SlotGame.getBetPerLine());
				winningsLabel.setText("Won: " + (gameBoard[0][2].getValue() * SlotGame.getBetPerLine()));
				totalWinningsLabel.setText("Total Won: " + BonusRound.getTotalWinnings());
				displayBoard[0][2].removeMouseListener(this);
				BonusRound.incrementNumberOfSelectedImages();
			
			} else if(e.getComponent().getName().equals("Position 1 0")) {
				displayBoard[1][0].setIcon(BonusRound.getImageToDisplayAfterSelection());
				BonusRound.addToWinnings(gameBoard[1][0].getValue() * SlotGame.getBetPerLine());
				winningsLabel.setText("Won: " + (gameBoard[1][0].getValue() * SlotGame.getBetPerLine()));
				totalWinningsLabel.setText("Total Won: " + BonusRound.getTotalWinnings());
				displayBoard[1][0].removeMouseListener(this);
				BonusRound.incrementNumberOfSelectedImages();
			
			} else if(e.getComponent().getName().equals("Position 1 1")) {
				displayBoard[1][1].setIcon(BonusRound.getImageToDisplayAfterSelection());
				BonusRound.addToWinnings(gameBoard[1][1].getValue() * SlotGame.getBetPerLine());
				winningsLabel.setText("Won: " + (gameBoard[1][1].getValue() * SlotGame.getBetPerLine()));				
				totalWinningsLabel.setText("Total Won: " + BonusRound.getTotalWinnings());
				displayBoard[1][1].removeMouseListener(this);
				BonusRound.incrementNumberOfSelectedImages();
			
			} else if(e.getComponent().getName().equals("Position 1 2")) {
				displayBoard[1][2].setIcon(BonusRound.getImageToDisplayAfterSelection());
				BonusRound.addToWinnings(gameBoard[1][2].getValue() * SlotGame.getBetPerLine());
				winningsLabel.setText("Won: " + (gameBoard[1][2].getValue() * SlotGame.getBetPerLine()));				
				totalWinningsLabel.setText("Total Won: " + BonusRound.getTotalWinnings());
				displayBoard[1][2].removeMouseListener(this);
				BonusRound.incrementNumberOfSelectedImages();
			
			} else if(e.getComponent().getName().equals("Position 2 0")) {
				displayBoard[2][0].setIcon(BonusRound.getImageToDisplayAfterSelection());
				BonusRound.addToWinnings(gameBoard[2][0].getValue() * SlotGame.getBetPerLine());
				winningsLabel.setText("Won: " + (gameBoard[2][0].getValue() * SlotGame.getBetPerLine()));				
				totalWinningsLabel.setText("Total Won: " + BonusRound.getTotalWinnings());
				displayBoard[2][0].removeMouseListener(this);
				BonusRound.incrementNumberOfSelectedImages();
			
			} else if(e.getComponent().getName().equals("Position 2 1")) {
				displayBoard[2][1].setIcon(BonusRound.getImageToDisplayAfterSelection());
				BonusRound.addToWinnings(gameBoard[2][1].getValue() * SlotGame.getBetPerLine());
				winningsLabel.setText("Won: " + (gameBoard[2][1].getValue() * SlotGame.getBetPerLine()));				
				totalWinningsLabel.setText("Total Won: " + BonusRound.getTotalWinnings());
				displayBoard[2][1].removeMouseListener(this);
				BonusRound.incrementNumberOfSelectedImages();
			
			} else if(e.getComponent().getName().equals("Position 2 2")) {
				displayBoard[2][2].setIcon(BonusRound.getImageToDisplayAfterSelection());
				BonusRound.addToWinnings(gameBoard[2][2].getValue() * SlotGame.getBetPerLine());
				winningsLabel.setText("Won: " + gameBoard[2][2].getValue() * SlotGame.getBetPerLine());				
				totalWinningsLabel.setText("Total Won: " + BonusRound.getTotalWinnings());
				displayBoard[2][2].removeMouseListener(this);
				BonusRound.incrementNumberOfSelectedImages();
			}
		} 
		if(BonusRound.getNumberOfSelectedImages() == 3) {
			returnToSlotsButton.setVisible(true);
		}
	}

	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout)(panel.getLayout());
		
		if(e.getActionCommand().equals("Return To Slots")) {
			SlotGame.addBonusRoundWinnings(Integer.parseInt(totalWinningsLabel.getText().substring(11)));
			GameGUI.updateWinningsLabelAfterBonusRound(Integer.parseInt(totalWinningsLabel.getText().substring(11)));
			
			cl.show(panel, "Main");
			BonusRound.resetBonusRoundData();
			SlotGame.createNewBonusGUI();
			
		}
		
	}
}
