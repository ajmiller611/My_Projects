package version3;

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
	JPanel cardHolderPanel;
	static JLabel welcomeLabel;
	
	public AccountMenuCard(JPanel panel) {
		cardHolderPanel = panel;
		cardHolderPanel.add(createAccountMenuCard(), "Account Menu");
	}

	//Creates GUI for account menu card.
	private JPanel createAccountMenuCard() {
		JPanel accountMenuCard = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel();
		
		//The welcome label font is enlarged as it is used as the title of the panel.
		welcomeLabel = new JLabel ("Welcome " + ATMApplication.currentUser.getFirstName() + "!");
		Font labelFont = welcomeLabel.getFont();
		welcomeLabel.setFont(new Font(labelFont.getFontName(), Font.PLAIN, 20));
		topPanel.add(welcomeLabel);
		accountMenuCard.add(topPanel, BorderLayout.NORTH);
		
		GridLayout centerPanelLayout = new GridLayout(2, 2, 30, 30);
		JPanel centerPanel = new JPanel(centerPanelLayout);
		
		//Use HTML to style the text on checking account, savings account, account balance, and transfer buttons.
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
	
	public static void updateWelcomeLabelWithUserName(String userName) {
		welcomeLabel.setText("Welcome " + userName + "!");
	}
	
	//Switch to a card in the card holder panel based on what button is pressed.
	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout)(cardHolderPanel.getLayout());
		
		if ("CheckingAccount".equals(e.getActionCommand())) {
			CheckingAccountCard.updateCheckingAccountBalanceLabel(ATMApplication.currentUser.getCheckingAccount().getBalance());
			cl.show(cardHolderPanel, "Checking Account");
		} else if ("SavingsAccount".equals(e.getActionCommand())) {
			SavingsAccountCard.updateSavingsAccountBalanceLabels(ATMApplication.currentUser.getSavingsAccount().getBalance(), 
					ATMApplication.currentUser.getSavingsAccount().getAvailableBalance());
			cl.show(cardHolderPanel, "Savings Account");
		} else if ("AccountBalance".equals(e.getActionCommand())) {
			AccountBalanceCard.updateAccountBalances(ATMApplication.currentUser.getCheckingAccount().getBalance(),
					ATMApplication.currentUser.getSavingsAccount().getBalance(), ATMApplication.currentUser.getSavingsAccount().getAvailableBalance());
			cl.show(cardHolderPanel, "Account Balance");
		} else if ("Transfers".equals(e.getActionCommand())) {
			cl.show(cardHolderPanel, "Transfer");
		} else if ("GoToMainMenu".equals(e.getActionCommand())) {
			LoginCard.enterPinTextBox.setValue(null);
			cl.first(cardHolderPanel);
		}
	}
}
