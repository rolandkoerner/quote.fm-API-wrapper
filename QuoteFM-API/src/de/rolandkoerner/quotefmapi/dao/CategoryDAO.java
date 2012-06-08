package de.rolandkoerner.quotefmapi.dao;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.rolandkoerner.quotefmapi.APIWrapper;
import de.rolandkoerner.quotefmapi.IWebClient;
import de.rolandkoerner.quotefmapi.entities.AbstractEntity;
import de.rolandkoerner.quotefmapi.entities.Category;
import de.rolandkoerner.quotefmapi.entities.User;

public class CategoryDAO extends DAO<Category> {

	private List<Category> categories;

	public CategoryDAO(IWebClient webClient) {
		super(webClient);
	}

	public List<Category> list() {
		if (categories == null) {
			String url = "https://quote.fm/api/category/list";
			categories = getList(url);
		}

		return categories;
	}

	public String getCommaSeparatedIds(int[] ids) {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < ids.length; i++) {
			stringBuilder.append(ids[i]);
			if (i < ids.length - 1)
				stringBuilder.append(',');
		}

		String idString = stringBuilder.toString();
		return idString;
	}

	public String getCommaSeparatedIds() {
		return getCommaSeparatedIds(list());
	}

	protected String getCommaSeparatedIds(List<Category> list) {

		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < list.size(); i++) {
			stringBuilder.append(list.get(i).getId());
			if (i < list.size() - 1)
				stringBuilder.append(',');
		}

		String idString = stringBuilder.toString();
		return idString;
	}

	@Override
	protected Category parse(JSONObject jsonObject) {

		int id = getId(jsonObject);
		Category category = new Category(id);

		try {
			String name = jsonObject.getString("name");
			category.setName(name);
		} catch (JSONException e) {
			category.setName("[Error]");
		}
		try {
			int order = jsonObject.getInt("order");
			category.setOrder(order);
		} catch (JSONException e) {
			category.setOrder(0);
		}

		try {
			JSONObject curatorJSONObject = jsonObject.getJSONObject("curator");
			User curator = APIWrapper.getInstance().users()
					.parse(curatorJSONObject);
			category.setCurator(curator);
		} catch (JSONException e) {
			category.setCurator(null);
		}

		return category;
	}

}
