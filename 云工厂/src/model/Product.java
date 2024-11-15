package model;

public class Product {
	
	private String id;
	private String name;
	private String type;
	private String specification;
	private String description;

	public Product(String id, String name, String type, String specification, String description) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.specification = specification;
		this.description = description;
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

}
