package version2;

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

public class CheckingAccountCard extends JPanel implements ActionListener{
	JPanel cardLayoutPanel;
	CardLayout cl;
	User currentUser;
	static JLabel checkingAccountBalanceLabel;
	JFormattedTextField depositIntoCheckingTextField, withdrawFromCheckingTextField;

	public CheckingAccountCard(JPanel panel, CardLayout layout, User user) {
		cardLayoutPanel = panel;
		cl = layout;
		currentUser = user;
	}
	
	public JPanel createCheckingAccountCard() {
		JPanel checkingAccountCard = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		JLabel checkingAccountLabel = new JLabel("Checking Account");
		Font labelFont = checkingAccountLabel.getFont();
		checkingAccountLabel.setFont(new Font(labelFont.getFontName(), Font.PLAIN, 20));
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(0, 0, 20, 0);
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.weightx = 0.5;
		checkingAccountCard.add(checkingAccountLabel, constraints);
		
		JLabel accountBalanceLabel = new JLabel("Balance: ");
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(0, 20, 20, 0);
		constraints.anchor = GridBagConstraints.LINE_START;
		checkingAccountCard.add(accountBalanceLabel, constraints);
		
		checkingAccountBalanceLabel = new JLabel("$" + currentUser.getCheckingAccount().getBalance());
		checkingAccountBalanceLabel.setFocusable(true);
		checkingAccountBalanceLabel.grabFocus();
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, -60, 20, 0);
		checkingAccountCard.add(checkingAccountBalanceLabel, constraints);
		
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
		format.setMaximumFractionDigits(2);
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setMinimum(0.0);
		formatter.setOverwriteMode(false);
		depositIntoCheckingTextField = new JFormattedTextField(formatter);
		depositIntoCheckingTextField.setValue((double)0.00);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.ipadx = 50;
		constraints.weightx = 0.5;
		constraints.insets = new Insets(20, 20, 20, 0);
		checkingAccountCard.add(depositIntoCheckingTextField, constraints);
		
		JButton depositButton = new JButton("Deposit");
		depositButton.setActionCommand("DepositIntoChecking");
		depositButton.addActionListener(this);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.ipadx = 0;
		constraints.insets = new Insets(0, 20, 20, 0);
		checkingAccountCard.add(depositButton, constraints);
		
		withdrawFromCheckingTextField = new JFormattedTextField(formatter);
		withdrawFromCheckingTextField.setValue((double)0.00);
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.ipadx = 50;
		constraints.insets = new Insets(20, 20, 20, 0);
		checkingAccountCard.add(withdrawFromCheckingTextField, constraints);
		
		JButton withdrawButton = new JButton("Withdraw");
		withdrawButton.setActionCommand("WithdrawFromChecking");
		withdrawButton.addActionListener(this);
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.ipadx = 0;
		constraints.insets = new Insets(0, 20, 20, 0);
		checkingAccountCard.add(withdrawButton, constraints);
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton backButton = new JButton("Back");
		backButton.setActionCommand("GoBackToAccountMenu");
		backButton.addActionListener(this);
		bottomPanel.add(backButton);
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		constraints.weighty = 1.0;
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		checkingAccountCard.add(bottomPanel, constraints);
		
		return checkingAccountCard;
	}
	
	public static void updateCheckingAccountBalanceLabel(Double checkingAccountBalance) {
		checkingAccountBalanceLabel.setText("$" + checkingAccountBalance.toString());
	}
	
	public void actionPerformed(ActionEvent e) {
		if ("DepositIntoChecking".equals(e.getActionCommand())) {
			try {
				Double amount = cleanAndConvertAmountString(depositIntoCheckingTextField.getText().substring(1));
				
				currentUser.getCheckingAccount().deposit(amount);
				checkingAccountBalanceLabel.setText("$" + currentUser.getCheckingAccount().getBalance());
				depositIntoCheckingTextField.setValue(0.00);
			} catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if("WithdrawFromChecking".equals(e.getActionCommand())) {
			try {
				Double amount = cleanAndConvertAmountString(withdrawFromCheckingTextField.getText().substring(1));
				
				currentUser.getCheckingAccount().withdraw(amount);
				checkingAccountBalanceLabel.setText("$" + currentUser.getCheckingAccount().getBalance());
				withdrawFromCheckingTextField.setValue(0.00);
			} catch(ArithmeticException ex) {
				JOptionPane.showMessageDialog(null, "Not enough available funds.", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
		} else if("GoBackToAccountMenu".equals(e.getActionCommand())) {
			cl.show(cardLayoutPanel, "Account Menu");
			depositIntoCheckingTextField.setValue(0.00);
			withdrawFromCheckingTextField.setValue(0.00);
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
