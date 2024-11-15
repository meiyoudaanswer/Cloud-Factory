package service;

import java.util.*;
import model.*;
import fileUtil.*;

public class OrderCheck implements BaseService{
	
	private CloudSystem system = CloudSystem.getInstance();
	private User factoryManager;

	public OrderCheck(User factoryManager) {
		this.factoryManager = factoryManager;
	}

	public void save() throws Exception {

	}

	public List<Object> queryAll() {
		return null;
	}

	public List<Object> queryAll(int choice) {
		List<Object> res = new ArrayList<Object>();
		// 已投标
		if (choice == 1) {
			for (Order order : system.getOrder().values()) {
				if (order.getState() == 2 && order.getBidFactory().get(factoryManager.getFactoryName()) != null)
					res.add(order);
			}
		}
		// 已中标
		else if (choice == 2) {
			for (Order order : system.getOrder().values()) {
				if (order.getBidWinnerFactory() != null
						&& order.getBidWinnerFactory().equals(factoryManager.getFactoryName()))
					res.add(order);
			}
		}
		return res;
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
