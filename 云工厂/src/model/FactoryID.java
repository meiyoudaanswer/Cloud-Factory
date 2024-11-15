package model;

public class FactoryID {
	
	private static int product = 24020;
	private static int device = 360012;
	private static int order = 72016;

	public static String createId(String message) {

		String id = new String();
		if (message.equalsIgnoreCase("Product")) {
			product++;
			id = "STR" + product;
		} else if (message.equalsIgnoreCase("Device")) {
			device++;
			id = "MLP" + device;
		} else if (message.equalsIgnoreCase("Order")) {
			order++;
			id = "Ord" + order;
		}

		return id;
	}

}
