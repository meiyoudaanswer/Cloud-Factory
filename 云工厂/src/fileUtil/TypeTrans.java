package fileUtil;

import java.util.*;

public class TypeTrans {
//collection转list
	public static List<Object> collectionToList(Collection<?> collection) {
		List<Object> res = new ArrayList<Object>();
		for (Object obj : collection) {
			res.add(obj);
		}
		return res;
	}
}
