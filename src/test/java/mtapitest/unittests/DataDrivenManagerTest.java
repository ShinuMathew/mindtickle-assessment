package mtapitest.unittests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import mtapitest.managers.DataDrivenManager;
import mtapitest.objects.request.User;

public class DataDrivenManagerTest {
	
	private DataDrivenManager ddManager;

	@BeforeTest
	private void testSetup() throws Exception {
		this.ddManager = new DataDrivenManager();
	}

	@Test(priority = 1)
	private void loadUsers() throws Exception {
		List<User> users = this.ddManager.loadUserDataFromExcel();
		Assert.assertNotNull(users);
	}
}
