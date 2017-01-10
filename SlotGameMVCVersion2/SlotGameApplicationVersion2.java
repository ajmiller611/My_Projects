import javax.swing.SwingUtilities;

import controllersVersion2.GameController;

public class SlotGameApplicationVersion2 {
	
	public SlotGameApplicationVersion2() {
	}
	
	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Create game controller, load resources, and start application.
				GameController controller = new GameController();
				controller.loadResources();
				controller.startApplication();
			}
		});
	}
	
}
