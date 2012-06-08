import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.rolandkoerner.quotefmapi.APIWrapper;
import de.rolandkoerner.quotefmapi.dao.ArticleDAO;
import de.rolandkoerner.quotefmapi.entities.AbstractEntity;
import de.rolandkoerner.quotefmapi.entities.Article;
import de.rolandkoerner.quotefmapi.entities.Category;
import de.rolandkoerner.quotefmapi.entities.Recommendation;

public class TestArticleDAO {

	static ArticleDAO articleDAO;

	@Before
	public void before() {
		articleDAO = APIWrapper.getInstance().getArticleDAO();
	}

	@Test
	public void testGetArticle() {
		Article expected1 = new Article(1);
		expected1.setId(1);
		expected1.setTitle("NO BLASTERS!");
		expected1.setEstimatedReadingTime(11);
		expected1.setLanguage("en");
		expected1.setLength(2633l);
		expected1.setPopularity(-512.406d);
		expected1.setTopRecommendation(new Recommendation(1213213));
		expected1
				.setUrl("http://noblasters.com/post/1650102322/my-tsa-encounter");

		Article expected2 = new Article(21);
		expected2.setId(2l);
		expected2
				.setTitle("Why Products Suck (And How To Make Them Suck\u00A0Less)");

		Article actual1 = null;
		Article actual2 = null;
		actual1 = articleDAO.getById(1);
		actual2 = articleDAO.getById(2);

		assertNotNull(actual1);
		assertEquals(expected1.getId(), actual1.getId());
		assertEquals(expected1.getTitle(), actual1.getTitle());
		assertEquals(expected2.getTitle(), actual2.getTitle());

		assertEquals(expected1.getEstimatedReadingTime(),
				actual1.getEstimatedReadingTime());
		assertEquals(expected1.getLanguage(), actual1.getLanguage());
		assertEquals(expected1.getLength(), actual1.getLength());

		// Changes all the Time!
		// assertEquals(expected1.getPopularity(), actual1.getPopularity(), 0d);

		assertEquals(expected1.getUrl(), actual1.getUrl());

	}

	@Test
	public void testGetArticlesByPage() {
		int expectedCount = 57;

		List<Article> actual = null;
		actual = articleDAO.listByPageId(23);

		assertTrue(expectedCount <= actual.size());
	}

	@Test
	public void testGetArticlesByCategories() {
		int expectedCount = 15;

		String categories =  "1,2,4,5,3";

		List<Article> actual = null;
		actual = articleDAO.listByCategoryIds(categories);

		assertEquals(expectedCount, actual.size());


	}
}
