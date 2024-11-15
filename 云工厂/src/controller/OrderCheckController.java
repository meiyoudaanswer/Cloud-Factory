package controller;

import java.util.*;
import model.*;
import service.*;

public class OrderCheckController extends BaseController{
	
	public OrderCheckController(String message, User user) {
		super(message, user);
	}

	public List<Object> queryAll(int choice) {
		return ((OrderCheck) super.getBaseService()).queryAll(choice);
	}

}
