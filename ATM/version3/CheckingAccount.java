package version3;

import java.io.Serializable;

public class CheckingAccount extends Account implements Serializable {
	private double interestRate = 0.02;
	
	public CheckingAccount() {
		
	}
	
	public double getBalance() {
		return super.getBalance();
	}
	
	public void applyInterestRate() {
		double interest = super.getBalance() * interestRate;
		super.deposit(interest);
	}
}
