package scheduler;

import javax.swing.SwingUtilities;

public class SchedulerApplication {
	
	public SchedulerApplication() {
		createAndShowGUI();
	}
	
	public void createAndShowGUI() {
		GUI gui = new GUI();
		gui.showGUI();
	}
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				SchedulerApplication app = new SchedulerApplication();
			}
		});
	}
}
