package modelsVersion2;

import modelsVersion2.Reels;

public class SlotGameModel {
	private Reels reels;
	private SymbolEnum[][] reelBoard = new SymbolEnum[3][5];
	private int totalCredits = 100;
	private int betPerLine = 1;
	private int totalBet = 1;
	private int linesPlayed = 1;
	
	public SlotGameModel() {
		reels = new Reels();
	}
	
	public void createReels() {
		reels.createReels();
	}
	
	public CircularLinkedList getReel(int index) {
		return reels.getReel(index);
	}
	
	public SymbolEnum[][] getResultsBoard() {
		return reelBoard;
	}
	
	public void generateResultsBoard() {
		reelBoard = reels.getReelBoardResult();
	}
	
	public void addCredits(int credits) {
		totalCredits += credits;
	}
	
	public int getTotalCredits() {
		return totalCredits;
	}
	
	public int getTotalCreditsAfterBet() {
		totalCredits -= totalBet;
		return totalCredits;
	}
	
	public void setBetPerLine(int bet) {
		betPerLine = bet;
	}
	
	public int getBetPerLine() {
		return betPerLine;
	}
	
	public int getTotalBet() {
		return totalBet;
	}
	
	public void updateTotalBet() {
		totalBet = linesPlayed * betPerLine;
	}
	
	public void setLinesPlayed(int lines) {
		linesPlayed = lines;
	}
	
	public int getLinesPlayed() {
		return linesPlayed;
	}
	
}
