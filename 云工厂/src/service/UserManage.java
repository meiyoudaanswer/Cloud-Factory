package service;

import java.util.*;
import model.*;
import fileUtil.*;

public class UserManage implements BaseService{
	
	private CloudSystem system = CloudSystem.getInstance();

	public void save() throws Exception {
		ReadWrite.writeData("user.txt", TypeTrans.collectionToList(system.getUser_account().values()));
		ReadWrite.writeData("factory.txt", TypeTrans.collectionToList(system.getFactory().values()));
	}

	public List<Object> queryAll() {
		return TypeTrans.collectionToList(system.getUser_account().values());
	}

	public boolean add(Object obj) {
		User user = (User) obj;
		if (system.getUser_account().get(user.getAccount()) != null)
			return false;
		if (user.getType().equalsIgnoreCase("factoryManager")) {
			FactoryManager factoryManager = new FactoryManager(user.getAccount(), user.getPassword(), user.getName(),
					user.getContactInfo(), user.getType(), "云工厂" + user.getAccount(), "无");
			system.getUser_account().put(factoryManager.getAccount(), factoryManager);
			system.getUser_name().put(factoryManager.getName(), factoryManager);
			Factory factory = new Factory(factoryManager.getFactoryName(), factoryManager.getFactoryDescription(),
					factoryManager.getName());
			system.getFactory().put(factory.getName(), factory);
		} else if (user.getType().equalsIgnoreCase("dealer")) {
			Dealer dealer = new Dealer(user.getAccount(), user.getPassword(), user.getName(), user.getContactInfo(),
					user.getType());
			system.getUser_account().put(dealer.getAccount(), dealer);
			system.getUser_name().put(dealer.getName(), dealer);
		}
		return true;
	}

	public void delete(Object key) {
		User user = system.getUser_account().get(key);
		system.getUser_account().remove(user.getAccount());
		system.getUser_name().remove(user.getName());

	}

	public void replace(String key, Object newObj) {
		User user = system.getUser_account().get(key);
		system.getUser_account().replace(user.getAccount(), (User) newObj);
		system.getUser_name().replace(user.getName(), (User) newObj);

	}

	public Object search(Object key) {
		Object res = system.getUser_name().get(key);
		if (res != null)
			return res;
		res = system.getUser_account().get(key);
		if (res != null)
			return res;
		return null;
	}

}
