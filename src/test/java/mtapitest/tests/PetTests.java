package mtapitest.tests;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import mtapitest.enums.mtenums.PetStatus;
import mtapitest.framework.BaseTest;
import mtapitest.objects.request.Pet;

/***
 * @author shinumathew Pet test cases
 */
@Feature("Pets")
public class PetTests extends BaseTest {

	@Severity(SeverityLevel.CRITICAL)
    @Description("Create a pet and validate details")
    @Story("Test Pet CRUD APIs")
	@Test(enabled = true, priority = 1, description = "PE11901")
	public void createAndValidatePet() throws Exception {
		Pet pet = this.commonTestHelper.createNewPet();
		this.commonTestHelper.verifyCreatedPet(pet);
	}

	@Severity(SeverityLevel.CRITICAL)
    @Description("Update pet as PENDING and validate details")
    @Story("Test Pet CRUD APIs")
	@Test(enabled = true, priority = 2, description = "PE11902")
	public void updateAndValidatePendingPets() throws Exception {
		this.commonTestHelper.updateAndVerifyPet(PetStatus.PENDING);
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Update pet as SOLD and validate details")
	@Story("Test Pet CRUD APIs")
	@Test(enabled = true, priority = 2, description = "PE11902")
	public void updateAndValidateSoldPets() throws Exception {
		this.commonTestHelper.updateAndVerifyPet(PetStatus.SOLD);
	}

}
