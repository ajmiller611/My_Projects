import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;


public class SlotGame {	
	private JFrame frame;
	private static SymbolEnum[][] reelBoard = new SymbolEnum[3][5];
	private static int totalCredits = 100;
	private static int betPerLine = 1;
	private static int totalBet = 1;
	private static int linesPlayed = 1;
	private static Payout payout = new Payout();
	private Clip backgroundMusicClip;
	private static JPanel mainPanel;
	
	public SlotGame() {		
		//Create reels, setup the pay lines, setup bonus round images before GUI creation.
		Reels.createReels();
		payout.setupPaylines();
		BonusRound.setupImages();
		GameSounds.startBackgroundMusic();
		initializeUI();
	}
	
	private void initializeUI()	{
		/*
		 * Originally had the class inheriting JFrame until doing research on serial version UID.
		 * I found that creating a serial version UID was only used if a class would be using serialization.
		 * I was getting a warning about creating a serial version UID and it was from inheriting JFrame.
		 * Commented out code in this method was used when class was inheriting JFrame.
		 */
		//super("Slot Machine");
		//this.setSize(new Dimension(500, 500));
		//this.setResizable(false);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame = new JFrame("Slot Machine");
		frame.setSize(new Dimension(500, 500));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new JPanel(new CardLayout());
		
		//JPanel mainPanel = new JPanel();
		//mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		new GameGUI(mainPanel);
		new BonusGUI(mainPanel);
		
		//CardLayout cl = (CardLayout)(mainPanel.getLayout());
		//cl.show(mainPanel, "Bonus");
		
		//this.add(mainPanel);
		frame.add(mainPanel);
	}
	
	public static SymbolEnum[][] getResultsBoard() {
		reelBoard = Reels.getReelBoardResult();
		return reelBoard;
	}
	
	public static SymbolEnum[][] getBoardForSpin() {
		return Reels.getReelBoardResult();
	}
	
	public static void setBetPerLine(int bet) {
		betPerLine = bet;
	}
	
	public static int getBetPerLine() {
		return betPerLine;
	}
	
	public static void setLinesPlayed(int lines) {
		linesPlayed = lines;
	}
	
	public static int getLinesPlayed() {
		return linesPlayed;
	}
	
	public static void setResultsBoard(SymbolEnum[][] board) {
		reelBoard = board;
	}
	
	public static int getTotalCreditsAfterBet() {
		totalCredits -= totalBet;
		return totalCredits;
	}
	
	public static int getPayout() {
		totalCredits += payout.calculatePayout(reelBoard);
		return payout.calculatePayout(reelBoard);
	}
	
	public static void addCredits(int credits) {
		totalCredits = credits;
	}
	
	public static int getTotalCredits()	{
		return totalCredits;
	}
	
	public static void updateTotalBet() {
		totalBet = linesPlayed * betPerLine;
	}
	
	public static int getTotalBet() {
		return totalBet;
	}
	
	public static void addBonusRoundWinnings(int winnings) {
		totalCredits += winnings;
	}
	
	public static void changeStatusOfPaylines() {
		payout.updateActiveLinesPlayed(linesPlayed);
	}
	
	private void allowVisibility() {
		frame.setVisible(true);
	}
	
	public void pauseBackgroundMusic() {
		backgroundMusicClip.stop();
	}
	
	public void startBackgroundMusic() {
		backgroundMusicClip.start();
	}
	
	public static void showBonusRound() {
		CardLayout cl = (CardLayout)(mainPanel.getLayout());
		cl.show(mainPanel, "Bonus");
	}
	
	//create new BonusGUI object to reset bonus round.
	public static void createNewBonusGUI() {
		mainPanel.remove(1);
		new BonusGUI(mainPanel);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				SlotGame game = new SlotGame();
				game.allowVisibility();
			}
		});
	}

}
