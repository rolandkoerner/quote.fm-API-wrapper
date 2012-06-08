import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.jetwick.snacktory.HtmlFetcher;
import de.jetwick.snacktory.JResult;
import de.rolandkoerner.quotefmapi.APIWrapper;
import de.rolandkoerner.quotefmapi.ApacheWebClient;
import de.rolandkoerner.quotefmapi.entities.Article;


public class SnacktoryTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		APIWrapper.init(new ApacheWebClient());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() throws Exception {
	
		Article article = Article.get(503);
		 HtmlFetcher fetcher = new HtmlFetcher();

		JResult res = fetcher.fetchAndExtract(article.getUrl(), 1000*10, true);
		 String text = res.getText(); 
		 String title = res.getTitle(); 
		 
		 System.err.println(title);
		 System.out.println(text);
	}

}
