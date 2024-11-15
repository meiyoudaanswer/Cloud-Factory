package service;

import java.util.*;
import model.*;
import fileUtil.*;

public class MyDeviceManage implements BaseService{
	
	private CloudSystem system = CloudSystem.getInstance();
	private User factoryManager;

	public MyDeviceManage(User factoryManager) {
		this.factoryManager = factoryManager;
	}

	public void save() throws Exception {
		ReadWrite.writeData("device.txt", TypeTrans.collectionToList(system.getDevice().values()));
	}

	public List<Object> queryAll() {
		List<Object> res = new ArrayList<Object>();
		for (Device device : system.getDevice().values()) {
			if (device.getFactoryName() != null && device.getFactoryName().equals(factoryManager.getFactoryName()))
				res.add(device);
		}
		return res;
	}

	public boolean add(Object obj) {
		Device device = (Device) obj;
		if (system.getDevice().get(device.getName()) != null)
			return false;
		system.getDevice().put(device.getName(), device);
		return true;
	}

	public void delete(Object key) {
		Device device = system.getDevice().get(key);
		system.getDevice().remove(device.getName());
	}

	public void replace(String key, Object newObj) {
		system.getDevice().replace(key, (Device) newObj);
	}

	public Object search(Object key) {
		Object res = system.getDevice().get(key);
		if (res == null)
			return null;
		Device device = (Device) res;
		if (device.getFactoryName() != null && device.getFactoryName().equals(factoryManager.getFactoryName()))
			return device;
		else
			return null;
	}

}
