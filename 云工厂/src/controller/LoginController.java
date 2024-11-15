package controller;

import model.*;
import service.*;

public class LoginController {
	
	Login loginService = new Login();

	public User login(String account, String password) {
		return loginService.login(account, password);
	}

}
