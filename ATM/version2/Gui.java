package version2;

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

public class Gui extends JFrame implements WindowListener {	
	JPanel cardLayoutPanel = new JPanel(new CardLayout());
	final String mainMenuCardName = "Main Menu";
	final String loginCardName = "Login";
	final String createUserCardName = "Create User";
	final String accountMenuCardName = "Account Menu";
	final String checkingAccountCardName = "Checking Account";
	final String savingsAccountCardName = "Savings Account";
	final String accountBalanceCardName = "Account Balance";
	final String transferCardName = "Transfer";
	Atm atm = new Atm();
	static User currentUser = new User();
	CardLayout cl;
	
	public Gui() {
		loadDataForAccountsFromFile();
		initialUI();
	}
	
	private void loadDataForAccountsFromFile() {
		boolean fileExists = (new File("./DATAFILE")).exists(); 
	    if (fileExists) 
	    { 
	         JOptionPane.showMessageDialog(null,"Data file loaded"); 
	         try 
	         { 
	             FileInputStream filein = new FileInputStream("./DATAFILE"); 
	             ObjectInputStream oin = new ObjectInputStream(filein); 
	             atm =(Atm) oin.readObject(); 
	             oin.close(); 
	         } catch(IOException e) {
	        	 JOptionPane.showMessageDialog(null,e); 
	         } catch(ClassNotFoundException ff) { 
	        	 JOptionPane.showMessageDialog(null,"Sorry");
	         } 
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
		
		cl = (CardLayout)(cardLayoutPanel.getLayout());
		
		MainMenuCard mainMenuCard = new MainMenuCard(cardLayoutPanel, cl);
		LoginCard loginCard = new LoginCard(cardLayoutPanel, cl, currentUser, atm);
		UserCreationCard userCreationCard = new UserCreationCard(cardLayoutPanel, cl, atm);
		AccountMenuCard accountMenuCard = new AccountMenuCard(cardLayoutPanel, cl, currentUser);
		CheckingAccountCard checkingAccountCard = new CheckingAccountCard(cardLayoutPanel, cl, currentUser);
		SavingsAccountCard savingsAccountCard = new SavingsAccountCard(cardLayoutPanel, cl, currentUser);
		AccountBalanceCard accountBalanceCard = new AccountBalanceCard(cardLayoutPanel, cl, currentUser);
		TransferCard transferCard = new TransferCard(cardLayoutPanel, cl, currentUser);
		
		
		cardLayoutPanel.add(mainMenuCard.createMainMenuCard(), mainMenuCardName);
		
		cardLayoutPanel.add(loginCard.createLoginCard(), loginCardName);
		
		cardLayoutPanel.add(userCreationCard.createUserCreationCard(), createUserCardName);
		
		cardLayoutPanel.add(accountMenuCard.createAccountMenuCard(), accountMenuCardName);
		
		cardLayoutPanel.add(checkingAccountCard.createCheckingAccountCard(), checkingAccountCardName);
		
		cardLayoutPanel.add(savingsAccountCard.createSavingsAccountCard(), savingsAccountCardName);
		
		cardLayoutPanel.add(accountBalanceCard.createAccountBalanceCard(), accountBalanceCardName);
		
		cardLayoutPanel.add(transferCard.createTransferCard(), transferCardName);
		
		this.add(cardLayoutPanel);
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
				Gui atm = new Gui();
				atm.setVisible(true);
			}
		});
	}
}
