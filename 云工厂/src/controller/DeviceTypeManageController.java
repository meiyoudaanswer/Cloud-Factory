package controller;

import java.util.*;
import service.*;

public class DeviceTypeManageController extends BaseController{
	
	public DeviceTypeManageController(String message) {
		super(message);
	}

	public List<Object> queryAllCitedDevice(String deviceTypeName) {
		return ((DeviceTypeManage) super.getBaseService()).queryAllCitedDevice(deviceTypeName);
	}

}
