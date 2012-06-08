package de.rolandkoerner.quotefmapi.entities;

import java.util.Date;
import java.util.List;

import de.rolandkoerner.quotefmapi.APIWrapper;

public class User extends AbstractEntity {

	public User(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 6793052749145821489L;

	private String fullName;
	private String userName;
	private String bio;
	private String avatar;
	private String twitter;
	private String location;
	private String url;

	private Date created;

	public static User get(int id) {
		return APIWrapper.getInstance().users().getById(id);
	}

	public static User get(String userName) {
		return APIWrapper.getInstance().users().getByUserName(userName);
	}

	public List<Recommendation> getRecommendations() {
		return APIWrapper.getInstance().recommendations().listByUserName(getUserName());
	}

	public String getFullName() {
		return fullName;
	}

	public String getUserName() {
		return userName;
	}

	public String getBio() {
		return bio;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getTwitter() {
		return twitter;
	}

	public String getLocation() {
		return location;
	}

	public String getUrl() {
		return url;
	}

	public Date getCreated() {
		return created;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", userName="
				+ userName + ", bio=" + bio + ", avatar=" + avatar
				+ ", twitter=" + twitter + ", location=" + location + ", url="
				+ url + ", created=" + created + "]";
	}

}
