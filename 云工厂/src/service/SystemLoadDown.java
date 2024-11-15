package service;

import java.util.List;
import model.*;
import fileUtil.*;

public class SystemLoadDown {
	
	private CloudSystem system = CloudSystem.getInstance();

	public void Init() throws Exception {
		List<Object> userList = ReadWrite.readData("user.txt", User.class);
		List<Object> factoryList = ReadWrite.readData("factory.txt", Factory.class);
		List<Object> productTypeList = ReadWrite.readData("productType.txt", ProductType.class);
		List<Object> productList = ReadWrite.readData("product.txt", Product.class);
		List<Object> deviceTypeList = ReadWrite.readData("deviceType.txt", DeviceType.class);
		List<Object> deviceList = ReadWrite.readData("device.txt", Device.class);
		List<Object> orderList = ReadWrite.readData("order.txt", Order.class);
		system.Init(userList, factoryList, productTypeList, productList, deviceTypeList, deviceList, orderList);
	}

}
