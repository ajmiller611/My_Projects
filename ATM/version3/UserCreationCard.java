package version3;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class UserCreationCard extends JPanel implements ActionListener{
	JPanel cardHolderPanel;
	JTextField firstNameTextField, lastNameTextField;
	JFormattedTextField createPinTextField, verifyPinTextField;
	
	public UserCreationCard(JPanel panel) {
		cardHolderPanel = panel;
		cardHolderPanel.add(createUserCreationCard(), "Create User");
	}
	
	//Creates the GUI for the create user card.
	private JPanel createUserCreationCard() {
		JPanel userCreationCard = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		//The create user label font is enlarged as it is used as the title of the panel
		JLabel createUserLabel = new JLabel("Create a User");
		Font labelFont = createUserLabel.getFont();
		createUserLabel.setFont(new Font(labelFont.getFontName(), Font.PLAIN, 20));
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(0, 0, 20, 0);
		userCreationCard.add(createUserLabel, constraints);
		
		JLabel firstNameLabel = new JLabel("First Name: ");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, 0, 20, 5);
		constraints.gridwidth = 1;
		userCreationCard.add(firstNameLabel, constraints);

		firstNameTextField = new JTextField(20);
		firstNameTextField.setMaximumSize(new Dimension(200, 20));
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.ipadx = 0;
		constraints.insets = new Insets(0, 0, 20, 0);
		userCreationCard.add(firstNameTextField, constraints);
		
		JLabel lastNameLabel = new JLabel("Last Name: ");
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(0, 0, 20, 5);
		userCreationCard.add(lastNameLabel, constraints);
		
		lastNameTextField = new JTextField(20);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(0, 0, 20, 0);
		constraints.weightx = 0.5;
		userCreationCard.add(lastNameTextField, constraints);
		
		JLabel createPinLabel = new JLabel("Enter 4-digit PIN: ");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridwidth = 2;
		constraints.weightx = 0.0;
		userCreationCard.add(createPinLabel, constraints);
		
		createPinTextField = createPinTextField();
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.insets = new Insets(0, 0, 10, 0);
		constraints.ipadx = 35;
		userCreationCard.add(createPinTextField, constraints);
		
		JLabel verifyPinLabel = new JLabel("Enter PIN Again: ");
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(0, 0, 0, 0);
		userCreationCard.add(verifyPinLabel, constraints);
		
		verifyPinTextField = createPinTextField();
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.ipadx = 35;
		userCreationCard.add(verifyPinTextField, constraints);
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand("CreateUserSubmitButton");
		submitButton.addActionListener(this);
		bottomPanel.add(submitButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("CancelCreateUser");
		cancelButton.addActionListener(this);
		bottomPanel.add(cancelButton);
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.ipadx = 0;
		constraints.weighty = 1.0;
		userCreationCard.add(bottomPanel, constraints);
		
		return userCreationCard;
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
	
	//Switch to a card in the card holder panel based on what button is pressed.
	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout)(cardHolderPanel.getLayout());
		
		if ("CreateUserSubmitButton".equals(e.getActionCommand())) {
			/* Verify the createPin and verifyPin text fields match.
			 * Check that the createPin text field doesn't equal the placeholder characters.
			 * Check that first and last name fields are not empty.
			 * Create user or show error
			 */
			if ((createPinTextField.getText().equals(verifyPinTextField.getText()) && !createPinTextField.getText().equals("####"))
					&& !firstNameTextField.getText().equals("") && !lastNameTextField.getText().equals("")) {
				
				User newUser = new User();
				newUser.setFirstName(firstNameTextField.getText());
				newUser.setLastName(lastNameTextField.getText());
				newUser.setPin(Integer.parseInt(createPinTextField.getText()));
				ATMApplication.atm.addUser(newUser);
				
				JOptionPane.showMessageDialog(null, "User created.", "Success", JOptionPane.INFORMATION_MESSAGE);
				cl.show(cardHolderPanel, "Main Menu");
				firstNameTextField.setText("");
				lastNameTextField.setText("");
				createPinTextField.setValue(null);
				verifyPinTextField.setValue(null);				
			} else {
				JOptionPane.showMessageDialog(null, "Please check the input data.", "Error", JOptionPane.ERROR_MESSAGE);
				createPinTextField.setValue(null);
				verifyPinTextField.setValue(null);
			}
		} else if ("CancelCreateUser".equals(e.getActionCommand())) {
			cl.show(cardHolderPanel, "Main Menu");
			firstNameTextField.setText("");
			lastNameTextField.setText("");
			createPinTextField.setValue(null);
			verifyPinTextField.setValue(null);
		}
	}
}
