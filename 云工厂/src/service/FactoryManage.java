package service;

import java.util.*;
import model.*;
import fileUtil.*;

public class FactoryManage implements BaseService{
	
	private CloudSystem system = CloudSystem.getInstance();
	
	public void save() throws Exception {
		ReadWrite.writeData("factory.txt", TypeTrans.collectionToList(system.getFactory().values()));
	}

	public List<Object> queryAll() {
		return TypeTrans.collectionToList(system.getFactory().values());
	}

	public boolean add(Object obj) {
		Factory factory = (Factory) obj;
		if (system.getFactory().get(factory.getName()) != null)
			return false;
		system.getFactory().put(factory.getName(), factory);
		return true;
	}

	public void delete(Object key) {
		Factory factory = system.getFactory().get(key);
		system.getFactory().remove(factory.getName());
	}

	public void replace(String key, Object newObj) {
		system.getFactory().replace(key, (Factory) newObj);
	}

	public Object search(Object key) {
		return system.getFactory().get(key);
	}

}
