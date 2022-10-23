package mtapitest.tests.pet;

import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import mtapitest.enums.mtenums.PetStatus;
import mtapitest.framework.BaseTest;

/***
 * @author shinumathew User - Happy path test cases
 */
@Feature("Pet - Negative tests")
public class PetNegativeTests extends BaseTest {
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Create pet with invalid schema")
	@Story("Test Pet CRUD APIs - Negative")
	@Test(enabled = true, priority = 1, description = "PE12901")
	public void createPetInvalidSchema() {
		this.commonTestHelper.createAndValidateInvalidPet();
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Fetch pet with invalid status")
	@Story("Test Pet CRUD APIs - Negative")
	@Test(enabled = true, priority = 2, description = "US22902")
	public void fetchInvalidUser() {
		this.commonTestHelper.getPetByInvalidStatus(PetStatus.INVALID);
	}
}