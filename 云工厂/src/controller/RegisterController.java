package controller;

import model.*;
import service.*;

public class RegisterController {
	
	Register registerService = new Register();

	public void save() throws Exception {
		registerService.save();
	}

	public boolean add(User user) {
		return registerService.add(user);
	}

}
