package service;

import java.util.*;
import model.*;
import fileUtil.*;

public class DeviceTypeManage implements BaseService{
	
	private CloudSystem system = CloudSystem.getInstance();
	
	public void save() throws Exception {
		ReadWrite.writeData("deviceType.txt", TypeTrans.collectionToList(system.getDeviceType().values()));
	}

	public List<Object> queryAll() {
		return TypeTrans.collectionToList(system.getDeviceType().values());
	}

	public List<Object> queryAllCitedDevice(String deviceTypeName) {
		List<Object> res = new ArrayList<Object>();
		for (Device device : system.getDevice().values()) {
			if (device.getType().equals(deviceTypeName))
				res.add(device);
		}
		return res;
	}

	public boolean add(Object obj) {
		DeviceType deviceType = (DeviceType) obj;
		if (system.getDeviceType().get(deviceType.getName()) != null)
			return false;
		system.getDeviceType().put(deviceType.getName(), deviceType);
		return true;
	}

	public void delete(Object key) {
		DeviceType deviceType = system.getDeviceType().get(key);
		system.getDeviceType().remove(deviceType.getName());
	}

	public void replace(String key, Object newObj) {
		system.getDeviceType().remove(key);
		DeviceType deviceType = (DeviceType) newObj;
		system.getDeviceType().put(deviceType.getName(), deviceType);
	}

	public Object search(Object key) {
		return system.getDeviceType().get(key);
	}

}
