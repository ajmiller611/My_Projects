package version2;

import java.io.Serializable;

public class Account implements Serializable {
	private double balance;
	
	public Account() {
		
	}
	
	public void deposit(double amount) {
		balance = balance + amount;
	}
	
	public void withdraw(double amount) {
		if(balance >= amount) {
			balance = balance - amount;
		}
		else {
			throw new ArithmeticException("Not enough available funds.");
		}
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double newBalance) {
		balance = newBalance;
	}
}
