package model;

import java.util.*;

public class Order {
	
	private String id;
	private String productName;
	private int num;
	private String productionDueDate;
	private String bidEndDate;
	private Map<String, Integer> bidFactory;
	private String bidWinnerFactory;
	private int state;
	// 0未保存 1已保存 2已发布 3投标结束 4已排产 5生产结束 6已发货 7已收货
	private String dealerName;
	
	public Order(String id, String productName, int num, String productionDueDate, String bidEndDate, int state,
			String dealerName) {
		this.id = id;
		this.productName = productName;
		this.num = num;
		this.productionDueDate = productionDueDate;
		this.bidEndDate = bidEndDate;
		this.state = state;
		this.dealerName = dealerName;
		state = 0;
		bidFactory = new LinkedHashMap<String, Integer>();
		bidWinnerFactory = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getProductionDueDate() {
		return productionDueDate;
	}

	public void setProductionDueDate(String productionDueDate) {
		this.productionDueDate = productionDueDate;
	}

	public String getBidEndDate() {
		return bidEndDate;
	}

	public void setBidEndDate(String bidEndDate) {
		this.bidEndDate = bidEndDate;
	}

	public String getBidWinnerFactory() {
		return bidWinnerFactory;
	}

	public void setBidWinnerFactory(String bidWinnerFactory) {
		this.bidWinnerFactory = bidWinnerFactory;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public boolean addBidFactory(String factoryName, int money) {
		if (bidFactory.get(factoryName) != null)
			return false;
		bidFactory.put(factoryName, money);
		return true;
	}

	public Map<String, Integer> getBidFactory() {
		return bidFactory;
	}

}
