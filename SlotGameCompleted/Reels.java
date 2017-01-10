import java.util.*;

public class Reels {
	private static CLinkedList reel1 = new CLinkedList();
	private static CLinkedList reel2 = new CLinkedList();
	private static CLinkedList reel3 = new CLinkedList();
	private static CLinkedList reel4 = new CLinkedList();
	private static CLinkedList reel5 = new CLinkedList();
	private static Map<Integer, CLinkedList> map = new HashMap<Integer, CLinkedList>();
	
	public Reels() {
			
	}
	
	public static void createReels() {
		//Add a symbol to each reel a total of 6 times.
		for (int a = 0; a < 6; a++) {
			switch (a) {
				case 0: reel1.add(SymbolEnum.horseshoe);
						reel2.add(SymbolEnum.gold);
						reel3.add(SymbolEnum.clover);
						reel4.add(SymbolEnum.horseshoe);
						reel5.add(SymbolEnum.gold);
						break;
				case 1: reel1.add(SymbolEnum.gold);
						reel2.add(SymbolEnum.clover);
						reel3.add(SymbolEnum.horseshoe);
						reel4.add(SymbolEnum.gold);
						reel5.add(SymbolEnum.clover);
						break;
				case 2: reel1.add(SymbolEnum.clover);
						reel2.add(SymbolEnum.horseshoe);
						reel3.add(SymbolEnum.gold);
						reel4.add(SymbolEnum.clover);
						reel5.add(SymbolEnum.horseshoe);
						break;
				case 3: reel1.add(SymbolEnum.horseshoe);
						reel2.add(SymbolEnum.gold);
						reel3.add(SymbolEnum.clover);
						reel4.add(SymbolEnum.horseshoe);
						reel5.add(SymbolEnum.gold);
						break;
				case 4: reel1.add(SymbolEnum.gold);
						reel2.add(SymbolEnum.clover);
						reel3.add(SymbolEnum.horseshoe);
						reel4.add(SymbolEnum.gold);
						reel5.add(SymbolEnum.clover);
						break;
				case 5: reel1.add(SymbolEnum.clover);
						reel2.add(SymbolEnum.horseshoe);
						reel3.add(SymbolEnum.gold);
						reel4.add(SymbolEnum.clover);
						reel5.add(SymbolEnum.horseshoe);
						break;
			}
		}
		
		reel2.add(SymbolEnum.potOfGoldBonusRound);
		reel3.add(SymbolEnum.potOfGoldBonusRound);
		reel4.add(SymbolEnum.potOfGoldBonusRound);
		
		map.put(1, reel1);
		map.put(2, reel2);
		map.put(3, reel3);
		map.put(4, reel4);
		map.put(5, reel5);
	}
	
	/*
	 * Slot Machines in casinos randomized a stop position for the reel to stop on.
	 * This program has 6 symbols on a reel so there are 6 stop positions.
	 */
	public static SymbolEnum[][] getReelBoardResult() {
		SymbolEnum[][] reelBoard = new SymbolEnum[3][5];
		SymbolEnum[] reelArray = new SymbolEnum[3]; 
		int stopPosition;
		
		for(int a = 0; a < 5; a++) {
			stopPosition = (int) (Math.random() * 6);
			
			//Array holds the 3 symbols that need to be displayed for that stop position on a reel.
			reelArray = Reels.getResults(stopPosition, a + 1);
			
			int row = 0;
			for(int b = 0; b < 3; b++) {
				//Add the 3 symbols from each reel to the board
				reelBoard[row][a] = reelArray[b];
				row++;
			}
		}
		
		return reelBoard;
	}
	
	private static SymbolEnum[] getResults (int stop, int reel){
		SymbolEnum[] result = new SymbolEnum[3];
		CLinkedList temp = map.get(reel);
		result[0] = temp.get(stop - 1); 
		result[1] = temp.get(stop);
		result[2] = temp.get(stop + 1);
		return result;
	}
}
