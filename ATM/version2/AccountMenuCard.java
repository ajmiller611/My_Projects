package version2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AccountMenuCard extends JPanel implements ActionListener {
	JPanel cardLayoutPanel;
	CardLayout cl;
	User currentUser;
	static JLabel welcomeLabel;
	
	public AccountMenuCard(JPanel panel, CardLayout layout, User user) {
		cardLayoutPanel = panel;
		cl = layout;
		currentUser = user;
	}
	
	public JPanel createAccountMenuCard() {
		JPanel accountMenuCard = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel();
		
		welcomeLabel = new JLabel ("Welcome " + currentUser.getFirstName() + "!");
		Font labelFont = welcomeLabel.getFont();
		welcomeLabel.setFont(new Font(labelFont.getFontName(), Font.PLAIN, 20));
		topPanel.add(welcomeLabel);
		accountMenuCard.add(topPanel, BorderLayout.NORTH);
		
		GridLayout centerPanelLayout = new GridLayout(2, 2, 30, 30);
		JPanel centerPanel = new JPanel(centerPanelLayout);
		String checkingAccountButtonText = "<html>"
				+ "<body>"
				+ "<p style='font-size:20pt'>Checking</p>"
				+ "<p style='font-size:20pt'>Account</p>"
				+ "</body>"
				+ "</html>";
		JButton checkingAccountButton = new JButton(checkingAccountButtonText);
		checkingAccountButton.setActionCommand("CheckingAccount");
		checkingAccountButton.addActionListener(this);
		centerPanel.add(checkingAccountButton);
		
		String savingsAccountButtonText = "<html>"
				+ "<body>"
				+ "<p style='font-size:20pt'>Savings</p>"
				+ "<p style='font-size:20pt'>Account</p>"
				+ "</body>"
				+ "</html>";
		JButton savingsAccountButton = new JButton(savingsAccountButtonText);
		savingsAccountButton.setActionCommand("SavingsAccount");
		savingsAccountButton.addActionListener(this);
		centerPanel.add(savingsAccountButton);
		
		String accountBalancesButtonText = "<html>"
				+ "<body>"
				+ "<p style='font-size:20pt'>Account</p>"
				+ "<p style='font-size:20pt'>Balance</p>"
				+ "</body>"
				+ "</html>";
		JButton accountBalancesButton = new JButton(accountBalancesButtonText);
		accountBalancesButton.setActionCommand("AccountBalance");
		accountBalancesButton.addActionListener(this);
		centerPanel.add(accountBalancesButton);
		
		String transfersButtonText = "<html>"
				+ "<body>"
				+ "<p style='font-size:20pt'>Transfers</p>"
				+ "</body>"
				+ "</html>";
		JButton transferButton = new JButton(transfersButtonText);
		transferButton.setActionCommand("Transfers");
		transferButton.addActionListener(this);
		centerPanel.add(transferButton);
		
		accountMenuCard.add(centerPanel, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton backButton = new JButton("Back");
		backButton.setActionCommand("GoToMainMenu");
		backButton.addActionListener(this);
		bottomPanel.add(backButton);
		accountMenuCard.add(bottomPanel, BorderLayout.SOUTH);
		
		return accountMenuCard;
	}
	
	public void actionPerformed(ActionEvent e) {		
		if ("CheckingAccount".equals(e.getActionCommand())) {
			CheckingAccountCard.updateCheckingAccountBalanceLabel(currentUser.getCheckingAccount().getBalance());
			cl.show(cardLayoutPanel, "Checking Account");
		} else if ("SavingsAccount".equals(e.getActionCommand())) {
			SavingsAccountCard.updateSavingsAccountBalanceLabels(currentUser.getSavingsAccount().getBalance(), currentUser.getSavingsAccount().getAvailableBalance());
			cl.show(cardLayoutPanel, "Savings Account");
		} else if ("AccountBalance".equals(e.getActionCommand())) {
			AccountBalanceCard.updateAccountBalancesLabels(currentUser.getCheckingAccount().getBalance(),
					currentUser.getSavingsAccount().getBalance(), currentUser.getSavingsAccount().getAvailableBalance());
			cl.show(cardLayoutPanel, "Account Balance");
		} else if ("Transfers".equals(e.getActionCommand())) {
			cl.show(cardLayoutPanel, "Transfer");
		} else if ("GoToMainMenu".equals(e.getActionCommand())) {
			LoginCard.enterPinTextBox.setValue(null);
			cl.first(cardLayoutPanel);
		}
	}
}
