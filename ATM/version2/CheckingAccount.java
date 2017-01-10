package version2;

import java.io.Serializable;

public class CheckingAccount extends Account implements Serializable {
	
	public CheckingAccount() {
		
	}
	
	public double getBalance() {
		return super.getBalance();
	}
}
