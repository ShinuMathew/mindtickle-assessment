package mtapitest.tests.user;

import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import mtapitest.framework.BaseTest;

/***
 * @author shinumathew User - Negative scenarios
 */
@Feature("User - Negative tests")
public class UserNegativeTests extends BaseTest {

	@Severity(SeverityLevel.CRITICAL)
	@Description("Create user with invalid schema")
	@Story("Test Users CRUD APIs - Negative")
	@Test(enabled = true, priority = 1, description = "US22901")
	public void createUserInvalidSchema() {
		this.commonTestHelper.validateCreateUserIncorrectSchema();
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Fetch invalid user")
	@Story("Test Users CRUD APIs - Negative")
	@Test(enabled = true, priority = 2, description = "US22902")
	public void fetchInvalidUser() {
		this.commonTestHelper.fetchAndVerifyInvalidUser();
	}
	
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Update invalid user")
	@Story("Test Users CRUD APIs - Negative")
	@Test(enabled = true, priority = 2, description = "US22903")
	public void updateInvalidUser() {
		this.commonTestHelper.updateAndVerifyInvalidUser();
	}
}
