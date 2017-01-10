package version3;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.Locale;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

public class TransferCard extends JPanel implements ActionListener {
	JPanel cardHolderPanel;
	ButtonGroup transferToGroup, transferFromGroup;
	JFormattedTextField transferTextField;
	
	public TransferCard(JPanel panel) {
		cardHolderPanel = panel;
		cardHolderPanel.add(createTransferCard(), "Transfer");
	}
	
	//Creates GUI for transfer card.
	private JPanel createTransferCard() {
		JPanel transferCard = new JPanel(new GridLayout(9, 0, 0, -5));
		
		//The transfer label font is enlarged as it is used as the title of the panel.
		JLabel transferLabel = new JLabel("Transfer", SwingConstants.CENTER);
		Font labelFont = transferLabel.getFont();
		transferLabel.setFont(new Font(labelFont.getFontName(), Font.PLAIN, 20));
		transferCard.add(transferLabel);
		
		JLabel transferToLabel = new JLabel("Transfer To:");
		transferCard.add(transferToLabel);
		
		//Creates a JMenu to hold the button group to allow the selection of checking or savings radio buttons.
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
		
		//Creates a JMenu to hold the button group to allow the selection of checking or savings radio buttons.
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
		
		//Set format for the transfer text field.
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
		
		return transferCard;
	}
	
	//Switch to a card in the card holder panel based on what button is pressed.
	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout)(cardHolderPanel.getLayout());
		
		if ("TransferSubmitButton".equals(e.getActionCommand())) {
			try {
				Double amount = cleanAndConvertAmountString(transferTextField.getText().substring(1));
				
				String selectedTransferToButton = getSelectedRadioButtonText(transferToGroup);
				String selectedTransferFromButton = getSelectedRadioButtonText(transferFromGroup);
				
				if (selectedTransferToButton == null || selectedTransferFromButton == null || (selectedTransferToButton == null && selectedTransferFromButton == null)) {
					throw new NullPointerException();
				} else if(selectedTransferToButton.equals("Checking") && selectedTransferFromButton.equals("Savings")) {
					if(ATMApplication.currentUser.getSavingsAccount().getAvailableBalance() >= amount) {
						ATMApplication.currentUser.getSavingsAccount().withdraw(amount);
						ATMApplication.currentUser.getCheckingAccount().deposit(amount);
						JOptionPane.showMessageDialog(null, "$" + String.format("%.2f", amount) + " was successfully transfered from your Savings account to your Checking account.", "Success!", JOptionPane.INFORMATION_MESSAGE);
					} else {
						throw new ArithmeticException();
					}
				} else if(selectedTransferToButton.equals("Savings") && selectedTransferFromButton.equals("Checking")) {
					if(ATMApplication.currentUser.getCheckingAccount().getBalance() >= amount) {
						ATMApplication.currentUser.getCheckingAccount().withdraw(amount);
						ATMApplication.currentUser.getSavingsAccount().deposit(amount);
						JOptionPane.showMessageDialog(null, "$" + String.format("%.2f",amount) + " was successfully transfered from your Checking account to your Savings account.", "Success!", JOptionPane.INFORMATION_MESSAGE);
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
			} catch(NullPointerException ex) {
				JOptionPane.showMessageDialog(null, "Please make sure an account is selected for both Transfer To and Transfer From sections.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if("GoBackToAccountMenu".equals(e.getActionCommand())) {
			cl.show(cardHolderPanel, "Account Menu");
			transferToGroup.clearSelection();
			transferFromGroup.clearSelection();
		}
	}
	
	private Double cleanAndConvertAmountString(String amountString) {
		String cleanAmountString = "";
		for (int i = 0; i < amountString.length(); i++) {
			if (amountString.charAt(i) != ',') {
				cleanAmountString += amountString.charAt(i);
			}
		}
		Double amount = Double.parseDouble(cleanAmountString);
		return amount;
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
}
