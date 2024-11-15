package service;

import java.util.*;

public interface BaseService {
	
	public void save() throws Exception;

	public List<Object> queryAll();

	public boolean add(Object obj);

	public void delete(Object key);

	public void replace(String key, Object newObj);

	public Object search(Object key);

}
