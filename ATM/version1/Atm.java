package version1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Atm implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<User> userList;
	
	public Atm() {
		userList = new ArrayList<User>();
	}
	
	public void addUser(User newUser) {
		userList.add(newUser);
	}
	
	public User getUserByPin(int pin) {
		User foundUser;
		
		for(int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getPin() == pin) {
				foundUser = userList.get(i);
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
}
