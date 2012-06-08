package de.rolandkoerner.quotefmapi.dao;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.rolandkoerner.quotefmapi.IWebClient;
import de.rolandkoerner.quotefmapi.entities.AbstractEntity;
import de.rolandkoerner.quotefmapi.exceptions.DAOException;

public abstract class DAO<Entity> {

	private final static int MAX_CACHE_SIZE = 200;
	// protected final static int PAGINATOR_SIZE = 100;

	protected LinkedHashMap<Integer, Entity> cache = new LinkedHashMap<Integer, Entity>(
			MAX_CACHE_SIZE, 0.75f);

	protected IWebClient webClient;

	private DAO() {
	}

	public DAO(IWebClient webClient) {
		this();
		this.webClient = webClient;
	}

	protected abstract Entity parse(JSONObject jsonObject);

	protected Entity parse(String jsonString) {
		try {
			return parse(new JSONObject(jsonString));
		} catch (JSONException e) {
			throw new DAOException(e, jsonString);
		}
	}

	protected List<Entity> getList(String url) {

		String result = webClient.getContent(url);

		try {
			JSONObject jsonObject = new JSONObject(result);
			JSONArray entities = (JSONArray) jsonObject.get("entities");

			return parseList(entities);

		} catch (JSONException e) {

			throw new DAOException(e, result);
		}
	}

	public List<Entity> parseList(JSONArray jsonArray) {
		List<Entity> list = new LinkedList<Entity>();
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				list.add(parse(jsonArray.getJSONObject(i)));
			} catch (JSONException e) {
				// ignore if parsing one entity fails
				// TODO log!
			}
		}

		return list;
	}

	protected int getId(JSONObject jsonObject) {
		Integer id = null;
		try {
			id = jsonObject.getInt("id");
		} catch (JSONException e) {
			throw new DAOException(e, jsonObject.toString());
		}
		return id;
	}



	protected Entity getFromCache(JSONObject jsonObject) {
		int id = getId(jsonObject);
		return getFromCache(id);
	}

	protected Entity getFromCache(int id) {
		if (cache.containsKey(id)) {
			Entity entity = cache.get(id);
			System.out.println("[Cache]\t" + entity.toString());
			return entity;
		} else
			return null;
	}
}
