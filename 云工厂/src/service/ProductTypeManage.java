package service;

import java.util.*;
import model.*;
import fileUtil.*;

public class ProductTypeManage implements BaseService{
	
	private CloudSystem system = CloudSystem.getInstance();

	public void save() throws Exception {
		ReadWrite.writeData("productType.txt", TypeTrans.collectionToList(system.getProductType().values()));
	}

	public List<Object> queryAll() {
		return TypeTrans.collectionToList(system.getProductType().values());
	}

	public List<Object> queryAllCitedDevice(String productTypeName) {
		List<Object> res = new ArrayList<Object>();
		for (Product product : system.getProduct().values()) {
			if (product.getType().equals(productTypeName))
				res.add(product);
		}
		return res;
	}

	public boolean add(Object obj) {
		ProductType productType = (ProductType) obj;
		if (system.getProductType().get(productType.getName()) != null)
			return false;
		system.getProductType().put(productType.getName(), productType);
		return true;
	}

	public void delete(Object key) {
		ProductType productType = system.getProductType().get(key);
		system.getProductType().remove(productType.getName());
	}

	public void replace(String key, Object newObj) {
		system.getProductType().remove(key);
		ProductType productType = (ProductType) newObj;
		system.getProductType().put(productType.getName(), productType);
	}

	public Object search(Object key) {
		return system.getProductType().get(key);
	}

}
