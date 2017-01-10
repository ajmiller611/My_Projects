package modelsVersion2;

import java.awt.Point;

public class Payline {
	private Point reelOneCoordinate;
	private Point reelTwoCoordinate;
	private Point reelThreeCoordinate;
	private Point reelFourCoordinate;
	private Point reelFiveCoordinate;
	private SymbolEnum[] paylineArray = new SymbolEnum[5];
	private boolean activeFlag;

	public Payline() {
		
	}
	
	public void setReelOneCoordinate(Point coordinate) {
		reelOneCoordinate = coordinate;
	}
	
	public void setReelTwoCoordinate(Point coordinate) {
		reelTwoCoordinate = coordinate;
	}
	
	public void setReelThreeCoordinate(Point coordinate) {
		reelThreeCoordinate = coordinate;
	}
	
	public void setReelFourCoordinate(Point coordinate) {
		reelFourCoordinate = coordinate;
	}
	
	public void setReelFiveCoordinate(Point coordinate) {
		reelFiveCoordinate = coordinate;
	}
	
	/*
	 * Returns a SymbolEnum array of the SymbolEnum objects that are at the coordinates of the payline
	 */
	public SymbolEnum[] getPaylineArray(SymbolEnum[][] board) {
		SymbolEnum[][] reelBoard = board;
		paylineArray[0] = reelBoard[(int) reelOneCoordinate.getX()][(int) reelOneCoordinate.getY()];
		paylineArray[1] = reelBoard[(int) reelTwoCoordinate.getX()][(int) reelTwoCoordinate.getY()];
		paylineArray[2] = reelBoard[(int) reelThreeCoordinate.getX()][(int) reelThreeCoordinate.getY()];
		paylineArray[3] = reelBoard[(int) reelFourCoordinate.getX()][(int) reelFourCoordinate.getY()];
		paylineArray[4] = reelBoard[(int) reelFiveCoordinate.getX()][(int) reelFiveCoordinate.getY()];
		return paylineArray;
	}
	
	public void setActiveFlag(boolean flag) {
		activeFlag = flag;
	}
	
	public boolean isActive() {
		return activeFlag;
	}	
}
