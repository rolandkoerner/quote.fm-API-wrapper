package de.rolandkoerner.quotefmapi.entities;

import java.util.List;

import de.rolandkoerner.quotefmapi.APIWrapper;

public class Article extends AbstractEntity {

	public Article(int id) {
		super(id);
	}

	private static final long serialVersionUID = 8795840419862463599L;

	private String url;
	private String title;
	private long length;
	private int estimatedReadingTime;
	private String language;
	private double popularity;
	private Recommendation topRecommendation;

	public static Article get(int id) {
		return APIWrapper.getInstance().getArticleDAO().getById(id);
	}
	
	public static List<Article> list(String language, String scope) {
		String categoryIds = APIWrapper.getInstance().getCategoryDAO()
				.getCommaSeparatedIds();
		return APIWrapper.getInstance().getArticleDAO()
				.listByCategoryIds(categoryIds, language, scope);
	}

	public List<Recommendation> getRecommendations() {
		return APIWrapper.getInstance().getRecommendationDAO()
				.listByArticleId(this.getId());
	}


	public Recommendation getTopRecommendation() {
		return topRecommendation;
	}

	public String getTitle() {
		return title;
	}

	public long getLength() {
		return length;
	}

	public int getEstimatedReadingTime() {
		return estimatedReadingTime;
	}

	public String getLanguage() {
		return language;
	}

	public double getPopularity() {
		return popularity;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public void setEstimatedReadingTime(int estimatedReadingTime) {
		this.estimatedReadingTime = estimatedReadingTime;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public void setTopRecommendation(Recommendation topRecommendation) {
		this.topRecommendation = topRecommendation;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", url=" + url + ", title=" + title
				+ ", length=" + length + ", estimatedReadingTime="
				+ estimatedReadingTime + ", language=" + language
				+ ", popularity=" + popularity + ", topRecommendation="
				+ topRecommendation + "]";
	}

	public String getUrl() {
		return url;
	}

}
