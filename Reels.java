import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.imageio.*;
import javax.swing.JOptionPane;

import java.io.*;

public class Reels {
	private static CLinkedList reel1 = new CLinkedList();
	private static CLinkedList reel2 = new CLinkedList();
	private static CLinkedList reel3 = new CLinkedList();
	private static CLinkedList reel4 = new CLinkedList();
	private static CLinkedList reel5 = new CLinkedList();
	private static BufferedImage horseshoe;
	private static BufferedImage gold;
	private static BufferedImage clover;
	private static Map<Integer, CLinkedList> map = new HashMap<Integer, CLinkedList>();
	
	public Reels() {
			
	}
	
	public static void loadReels(){
		//Read the images for the icons that will be on the reels
		try{
			horseshoe = ImageIO.read(new File("horseshoe.png"));
			gold = ImageIO.read(new File("gold.png"));
			clover = ImageIO.read(new File("clover.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error:" + e);
		}
		
		//Place icons into the each reel's linked list
		for (int a = 0; a < 6; a++)
			switch (a){
				case 0: reel1.add(horseshoe);
						reel2.add(gold);
						reel3.add(clover);
						reel4.add(horseshoe);
						reel5.add(gold);
						break;
				case 1: reel1.add(gold);
						reel2.add(clover);
						reel3.add(horseshoe);
						reel4.add(gold);
						reel5.add(clover);
						break;
				case 2: reel1.add(clover);
						reel2.add(horseshoe);
						reel3.add(gold);
						reel4.add(clover);
						reel5.add(horseshoe);
						break;
				case 3: reel1.add(horseshoe);
						reel2.add(gold);
						reel3.add(clover);
						reel4.add(horseshoe);
						reel5.add(gold);
						break;
				case 4: reel1.add(gold);
						reel2.add(clover);
						reel3.add(horseshoe);
						reel4.add(gold);
						reel5.add(clover);
						break;
				case 5: reel1.add(clover);
						reel2.add(horseshoe);
						reel3.add(gold);
						reel4.add(clover);
						reel5.add(horseshoe);
						break;			
			}
		
		//Use HashMap to store each reel to be able to reference each reel by number
		map.put(1, reel1);
		map.put(2, reel2);
		map.put(3, reel3);
		map.put(4, reel4);
		map.put(5, reel5);
	}
	
	//Get the icons at the stop position and the icons above and below the stop position.
	public static BufferedImage[] getResults (int stop, int reel){
		BufferedImage[] result = new BufferedImage[3];
		CLinkedList temp = map.get(reel);
		result[0] = temp.get(stop - 1);
		result[1] = temp.get(stop);
		result[2] = temp.get(stop + 1);
		return result;
	}
	
	public static BufferedImage show(){
		return reel1.get(0);
	}
}
