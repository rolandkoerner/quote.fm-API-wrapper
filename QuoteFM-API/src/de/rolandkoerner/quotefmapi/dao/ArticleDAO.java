package de.rolandkoerner.quotefmapi.dao;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import de.rolandkoerner.quotefmapi.APIWrapper;
import de.rolandkoerner.quotefmapi.IWebClient;
import de.rolandkoerner.quotefmapi.entities.Article;
import de.rolandkoerner.quotefmapi.entities.Recommendation;

public class ArticleDAO extends DAO<Article> {

	public ArticleDAO(IWebClient webClient) {
		super(webClient);
	}

	public Article getById(int id) {

		Article article = null;

		article = getFromCache(id);

		if (article == null) {
			String url = "https://quote.fm/api/article/get/?id=%1d";
			String result = webClient.getContent(String.format(url, id));

			article = parse(result);

			System.out.println("[New]\t" + article.toString());
		} else
			System.out.println("[Cache]\t" + article.toString());

		return article;
	}

	public Article getByUrl(String articleUrl) {

		for (Article article : cache.values()) {
			if (article.getUrl().equals(articleUrl)) {
				System.out.println("[Cache]\t" + article.toString());
				return article;
			}
		}

		String url = "https://quote.fm/api/article/get/?url=1";
		String result = webClient.getContent(String.format(url, articleUrl));

		Article article = parse(result);

		return article;
	}

	public List<Article> listByPageId(long pageId) {
		return listByPageId(pageId, "time", 100, 0);
	}

	public List<Article> listByPageId(long pageId, String scope,
			int paginatorSize, int paginatorSkip) {
		String url = "https://quote.fm/api/article/listByPage?id=%d&scope=%s&pageSize=%d&page=%d";
		String request = String.format(url, pageId, scope, paginatorSize,
				paginatorSkip);

		return getList(request);
	}

	public List<Article> listByCategoryIds(String categoryIds) {
		return listByCategoryIds(categoryIds, "any", "time");
	}

	public List<Article> listByCategoryIds(String categoryIds, String language,
			String scope) {
		return listByCategoryIds(categoryIds, language, scope, 100, 0);
	}

	public List<Article> listByCategoryIds(String categoryIds, String language,
			String scope, int paginatorSize, int paginatorSkip) {

		String url = "https://quote.fm/api/article/listByCategories?ids=%s&language=%s&scope=%s&pageSize=%d&page=%d";
		String request = String.format(url, categoryIds, language, scope,
				paginatorSize, paginatorSkip);

		return getList(request);
	}

	@Override
	protected Article parse(JSONObject jsonObject) {
		Article article = null;

		article = getFromCache(jsonObject);

		if (article == null) {
			int id = getId(jsonObject);
			article = new Article(id);

			try {
				String title = jsonObject.getString("title");
				article.setTitle(title);
			} catch (JSONException e) {
				article.setTitle("");
			}

			try {
				Integer estimatedReadingTime = jsonObject.getInt("ert");
				article.setEstimatedReadingTime(estimatedReadingTime);
			} catch (JSONException e) {
				article.setEstimatedReadingTime(0);
			}

			try {
				String language = jsonObject.getString("language");
				article.setLanguage(language);
			} catch (JSONException e) {
				article.setLanguage("");
			}

			try {
				Integer length = jsonObject.getInt("length");
				article.setLength(length);
			} catch (JSONException e) {
				article.setLength(0);
			}

			try {
				Double popularity = jsonObject.getDouble("popularity");
				article.setPopularity(popularity);
			} catch (JSONException e) {
				article.setPopularity(0d);
			}

			try {
				String url = jsonObject.getString("url");
				article.setUrl(url);
			} catch (JSONException e) {
				article.setUrl("");
			}

			try {
				JSONObject recommendationObject = jsonObject
						.getJSONObject("topquote");
				Recommendation recommendation = APIWrapper.getInstance()
						.getRecommendationDAO().parse(recommendationObject);
				article.setTopRecommendation(recommendation);
			} catch (JSONException e) {
				article.setTopRecommendation(null);
			}

			cache.put(id, article);
		}

		return article;

	}

}
