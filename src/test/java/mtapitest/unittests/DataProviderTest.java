package mtapitest.unittests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import mtapitest.enums.mtenums.PetStatus;
import mtapitest.managers.DataProvider;
import mtapitest.objects.request.Pet;
import mtapitest.objects.request.User;

public class DataProviderTest {
	
	DataProvider dp;
	
	@BeforeTest
	private void setup() {
		dp = new DataProvider();
	}
	
	@Test
	private void getRandomUsers() {
		User user = dp.generateRandomUser();
		Assert.assertNotNull(user);
	}
	
	@Test
	private void generateRandomPet() {
		Pet pet = dp.generateRandomPet(PetStatus.AVAILABLE);
		Assert.assertNotNull(pet);
	}

}
