package model;

import java.util.*;

public class Device {
	
	private String id;
	private String name;
	private String type;
	private String specification;
	private String description;
	private boolean isPrivate;
	private String factoryName;
	private boolean isOn;
	private boolean isProducing;
	private boolean isRented;
	private Map<String, Integer> productionCapacity;
	
	public Device(String id, String name, String type, String specification, String description, boolean isPrivate,
			String factoryName) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.specification = specification;
		this.description = description;
		this.isPrivate = isPrivate;
		this.factoryName = factoryName;
		isOn = true;
		isProducing = false;
		isRented = false;
		productionCapacity = new LinkedHashMap<String, Integer>();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public boolean isOn() {
		return isOn;
	}

	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}

	public boolean isProducing() {
		return isProducing;
	}

	public void setProducing(boolean isProducing) {
		this.isProducing = isProducing;
	}

	public boolean isRented() {
		return isRented;
	}

	public void setRented(boolean isRented) {
		this.isRented = isRented;
	}

	public boolean addProductionCapacity(String productName, int num) {
		if (productionCapacity.get(productName) != null)
			return false;
		productionCapacity.put(productName, num);
		return true;
	}

	public Map<String, Integer> getProductionCapacity() {
		return productionCapacity;
	}

}
