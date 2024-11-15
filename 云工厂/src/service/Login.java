package service;

import model.*;

public class Login {
	
	private CloudSystem system = CloudSystem.getInstance();
	
	public User login(String account, String password) {
		User user = system.getUser_account().get(account);
		if (user == null || !user.getPassword().equals(password))
			return null;
		return user;
	}

}
