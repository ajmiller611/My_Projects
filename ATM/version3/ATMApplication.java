package version3;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.*;

public class ATMApplication extends JFrame implements WindowListener {
	static Atm atm = new Atm();
	static User currentUser = new User();
	
	public ATMApplication() {
		loadAtmDataFromFile();
		initializeUI();
		applyInterestRatesAtEndOfMonth();
	}
	
	private void loadAtmDataFromFile() {
		//Check for the data file existence then load atm object if file exists.
		boolean fileExists = (new File("./DATAFILE")).exists();
		if (fileExists) {
			try {
				FileInputStream fileInputStream = new FileInputStream("./DATAFILE");
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
				atm = (Atm) objectInputStream.readObject();
				objectInputStream.close();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
			} catch (ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "no file", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void initializeUI() {
		//Setup JFrame window.
		this.setSize(320, 320);
		this.setTitle("ATM");
		this.setResizable(false);
		this.addWindowListener(this);
		JPanel cardHolderPanel = new JPanel(new CardLayout());
		
		//Create the panels that the card layout will be able to used as cards to flip through as needed.
		new MainMenuCard(cardHolderPanel);
		
		new LoginCard(cardHolderPanel);
		
		new UserCreationCard(cardHolderPanel);
		
		new AccountMenuCard(cardHolderPanel);
		
		new CheckingAccountCard(cardHolderPanel);
		
		new SavingsAccountCard(cardHolderPanel);
		
		new AccountBalanceCard(cardHolderPanel);
		
		new TransferCard(cardHolderPanel);
		
		this.add(cardHolderPanel);
	}
	
	public void applyInterestRatesAtEndOfMonth() {
		//Find the language, country, and time zone from the user's platform environment.
		Locale locale = new Locale(System.getProperty("user.language"), System.getProperty("user.country"));
		TimeZone timeZone = TimeZone.getTimeZone(System.getProperty("user.timezone"));
		Calendar todaysDate = Calendar.getInstance(timeZone, locale);
		//todaysDate.set(Calendar.DATE, todaysDate.getActualMaximum(Calendar.DATE) - 1);
		//todaysDate.set(Calendar.DATE, todaysDate.getActualMaximum(Calendar.DATE));
		
		/* At the end of the month, allow and apply interest rates to users accounts.
		 * The approach used is just for demonstration. A company would want to use an approach that uses their system to allow and apply the
		 * interest rate using the date on their system.
		 */
		DateFormat dateFormat = new SimpleDateFormat("dd");
		String dayBeforeEndOfTheMonth = Integer.toString(todaysDate.getActualMaximum(Calendar.DATE) - 1);
		String dayOfTheEndOfTheMonth = Integer.toString(todaysDate.getActualMaximum(Calendar.DATE));
		if(dateFormat.format(todaysDate.getTime()).toString().equals(dayBeforeEndOfTheMonth)) {
			atm.allowApplicationOfInterestRates();
		} else if(dateFormat.format(todaysDate.getTime()).toString().equals(dayOfTheEndOfTheMonth)) {
			atm.applyInterestRatesToUsers();
		}
	}
	
	//The following window methods must be implemented for WindowsListener
	public void windowClosed(WindowEvent e) {
		
	}
	
	public void windowOpened(WindowEvent e) {
		
	}
	
	public void windowClosing(WindowEvent e) {
		//Creates the data file if it doesn't exist or updates the data file with the new atm object.
		boolean fileExists = (new File("./DATAFILE")).exists(); 
	    if (fileExists) {
	    	try {
	    		FileOutputStream fileOutputStream = new FileOutputStream("./DATAFILE");
	    		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
	 
	    		objectOutputStream.writeObject(atm);	        
	    		objectOutputStream.flush();
	    		objectOutputStream.close();
	        }
	        catch (IOException err)
	        {
	            JOptionPane.showMessageDialog(null, err, "Error", JOptionPane.ERROR_MESSAGE);
	        }        
	        System.exit(0);
	    }
	    else {
	    	String filePath = "./DATAFILE";
	    	try {
	    		FileOutputStream fileObjectStream = new FileOutputStream(new File(filePath));
	    		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileObjectStream);
	    		
	    		objectOutputStream.writeObject(atm);	    		
	    		objectOutputStream.flush();
	    		objectOutputStream.close();
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
		//Schedules a job for the event dispatch thread
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				ATMApplication atmApplication = new ATMApplication();
				atmApplication.setVisible(true);
			}
		});
	}
}
