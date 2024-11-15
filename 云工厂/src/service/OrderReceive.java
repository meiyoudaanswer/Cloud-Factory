package service;

import java.util.*;
import model.*;
import fileUtil.*;

public class OrderReceive implements BaseService{
	
	private CloudSystem system = CloudSystem.getInstance();
	private User factoryAdmin;

	public OrderReceive(User factoryAdmin) {
		this.factoryAdmin = factoryAdmin;
	}

	public void save() throws Exception {
		ReadWrite.writeData("order.txt", TypeTrans.collectionToList(system.getOrder().values()));
	}

	public List<Object> queryAll() {
		List<Object> res = new ArrayList<Object>();
		for (Order order : system.getOrder().values()) {
			if (order.getState() == 2 && order.getBidFactory().get(factoryAdmin.getFactoryName()) == null)
				res.add(order);
		}
		return res;
	}

	public boolean add(Object obj) {
		return true;
	}

	public void delete(Object key) {

	}

	public void replace(String key, Object newObj) {
		system.getOrder().replace(key, (Order) newObj);
	}

	public Object search(Object key) {
		return system.getOrder().get(key);
	}

}
