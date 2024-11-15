package service;

import java.util.*;
import model.*;
import fileUtil.*;

public class ProductManage implements BaseService{
	
	private CloudSystem system = CloudSystem.getInstance();

	public void save() throws Exception {
		ReadWrite.writeData("product.txt", TypeTrans.collectionToList(system.getProduct().values()));
	}

	public List<Object> queryAll() {
		return TypeTrans.collectionToList(system.getProduct().values());
	}

	public boolean add(Object obj) {
		Product product = (Product) obj;
		if (system.getProduct().get(product.getName()) != null)
			return false;
		system.getProduct().put(product.getName(), product);
		return true;
	}

	public void delete(Object key) {
		Product product = system.getProduct().get(key);
		system.getProduct().remove(product.getName());
	}

	public void replace(String key, Object newObj) {
		system.getProduct().replace(key, (Product) newObj);
	}

	public Object search(Object key) {
		return system.getProduct().get(key);
	}

}
