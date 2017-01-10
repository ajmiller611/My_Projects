package modelsVersion2;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public enum SymbolEnum {
	horseshoe("horseshoe.png", "horseshoe", 3), 
	gold("gold.png", "gold", 2), 
	clover("clover.png", "clover", 1),
	potOfGoldBonusRound("pot_of_gold_bonus.png", "bonus", 0),
	potOfGold100("pot_of_gold.png", "pot100", 100),
	potOfGold500("pot_of_gold.png", "pot500", 500),
	potOfGold1000("pot_of_gold.png", "pot1000", 1000);
	
	private ImageIcon image;
	private String name;
	private int value;
	
	private SymbolEnum(String filename, String name, int value) {
		try {
			this.image = new ImageIcon();
			this.image.setImage(ImageIO.read(new File(filename)));
			this.name = name;
			this.value = value;
		} catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e);
		}
	}
	
	public ImageIcon getImage() {
		return image;
	}
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return value;
	}
}
