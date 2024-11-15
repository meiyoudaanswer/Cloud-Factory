package model;

import java.util.*;

public class OrderSchedule {
	
	List<OrderScheduleItem> orderScheduleItemList;

	public OrderSchedule() {
		orderScheduleItemList = new ArrayList<OrderScheduleItem>();
	}

	public void addOrderScheduleItem(OrderScheduleItem orderScheduleItem) {
		orderScheduleItemList.add(orderScheduleItem);
	}

	public List<OrderScheduleItem> getOrderScheduleItemList() {
		return orderScheduleItemList;
	}

}
