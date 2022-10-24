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
		User user1 = dp.generateRandomUser();
		Assert.assertNotNull(user1);
		User user2 = dp.generateRandomUser();
		Assert.assertNotEquals(user1.getEmail(), user2.getEmail());
	}
	
	@Test
	private void generateRandomPet() {
		Pet pet1 = dp.generateRandomPet(PetStatus.AVAILABLE);
		Assert.assertNotNull(pet1);
		Pet pet2 = dp.generateRandomPet(PetStatus.AVAILABLE);
		Assert.assertNotEquals(pet1.getName(), pet2.getName());
	}

}
