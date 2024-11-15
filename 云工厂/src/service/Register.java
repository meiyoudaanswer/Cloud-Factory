package service;

import model.*;
import fileUtil.*;

public class Register {
	
	private CloudSystem system = CloudSystem.getInstance();

	public void save() throws Exception {
		ReadWrite.writeData("user.txt", TypeTrans.collectionToList(system.getUser_account().values()));
		ReadWrite.writeData("factory.txt", TypeTrans.collectionToList(system.getFactory().values()));
	}

	public boolean add(User user) {
		if (system.getUser_account().get(user.getAccount()) != null)
			return false;
		if (user.getType().equalsIgnoreCase("factoryManager")) {
			FactoryManager factoryManager = (FactoryManager) user;
			system.getUser_account().put(factoryManager.getAccount(), factoryManager);
			system.getUser_name().put(factoryManager.getName(), factoryManager);
			Factory factory = new Factory(factoryManager.getFactoryName(), factoryManager.getFactoryDescription(),
					factoryManager.getName());
			system.getFactory().put(factory.getName(), factory);
		} else if (user.getType().equalsIgnoreCase("dealer")) {
			system.getUser_account().put(user.getAccount(), (Dealer) user);
			system.getUser_name().put(user.getName(), (Dealer) user);
		}
		return true;
	}

}
