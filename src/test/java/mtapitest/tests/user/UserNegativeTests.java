package mtapitest.tests.user;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import mtapitest.framework.BaseTest;

/***
 * @author shinumathew User - Negative scenarios are covered here
 */
@Feature("User - Negative tests")
public class UserNegativeTests extends BaseTest {

	/***
	 * Creates users with invalid data and validates it.
	 */
	@Severity(SeverityLevel.CRITICAL)
	@Description("Create user with invalid schema")
	@Story("Test Users CRUD APIs - Negative")
	@Test(enabled = true, priority = 1, description = "US22901")
	public void createUserInvalidSchema() {
		this.commonTestHelper.validateCreateUserIncorrectSchema();
	}
	
	/***
	 * Tries to get user, which is potentially not in the system and validates it 
	 */
	@Severity(SeverityLevel.CRITICAL)
	@Description("Fetch invalid user")
	@Story("Test Users CRUD APIs - Negative")
	@Test(enabled = true, priority = 2, description = "US22902")
	public void fetchInvalidUser() {
		this.commonTestHelper.fetchAndVerifyInvalidUser();
	}
	
	/***
	 * Updates invalid user and validates it 
	 */
	@Severity(SeverityLevel.CRITICAL)
	@Description("Update invalid user")
	@Story("Test Users CRUD APIs - Negative")
	@Test(enabled = true, priority = 2, description = "US22903")
	public void updateInvalidUser() {
		this.commonTestHelper.updateAndVerifyInvalidUser();
	}

	/***
	 * Deletes users created during the test.
	 */
	@AfterClass
	public void tearDownUserSetups() {
		this.commonTestHelper.deleteUsersFromCache();
	}
}
