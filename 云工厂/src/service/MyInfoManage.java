package service;

import java.util.*;
import model.*;
import fileUtil.*;

public class MyInfoManage implements BaseService{
	
	private CloudSystem system = CloudSystem.getInstance();

	public MyInfoManage(User user) {

	}

	public void save() throws Exception {
		ReadWrite.writeData("user.txt", TypeTrans.collectionToList(system.getUser_account().values()));
		ReadWrite.writeData("factory.txt", TypeTrans.collectionToList(system.getFactory().values()));
	}

	public List<Object> queryAll() {
		return TypeTrans.collectionToList(system.getUser_account().values());
	}

	public boolean add(Object obj) {
		return true;
	}

	public void delete(Object key) {

	}

	public void replace(String key, Object newObj) {
		User user = system.getUser_account().get(key);
		system.getUser_account().replace(user.getAccount(), (User) newObj);
		system.getUser_name().replace(user.getName(), (User) newObj);
	}

	public Object search(Object key) {
		return null;
	}

}
