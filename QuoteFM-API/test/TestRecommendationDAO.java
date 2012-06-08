import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.rolandkoerner.quotefmapi.APIWrapper;
import de.rolandkoerner.quotefmapi.ApacheWebClient;
import de.rolandkoerner.quotefmapi.entities.Recommendation;


public class TestRecommendationDAO {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		APIWrapper.init(new ApacheWebClient());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
			Recommendation actual = APIWrapper.getInstance().recommendations().getById(600);

			
			APIWrapper.getInstance().recommendations().getById(600);

	}

}
