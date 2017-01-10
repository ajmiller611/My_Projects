package version1;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

public class AtmGui extends JFrame implements ActionListener, WindowListener {	
	private static final long serialVersionUID = 1L;
	JPanel cardLayoutPanel = new JPanel(new CardLayout());
	final String mainMenuCardName = "Main Menu";
	final String loginCardName = "Login";
	final String createUserCardName = "Create User";
	final String accountMenuCardName = "Account Menu";
	final String checkingAccountCardName = "Checking Account";
	final String savingsAccountCardName = "Savings Account";
	final String accountBalanceCardName = "Account Balance";
	final String transferCardName = "Transfer";
	JFormattedTextField enterPinTextBox, createPinTextField, verifyPinTextField, depositIntoCheckingTextField, withdrawFromCheckingTextField;
	JFormattedTextField depositIntoSavingsTextField, withdrawFromSavingsTextField, transferTextField;
	JTextField firstNameTextField, lastNameTextField;
	JLabel welcomeLabel,  checkingAccountBalance, savingsAccountBalance, availableAccountBalance;
	JLabel checkingAccountBalanceLabel, savingsAccountBalanceLabel, availableBalanceLabel;
	ButtonGroup transferToGroup, transferFromGroup;
	Atm atm = new Atm();
	User currentUser;
	
	public AtmGui() {
		loadDataForAccountsFromFile();
		initialUI();
	}
	
	private void loadDataForAccountsFromFile() {
		boolean exists = (new File("./DATAFILE")).exists(); 
	    if (exists) 
	    { 
	         JOptionPane.showMessageDialog(null,"good"); 
	         try 
	         { 
	             FileInputStream filein = new FileInputStream("./DATAFILE"); 
	             ObjectInputStream oin = new ObjectInputStream(filein); 
	             //for(int i = 0; i < atm.getListSize(); i++) {
	            	 atm =(Atm) oin.readObject(); 
	             //}
	             oin.close(); 
	         } 
	         catch(IOException e) {JOptionPane.showMessageDialog(null,e); }
	 
	         catch(ClassNotFoundException ff) { JOptionPane.showMessageDialog(null,"Sorry");} 
	    } 
	    else 
	    {
	        JOptionPane.showMessageDialog(null,"no file"); 
	    }
	}
	
	private void initialUI() {
		setSize(300, 300);
		setTitle("ATM");
		setResizable(false);
		addWindowListener(this);
		
		cardLayoutPanel.add(createMainMenuCard(), mainMenuCardName);
		
		cardLayoutPanel.add(createLoginCard(), loginCardName);
		
		cardLayoutPanel.add(createUserCreationCard(), createUserCardName);
		
		cardLayoutPanel.add(createAccountMenuCard(), accountMenuCardName);
		
		cardLayoutPanel.add(createCheckingAccountCard(), checkingAccountCardName);
		
		cardLayoutPanel.add(createSavingsAccountCard(), savingsAccountCardName);
		
		cardLayoutPanel.add(createAccountBalanceCard(), accountBalanceCardName);
		
		cardLayoutPanel.add(createTransferCard(), transferCardName);
		
		this.add(cardLayoutPanel);
	}
	
	private JPanel createMainMenuCard() {
		JPanel mainMenuCard = new JPanel(new BorderLayout());		
		
		JPanel topPanelMenu = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		JLabel welcomeLabel = new JLabel("Welcome to ATM");
		Font labelFont = welcomeLabel.getFont();
		welcomeLabel.setFont(new Font(labelFont.getFontName(), Font.PLAIN, 20));
		welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
		topPanelMenu.add(welcomeLabel);
		mainMenuCard.add(topPanelMenu, BorderLayout.NORTH);
		
		
		buttonPanel.add(Box.createHorizontalGlue());
		
		String enterPinButtonText = "<html>"
				+ "<body>"
				+ "<p style='font-size:16pt'>Login</p>"
				+ "<p style='font-size:16pt'>with</p>"
				+ "<p style='font-size:16pt'>PIN</p>"
				+ "</body>"
				+ "</html>";
		
		JButton enterPinButton = new JButton(enterPinButtonText);
		enterPinButton.setMaximumSize(new Dimension(95, 70));
		enterPinButton.setPreferredSize(new Dimension(95, 70));
		enterPinButton.setActionCommand("PinButton");
		enterPinButton.addActionListener(this);
		buttonPanel.add(enterPinButton);
		
		buttonPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		
		String createUserButtonText = "<html>"
				+ "<body>"
				+ "<p style='font-size:16pt'>Create</p>"
				+ "<p style='font-size:16pt'>User</p>"
				+ "</body>"
				+ "</html>";
		
		JButton createUserButton = new JButton(createUserButtonText);
		createUserButton.setMaximumSize(new Dimension(95, 70));
		createUserButton.setPreferredSize(new Dimension(95, 70));
		createUserButton.setActionCommand("CreateUser");
		createUserButton.addActionListener(this);
		buttonPanel.add(createUserButton);
		
		buttonPanel.add(Box.createHorizontalGlue());
		
		mainMenuCard.add(buttonPanel, BorderLayout.CENTER);
		
		return mainMenuCard;
	}
	
	private JPanel createLoginCard() {
		JPanel loginCard = new JPanel(new BorderLayout(0, 10));
		JPanel topPanelLogin = new JPanel();
		topPanelLogin.setLayout(new BoxLayout(topPanelLogin, BoxLayout.Y_AXIS));
		
		JLabel enterPinLabel = new JLabel("Enter PIN:");
		enterPinLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		enterPinTextBox = createPinTextField();
		enterPinTextBox.setEditable(false);
		enterPinTextBox.setMaximumSize(new Dimension(55, 20));
		
		topPanelLogin.add(enterPinLabel);
		topPanelLogin.add(enterPinTextBox);
		
		loginCard.add(topPanelLogin, BorderLayout.NORTH);
		
		GridLayout gridPanelLayout = new GridLayout(3, 3);
		gridPanelLayout.setHgap(20);
		gridPanelLayout.setVgap(20);
		JPanel buttonGridPanel = new JPanel(gridPanelLayout);
		
		for (int i = 1; i < 10; i++) {
			buttonGridPanel.add(createNumberedButton(i));
		}
		loginCard.add(buttonGridPanel, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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
	
	private JPanel createUserCreationCard() {
		JPanel createUserCard = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		JLabel createUserLabel = new JLabel("Create a User");
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
	
	private JPanel createAccountMenuCard() {
		JPanel accountMenuCard = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel();
		
		welcomeLabel = new JLabel ();
		topPanel.add(welcomeLabel);
		accountMenuCard.add(topPanel, BorderLayout.NORTH);
		
		GridLayout centerPanelLayout = new GridLayout(2, 2, 30, 30);
		JPanel centerPanel = new JPanel(centerPanelLayout);
		String checkingAccountButtonText = "<html>"
				+ "<body>"
				+ "<p style='font-size:16pt'>Checking</p>"
				+ "<p style='font-size:16pt'>Account</p>"
				+ "</body>"
				+ "</html>";
		JButton checkingAccountButton = new JButton(checkingAccountButtonText);
		checkingAccountButton.setActionCommand("CheckingAccount");
		checkingAccountButton.addActionListener(this);
		centerPanel.add(checkingAccountButton);
		String savingsAccountButtonText = "<html>"
				+ "<body>"
				+ "<p style='font-size:16pt'>Savings</p>"
				+ "<p style='font-size:16pt'>Account</p>"
				+ "</body>"
				+ "</html>";
		JButton savingsAccountButton = new JButton(savingsAccountButtonText);
		savingsAccountButton.setActionCommand("SavingsAccount");
		savingsAccountButton.addActionListener(this);
		centerPanel.add(savingsAccountButton);
		String accountBalancesButtonText = "<html>"
				+ "<body>"
				+ "<p style='font-size:16pt'>Account</p>"
				+ "<p style='font-size:16pt'>Balance</p>"
				+ "</body>"
				+ "</html>";
		JButton accountBalancesButton = new JButton(accountBalancesButtonText);
		accountBalancesButton.setActionCommand("AccountBalance");
		accountBalancesButton.addActionListener(this);
		centerPanel.add(accountBalancesButton);
		String transfersButtonText = "<html>"
				+ "<body>"
				+ "<p style='font-size:16pt'>Transfers</p>"
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
	
	private JPanel createCheckingAccountCard() {
		JPanel checkingAccountCard = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		JLabel checkingAccountLabel = new JLabel("Checking Account");
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
		
		checkingAccountBalance = new JLabel(/*"$" + currentUser.getCheckingAccount().getBalance()*/);
		checkingAccountBalance.setFocusable(true);
		checkingAccountBalance.grabFocus();
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, -60, 20, 0);
		checkingAccountCard.add(checkingAccountBalance, constraints);
		
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
	
	private JPanel createSavingsAccountCard() {
		JPanel savingsAccountCard = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		JLabel savingsAccountLabel = new JLabel("Savings Account");
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
		
		savingsAccountBalance = new JLabel(/*"$" + currentUser.getSavingsAccount().getBalance()*/);
		savingsAccountBalance.setFocusable(true);
		savingsAccountBalance.grabFocus();
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, -60, 0, 0);
		savingsAccountCard.add(savingsAccountBalance, constraints);
		
		JLabel availableAccountBalanceLabel = new JLabel("Available Balance: ");
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.insets = new Insets(0, 20, 20, 0);
		constraints.anchor = GridBagConstraints.LINE_START;
		savingsAccountCard.add(availableAccountBalanceLabel, constraints);
		
		availableAccountBalance = new JLabel(/*"$" + currentUser.getSavingsAccount().getAvailableBalance()*/);
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.insets = new Insets(0, -10, 20, 0);
		savingsAccountCard.add(availableAccountBalance, constraints);
		
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
	
	private JPanel createAccountBalanceCard() {
		JPanel accountBalanceCard = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		JLabel accountBalanceLabel = new JLabel("Account Balances");
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
		
		checkingAccountBalanceLabel = new JLabel(/*"$" + currentUser.getCheckingAccount().getBalance()*/);
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
		
		savingsAccountBalanceLabel = new JLabel(/*"$" + currentUser.getSavingsAccount().getBalance()*/);
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
		
		availableBalanceLabel = new JLabel(/*"$" + currentUser.getSavingsAccount().getAvailableBalance()*/);
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
	
	private JPanel createTransferCard() {
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
		
		return transferCard;
	}
	
	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout)(cardLayoutPanel.getLayout());
		if ("PinButton".equals(e.getActionCommand())) {
			cl.show(cardLayoutPanel, loginCardName);
		} else if ("CreateUser".equals(e.getActionCommand())) {
			cl.show(cardLayoutPanel, createUserCardName);
		} else  if ("SubmitPin".equals(e.getActionCommand())) {
			if (!enterPinTextBox.getText().equals("####")) {
				try {
				currentUser = atm.getUserByPin(Integer.parseInt(enterPinTextBox.getText()));
				welcomeLabel.setText("Welcome " + currentUser.getFirstName() + "!");				
				cl.show(cardLayoutPanel, accountMenuCardName);
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
			cl.first(cardLayoutPanel);
		} else if ("CreateUserSubmitButton".equals(e.getActionCommand())) {
			if ((createPinTextField.getText().equals(verifyPinTextField.getText()) && !createPinTextField.getText().equals("####")) && !firstNameTextField.getText().equals("") && !lastNameTextField.getText().equals("")) {
				User newUser = new User();
				newUser.setFirstName(firstNameTextField.getText());
				newUser.setLastName(lastNameTextField.getText());
				newUser.setPin(Integer.parseInt(createPinTextField.getText()));
				atm.addUser(newUser);
				
				JOptionPane.showMessageDialog(null, "User created.", "Success", JOptionPane.INFORMATION_MESSAGE);
				cl.show(cardLayoutPanel, mainMenuCardName);
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
			cl.show(cardLayoutPanel, mainMenuCardName);
			firstNameTextField.setText("");
			lastNameTextField.setText("");
			createPinTextField.setValue(null);
			verifyPinTextField.setValue(null);
		} else if ("CheckingAccount".equals(e.getActionCommand())) {
			cl.show(cardLayoutPanel, checkingAccountCardName);
		} else if ("SavingsAccount".equals(e.getActionCommand())) {
			cl.show(cardLayoutPanel, savingsAccountCardName);
		} else if ("AccountBalance".equals(e.getActionCommand())) {
			checkingAccountBalanceLabel.setText("$" + currentUser.getCheckingAccount().getBalance());
			savingsAccountBalanceLabel.setText("$" + currentUser.getSavingsAccount().getBalance());
			availableBalanceLabel.setText("$" + currentUser.getSavingsAccount().getAvailableBalance());			
			cl.show(cardLayoutPanel, accountBalanceCardName);
		} else if ("Transfers".equals(e.getActionCommand())) {
			cl.show(cardLayoutPanel, transferCardName);
		} else if ("DepositIntoChecking".equals(e.getActionCommand())) {
			try {
				String amountString = depositIntoCheckingTextField.getText().substring(1);
				String cleanAmountString = "";
				for (int i = 0; i < amountString.length(); i++) {
					if (amountString.charAt(i) != ',') {
						cleanAmountString += amountString.charAt(i);
					}
				}
				Double amount = Double.parseDouble(cleanAmountString);
				
				currentUser.getCheckingAccount().deposit(amount);
				checkingAccountBalance.setText("$" + currentUser.getCheckingAccount().getBalance());
				depositIntoCheckingTextField.setValue(0.00);
			} catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if("WithdrawFromChecking".equals(e.getActionCommand())) {
			try {
				String amountString = withdrawFromCheckingTextField.getText().substring(1);
				String cleanAmountString = "";
				for (int i = 0; i < amountString.length(); i++) {
					if (amountString.charAt(i) != ',') {
						cleanAmountString += amountString.charAt(i);
					}
				}
				Double amount = Double.parseDouble(cleanAmountString);
				
				currentUser.getCheckingAccount().withdraw(amount);
				checkingAccountBalance.setText("$" + currentUser.getCheckingAccount().getBalance());
				withdrawFromCheckingTextField.setValue(0.00);
			} catch(ArithmeticException ex) {
				JOptionPane.showMessageDialog(null, "Not enough available funds.", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
		} else if("GoBackToAccountMenu".equals(e.getActionCommand())) {
			cl.show(cardLayoutPanel, accountMenuCardName);
			depositIntoCheckingTextField.setValue(0.00);
			withdrawFromCheckingTextField.setValue(0.00);
			depositIntoSavingsTextField.setValue(0.00);
			withdrawFromSavingsTextField.setValue(0.00);
		} else if ("DepositIntoSavings".equals(e.getActionCommand())) {
			try {
				String amountString = depositIntoSavingsTextField.getText().substring(1);
				String cleanAmountString = "";
				for (int i = 0; i < amountString.length(); i++) {
					if (amountString.charAt(i) != ',') {
						cleanAmountString += amountString.charAt(i);
					}
				}
				Double amount = Double.parseDouble(cleanAmountString);
				
				currentUser.getSavingsAccount().deposit(amount);
				savingsAccountBalance.setText("$" + currentUser.getSavingsAccount().getBalance());
				availableAccountBalance.setText("$" + currentUser.getSavingsAccount().getAvailableBalance());
				depositIntoSavingsTextField.setValue(0.00);
			} catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if ("WithdrawFromSavings".equals(e.getActionCommand())) {
			try {
				String amountString = withdrawFromSavingsTextField.getText().substring(1);
				String cleanAmountString = "";
				for (int i = 0; i < amountString.length(); i++) {
					if (amountString.charAt(i) != ',') {
						cleanAmountString += amountString.charAt(i);
					}
				}
				Double amount = Double.parseDouble(cleanAmountString);
				
				currentUser.getSavingsAccount().withdraw(amount);
				savingsAccountBalance.setText("$" + currentUser.getSavingsAccount().getBalance());
				availableAccountBalance.setText("$" + currentUser.getSavingsAccount().getAvailableBalance());
				withdrawFromSavingsTextField.setValue(0.00);
			} catch(ArithmeticException ex) {
				JOptionPane.showMessageDialog(null, "Not enough available funds.", "Error", JOptionPane.ERROR_MESSAGE);
				
			}
		} else if ("TransferSubmitButton".equals(e.getActionCommand())) {
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
	
	public String getSelectedRadioButtonText(ButtonGroup group) {
		for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();
			
			if(button.isSelected()) {
				return button.getText();
			}
		}
		
		return null;
	}
	
	public void windowClosed(WindowEvent e) {
		
	}
	
	public void windowOpened(WindowEvent e) {
		
	}
	
	public void windowClosing(WindowEvent e) {
		boolean exists = (new File("./DATAFILE")).exists(); 
	    if (exists) {
	    	try
	        {
	        FileOutputStream fos = new FileOutputStream("./DATAFILE");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	 
	        //for (int i = 0; i < atm.getListSize(); i++)
	        oos.writeObject(atm);
	        System.out.println(atm.getListSize());
	        
	        oos.flush();
	        oos.close();
	        }
	        catch (IOException err)
	        {
	            JOptionPane.showMessageDialog(null, err, "Error", JOptionPane.ERROR_MESSAGE);
	        }        
	        System.exit(0);
	    }
	    else {
	    	String filePath = "./DATAFILE";
	    	//new File(filePath);
	    	try
	        {
	        FileOutputStream fos = new FileOutputStream(new File(filePath));
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	 
	        for (int i = 0; i < atm.getListSize(); i++)
	        oos.writeObject(atm);
	        
	        oos.flush();
	        oos.close();
	        }
	        catch (IOException err)
	        {
	            JOptionPane.showMessageDialog(null, err, "Error", JOptionPane.ERROR_MESSAGE);
	        }
	        System.exit(0);
	    }
	}
	
	public void windowIconified(WindowEvent e) {
		
	}
	
	public void windowDeiconified(WindowEvent e) {
		
	}
	
	public void windowActivated(WindowEvent e) {
		
	}
	
	public void windowDeactivated(WindowEvent e) {
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				AtmGui atm = new AtmGui();
				atm.setVisible(true);
			}
		});
	}
}
