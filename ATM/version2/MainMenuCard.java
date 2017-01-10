package version2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;


import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuCard extends JPanel implements ActionListener{
	JPanel cardLayoutPanel;
	CardLayout cl;
	
	public MainMenuCard(JPanel panel, CardLayout layout) {
		cardLayoutPanel = panel;
		cl = layout;
	}
	
	public JPanel createMainMenuCard() {
		JPanel mainMenuCard = new JPanel(new BorderLayout());		
		
		JPanel topPanelMenu = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		JLabel welcomeLabel = new JLabel("Welcome to ATM");
		Font labelFont = welcomeLabel.getFont();
		welcomeLabel.setFont(new Font(labelFont.getFontName(), Font.PLAIN, 20));
		welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
		topPanelMenu.add(welcomeLabel);
		mainMenuCard.add(topPanelMenu, BorderLayout.NORTH);
		
		
		buttonPanel.add(Box.createHorizontalGlue());
		
		String enterPinButtonText = "<html>"
				+ "<body>"
				+ "<p style='font-size:16pt'>Login</p>"
				+ "<p style='font-size:16pt'>with</p>"
				+ "<p style='font-size:16pt'>PIN</p>"
				+ "</body>"
				+ "</html>";
		
		JButton enterPinButton = new JButton(enterPinButtonText);
		enterPinButton.setMaximumSize(new Dimension(95, 70));
		enterPinButton.setPreferredSize(new Dimension(95, 70));
		enterPinButton.setActionCommand("PinButton");
		enterPinButton.addActionListener(this);
		buttonPanel.add(enterPinButton);
		
		buttonPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		
		String createUserButtonText = "<html>"
				+ "<body>"
				+ "<p style='font-size:16pt'>Create</p>"
				+ "<p style='font-size:16pt'>User</p>"
				+ "</body>"
				+ "</html>";
		
		JButton createUserButton = new JButton(createUserButtonText);
		createUserButton.setMaximumSize(new Dimension(95, 70));
		createUserButton.setPreferredSize(new Dimension(95, 70));
		createUserButton.setActionCommand("CreateUser");
		createUserButton.addActionListener(this);
		buttonPanel.add(createUserButton);
		
		buttonPanel.add(Box.createHorizontalGlue());
		
		mainMenuCard.add(buttonPanel, BorderLayout.CENTER);
		
		return mainMenuCard;
	}
	
	public void actionPerformed(ActionEvent e) {
		if ("PinButton".equals(e.getActionCommand())) {
			cl.show(cardLayoutPanel, "Login");
		} else if ("CreateUser".equals(e.getActionCommand())) {
			cl.show(cardLayoutPanel, "Create User");
		}
	}
}
	
