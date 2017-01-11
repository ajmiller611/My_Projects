package models;

public class SlotGameModel {
	private SymbolEnum[][] reelBoard = new SymbolEnum[3][5];
	private int totalCredits = 100;
	private int betPerLine = 1;
	private int totalBet = 1;
	private int linesPlayed = 1;
	
	public SlotGameModel() {
		
	}
	
	public SymbolEnum[][] getResultsBoard() {
		return reelBoard;
	}
	
	public void generateResultsBoard() {
		Reels reels = new Reels();
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
