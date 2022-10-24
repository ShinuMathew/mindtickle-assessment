package mtapitest.tests.user;

import java.util.List;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import mtapitest.framework.BaseTest;
import mtapitest.objects.request.User;

/***
 * @author shinumathew User - Happy path scenarios are covered here
 */
@Feature("Users - Happy path tests")
public class UserHappyPathTests extends BaseTest {

	private User user;

	/***
	 * Creates a single user and validates it.
	 */
	@Severity(SeverityLevel.CRITICAL)
	@Description("Create single user and validate")
	@Story("Test Users CRUD APIs - Happy path")
	@Test(enabled = true, priority = 1, description = "US21901")
	public void createAndValidateSingleUser() {
		this.user = this.commonTestHelper.createAndValidateSingleUser();
		this.commonTestHelper.verifyCreatedUser(this.user);
	}

	/***
	 * Gets an existing user from cache or creates a random user, updates it and
	 * validates it.
	 */
	@Severity(SeverityLevel.CRITICAL)
	@Description("Update user and validate")
	@Story("Test Users CRUD APIs - Happy path")
	@Test(enabled = true, priority = 2, description = "US21902")
	public void updateAndValidateUser() {
		User newUserData = this.commonTestHelper.updateExistingUser(this.user);
		this.commonTestHelper.verifyCreatedUser(newUserData);
	}

	/***
	 * Creates multiple users and validates each of them.
	 */
	@Severity(SeverityLevel.CRITICAL)
	@Description("Create multiple user and validate")
	@Story("Test Users CRUD APIs - Happy path")
	@Test(enabled = true, priority = 3, description = "US21903")
	public void createAndValidateMultipleUser() {
		List<User> users = this.commonTestHelper.createAndValidateMultipleUser(3);
		this.commonTestHelper.verifyCreatedUsers(users);
	}

	/***
	 * Deletes users created during the test.
	 */
	@AfterTest
	public void tearDownUserSetups() {
		this.commonTestHelper.deleteUsersFromCache();
	}
}
