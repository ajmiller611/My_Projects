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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AccountBalanceCard extends JPanel implements ActionListener {
	JPanel cardHolderPanel;
	static JLabel checkingAccountBalanceLabel, savingsAccountBalanceLabel, availableBalanceLabel;
	
	public AccountBalanceCard(JPanel panel) {
		cardHolderPanel = panel;
		cardHolderPanel.add(createAccountBalanceCard(), "Account Balance");
	}
	
	//Creates GUI for account balance card.
	private JPanel createAccountBalanceCard() {
		JPanel accountBalanceCard = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		//The account balance label font is enlarged as it is used as the title of the panel.
		JLabel accountBalanceLabel = new JLabel("Account Balances");
		Font labelFont = accountBalanceLabel.getFont();
		accountBalanceLabel.setFont(new Font(labelFont.getFontName(), Font.PLAIN, 20));
		constraints.fill = GridBagConstraints.CENTER;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(0, 0, 15, 0);
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.weightx = 0.5;
		accountBalanceCard.add(accountBalanceLabel, constraints);
		
		JPanel checkingAccountPanel = new JPanel(new GridBagLayout());
		Dimension size = new Dimension(260, 50);
		checkingAccountPanel.setMaximumSize(size);
		checkingAccountPanel.setMinimumSize(size);
		checkingAccountPanel.setPreferredSize(size);
		
		checkingAccountPanel.setBorder(BorderFactory.createTitledBorder("Checking"));
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, 0, 20, 0);
		constraints.anchor = GridBagConstraints.CENTER;
		accountBalanceCard.add(checkingAccountPanel, constraints);
		
		JLabel checkingAccountLabel = new JLabel("Balance:");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.anchor = GridBagConstraints.LINE_START;
		checkingAccountPanel.add(checkingAccountLabel, constraints);
		
		checkingAccountBalanceLabel = new JLabel("$" + String.format("%.2f", ATMApplication.currentUser.getCheckingAccount().getBalance()));
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, -65, 0, 0);
		checkingAccountPanel.add(checkingAccountBalanceLabel, constraints);
		
		
		JPanel savingsAccountPanel = new JPanel(new GridBagLayout());
		size = new Dimension(260, 100);
		savingsAccountPanel.setMaximumSize(size);
		savingsAccountPanel.setMinimumSize(size);
		savingsAccountPanel.setPreferredSize(size);
		savingsAccountPanel.setBorder(BorderFactory.createTitledBorder("Savings"));
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.anchor = GridBagConstraints.CENTER;
		accountBalanceCard.add(savingsAccountPanel, constraints);
		
		JLabel savingsAccountLabel = new JLabel("Total Balance:");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(3, 0, 10, 0);
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		savingsAccountPanel.add(savingsAccountLabel, constraints);
		
		savingsAccountBalanceLabel = new JLabel("$" + String.format("%.2f", ATMApplication.currentUser.getSavingsAccount().getBalance()));
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(3, -60, 10, 0);
		savingsAccountPanel.add(savingsAccountBalanceLabel, constraints);
		
		JLabel availableLabel = new JLabel("Available Balance:");
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.anchor = GridBagConstraints.LINE_START;
		savingsAccountPanel.add(availableLabel, constraints);
		
		availableBalanceLabel = new JLabel("$" + String.format("%.2f", ATMApplication.currentUser.getSavingsAccount().getAvailableBalance()));
		constraints.gridx = 1;
		constraints.insets = new Insets(0, -60, 0, 0);
		savingsAccountPanel.add(availableBalanceLabel, constraints);
		
		JPanel panelToGrabExtraVerticalSpace = new JPanel();
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weighty = 1.0;
		savingsAccountPanel.add(panelToGrabExtraVerticalSpace, constraints);
		
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton backButton = new JButton("Back");
		backButton.setActionCommand("GoBackToAccountMenu");
		backButton.addActionListener(this);
		bottomPanel.add(backButton);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.weighty = 1.0;
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		accountBalanceCard.add(bottomPanel, constraints);
		
		return accountBalanceCard;
	}
	
	public static void updateAccountBalances(Double checkingAccountBalance, Double savingsAccountTotalBalance, Double savingsAccountAvailableBalance) {
		checkingAccountBalanceLabel.setText("$" + String.format("%.2f", checkingAccountBalance));
		savingsAccountBalanceLabel.setText("$" + String.format("%.2f", savingsAccountTotalBalance));
		availableBalanceLabel.setText("$" + String.format("%.2f", savingsAccountAvailableBalance));
	}
	
	//Switch to a card in the card holder panel based on what button is pressed.
	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout)(cardHolderPanel.getLayout());
		
		if("GoBackToAccountMenu".equals(e.getActionCommand())) {
			cl.show(cardHolderPanel, "Account Menu");
		}
	}
}
