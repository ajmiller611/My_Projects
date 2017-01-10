package version1;

import java.io.Serializable;

public class SavingsAccount extends Account implements Serializable{
	private double availableBalance = 0;
	
	public SavingsAccount() {
		
	}
	
	public void withdraw(double amount) {
		availableBalance = super.getBalance() - 10;
		if(availableBalance >= amount) {
			double newBalance = super.getBalance() - amount;
			super.setBalance(newBalance);
		}
		else {
			throw new ArithmeticException("Not enough available funds.");
		}
	}
	
	public double getBalance() {
		return super.getBalance();
	}
	
	public double getAvailableBalance() {
		availableBalance = super.getBalance() - 10;
		return availableBalance;
	}
}

