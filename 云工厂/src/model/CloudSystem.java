package model;

import java.util.*;

public class CloudSystem {
	
	private Map<String, User> user_account;
	private Map<String, User> user_name;
	private Map<String, Factory> factory;
	private Map<String, ProductType> productType;
	private Map<String, Product> product;
	private Map<String, DeviceType> deviceType;
	private Map<String, Device> device;
	private Map<String, Order> order;
	//单例模式
	private static CloudSystem instance = null;
	public static CloudSystem getInstance() {
		if (instance == null)
			init();
		return instance;
	}
	private synchronized static void init() {
		instance = new CloudSystem();
	}//保证同一时刻只有一条线程运行

	private CloudSystem() {
		user_account = new LinkedHashMap<String, User>();
		user_name = new LinkedHashMap<String, User>();
		factory = new LinkedHashMap<String, Factory>();
		productType = new LinkedHashMap<String, ProductType>();
		product = new LinkedHashMap<String, Product>();
		deviceType = new LinkedHashMap<String, DeviceType>();
		device = new LinkedHashMap<String, Device>();
		order = new LinkedHashMap<String, Order>();
	}
	
	public void Init(List<Object> userList, List<Object> factoryList, List<Object> productTypeList,
			List<Object> productList, List<Object> deviceTypeList, List<Object> deviceList, List<Object> orderList) {
		
		SystemManager superAdmin = new SystemManager("000", "123", "系统管理员", "000", "systemManager");
		user_account.put(superAdmin.getAccount(), superAdmin);
		user_name.put(superAdmin.getName(), superAdmin);
		for (Object obj : userList) {
			User user = (User) obj;
			user_account.put(user.getAccount(), user);
			user_name.put(user.getName(), user);
		}
		for (Object obj : factoryList) {
			Factory factory = (Factory) obj;
			this.factory.put(factory.getName(), factory);
		}
		for (Object obj : productTypeList) {
			ProductType productType = (ProductType) obj;
			this.productType.put(productType.getName(), productType);
		}
		for (Object obj : productList) {
			Product product = (Product) obj;
			this.product.put(product.getName(), product);
		}
		for (Object obj : deviceTypeList) {
			DeviceType deviceType = (DeviceType) obj;
			this.deviceType.put(deviceType.getName(), deviceType);
		}
		for (Object obj : deviceList) {
			Device device = (Device) obj;
			this.device.put(device.getName(), device);
		}
		for (Object obj : orderList) {
			Order order = (Order) obj;
			this.order.put(order.getId(), order);
		}
	}

	public Map<String, User> getUser_account() {
		return user_account;
	}

	public Map<String, User> getUser_name() {
		return user_name;
	}

	public Map<String, Factory> getFactory() {
		return factory;
	}

	public Map<String, ProductType> getProductType() {
		return productType;
	}

	public Map<String, Product> getProduct() {
		return product;
	}

	public Map<String, DeviceType> getDeviceType() {
		return deviceType;
	}

	public Map<String, Device> getDevice() {
		return device;
	}

	public Map<String, Order> getOrder() {
		return order;
	}

}
