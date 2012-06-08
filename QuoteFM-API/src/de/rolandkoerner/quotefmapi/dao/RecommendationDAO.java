package de.rolandkoerner.quotefmapi.dao;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.rolandkoerner.quotefmapi.APIWrapper;
import de.rolandkoerner.quotefmapi.IWebClient;
import de.rolandkoerner.quotefmapi.entities.Article;
import de.rolandkoerner.quotefmapi.entities.Comment;
import de.rolandkoerner.quotefmapi.entities.Page;
import de.rolandkoerner.quotefmapi.entities.Recommendation;
import de.rolandkoerner.quotefmapi.entities.User;

public class RecommendationDAO extends DAO<Recommendation> {

	public RecommendationDAO(IWebClient webClient) {
		super(webClient);
	}

	public Recommendation getById(int id) {
		Recommendation recommendation = null;

		recommendation = getFromCache(id);

		if (recommendation == null) {
			String url = "https://quote.fm/api/recommendation/get/?id=%1d";
			String result = webClient.getContent(String.format(url, id));

			recommendation = parse(result);
		} 
		return recommendation;

	}

	public List<Recommendation> listByUserName(String userName) {
		String url = "https://quote.fm/api/recommendation/listByUser/?username=%1s";
		String request = String.format(url, userName);

		return getList(request);
	}

	public List<Recommendation> listByUserName(String userName, String scope,
			int paginatorSize, int paginatorSkip) {
		String url = "https://quote.fm/api/recommendation/listByUser/?username=%1s&scope=%2&pageSize=%3&page=%4";
		String request = String.format(url, userName, scope, paginatorSize,
				paginatorSkip);

		return getList(request);
	}

	public List<Recommendation> listByArticleId(long articleId) {

		String url = "https://quote.fm/api/recommendation/listByArticle/?id=%1d";
		String request = String.format(url, articleId);

		return getList(request);
	}

	public List<Recommendation> listByArticleId(long articleId, String scope,
			int paginatorSize, int paginatorSkip) {

		String url = "https://quote.fm/api/recommendation/listByArticle?id=%1d&scope=%2&pageSize=%3&page=%4";
		String request = String.format(url, articleId, scope, paginatorSize,
				paginatorSkip);

		return getList(request);
	}

	public List<Recommendation> listByUser(User user) {
		if (user == null)
			return new LinkedList<Recommendation>();
		else
			return listByUserName(user.getUserName());
	}

	public List<Recommendation> listByUser(User user, String scope,
			int paginatorSize, int paginatorSkip) {
		if (user == null)
			return new LinkedList<Recommendation>();
		else
			return listByUserName(user.getUserName(), scope, paginatorSize,
					paginatorSkip);
	}

	public List<Recommendation> listByArticle(Article article) {
		if (article == null)
			return new LinkedList<Recommendation>();
		else
			return listByArticleId(article.getId());
	}

	public List<Recommendation> listByArticle(Article article, String scope,
			int paginatorSize, int paginatorSkip) {
		if (article == null)
			return new LinkedList<Recommendation>();
		else
			return listByArticleId(article.getId(), scope, paginatorSize,
					paginatorSkip);
	}

	@Override
	protected Recommendation parse(JSONObject jsonObject) {
		Recommendation recommendation = null;

		recommendation = getFromCache(jsonObject);

		if (recommendation == null) {
			int id = getId(jsonObject);
			recommendation = new Recommendation(id);
			
			try {
				String quote = jsonObject.getString("quote");
				recommendation.setQuote(quote);
			} catch (JSONException e) {
				recommendation.setQuote("");
			}

			try {
				Double popularity = jsonObject.getDouble("popularity");
				recommendation.setPopularity(popularity);
			} catch (JSONException e) {
				recommendation.setPopularity(0d);
			}

			try {
				Integer userId = jsonObject.getInt("user_id");
				recommendation.setUserId(userId);
			} catch (JSONException e) {
				recommendation.setUserId(0);
			}

			try {
				Integer articleId = jsonObject.getInt("article_id");
				recommendation.setArticleId(articleId);
			} catch (JSONException e) {
				recommendation.setArticleId(0);
			}

			try {
				Integer pageId = jsonObject.getInt("page_id");
				recommendation.setPageId(pageId);
			} catch (JSONException e) {
				recommendation.setPageId(0);
			}

			try {
				String created = jsonObject.getString("created");
				Date date = new Date(Date.parse(created));
				recommendation.setCreated(date);

			} catch (JSONException e) {
				recommendation.setCreated(null);
			}

			try {
				JSONObject articleObject = jsonObject.getJSONObject("article");
				Article article = APIWrapper.getInstance().articles()
						.parse(articleObject);
				recommendation.setArticle(article);
			} catch (JSONException e) {
				recommendation.setArticle(null);
			}

			try {
				JSONObject userObject = jsonObject.getJSONObject("user");
				User user = APIWrapper.getInstance().users().parse(userObject);
				recommendation.setUser(user);
			} catch (JSONException e) {
				recommendation.setUser(null);
			}

			try {
				JSONObject pageObject = jsonObject.getJSONObject("domain");
				Page page = APIWrapper.getInstance().pages().parse(pageObject);
				recommendation.setPage(page);
			} catch (JSONException e) {
				recommendation.setPage(null);
			}

			try {
				JSONArray commentsArray = jsonObject.getJSONArray("comments");
				List<Comment> comments = APIWrapper.getInstance().comments()
						.parseList(commentsArray);
				recommendation.setComments(comments);
			} catch (JSONException e) {
				recommendation.setComments(new LinkedList<Comment>());
			}

			cache.put(id, recommendation);
			System.out.println("[New]\t" + recommendation.toString());

		}
		return recommendation;
	}
}
