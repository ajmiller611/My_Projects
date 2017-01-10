package version2;

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
	JPanel cardLayoutPanel;
	CardLayout cl;
	Atm atm;
	JTextField firstNameTextField, lastNameTextField;
	JFormattedTextField createPinTextField, verifyPinTextField;
	
	public UserCreationCard(JPanel panel, CardLayout layout, Atm a) {
		cardLayoutPanel = panel;
		cl = layout;
		atm = a;
	}
	
	public JPanel createUserCreationCard() {
		JPanel createUserCard = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		JLabel createUserLabel = new JLabel("Create a User");
		Font labelFont = createUserLabel.getFont();
		createUserLabel.setFont(new Font(labelFont.getFontName(), Font.PLAIN, 20));
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(0, 0, 20, 0);
		createUserCard.add(createUserLabel, constraints);
		
		JLabel firstNameLabel = new JLabel("First Name: ");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, 0, 20, 5);
		constraints.gridwidth = 1;
		createUserCard.add(firstNameLabel, constraints);

		firstNameTextField = new JTextField(20);
		firstNameTextField.setMaximumSize(new Dimension(200, 20));
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.ipadx = 0;
		constraints.insets = new Insets(0, 0, 20, 0);
		createUserCard.add(firstNameTextField, constraints);
		
		JLabel lastNameLabel = new JLabel("Last Name: ");
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(0, 0, 20, 5);
		createUserCard.add(lastNameLabel, constraints);
		
		lastNameTextField = new JTextField(20);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(0, 0, 20, 0);
		constraints.weightx = 0.5;
		createUserCard.add(lastNameTextField, constraints);
		
		JLabel createPinLabel = new JLabel("Enter 4-digit PIN: ");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridwidth = 2;
		constraints.weightx = 0.0;
		createUserCard.add(createPinLabel, constraints);
		
		createPinTextField = createPinTextField();
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.insets = new Insets(0, 0, 10, 0);
		constraints.ipadx = 35;
		createUserCard.add(createPinTextField, constraints);
		
		JLabel verifyPinLabel = new JLabel("Enter PIN Again: ");
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(0, 0, 0, 0);
		createUserCard.add(verifyPinLabel, constraints);
		
		verifyPinTextField = createPinTextField();
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.ipadx = 35;
		createUserCard.add(verifyPinTextField, constraints);
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand("CreateUserSubmitButton");
		submitButton.addActionListener(this);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("CancelCreateUser");
		cancelButton.addActionListener(this);
		
		bottomPanel.add(submitButton);
		bottomPanel.add(cancelButton);
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.ipadx = 0;
		constraints.weighty = 1.0;
		createUserCard.add(bottomPanel, constraints);
		
		return createUserCard;
	}
	
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
	
	public void actionPerformed(ActionEvent e) {
		if ("CreateUserSubmitButton".equals(e.getActionCommand())) {
			if ((createPinTextField.getText().equals(verifyPinTextField.getText()) && !createPinTextField.getText().equals("####")) && !firstNameTextField.getText().equals("") && !lastNameTextField.getText().equals("")) {
				User newUser = new User();
				newUser.setFirstName(firstNameTextField.getText());
				newUser.setLastName(lastNameTextField.getText());
				newUser.setPin(Integer.parseInt(createPinTextField.getText()));
				atm.addUser(newUser);
				
				JOptionPane.showMessageDialog(null, "User created.", "Success", JOptionPane.INFORMATION_MESSAGE);
				cl.show(cardLayoutPanel, "Main Menu");
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
			cl.show(cardLayoutPanel, "Main Menu");
			firstNameTextField.setText("");
			lastNameTextField.setText("");
			createPinTextField.setValue(null);
			verifyPinTextField.setValue(null);
		}
	}
}
