package service;

import model.*;
import fileUtil.*;

public class ServiceManage {
//系统管理员
	public static BaseService createService(String message) {
		BaseService baseService = null;
		if (message.equalsIgnoreCase("UserManage"))
			baseService = new UserManage();
		else if (message.equalsIgnoreCase("FactoryManage"))
			baseService = new FactoryManage();
		else if (message.equalsIgnoreCase("ProductTypeManage"))
			baseService = new ProductTypeManage();
		else if (message.equalsIgnoreCase("ProductManage"))
			baseService = new ProductManage();
		else if (message.equalsIgnoreCase("DeviceTypeManage"))
			baseService = new DeviceTypeManage();
		else if (message.equalsIgnoreCase("DeviceManage"))
			baseService = new DeviceManage();
		else if (message.equalsIgnoreCase("OrderManage"))
			baseService = new OrderManage();
		return baseService;
	}
//云工厂管理员
	public static BaseService createService(String message, User user) {
		BaseService baseService = null;
		if (message.equalsIgnoreCase("MyOrderManage"))
			baseService = new MyOrderManage(user);
		if (message.equalsIgnoreCase("MyDeviceManage"))
			baseService = new MyDeviceManage(user);
		if (message.equalsIgnoreCase("OrderReceivingManage"))
			baseService = new OrderReceive(user);
		if (message.equalsIgnoreCase("MyInfoManage"))
			baseService = new MyInfoManage(user);
		if (message.equalsIgnoreCase("OrderCheckManage"))
			baseService = new OrderCheck(user);
		if (message.equalsIgnoreCase("OrderScheduleManage"))
			baseService = new OrderSchedule(user);
		return baseService;
	}

}
