package modelsVersion2;

import java.util.HashMap;
import java.util.Map;

public class Reels {
	private CircularLinkedList reel1 = new CircularLinkedList();
	private CircularLinkedList reel2 = new CircularLinkedList();
	private CircularLinkedList reel3 = new CircularLinkedList();
	private CircularLinkedList reel4 = new CircularLinkedList();
	private CircularLinkedList reel5 = new CircularLinkedList();
	private Map<Integer, CircularLinkedList> map = new HashMap<Integer, CircularLinkedList>();
	
	public Reels() {
			
	}
	
	public void createReels() {
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
		
		//Reel 1 and 5 get an extra symbol to compensate for the bonus round symbols on reels 2-4.
		reel1.add(SymbolEnum.clover);
		reel2.add(SymbolEnum.potOfGoldBonusRound);
		reel3.add(SymbolEnum.potOfGoldBonusRound);
		reel4.add(SymbolEnum.potOfGoldBonusRound);
		reel5.add(SymbolEnum.gold);
		
		map.put(1, reel1);
		map.put(2, reel2);
		map.put(3, reel3);
		map.put(4, reel4);
		map.put(5, reel5);
	}
	
	/*
	 * According to my research, Slot Machines in casinos randomized a stop position for the reel to stop on.
	 * This program has 7 total symbols on a reel so there are 7 stop positions.
	 */
	public SymbolEnum[][] getReelBoardResult() {
		SymbolEnum[][] reelBoard = new SymbolEnum[3][5];
		SymbolEnum[] reelArray = new SymbolEnum[3]; 
		int stopPosition;
		
		for(int a = 0; a < 5; a++) {
			stopPosition = (int) (Math.random() * 7);
			
			//Array holds the 3 symbols that need to be displayed for that stop position on a reel.
			reelArray = getResults(stopPosition, a + 1);
			
			int row = 0;
			for(int b = 0; b < 3; b++) {
				//Add the 3 symbols from each reel to the board
				reelBoard[row][a] = reelArray[b];
				row++;
			}
		}
		
		return reelBoard;
	}
	
	private SymbolEnum[] getResults (int stop, int reel){
		SymbolEnum[] result = new SymbolEnum[3];
		CircularLinkedList temp = map.get(reel);
		result[0] = temp.get(stop - 1); 
		result[1] = temp.get(stop);
		result[2] = temp.get(stop + 1);
		return result;
	}
	
	public CircularLinkedList getReel(int index) {
		return map.get(index);
	}
}
