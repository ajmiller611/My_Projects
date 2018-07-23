import javax.swing.SwingUtilities;

import controllersVersion2.BonusRoundController;
import controllersVersion2.GameController;
import controllersVersion2.PayoutController;

public class SlotGameApplicationVersion2 {
	
	public SlotGameApplicationVersion2() {
	}
	
	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread
		//Reference: https://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Create game controller, load resources, and start application.
				GameController controller = GameController.getGameControllerInstance();
				controller.setBonusRoundController(BonusRoundController.getBonusRoundControllerInstance());
				controller.setPayoutController(PayoutController.getPayoutControllerInstance());
				controller.loadResources();
				controller.startApplication();
			}
		});
	}
	
}
