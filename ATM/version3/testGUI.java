package version3;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton.ToggleButtonModel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Enumeration;
import java.util.Locale;

import javax.swing.*;

public class testGUI extends JFrame implements ActionListener, FocusListener {
	JPanel cardLayoutPanel = new JPanel(new CardLayout());
	final String CardName = "AccountMenu";
	Atm atm = new Atm();
	User currentUser;
	JFormattedTextField depositIntoSavingsTextField, withdrawFromSavingsTextField, transferTextField;
	JLabel accountBalance, availableAccountBalance;
	
	JRadioButton transferToChecking, transferToSavings, transferFromChecking, transferFromSavings;
	ButtonGroup transferToGroup, transferFromGroup;
	
	public testGUI() {
		setSize(300, 300);
		setTitle("ATM");
		//setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		createTestUser();
		currentUser.getSavingsAccount().deposit(100.00);
		currentUser.getCheckingAccount().deposit(100.00);

		JPanel transferCard = new JPanel(new GridLayout(9, 0, 0, -5));
		
		JLabel transferLabel = new JLabel("Transfer", SwingConstants.CENTER);
		transferCard.add(transferLabel);
		
		JLabel transferToLabel = new JLabel("Transfer To:");
		transferCard.add(transferToLabel);
		
		JMenu transferToMenu = new JMenu();
		transferToGroup = new ButtonGroup();
		
		JRadioButton transferToChecking = new JRadioButton("Checking");
		transferToGroup.add(transferToChecking);
		transferToMenu.add(transferToChecking);
		transferCard.add(transferToChecking);
		
		JRadioButton transferToSavings = new JRadioButton("Savings");
		transferToGroup.add(transferToSavings);
		transferToMenu.add(transferToSavings);
		transferCard.add(transferToSavings);
		
		JLabel transferFromLabel = new JLabel("Transfer From:");
		transferCard.add(transferFromLabel);
		
		JMenu transferFromMenu = new JMenu();
		transferFromGroup = new ButtonGroup();
		
		JRadioButton transferFromChecking = new JRadioButton("Checking");
		transferFromGroup.add(transferFromChecking);
		transferFromMenu.add(transferFromChecking);
		transferCard.add(transferFromChecking);
		
		JRadioButton transferFromSavings = new JRadioButton("Savings");
		transferFromGroup.add(transferFromSavings);
		transferFromMenu.add(transferFromSavings);
		transferCard.add(transferFromSavings);
		
		JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel amountLabel = new JLabel("Amount:");
		amountPanel.add(amountLabel);
		
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
		format.setMaximumFractionDigits(2);
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setMinimum(0.0);
		formatter.setOverwriteMode(false);
		transferTextField = new JFormattedTextField(formatter);
		transferTextField.setColumns(10);
		transferTextField.setValue((double)0.00);
		amountPanel.add(transferTextField);
		
		transferCard.add(amountPanel);
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand("TransferSubmitButton");
		submitButton.addActionListener(this);
		bottomPanel.add(submitButton);
		
		JButton backButton = new JButton("Back");
		backButton.setActionCommand("GoBackToAccountMenu");
		backButton.addActionListener(this);
		bottomPanel.add(backButton);
		
		transferCard.add(bottomPanel);
		
		cardLayoutPanel.add(transferCard, CardName);
		this.add(cardLayoutPanel);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if ("TransferSubmitButton".equals(e.getActionCommand())) {
			try {
				String selectedTransferToButton = getSelectedRadioButtonText(transferToGroup);
				String selectedTransferFromButton = getSelectedRadioButtonText(transferFromGroup);
				
				String amountString = transferTextField.getText().substring(1);
				String cleanAmountString = "";
				for (int i = 0; i < amountString.length(); i++) {
					if (amountString.charAt(i) != ',') {
						cleanAmountString += amountString.charAt(i);
					}
				}
				Double amount = Double.parseDouble(cleanAmountString);
				
				if(selectedTransferToButton.equals("Checking") && selectedTransferFromButton.equals("Savings")) {
					if(currentUser.getSavingsAccount().getAvailableBalance() >= amount) {
						currentUser.getSavingsAccount().withdraw(amount);
						currentUser.getCheckingAccount().deposit(amount);
						JOptionPane.showMessageDialog(null, "$" + amount + " was successfully transfered from your Savings account to your Checking account.", "Success!", JOptionPane.INFORMATION_MESSAGE);
					} else {
						throw new ArithmeticException();
					}
				} else if(selectedTransferToButton.equals("Savings") && selectedTransferFromButton.equals("Checking")) {
					if(currentUser.getCheckingAccount().getBalance() >= amount) {
						currentUser.getCheckingAccount().withdraw(amount);
						currentUser.getSavingsAccount().deposit(amount);
						JOptionPane.showMessageDialog(null, "$" + amount + " was successfully transfered from your Checking account to your Savings account.", "Success!", JOptionPane.INFORMATION_MESSAGE);
					} else {
						throw new ArithmeticException();
					}
				} else {
					throw new IllegalStateException();
				}
				
				transferTextField.setValue(0.00);
			} catch(NumberFormatException ex) {
				System.out.println(ex);
			} catch(IllegalStateException ex) {
				JOptionPane.showMessageDialog(null, "Same type of account are selected. Please select different type of accounts.", "Error", JOptionPane.ERROR_MESSAGE);
			} catch(ArithmeticException ex) {
				JOptionPane.showMessageDialog(null, "Not enough funds in the account to transfer.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void focusGained(FocusEvent e) {
		if (e.getSource().equals(depositIntoSavingsTextField) && e.getOppositeComponent().equals(withdrawFromSavingsTextField)) {
			withdrawFromSavingsTextField.setValue(0.00);
		}
	}
	
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(depositIntoSavingsTextField) && e.getOppositeComponent().equals(withdrawFromSavingsTextField)) {
			depositIntoSavingsTextField.setValue(0.00);
		}
	}
	
	public void createTestUser() {
		User newUser = new User();
		newUser.setFirstName("Andrew");
		newUser.setLastName("Miller");
		newUser.setPin(1234);
		atm.addUser(newUser);
		currentUser = newUser;
	}
	
	public String getSelectedRadioButtonText(ButtonGroup group) {
		for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			
			if(button.isSelected()) {
				return button.getText();
			}
		}
		
		return null;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				testGUI test = new testGUI();
				test.setVisible(true);
			}
		});
	}

}
