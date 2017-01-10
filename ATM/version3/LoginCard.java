package version3;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

public class LoginCard extends JPanel implements ActionListener {
	JPanel cardHolderPanel;
	static JFormattedTextField enterPinTextBox;
	
	public LoginCard(JPanel panel) {
		cardHolderPanel = panel;
		cardHolderPanel.add(createLoginCard(), "Login");
	}

	//Creates the GUI for the create login card.
	private JPanel createLoginCard() {
		JPanel loginCard = new JPanel(new BorderLayout(0, 10));
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		//The enter pin label font is enlarged as it is used as the title of the panel
		JLabel enterPinLabel = new JLabel("Enter PIN:");
		Font labelFont = enterPinLabel.getFont();
		enterPinLabel.setFont(new Font(labelFont.getFontName(), Font.PLAIN, 20));
		enterPinLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		enterPinTextBox = createPinTextField();
		enterPinTextBox.setEditable(false);
		enterPinTextBox.setMaximumSize(new Dimension(55, 20));
		
		topPanel.add(enterPinLabel);
		topPanel.add(enterPinTextBox);
		
		loginCard.add(topPanel, BorderLayout.NORTH);
		
		GridLayout gridLayoutPanel = new GridLayout(3, 3);
		gridLayoutPanel.setHgap(20);
		gridLayoutPanel.setVgap(20);
		JPanel buttonGridPanel = new JPanel(gridLayoutPanel);
		
		for (int i = 1; i < 10; i++) {
			buttonGridPanel.add(createNumberedButton(i));
		}
		
		loginCard.add(buttonGridPanel, BorderLayout.CENTER);
		
		JButton backButton = new JButton("Back");
		backButton.setActionCommand("GoToMainMenu");
		backButton.addActionListener(this);
		
		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand("SubmitPin");
		submitButton.addActionListener(this);
		
		bottomPanel.add(submitButton);
		bottomPanel.add(backButton);
		
		loginCard.add(bottomPanel, BorderLayout.SOUTH);
		
		return loginCard;
	}
	
	//Creates a text field with desired format for pin
	private JFormattedTextField createPinTextField() {
		MaskFormatter format = null;
		try {
			format = new MaskFormatter("####");
		} catch (java.text.ParseException e) {
			System.err.println("formatter is bad: " + e.getMessage());
			System.exit(-1);
		}
		format.setPlaceholderCharacter('#');
		
		JFormattedTextField pinTextField = new JFormattedTextField(format);
		return pinTextField;
	}
	
	private JButton createNumberedButton(int number) {
		JButton numberedButton = new JButton(Integer.toString(number));
		numberedButton.setActionCommand(Integer.toString(number));
		numberedButton.addActionListener(this);
		return numberedButton;
	}
	
	//Switch to a card in the card holder panel based on what button is pressed.
	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout)(cardHolderPanel.getLayout());
		
		if ("SubmitPin".equals(e.getActionCommand())) {
			if (!enterPinTextBox.getText().equals("####")) {
				//Find user with entered pin or display appropriate error.
				try {
				ATMApplication.currentUser = ATMApplication.atm.getUserByPin(Integer.parseInt(enterPinTextBox.getText()));
				AccountMenuCard.updateWelcomeLabelWithUserName(ATMApplication.currentUser.getFirstName());
				cl.show(cardHolderPanel, "Account Menu");
				} catch (NullPointerException ex) {
					JOptionPane.showMessageDialog(null, "The PIN does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
					enterPinTextBox.setValue(null);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please enter a 4-digit PIN.", "Error", JOptionPane.ERROR_MESSAGE);
					enterPinTextBox.setValue(null);
				}
			} else if(enterPinTextBox.getText().equals("####")) {
				JOptionPane.showMessageDialog(null, "Please enter a PIN.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "PIN not correct.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} else if ("GoToMainMenu".equals(e.getActionCommand())) {
			enterPinTextBox.setValue(null);
			cl.first(cardHolderPanel);
		}
		
		//switch statement for the action if one of the numbered buttons is pressed.
		String textFieldValue = (String) enterPinTextBox.getValue();
		switch (e.getActionCommand()) {
			case "1" : 
				if (textFieldValue == null) {
					textFieldValue = "1";
				} else {
					textFieldValue += "1";
				}			
				enterPinTextBox.setValue(textFieldValue);
				break;
			case "2" :
				if (textFieldValue == null) {
					textFieldValue = "2";
				} else {
					textFieldValue += "2";
				}			
				enterPinTextBox.setValue(textFieldValue);
				break;
			case "3" :
				if (textFieldValue == null) {
					textFieldValue = "3";
				} else {
					textFieldValue += "3";
				}			
				enterPinTextBox.setValue(textFieldValue);
				break;
			case "4" :
				if (textFieldValue == null) {
					textFieldValue = "4";
				} else {
					textFieldValue += "4";
				}			
				enterPinTextBox.setValue(textFieldValue);
				break;
			case "5" :
				if (textFieldValue == null) {
					textFieldValue = "5";
				} else {
					textFieldValue += "5";
				}			
				enterPinTextBox.setValue(textFieldValue);
				break;
			case "6" :
				if (textFieldValue == null) {
					textFieldValue = "6";
				} else {
					textFieldValue += "6";
				}			
				enterPinTextBox.setValue(textFieldValue);
				break;
			case "7" :
				if (textFieldValue == null) {
					textFieldValue = "7";
				} else {
					textFieldValue += "7";
				}			
				enterPinTextBox.setValue(textFieldValue);
				break;
			case "8" :
				if (textFieldValue == null) {
					textFieldValue = "8";
				} else {
					textFieldValue += "8";
				}			
				enterPinTextBox.setValue(textFieldValue);
				break;
			case "9" :
				if (textFieldValue == null) {
					textFieldValue = "9";
				} else {
					textFieldValue += "9";
				}			
				enterPinTextBox.setValue(textFieldValue);
				break;
		}
	}
}
