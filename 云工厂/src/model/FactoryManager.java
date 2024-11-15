package model;

public class FactoryManager extends User{
	
	public FactoryManager(String account, String password, String name, String contactInfo, String type,
			String factoryName, String factoryDescription) {
		super(account, password, name, contactInfo, type);
		super.setFactoryName(factoryName);
		super.setFactoryDescription(factoryDescription);
	}

}
