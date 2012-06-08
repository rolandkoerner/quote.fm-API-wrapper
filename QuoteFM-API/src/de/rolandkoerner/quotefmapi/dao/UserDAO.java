package de.rolandkoerner.quotefmapi.dao;

import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import de.rolandkoerner.quotefmapi.IWebClient;
import de.rolandkoerner.quotefmapi.entities.User;

public class UserDAO extends DAO<User> {

	public UserDAO(IWebClient webClient) {
		super(webClient);

	}

	public User getById(int id) {

		User user = null;
		user = getFromCache(id);

		if (user == null) {
			String url = "https://quote.fm/api/user/get/?id=%1d";
			String result = webClient.getContent(String.format(url, id));

			user = parse(result);
		}

		return user;
	}

	public User getByUserName(String userName) {
		if (userName == null)
			return null;

		for (User user : cache.values()) {
			if (userName.equals(user.getUserName())) {
				System.out.println("[CACHED]\t" + user.toString());
				return user;
			}
		}

		String url = "https://quote.fm/api/user/get/?username=%1s";
		String result = webClient.getContent(String.format(url, userName));

		User user = parse(result);
		return user;
	}

	public List<User> listFollowers(long id) {
		String url = "https://quote.fm/api/user/listFollowers/?id=%1d";
		String request = String.format(url, id);

		return getList(request);
	}

	public List<User> listFollowers(String userName) {
		String url = "https://quote.fm/api/user/listFollowers/?username=%1s";
		String request = String.format(url, userName);

		return getList(request);
	}

	public List<User> listFollowings(long id) {
		String url = "https://quote.fm/api/user/listFollowings/?id=%1d";
		String request = String.format(url, id);

		return getList(request);
	}

	public List<User> listFollowings(String userName) {
		String url = "https://quote.fm/api/user/listFollowings/?username=%1s";
		String request = String.format(url, userName);

		return getList(request);
	}

	@Override
	protected User parse(JSONObject jsonObject) {
		User user = null;

		user = getFromCache(jsonObject);
		if (user == null) {
			int id = getId(jsonObject);
			user = new User(id);

			try {
				String fullName = jsonObject.getString("fullname");
				user.setFullName(fullName);
			} catch (JSONException e) {
				user.setFullName("");
			}

			try {
				String userName = jsonObject.getString("username");
				user.setUserName(userName);
			} catch (JSONException e) {
				user.setUserName("");
			}

			try {
				String bio = jsonObject.getString("bio");
				user.setBio(bio);
			} catch (JSONException e) {
				user.setBio("");
			}

			try {
				String avatar = jsonObject.getString("avatar");
				user.setAvatar(avatar);
			} catch (JSONException e) {
				user.setAvatar("");
			}

			try {
				String twitter = jsonObject.getString("twitter");
				user.setTwitter(twitter);
			} catch (JSONException e) {
				user.setTwitter("");
			}

			try {
				String location = jsonObject.getString("location");
				user.setLocation(location);
			} catch (JSONException e) {
				user.setLocation("");
			}

			try {
				String url = jsonObject.getString("url");
				user.setUrl(url);
			} catch (JSONException e) {
				user.setUrl("");
			}

			try {
				String created = jsonObject.getString("created");
				Date date = new Date(Date.parse(created));
				user.setCreated(date);

			} catch (JSONException e) {
				user.setCreated(null);
			}

			cache.put(id, user);
			System.out.println("[New]\t" + user.toString());
		}

		return user;
	}

}
