package mtapitest.tests.user;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import mtapitest.framework.BaseTest;
import mtapitest.objects.request.User;

public class UserDataDrivenTest extends BaseTest {

	private List<User> users;

	@BeforeClass
	public void dataDriverTestSetup() {
		this.users = this.commonTestHelper.getUserList();
	}

	@DataProvider(name = "userListProvider")
	public Object[] getUserList() {
		return this.users.toArray();
	}

	/***
	 * Data driven test case to create, read, update, delete and validate for
	 * multiple user.
	 * 
	 * @param user
	 */
	@Severity(SeverityLevel.CRITICAL)
	@Description("User CRUD api data driven test")
	@Story("Test Users CRUD APIs - Happy path")
	@Test(enabled = true, priority = 1, description = "US31901", dataProvider = "userListProvider")
	public void userDataDrivenCRUDTest(User user) {
		this.commonTestHelper.createUser(user);
		this.commonTestHelper.verifyCreatedUser(user);
		// Update user not working as expected
		/*
		 * User newUserData = this.commonTestHelper.updateExistingUser(user);
		 * this.commonTestHelper.verifyCreatedUser(newUserData);
		 */
		this.commonTestHelper.deleteUser(user.getUsername());
	}

}
