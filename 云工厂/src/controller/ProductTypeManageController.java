package controller;

import java.util.*;
import service.*;

public class ProductTypeManageController extends BaseController{
	
	public ProductTypeManageController(String message) {
		super(message);
	}

	public List<Object> queryAllCitedDevice(String productTypeName) {
		return ((ProductTypeManage) super.getBaseService()).queryAllCitedDevice(productTypeName);
	}

}
