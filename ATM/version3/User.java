package version3;

import java.io.Serializable;

public class User implements Serializable {
	private String firstName;
	private String lastName;
	private int pin;
	private CheckingAccount checkingAccount;
	private SavingsAccount savingsAccount;
	private boolean applyInterestRateFlag;
	
	public User() {
		checkingAccount = new CheckingAccount();
		savingsAccount = new SavingsAccount();
		applyInterestRateFlag = false;
	}
	
	public void setFirstName(String firstNameInput) {
		firstName = firstNameInput;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setLastName(String lastNameInput) {
		lastName = lastNameInput;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setPin(int pinInput) {
		pin = pinInput;
	}
	
	public int getPin() {
		return pin;
	}
	
	public CheckingAccount getCheckingAccount() {
		return checkingAccount;
	}
	
	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}
	
	public void setInterestRateFlag(boolean flag) {
		applyInterestRateFlag = flag;
	}
	
	public boolean getInterestRateFlag() {
		return applyInterestRateFlag;
	}
}
