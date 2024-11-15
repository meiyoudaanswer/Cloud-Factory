package model;

import java.util.*;

public class Dealer extends User {
	
	public Dealer(String account, String password, String name, String contactInfo, String type) {
		super(account, password, name, contactInfo, type);
		super.setOrderList(new ArrayList<String>());
	}

}
