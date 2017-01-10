package version3;

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
	JPanel cardHolderPanel;

	public MainMenuCard(JPanel panel) {
		cardHolderPanel = panel;
		cardHolderPanel.add(createMainMenuCard(), "Main Menu");
	}
	
	//Creates the GUI for the main menu card.
	private JPanel createMainMenuCard() {
		JPanel mainMenuCard = new JPanel(new BorderLayout());		
		JPanel topPanel = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		//The welcome label font is enlarged as it is used as the title of the panel
		JLabel welcomeLabel = new JLabel("Welcome to ATM");
		Font labelFont = welcomeLabel.getFont();
		welcomeLabel.setFont(new Font(labelFont.getFontName(), Font.PLAIN, 20));
		welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
		topPanel.add(welcomeLabel);
		mainMenuCard.add(topPanel, BorderLayout.NORTH);
		
		buttonPanel.add(Box.createHorizontalGlue());
		
		//Use HTML to style the text of the login and create user buttons.
		String loginButtonText = "<html>"
				+ "<body>"
				+ "<p style='font-size:20pt'>Login</p>"
				+ "<p style='font-size:20pt'>with</p>"
				+ "<p style='font-size:20pt'>PIN</p>"
				+ "</body>"
				+ "</html>";
		
		JButton loginButton = new JButton(loginButtonText); 
		loginButton.setMaximumSize(new Dimension(125, 95)); // 95, 70
		loginButton.setPreferredSize(new Dimension(125, 95));
		loginButton.setActionCommand("PinButton");
		loginButton.addActionListener(this);
		buttonPanel.add(loginButton);
		
		//create empty space between the login and create user buttons.
		buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
		
		String createUserButtonText = "<html>"
				+ "<body>"
				+ "<p style='font-size:20pt'>Create</p>"
				+ "<p style='font-size:20pt'>User</p>"
				+ "</body>"
				+ "</html>";
		
		JButton createUserButton = new JButton(createUserButtonText);
		createUserButton.setMaximumSize(new Dimension(125, 95));
		createUserButton.setPreferredSize(new Dimension(120, 95));
		createUserButton.setActionCommand("CreateUser");
		createUserButton.addActionListener(this);
		buttonPanel.add(createUserButton);
		
		buttonPanel.add(Box.createHorizontalGlue());
		mainMenuCard.add(buttonPanel, BorderLayout.CENTER);
		
		return mainMenuCard;
	}
	
	//Switch to a card in the card holder panel based on what button is pressed.
	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout)(cardHolderPanel.getLayout());
		
		if ("PinButton".equals(e.getActionCommand())) {
			cl.show(cardHolderPanel, "Login");
		} else if ("CreateUser".equals(e.getActionCommand())) {
			cl.show(cardHolderPanel, "Create User");
		}
	}
}
	
