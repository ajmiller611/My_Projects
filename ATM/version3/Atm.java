package version3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Atm implements Serializable {
	private ArrayList<User> userList;
	
	public Atm() {
		userList = new ArrayList<User>();
	}
	
	public void addUser(User newUser) {
		userList.add(newUser);
	}
	
	public User getUserByPin(int pin) {
		User foundUser;
		
		for(User user : userList) {
			if (user.getPin() == pin) {
				foundUser = user;
				return foundUser;
			}
		}
		
		return null;
	}
	
	public int getListSize() {
		return userList.size();
	}
	
	public ArrayList<User> getListOfUsers() {
		return userList;
	}
	
	public void allowApplicationOfInterestRates() {
		for(User user : userList) {
			user.setInterestRateFlag(true);
		}
	}
	
	public void applyInterestRatesToUsers() {
		for(User user : userList) {
			if(user.getInterestRateFlag() == true) {
				user.getCheckingAccount().applyInterestRate();
				user.getSavingsAccount().applyInterestRate();
				user.setInterestRateFlag(false);
			}
		}
	}
}
