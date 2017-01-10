package models;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class BonusRoundModel {
	SymbolEnum[] symbolArray = new SymbolEnum[9];
	SymbolEnum[][] bonusGameBoard = new SymbolEnum[3][3];
	int totalWinnings = 0;
	int numberOfSelectedImages = 0;
	
	public BonusRoundModel() {
		
	}
	
	public void setupImages() {
		symbolArray[0] = SymbolEnum.potOfGold100;
		symbolArray[1] = SymbolEnum.potOfGold100;
		symbolArray[2] = SymbolEnum.potOfGold100;
		symbolArray[3] = SymbolEnum.potOfGold100;
		symbolArray[4] = SymbolEnum.potOfGold100;
		symbolArray[5] = SymbolEnum.potOfGold100;
		symbolArray[6] = SymbolEnum.potOfGold500;
		symbolArray[7] = SymbolEnum.potOfGold500;
		symbolArray[8] = SymbolEnum.potOfGold1000;
		randomizeSymbolArray();
	}
	
	private void randomizeSymbolArray() {
		for(int a = 0; a < 9; a++) {
			int randomIndex = (int) (Math.random() * 9);
			SymbolEnum temp = symbolArray[a];
			symbolArray[a] = symbolArray[randomIndex];
			symbolArray[randomIndex] = temp;
		}
	}
	
	public SymbolEnum[][] getGameBoard() {
		return bonusGameBoard;
	}
	
	public void generateGameBoard() {
		randomizeSymbolArray();
		
		int symbolArrayIndexCounter = 0;
		for(int a = 0; a < 3; a++) {
			for(int b = 0; b < 3; b++) {
				bonusGameBoard[a][b] = symbolArray[symbolArrayIndexCounter];
				symbolArrayIndexCounter++;
			}
		}
	}
	
	public ImageIcon getImageToDisplayAfterSelection() {
		ImageIcon image = new ImageIcon();
		try {
			BufferedImage imageForAfterSelected = ImageIO.read(new File("./leprechaun_sitting_on_gold.jpg"));
			image.setImage(imageForAfterSelected);
		} catch(Exception ex) {
			System.out.println("Error: " + ex);
		}
		return image;
	}
	
	public void addToWinnings(int winnings) {
		totalWinnings += winnings;
	}
	
	public int getTotalWinnings() {
		return totalWinnings;
	}
	
	public void incrementNumberOfSelectedImages() {
		numberOfSelectedImages++;
	}
	
	public int getNumberOfSelectedImages() {
		return numberOfSelectedImages;
	}
	
	public void resetBonusRoundData() {
		totalWinnings = 0;
		numberOfSelectedImages = 0;
	}
}
