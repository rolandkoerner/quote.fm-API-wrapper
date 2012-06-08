import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.rolandkoerner.quotefmapi.ApacheWebClient;
import de.rolandkoerner.quotefmapi.dao.UserDAO;
import de.rolandkoerner.quotefmapi.entities.User;


public class TestUserDAO {

	static UserDAO userDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		userDAO = new UserDAO(new ApacheWebClient());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUser() {
		String userName = "schnellleser9000";
		int userId = 4087;
		
		User user1 = userDAO.getById(4087);
		User user3 = userDAO.getById(4087);
		User user4 = userDAO.getById(4087);
		assertEquals(userName, user1.getUserName());
		
		User user2 = userDAO.getByUserName(userName);
		assertEquals(userId, user2.getId());
	}
	
	@Test
	public void testListFollowers(){
		String userName = "schnellleser9000";
		
		List<User> actual = userDAO.listFollowers(userName);
		assertEquals(1, actual.size());
	}
	
	@Test
	public void testListFollowings(){
		String userName = "schnellleser9000";
		
		List<User> actual = userDAO.listFollowings(userName);
		assertEquals(5, actual.size());
	}

}
