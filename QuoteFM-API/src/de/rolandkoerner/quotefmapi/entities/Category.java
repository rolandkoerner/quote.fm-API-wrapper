package de.rolandkoerner.quotefmapi.entities;


public class Category extends AbstractEntity {

	public Category(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -8584834441521355363L;

	private String name;
	private int order;
	private User curator;

	public String getName() {
		return name;
	}

	
	public int getOrder() {
		return order;
	}

	public User getCurator() {
		return curator;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setCurator(User curator) {
		this.curator = curator;
	}


	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", order=" + order
				+ ", curator=" + curator + "]";
	}

	
	
}
