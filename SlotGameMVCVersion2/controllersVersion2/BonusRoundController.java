package controllersVersion2;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import modelsVersion2.BonusRoundModel;
import modelsVersion2.SymbolEnum;
import viewsVersion2.BonusGUI;

public class BonusRoundController {
	private static BonusRoundController controllerInstance;
	private GameController gameController;
	private BonusGUI bonusView;
	private BonusRoundModel bonusModel;
	private ActionListener listener;
	
	public BonusRoundController() {
		//Set the game controller
		gameController = GameController.getGameControllerInstance();
		
		//Create the bonus round model
		bonusModel = new BonusRoundModel();
		
		//Create event handler for bonus round GUI
		listener = new EventHandler();
	}
	
	//Use singleton design pattern to allow only one instance of bonus round controller.
	public static BonusRoundController getBonusRoundControllerInstance() {
		if(controllerInstance == null) {
			controllerInstance = new BonusRoundController();
		}
		return controllerInstance;
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
					updateBonusGameIconAtPosition(e.getComponent().getName());					
				
				} else if(e.getComponent().getName().equals("Position 0 1")) {					
					updateBonusGameIconAtPosition(e.getComponent().getName());
				
				} else if(e.getComponent().getName().equals("Position 0 2")) {					
					updateBonusGameIconAtPosition(e.getComponent().getName());
				
				} else if(e.getComponent().getName().equals("Position 1 0")) {					
					updateBonusGameIconAtPosition(e.getComponent().getName());
				
				} else if(e.getComponent().getName().equals("Position 1 1")) {					
					updateBonusGameIconAtPosition(e.getComponent().getName());
				
				} else if(e.getComponent().getName().equals("Position 1 2")) {					
					updateBonusGameIconAtPosition(e.getComponent().getName());
				
				} else if(e.getComponent().getName().equals("Position 2 0")) {					
					updateBonusGameIconAtPosition(e.getComponent().getName());
				
				} else if(e.getComponent().getName().equals("Position 2 1")) {					
					updateBonusGameIconAtPosition(e.getComponent().getName());
				
				} else if(e.getComponent().getName().equals("Position 2 2")) {					
					updateBonusGameIconAtPosition(e.getComponent().getName());
				}
			} 
			if(bonusModel.getNumberOfSelectedImages() == 3) {
				bonusView.showReturnToSlotsButton();
			}
		}
		
		public void updateBonusGameIconAtPosition(String position) {
			int x = Integer.parseInt(position.substring(9, 10));
			int y = Integer.parseInt(position.substring(11));
			
			bonusView.getDisplayBoard()[x][y].setIcon(bonusModel.getImageToDisplayAfterSelection());
			bonusModel.addToWinnings(bonusModel.getGameBoard()[x][y].getValue() * gameController.getBetPerLine());
			bonusView.updateWinningsLabelText("Won: " + bonusModel.getGameBoard()[x][y].getValue() * gameController.getBetPerLine());				
			bonusView.updateTotalWinningsLabelText("Total Won: " + bonusModel.getTotalWinnings());
			bonusView.getDisplayBoard()[x][y].removeMouseListener(this);
			bonusModel.incrementNumberOfSelectedImages();
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
