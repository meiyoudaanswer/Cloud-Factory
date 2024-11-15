package service;

import java.util.*;
import model.*;
import fileUtil.*;

public class MyOrderManage implements BaseService{
	
	private CloudSystem system = CloudSystem.getInstance();
	private User dealer;

	public MyOrderManage(User dealer) {
		this.dealer = dealer;
	}

	public void save() throws Exception {
		ReadWrite.writeData("order.txt", TypeTrans.collectionToList(system.getOrder().values()));
		ReadWrite.writeData("user.txt", TypeTrans.collectionToList(system.getUser_account().values()));
	}

	public List<Object> queryAll() {
		List<Object> res = new ArrayList<Object>();
		for (String s : dealer.getOrderList()) {
			res.add(system.getOrder().get(s));
		}
		return res;
	}

	public boolean add(Object obj) {
		Order order = (Order) obj;
		if (system.getOrder().get(order.getId()) != null)
			return false;
		system.getOrder().put(order.getId(), order);
		dealer.addOrder(order.getId());
		system.getUser_account().replace(dealer.getAccount(), dealer);
		system.getUser_name().replace(dealer.getName(), dealer);
		return true;
	}

	public void delete(Object key) {
		Order order = system.getOrder().get(key);
		system.getOrder().remove(order.getId());
	}

	public void replace(String key, Object newObj) {
		system.getOrder().replace(key, (Order) newObj);
	}

	public Object search(Object key) {
		return system.getOrder().get(key);
	}

}
