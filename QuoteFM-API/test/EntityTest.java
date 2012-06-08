import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.rolandkoerner.quotefmapi.APIWrapper;
import de.rolandkoerner.quotefmapi.ApacheWebClient;
import de.rolandkoerner.quotefmapi.entities.Article;
import de.rolandkoerner.quotefmapi.entities.Page;
import de.rolandkoerner.quotefmapi.entities.Recommendation;
import de.rolandkoerner.quotefmapi.entities.User;

public class EntityTest {

	@Before
	public void setUp() throws Exception {
		APIWrapper.init(new ApacheWebClient());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Article article = Article.get(500);
		assertNotNull(article);

		User user = User.get(184);

		List<Recommendation> list = user.getRecommendations();

		for (Recommendation recommendation : list) {
			recommendation.getArticle();
		}
		
		Page page = Page.get(124);
		page.getArticles();
	}
}
