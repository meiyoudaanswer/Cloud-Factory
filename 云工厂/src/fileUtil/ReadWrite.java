package fileUtil;

import java.io.*;
import java.util.*;

public class ReadWrite {
	
	public static List<Object> readData(String fileName, Class<?> c) throws Exception {
		List<Object> res = new ArrayList<Object>();
		BufferedReader reader = new BufferedReader(new FileReader("Data/" + fileName));

		String line = reader.readLine();
		while (line != null) {
			res.add(GsonUtil.toObject(line, c));
			line = reader.readLine();
		}
		reader.close();
		return res;
	}

	public static void writeData(String fileName, List<Object> list) throws Exception {
		BufferedWriter bw = new BufferedWriter(new FileWriter("Data/" + fileName, false));
		StringBuilder res = new StringBuilder();
		for (Object obj : list) {
			res.append(GsonUtil.toJson(obj) + "\n");
		}
		bw.write(res.toString());
		bw.close();
	}

}
