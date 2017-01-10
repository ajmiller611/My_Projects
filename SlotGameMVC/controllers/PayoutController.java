package controllers;

import java.awt.Point;
import java.util.ArrayList;

import models.Payline;
import models.SymbolEnum;

public class PayoutController {
	private GameController controller;
	private Payline paylineOne, paylineTwo, paylineThree, paylineFour, paylineFive,
		paylineSix, paylineSeven, paylineEight, paylineNine;
	private ArrayList<Payline> paylineList = new ArrayList<Payline>();

	public PayoutController(GameController controller) {
		this.controller = controller;
		
		paylineOne = new Payline();
		paylineList.add(paylineOne);

		paylineTwo = new Payline();
		paylineList.add(paylineTwo);

		paylineThree = new Payline();
		paylineList.add(paylineThree);

		paylineFour = new Payline();
		paylineList.add(paylineFour);

		paylineFive = new Payline();
		paylineList.add(paylineFive);

		paylineSix = new Payline();
		paylineList.add(paylineSix);

		paylineSeven = new Payline();
		paylineList.add(paylineSeven);

		paylineEight = new Payline();
		paylineList.add(paylineEight);

		paylineNine = new Payline();
		paylineList.add(paylineNine);
	}

	public void setupPaylines() {
		paylineOne.setReelOneCoordinate(new Point(1, 0));
		paylineOne.setReelTwoCoordinate(new Point(1, 1));
		paylineOne.setReelThreeCoordinate(new Point(1, 2));
		paylineOne.setReelFourCoordinate(new Point(1, 3));
		paylineOne.setReelFiveCoordinate(new Point(1, 4));
		//Payline one will always be active.
		paylineOne.setActiveFlag(true);

		paylineTwo.setReelOneCoordinate(new Point(0, 0));
		paylineTwo.setReelTwoCoordinate(new Point(0, 1));
		paylineTwo.setReelThreeCoordinate(new Point(0, 2));
		paylineTwo.setReelFourCoordinate(new Point(0, 3));
		paylineTwo.setReelFiveCoordinate(new Point(0, 4));
		paylineTwo.setActiveFlag(false);

		paylineThree.setReelOneCoordinate(new Point(2, 0));
		paylineThree.setReelTwoCoordinate(new Point(2, 1));
		paylineThree.setReelThreeCoordinate(new Point(2, 2));
		paylineThree.setReelFourCoordinate(new Point(2, 3));
		paylineThree.setReelFiveCoordinate(new Point(2, 4));
		paylineThree.setActiveFlag(false);

		paylineFour.setReelOneCoordinate(new Point(0, 0));
		paylineFour.setReelTwoCoordinate(new Point(1, 1));
		paylineFour.setReelThreeCoordinate(new Point(2, 2));
		paylineFour.setReelFourCoordinate(new Point(1, 3));
		paylineFour.setReelFiveCoordinate(new Point(0, 4));
		paylineFour.setActiveFlag(false);

		paylineFive.setReelOneCoordinate(new Point(2, 0));
		paylineFive.setReelTwoCoordinate(new Point(1, 1));
		paylineFive.setReelThreeCoordinate(new Point(0, 2));
		paylineFive.setReelFourCoordinate(new Point(1, 3));
		paylineFive.setReelFiveCoordinate(new Point(2, 4));
		paylineFive.setActiveFlag(false);

		paylineSix.setReelOneCoordinate(new Point(1, 0));
		paylineSix.setReelTwoCoordinate(new Point(2, 1));
		paylineSix.setReelThreeCoordinate(new Point(2, 2));
		paylineSix.setReelFourCoordinate(new Point(2, 3));
		paylineSix.setReelFiveCoordinate(new Point(1, 4));
		paylineSix.setActiveFlag(false);

		paylineSeven.setReelOneCoordinate(new Point(1, 0));
		paylineSeven.setReelTwoCoordinate(new Point(0, 1));
		paylineSeven.setReelThreeCoordinate(new Point(0, 2));
		paylineSeven.setReelFourCoordinate(new Point(0, 3));
		paylineSeven.setReelFiveCoordinate(new Point(1, 4));
		paylineSeven.setActiveFlag(false);

		paylineEight.setReelOneCoordinate(new Point(0, 0));
		paylineEight.setReelTwoCoordinate(new Point(0, 1));
		paylineEight.setReelThreeCoordinate(new Point(1, 2));
		paylineEight.setReelFourCoordinate(new Point(2, 3));
		paylineEight.setReelFiveCoordinate(new Point(2, 4));
		paylineEight.setActiveFlag(false);

		paylineNine.setReelOneCoordinate(new Point(2, 0));
		paylineNine.setReelTwoCoordinate(new Point(2, 1));
		paylineNine.setReelThreeCoordinate(new Point(1, 2));
		paylineNine.setReelFourCoordinate(new Point(0, 3));
		paylineNine.setReelFiveCoordinate(new Point(0, 4));
		paylineNine.setActiveFlag(false);
	}

	public void updateActiveLinesPlayed(int linesPlayed) {
		//New logic to active and deactivate lines played that is more efficient in processing time.
		if(linesPlayed == 1) {
			for(int b = 1; b < paylineList.size(); b++) {
				paylineList.get(b).setActiveFlag(false);
			}
		} else {
			for(int a = 1; a < linesPlayed; a++) {
				paylineList.get(a).setActiveFlag(true);
			}	
		}

		/*
		 * Original logic to active and deactivate lines played.
		 * 
		 *
		for(int a = 1; a < linesPlayed; a++) {
			paylineList.get(a).setActiveFlag(true);
			System.out.println("Payline: " + a + " " + paylineList.get(a).isActive());
		}
		//Index for paylines to be deactivated would be number of lines played due to array list index starting at 0.
		for(int b = linesPlayed; b < paylineList.size(); b++) {
			paylineList.get(b).setActiveFlag(false);
			System.out.println("Payline: " + b + " " + paylineList.get(b).isActive());
		}*/
	}

	public int calculatePayout(SymbolEnum[][] board) {
		int payout = 0;
		for(Payline payline: paylineList) {
			if(payline.isActive()) {
				payout += this.calculateWinningsOfPayline(payline.getPaylineArray(board));
			}
		}

		return payout;
	}

	private int calculateWinningsOfPayline(SymbolEnum[] paylineArray) {
		int numberOfSameSymbols = 1;

		for(int a = 1; a < 5; a++ ) {
			if(paylineArray[a].equals(paylineArray[0])) {
				numberOfSameSymbols++;
			} else {
				break;
			}
		}

		if(numberOfSameSymbols >= 3) {
			return controller.getBetPerLine() * numberOfSameSymbols * paylineArray[0].getValue();
		}

		return 0;
	}
}
