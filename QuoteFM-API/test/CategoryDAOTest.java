import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.rolandkoerner.quotefmapi.APIWrapper;
import de.rolandkoerner.quotefmapi.ApacheWebClient;
import de.rolandkoerner.quotefmapi.entities.Article;

public class CategoryDAOTest {

	@Before
	public void before() {
		APIWrapper.init(new ApacheWebClient());
	}

	@Test
	public void test() {
//		List<Category> categories = APIWrapper.getInstance().categories()
//				.list();
//		for (Category category : categories) {
//
//			System.out.println(category);
//		}

		
		List<Article> articles = Article.list("any", "time");

	}

}
