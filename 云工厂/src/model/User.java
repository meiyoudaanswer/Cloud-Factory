package model;

import java.util.List;

public class User {
	private String account;
	private String password;
	private String name;
	private String contactInfo;
	private String type;
	private String factoryName;
	private String factoryDescription;
	private List<String> orderList;

	public User(String account, String password, String name, String contactInfo, String type) {
		this.account = account;
		this.password = password;
		this.name = name;
		this.contactInfo = contactInfo;
		this.type = type;
		factoryName = null;
		factoryDescription = null;
		orderList = null;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getFactoryDescription() {
		return factoryDescription;
	}

	public void setFactoryDescription(String factoryDescription) {
		this.factoryDescription = factoryDescription;
	}

	public List<String> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<String> orderList) {
		this.orderList = orderList;
	}

	public void addOrder(String orderId) {
		orderList.add(orderId);
	}
}
