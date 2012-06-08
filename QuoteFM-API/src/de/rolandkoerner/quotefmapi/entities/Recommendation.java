package de.rolandkoerner.quotefmapi.entities;

import java.util.Date;
import java.util.List;

import de.rolandkoerner.quotefmapi.APIWrapper;

public class Recommendation extends AbstractEntity {

	public Recommendation(int id) {
		super(id);
		
	}

	private static final long serialVersionUID = -7208191992197110147L;

	private int articleId;
	private int pageId;
	private int userId;

	private String quote;
	private double popularity;
	private Date created;

	private User user;
	private Article article;
	private Page page;
	private List<Comment> comments;
	
	public static Recommendation get(int id){
		return APIWrapper.getInstance().recommendations().getById(id);
	}

	public Article getArticle() {
		if (article == null && articleId > 0)
			setArticle(APIWrapper.getInstance().articles().getById(articleId));
		return article;
	}

	public User getUser() {
		if (user == null && userId > 0)
			setUser(APIWrapper.getInstance().users().getById(userId));
		return user;
	}

	public Page getPage() {
		if (page == null && pageId > 0)
			setPage(APIWrapper.getInstance().pages().getById(pageId));
		return page;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public int getArticleId() {
		return articleId;
	}

	public Date getCreated() {
		return created;
	}

	public int getPageId() {
		return pageId;
	}

	public double getPopularity() {
		return popularity;
	}

	public String getQuote() {
		return quote;
	}

	public int getUserId() {
		return userId;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Recommendation [articleId=" + articleId + ", pageId=" + pageId
				+ ", userId=" + userId + ", quote=" + quote + ", popularity="
				+ popularity + ", created=" + created + ", user=" + user
				+ ", article=" + article + ", page=" + page + ", comments="
				+ comments + "]";
	}

}
