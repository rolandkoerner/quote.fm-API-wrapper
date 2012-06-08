package de.rolandkoerner.quotefmapi.entities;

import java.util.Date;
import java.util.List;

import de.rolandkoerner.quotefmapi.APIWrapper;

public class Page extends AbstractEntity {

	public Page(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 3788431996568384460L;

	private String name;
	private String description;
	private Date created;

	public static Page get(int id) {
		return APIWrapper.getInstance().pages().getById(id);
	}

	public List<Article> getArticles() {
		return APIWrapper.getInstance().articles().listByPageId(this.getId());
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Date getCreated() {
		return created;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Page [id=" + id + ", name=" + name + ", description="
				+ description + ", created=" + created + "]";
	}

}
