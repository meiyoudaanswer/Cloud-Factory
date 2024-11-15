package fileUtil;

import com.google.gson.*;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

public class GsonUtil {
//Json转object
	public static Object toObject(String s, Class<?> c) {
		return new Gson().fromJson(new JsonParser().parse(s), c);		
	}
//object转Json
	public static String toJson(Object obj) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		String s = gson.toJson(obj);
		s = s.replace("\n", "");
		s = s.replace(" ", "");
		return s;
	}
}
