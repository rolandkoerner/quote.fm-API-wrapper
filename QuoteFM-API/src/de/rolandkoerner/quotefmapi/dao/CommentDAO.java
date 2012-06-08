package de.rolandkoerner.quotefmapi.dao;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import de.rolandkoerner.quotefmapi.APIWrapper;
import de.rolandkoerner.quotefmapi.IWebClient;
import de.rolandkoerner.quotefmapi.entities.Comment;
import de.rolandkoerner.quotefmapi.entities.User;

public class CommentDAO extends DAO<Comment> {
	

	public CommentDAO(IWebClient webClient) {
		super(webClient);
	}

	@Override
	protected Comment parse(JSONObject jsonObject) {
		Comment comment = null;

		comment = getFromCache(jsonObject);

		if (comment == null) {

			int id = getId(jsonObject);
			comment = new Comment(id);

			try {
				String text = jsonObject.getString("text");
				comment.setText(text);
			} catch (JSONException e) {
				comment.setText("");
			}

			try {
				String created = jsonObject.getString("created");
				Date date = new Date(Date.parse(created));
				comment.setCreated(date);

			} catch (JSONException e) {
				comment.setCreated(null);
			}

			try {
				Integer userId = jsonObject.getInt("user_id");
				comment.setUserId(userId);
			} catch (JSONException e) {
				comment.setUserId(0);
			}

			try {
				JSONObject userObject = jsonObject.getJSONObject("user");
				User user = APIWrapper.getInstance().getUserDAO().parse(userObject);
				comment.setUser(user);
			} catch (JSONException e) {
				comment.setUser(null);
			}

			cache.put(id, comment);
			System.out.println(comment.toString());
		}
		return comment;

	}
}
