package version3;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

public class SavingsAccountCard extends JPanel implements ActionListener {
	JPanel cardHolderPanel;
	static JLabel savingsAccountBalanceLabel, availableAccountBalanceLabel;
	JFormattedTextField depositIntoSavingsTextField, withdrawFromSavingsTextField;
	
	public SavingsAccountCard(JPanel panel) {
		cardHolderPanel = panel;
		cardHolderPanel.add(createSavingsAccountCard(), "Savings Account");
	}
	
	//Creates GUI for savings account card.
	private JPanel createSavingsAccountCard() {
		JPanel savingsAccountCard = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		//The savings account label font is enlarged as it is used as the title of the panel.
		JLabel savingsAccountLabel = new JLabel("Savings Account");
		Font labelFont = savingsAccountLabel.getFont();
		savingsAccountLabel.setFont(new Font(labelFont.getFontName(), Font.PLAIN, 20));
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(0, 0, 20, 0);
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.weightx = 0.5;
		savingsAccountCard.add(savingsAccountLabel, constraints);
		
		JLabel accountBalanceLabel = new JLabel("Balance: ");
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(0, 20, 0, 0);
		constraints.anchor = GridBagConstraints.LINE_START;
		savingsAccountCard.add(accountBalanceLabel, constraints);
		
		savingsAccountBalanceLabel = new JLabel("$" + String.format("%.2f", ATMApplication.currentUser.getSavingsAccount().getBalance()));
		savingsAccountBalanceLabel.setFocusable(true);
		savingsAccountBalanceLabel.grabFocus();
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, -60, 0, 0);
		savingsAccountCard.add(savingsAccountBalanceLabel, constraints);
		
		JLabel availableAccountBalanceLabelLabel = new JLabel("Available Balance: ");
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(0, 20, 20, 0);
		constraints.anchor = GridBagConstraints.LINE_START;
		savingsAccountCard.add(availableAccountBalanceLabelLabel, constraints);
		
		availableAccountBalanceLabel = new JLabel("$" + String.format("%.2f", ATMApplication.currentUser.getSavingsAccount().getAvailableBalance()));
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(0, -10, 20, 0);
		savingsAccountCard.add(availableAccountBalanceLabel, constraints);
		
		//Set format for the deposit and withdraw text field.
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
		format.setMaximumFractionDigits(2);
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setMinimum(0.0);
		formatter.setOverwriteMode(false);
		depositIntoSavingsTextField = new JFormattedTextField(formatter);
		depositIntoSavingsTextField.setValue((double)0.00);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.ipadx = 50;
		constraints.weightx = 0.5;
		constraints.insets = new Insets(20, 20, 20, 0);
		savingsAccountCard.add(depositIntoSavingsTextField, constraints);
		
		JButton depositButton = new JButton("Deposit");
		depositButton.setActionCommand("DepositIntoSavings");
		depositButton.addActionListener(this);
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.ipadx = 0;
		constraints.insets = new Insets(0, 20, 20, 0);
		savingsAccountCard.add(depositButton, constraints);
		
		withdrawFromSavingsTextField = new JFormattedTextField(formatter);
		withdrawFromSavingsTextField.setValue((double)0.00);
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.ipadx = 50;
		constraints.insets = new Insets(20, 20, 20, 0);
		savingsAccountCard.add(withdrawFromSavingsTextField, constraints);
		
		JButton withdrawButton = new JButton("Withdraw");
		withdrawButton.setActionCommand("WithdrawFromSavings");
		withdrawButton.addActionListener(this);
		constraints.gridx = 1;
		constraints.gridy = 4;
		constraints.ipadx = 0;
		constraints.insets = new Insets(0, 20, 20, 0);
		savingsAccountCard.add(withdrawButton, constraints);
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton backButton = new JButton("Back");
		backButton.setActionCommand("GoBackToAccountMenu");
		backButton.addActionListener(this);
		bottomPanel.add(backButton);
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 2;
		constraints.weighty = 1.0;
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		savingsAccountCard.add(bottomPanel, constraints);
		
		return savingsAccountCard;
	}
	
	public static void updateSavingsAccountBalanceLabels(Double savingsAccountBalance, Double availableAccountBalance) {
		savingsAccountBalanceLabel.setText("$" + String.format("%.2f", savingsAccountBalance));
		availableAccountBalanceLabel.setText("$" + String.format("%.2f", availableAccountBalance));
	}
	
	//Switch to a card in the card holder panel based on what button is pressed.
	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout)(cardHolderPanel.getLayout());
		
		if ("DepositIntoSavings".equals(e.getActionCommand())) {
			try {
				Double amount = cleanAndConvertAmountString(depositIntoSavingsTextField.getText().substring(1));
				
				ATMApplication.currentUser.getSavingsAccount().deposit(amount);
				savingsAccountBalanceLabel.setText("$" + String.format("%.2f", ATMApplication.currentUser.getSavingsAccount().getBalance()));
				availableAccountBalanceLabel.setText("$" + String.format("%.2f", ATMApplication.currentUser.getSavingsAccount().getAvailableBalance()));
				depositIntoSavingsTextField.setValue(0.00);
			} catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if ("WithdrawFromSavings".equals(e.getActionCommand())) {
			try {
				Double amount = cleanAndConvertAmountString(withdrawFromSavingsTextField.getText().substring(1));
				
				ATMApplication.currentUser.getSavingsAccount().withdraw(amount);
				savingsAccountBalanceLabel.setText("$" + String.format("%.2f", ATMApplication.currentUser.getSavingsAccount().getBalance()));
				availableAccountBalanceLabel.setText("$" + String.format("%.2f", ATMApplication.currentUser.getSavingsAccount().getAvailableBalance()));
				withdrawFromSavingsTextField.setValue(0.00);
			} catch(ArithmeticException ex) {
				JOptionPane.showMessageDialog(null, "Not enough available funds.", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
		} else if("GoBackToAccountMenu".equals(e.getActionCommand())) {
			cl.show(cardHolderPanel, "Account Menu");
			depositIntoSavingsTextField.setValue(0.00);
			withdrawFromSavingsTextField.setValue(0.00);
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
	
	
}
