package controllers;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import models.BonusRoundModel;
import models.SymbolEnum;
import views.BonusGUI;

public class BonusRoundController {
	private GameController gameController;
	private BonusGUI bonusView;
	private BonusRoundModel bonusModel;
	private ActionListener listener;
	
	public BonusRoundController(GameController controller) {
		gameController = controller;
		bonusModel = new BonusRoundModel();
		listener = new EventHandler();
	}
	
	public void generateGameBoard() {
		bonusModel.generateGameBoard();
	}
	
	public void setBonusGUIView(BonusGUI gui) {
		bonusView = gui;
	}
	
	public ActionListener getActionListener() {
		return listener;
	}
	
	public void setupImages() {
		bonusModel.setupImages();
	}
	
	public SymbolEnum[][] getGameBoard() {
		return bonusModel.getGameBoard();
	}
	
	public void resetBonusRoundGUI() {
		bonusView.resetBonusRoundGUIComponents();
	}
	
	class EventHandler extends MouseAdapter implements ActionListener {
		
		public void mouseClicked(MouseEvent e) {
			if(bonusModel.getNumberOfSelectedImages() < 3) {
				//Once a symbol is clicked, change the icon, get value of symbol and update labels, remove mouse listener
				if(e.getComponent().getName().equals("Position 0 0")) {
					bonusView.getDisplayBoard()[0][0].setIcon(bonusModel.getImageToDisplayAfterSelection());
					bonusModel.addToWinnings(bonusModel.getGameBoard()[0][0].getValue() * gameController.getBetPerLine());
					bonusView.updateWinningsLabelText("Won: " + (bonusModel.getGameBoard()[0][0].getValue() * gameController.getBetPerLine()));
					bonusView.updateTotalWinningsLabelText("Total Won: " + bonusModel.getTotalWinnings());
					bonusView.getDisplayBoard()[0][0].removeMouseListener(this);
					bonusModel.incrementNumberOfSelectedImages();
				
				} else if(e.getComponent().getName().equals("Position 0 1")) {
					bonusView.getDisplayBoard()[0][1].setIcon(bonusModel.getImageToDisplayAfterSelection());
					bonusModel.addToWinnings(bonusModel.getGameBoard()[0][1].getValue() * gameController.getBetPerLine());
					bonusView.updateWinningsLabelText("Won: " + (bonusModel.getGameBoard()[0][1].getValue() * gameController.getBetPerLine()));
					bonusView.updateTotalWinningsLabelText("Total Won: " + bonusModel.getTotalWinnings());
					bonusView.getDisplayBoard()[0][1].removeMouseListener(this);
					bonusModel.incrementNumberOfSelectedImages();
				
				} else if(e.getComponent().getName().equals("Position 0 2")) {
					bonusView.getDisplayBoard()[0][2].setIcon(bonusModel.getImageToDisplayAfterSelection());
					bonusModel.addToWinnings(bonusModel.getGameBoard()[0][2].getValue() * gameController.getBetPerLine());
					bonusView.updateWinningsLabelText("Won: " + (bonusModel.getGameBoard()[0][2].getValue() * gameController.getBetPerLine()));
					bonusView.updateTotalWinningsLabelText("Total Won: " + bonusModel.getTotalWinnings());
					bonusView.getDisplayBoard()[0][2].removeMouseListener(this);
					bonusModel.incrementNumberOfSelectedImages();
				
				} else if(e.getComponent().getName().equals("Position 1 0")) {
					bonusView.getDisplayBoard()[1][0].setIcon(bonusModel.getImageToDisplayAfterSelection());
					bonusModel.addToWinnings(bonusModel.getGameBoard()[1][0].getValue() * gameController.getBetPerLine());
					bonusView.updateWinningsLabelText("Won: " + (bonusModel.getGameBoard()[1][0].getValue() * gameController.getBetPerLine()));
					bonusView.updateTotalWinningsLabelText("Total Won: " + bonusModel.getTotalWinnings());
					bonusView.getDisplayBoard()[1][0].removeMouseListener(this);
					bonusModel.incrementNumberOfSelectedImages();
				
				} else if(e.getComponent().getName().equals("Position 1 1")) {
					bonusView.getDisplayBoard()[1][1].setIcon(bonusModel.getImageToDisplayAfterSelection());
					bonusModel.addToWinnings(bonusModel.getGameBoard()[1][1].getValue() * gameController.getBetPerLine());
					bonusView.updateWinningsLabelText("Won: " + (bonusModel.getGameBoard()[1][1].getValue() * gameController.getBetPerLine()));				
					bonusView.updateTotalWinningsLabelText("Total Won: " + bonusModel.getTotalWinnings());
					bonusView.getDisplayBoard()[1][1].removeMouseListener(this);
					bonusModel.incrementNumberOfSelectedImages();
				
				} else if(e.getComponent().getName().equals("Position 1 2")) {
					bonusView.getDisplayBoard()[1][2].setIcon(bonusModel.getImageToDisplayAfterSelection());
					bonusModel.addToWinnings(bonusModel.getGameBoard()[1][2].getValue() * gameController.getBetPerLine());
					bonusView.updateWinningsLabelText("Won: " + (bonusModel.getGameBoard()[1][2].getValue() * gameController.getBetPerLine()));				
					bonusView.updateTotalWinningsLabelText("Total Won: " + bonusModel.getTotalWinnings());
					bonusView.getDisplayBoard()[1][2].removeMouseListener(this);
					bonusModel.incrementNumberOfSelectedImages();
				
				} else if(e.getComponent().getName().equals("Position 2 0")) {
					bonusView.getDisplayBoard()[2][0].setIcon(bonusModel.getImageToDisplayAfterSelection());
					bonusModel.addToWinnings(bonusModel.getGameBoard()[2][0].getValue() * gameController.getBetPerLine());
					bonusView.updateWinningsLabelText("Won: " + (bonusModel.getGameBoard()[2][0].getValue() * gameController.getBetPerLine()));				
					bonusView.updateTotalWinningsLabelText("Total Won: " + bonusModel.getTotalWinnings());
					bonusView.getDisplayBoard()[2][0].removeMouseListener(this);
					bonusModel.incrementNumberOfSelectedImages();
				
				} else if(e.getComponent().getName().equals("Position 2 1")) {
					bonusView.getDisplayBoard()[2][1].setIcon(bonusModel.getImageToDisplayAfterSelection());
					bonusModel.addToWinnings(bonusModel.getGameBoard()[2][1].getValue() * gameController.getBetPerLine());
					bonusView.updateWinningsLabelText("Won: " + (bonusModel.getGameBoard()[2][1].getValue() * gameController.getBetPerLine()));				
					bonusView.updateTotalWinningsLabelText("Total Won: " + bonusModel.getTotalWinnings());
					bonusView.getDisplayBoard()[2][1].removeMouseListener(this);
					bonusModel.incrementNumberOfSelectedImages();
				
				} else if(e.getComponent().getName().equals("Position 2 2")) {
					bonusView.getDisplayBoard()[2][2].setIcon(bonusModel.getImageToDisplayAfterSelection());
					bonusModel.addToWinnings(bonusModel.getGameBoard()[2][2].getValue() * gameController.getBetPerLine());
					bonusView.updateWinningsLabelText("Won: " + bonusModel.getGameBoard()[2][2].getValue() * gameController.getBetPerLine());				
					bonusView.updateTotalWinningsLabelText("Total Won: " + bonusModel.getTotalWinnings());
					bonusView.getDisplayBoard()[2][2].removeMouseListener(this);
					bonusModel.incrementNumberOfSelectedImages();
				}
			} 
			if(bonusModel.getNumberOfSelectedImages() == 3) {
				bonusView.showReturnToSlotsButton();
			}
		}
		
		public void actionPerformed(ActionEvent e) {
			CardLayout cl = (CardLayout)(gameController.getMainPanelFromGameGUI().getLayout());
			
			if(e.getActionCommand().equals("Return To Slots")) {
				gameController.addBonusRoundWinnings(bonusModel.getTotalWinnings());
				gameController.updateWinningsAndCreditsLabelAfterBonusRound(bonusModel.getTotalWinnings());
				
				//Flip card back to main slot machine
				cl.show(gameController.getMainPanelFromGameGUI(), "Main");
				bonusModel.resetBonusRoundData();
				resetBonusRoundGUI();
				
			}
		}
		
	}
	
}
