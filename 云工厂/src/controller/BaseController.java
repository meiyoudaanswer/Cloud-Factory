package controller;

import java.util.*;
import model.*;
import fileUtil.*;
import service.*;

public class BaseController {
	
	private BaseService baseService;

	public BaseController(String message) {
		baseService = ServiceManage.createService(message);
	}

	public BaseController(String message, User user) {
		baseService = ServiceManage.createService(message, user);
	}

	public BaseService getBaseService() {
		return baseService;
	}

	public void save() throws Exception {
		baseService.save();
	}

	public List<Object> queryAll() {
		return baseService.queryAll();
	}

	public boolean add(Object obj) {
		return baseService.add(obj);
	}

	public void delete(Object key) {
		baseService.delete(key);
	}

	public void replace(String key, Object newObj) {
		baseService.replace(key, newObj);
	}

	public Object search(Object key) {
		return baseService.search(key);
	}

}
