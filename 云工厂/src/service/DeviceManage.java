package service;

import java.util.*;

import fileUtil.*;
import model.*;

public class DeviceManage implements BaseService{
	
	private CloudSystem system = CloudSystem.getInstance();
	
	public void save() throws Exception {
		ReadWrite.writeData("device.txt", TypeTrans.collectionToList(system.getDevice().values()));
	}

	public List<Object> queryAll() {
		return TypeTrans.collectionToList(system.getDevice().values());
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
		return system.getDevice().get(key);
	}

}
