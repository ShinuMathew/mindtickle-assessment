package mtapitest.tests;

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
 * @author shinumathew User test cases
 */
@Feature("Users")
public class UserTests extends BaseTest {

	@Severity(SeverityLevel.CRITICAL)
	@Description("Create single user and validate")
	@Story("Test Pet CRUD APIs")
	@Test(enabled = true, priority = 1, description = "US21901")
	public void createAndValidateSingleUser() {
		User user = this.commonTestHelper.createAndValidateSingleUser();
		this.commonTestHelper.verifyCreatedUser(user);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("Update user and validate")
	@Story("Test Pet CRUD APIs")
	@Test(enabled = true, priority = 2, description = "US21902")
	public void updateAndValidateUser() {
		User user = this.commonTestHelper.updateExistingUser();
		this.commonTestHelper.verifyCreatedUser(user);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("Create multiple user and validate")
	@Story("Test Pet CRUD APIs")
	@Test(enabled = true, priority = 3, description = "US21903")
	public void createAndValidateMultipleUser() {
		List<User> users = this.commonTestHelper.createAndValidateMultipleUser(3);
		this.commonTestHelper.verifyCreatedUsers(users);
	}
}
