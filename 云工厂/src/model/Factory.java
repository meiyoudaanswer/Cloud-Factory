package model;

import java.util.*;

public class Factory {
	
	private String name;
	private String description;
	private String adminName;
	private boolean isOn;
	private Map<String, OrderSchedule> orderDB;
	
	public Factory(String name, String description, String adminName) {
		this.name = name;
		this.description = description;
		this.adminName = adminName;
		isOn = true;
		orderDB = new LinkedHashMap<String, OrderSchedule>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}

	public void addOrder(String orderId) {
		orderDB.put(orderId, new OrderSchedule());
	}

	public void addOrderSchedule(String orderId, OrderSchedule orderSchedule) {
		orderDB.replace(orderId, orderSchedule);
	}

	public Map<String, OrderSchedule> getOrderDB() {
		return orderDB;
	}

}
