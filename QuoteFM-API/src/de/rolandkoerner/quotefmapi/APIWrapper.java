package de.rolandkoerner.quotefmapi;

import de.rolandkoerner.quotefmapi.dao.ArticleDAO;
import de.rolandkoerner.quotefmapi.dao.CategoryDAO;
import de.rolandkoerner.quotefmapi.dao.CommentDAO;
import de.rolandkoerner.quotefmapi.dao.PageDAO;
import de.rolandkoerner.quotefmapi.dao.RecommendationDAO;
import de.rolandkoerner.quotefmapi.dao.UserDAO;

public class APIWrapper {

	private static APIWrapper instance;

	private ArticleDAO articleDAO = null;
	private RecommendationDAO recommendationDAO = null;
	private UserDAO userDAO = null;
	private PageDAO pageDAO = null;
	private CommentDAO commentDAO = null;
	private CategoryDAO categoryDAO = null;
	
	private APIWrapper() {
		this(new ApacheWebClient());
	}

	private APIWrapper(IWebClient webClient) {
		articleDAO = new ArticleDAO(webClient);
		recommendationDAO = new RecommendationDAO(webClient);
		userDAO = new UserDAO(webClient);
		pageDAO = new PageDAO(webClient);
		commentDAO = new CommentDAO(webClient);
		categoryDAO = new CategoryDAO(webClient);
	}
	
	public static void init(IWebClient webClient){
		instance = new APIWrapper(webClient);
	}

	public static APIWrapper getInstance() {
		if (instance == null)
			instance = new APIWrapper();

		return instance;
	}

	public ArticleDAO articles() {
		return articleDAO;
	}

	public RecommendationDAO recommendations() {
		return recommendationDAO;
	}
	public UserDAO users() {
		return userDAO;
	}
	
	public PageDAO pages(){
		return pageDAO;
	}
	
	public CommentDAO comments(){
		return commentDAO;
	}
	
	public CategoryDAO categories(){
		return categoryDAO;
	}
}
