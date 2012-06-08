package de.rolandkoerner.quotefmapi.entities;

import java.util.Date;

public class Comment extends AbstractEntity {

	public Comment(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -2463845107041033915L;

	private int userId;
	private User user;
	private String text;
	private Date created;

	public User getUser() {
		return user;
	}

	public String getText() {
		return text;
	}

	public Date getCreated() {
		return created;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", userId=" + userId + ", user=" + user
				+ ", text=" + text + ", created=" + created + "]";
	}
	

}
