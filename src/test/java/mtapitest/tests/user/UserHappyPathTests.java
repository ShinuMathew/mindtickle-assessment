package mtapitest.tests.user;

import java.util.List;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import mtapitest.framework.BaseTest;
import mtapitest.objects.request.User;

/***
 * @author shinumathew User - Happy path test cases
 */
@Feature("Users - Happy path tests")
public class UserHappyPathTests extends BaseTest {
	
	private User user;

	@Severity(SeverityLevel.CRITICAL)
	@Description("Create single user and validate")
	@Story("Test Users CRUD APIs - Happy path")
	@Test(enabled = true, priority = 1, description = "US21901")
	public void createAndValidateSingleUser() {
		this.user = this.commonTestHelper.createAndValidateSingleUser();
		this.commonTestHelper.verifyCreatedUser(this.user);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("Update user and validate")
	@Story("Test Users CRUD APIs - Happy path")
	@Test(enabled = true, priority = 2, description = "US21902")
	public void updateAndValidateUser() {
		User newUserData = this.commonTestHelper.updateExistingUser(this.user);
		this.commonTestHelper.verifyCreatedUser(newUserData);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("Create multiple user and validate")
	@Story("Test Users CRUD APIs - Happy path")
	@Test(enabled = true, priority = 3, description = "US21903")
	public void createAndValidateMultipleUser() {
		List<User> users = this.commonTestHelper.createAndValidateMultipleUser(3);
		this.commonTestHelper.verifyCreatedUsers(users);
	}
}
