package de.rolandkoerner.quotefmapi.dao;

import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import de.rolandkoerner.quotefmapi.IWebClient;
import de.rolandkoerner.quotefmapi.entities.Page;

public class PageDAO extends DAO<Page> {

	public PageDAO(IWebClient webClient) {
		super(webClient);
	}

	public Page getById(int id) {
		Page page = null;
		page = getFromCache(id);
		if (page == null) {

			String url = "https://quote.fm/api/page/get/?id=%1d";
			String result = webClient.getContent(String.format(url, id));

			page = parse(result);
		}
		return page;
	}

	public Page getByName(String name) {
		String url = "https://quote.fm/api/page/get/?domain=%1s";
		String result = webClient.getContent(String.format(url, name));

		Page page = parse(result);
		return page;
	}

	public List<Page> list() {
		String url = "https://quote.fm/api/page/list/";
		String request = String.format(url);

		return getList(request);
	}

	public List<Page> list(int paginatorSize, int paginatorSkip) {
		String url = "https://quote.fm/api/page/list/&pageSize=%1&page=%2";
		String request = String.format(url, paginatorSize, paginatorSkip);

		return getList(request);
	}

	@Override
	protected Page parse(JSONObject jsonObject) {
		Page page = null;

		page = getFromCache(jsonObject);

		if (page == null) {
			int id = getId(jsonObject);
			page = new Page(id);

			try {
				String name = jsonObject.getString("name");
				page.setName(name);
			} catch (JSONException e) {
				page.setName("");
			}

			try {
				String description = jsonObject.getString("description");
				page.setDescription(description);
			} catch (JSONException e) {
				page.setDescription("");
			}

			try {
				String created = jsonObject.getString("created");
				Date date = new Date(Date.parse(created));
				page.setCreated(date);

			} catch (JSONException e) {
				page.setCreated(null);
			}

			cache.put(id, page);
			System.out.println("[New]\t" + page.toString());
		}

		return page;
	}

}