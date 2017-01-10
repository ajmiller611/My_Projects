import javax.imageio.ImageIO;
import javax.swing.*;
//import javax.swing.SpringLayout;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.lang.Math;

public class SlotGame extends JFrame implements Serializable {
	
	private JButton spin;
	private JButton betPerLine;
	private JButton linesPlayed;
	private JButton exit;
	private int stopPosition;
	private BufferedImage[][] reelBoard = new BufferedImage[3][5];
	private BufferedImage[] reelArray = new BufferedImage[3];
	private int totalChips;
	private int bet;
	private int lines;
	private int winnings;
	private ArrayList<JLabel> jList = new ArrayList<JLabel>();
	private JPanel mainPanel;
	private JPanel slotPanel = new JPanel(new GridLayout(3, 5));
	private SpringLayout layout;
	Container contain;
	
	public SlotGame() {
		//Setup the GUI
		super("Slot Machine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ButtonHandler handler = new ButtonHandler();
		mainPanel = new JPanel();
		layout = new SpringLayout();
		mainPanel.setLayout(layout);
		
		spin = new JButton("Spin");
		spin.setToolTipText("Spin the Reels");
		spin.addActionListener(handler);
		mainPanel.add(spin);
		layout.putConstraint(SpringLayout.WEST, spin, 5, SpringLayout.WEST, mainPanel);
		layout.putConstraint(SpringLayout.NORTH, spin, 5, SpringLayout.NORTH, mainPanel);
		
		betPerLine = new JButton("Bet Per Line");
		betPerLine.setToolTipText("Number of Chips Bet Per Line");
		betPerLine.addActionListener(handler);
		mainPanel.add(betPerLine);
		layout.putConstraint(SpringLayout.WEST, betPerLine, 5, SpringLayout.EAST, spin);
		layout.putConstraint(SpringLayout.NORTH, betPerLine, 5, SpringLayout.NORTH, mainPanel);
		
		linesPlayed = new JButton("Lines to Play");
		linesPlayed.setToolTipText("Number of Lines Played");
		linesPlayed.addActionListener(handler);
		mainPanel.add(linesPlayed);
		layout.putConstraint(SpringLayout.WEST, linesPlayed, 5, SpringLayout.EAST, betPerLine);
		layout.putConstraint(SpringLayout.NORTH, linesPlayed, 5, SpringLayout.NORTH, mainPanel);
		
		exit = new JButton("Exit");
		exit.setToolTipText("Exit the Game");
		exit.addActionListener(handler);
		mainPanel.add(exit);
		layout.putConstraint(SpringLayout.WEST, exit, 5, SpringLayout.EAST, linesPlayed);
		layout.putConstraint(SpringLayout.NORTH, exit, 5, SpringLayout.NORTH, mainPanel);
		
		Reels.loadReels();
		
		getContentPane().add(mainPanel);
		setSize(600,600);
		setVisible(true);
		
	}
	
public class ButtonHandler implements ActionListener {
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == spin){
			//slotPanel.setSize(100, 100);
			slotPanel.setBackground(Color.red);
			
			//slotPanel.setVisible(false);
			
			if (jList.isEmpty() != true) {
				mainPanel.remove(slotPanel);
				
				for (int f = 0; f < jList.size(); f++) {
					//slotPanel.remove(jList.get(f));
					//jList.set(f, null);
				}
			
				mainPanel.validate();
				validate();
				repaint();
			}
			
			//Randomly select a stop position and get the icons from each reel
			for (int a = 0; a < 5; a++) {
				stopPosition = (int) (Math.random() * 6);
				//System.out.println(stopPosition);
				reelArray = Reels.getResults(stopPosition, a + 1);
				//System.arraycopy(Reels.getResults(stopPosition, a + 1), 0, reelBoard[a], 0, 3);
				
				//Place the results from each reel into the reel board array to display the results like a slot machine
				for (int b = 0; b < 3; b++) {					
					reelBoard[b][a] = reelArray[b];
				}
			}
				
				for (int c = 0; c < 5; c++) {
					for (int d = 0; d < 3; d++) {
					ImageIcon image = new ImageIcon(reelBoard[d][c]);
					jList.add(new JLabel(image));
					}
				}
				
				for (int f = 0; f < jList.size(); f++) {
					slotPanel.add(jList.get(f));
				}
				
				mainPanel.add(slotPanel);
				
				layout.putConstraint(SpringLayout.WEST, slotPanel, 150, SpringLayout.WEST, mainPanel);
				layout.putConstraint(SpringLayout.NORTH, slotPanel, 150, SpringLayout.NORTH, mainPanel);
				mainPanel.validate();
				mainPanel.repaint();
				validate();
				repaint();
			
		}
		else if (e.getSource() == betPerLine){
			String sbet = JOptionPane.showInputDialog("Enter the bet per line amount.");
			int checkBet = Integer.parseInt(sbet);
			if (checkBet % 10 != 0 || checkBet > 100){
				JOptionPane.showMessageDialog(null, "Please enter a bet in increments of 10. Max bet is 100.");
			} else{
				bet = checkBet;
			}			
		}
		else if (e.getSource() == linesPlayed){
			String slines = JOptionPane.showInputDialog("Enter the number of lines to play. Max is 9.");
			int checkLines = Integer.parseInt(slines);
			if (checkLines > 0 && checkLines < 10)
				lines = checkLines;
			else
				JOptionPane.showMessageDialog(null, "Please select a number between 1 and 9.");
		}
		else if (e.getSource() == exit){
			System.exit(0);
		}
	}
}

	public static void main(String[] args) {
		new SlotGame();
	}

}
