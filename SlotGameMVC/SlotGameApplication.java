import javax.swing.SwingUtilities;

import controllers.GameController;

public class SlotGameApplication {
	
	public SlotGameApplication() {
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				GameController controller = new GameController();
				controller.loadResources();
				controller.startApplication();
			}
		});
	}
	
}
