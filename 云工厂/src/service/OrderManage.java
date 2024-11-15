package service;

import java.util.*;
import model.*;
import fileUtil.*;

public class OrderManage implements BaseService{
	
	private CloudSystem system = CloudSystem.getInstance();

	public void save() throws Exception {
	}

	public List<Object> queryAll() {
		return TypeTrans.collectionToList(system.getOrder().values());
	}

	public boolean add(Object obj) {
		return true;
	}

	public void delete(Object key) {

	}

	public void replace(String key, Object newObj) {

	}

	public Object search(Object key) {
		return system.getOrder().get(key);
	}

}
