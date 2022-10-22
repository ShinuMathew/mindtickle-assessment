package mtapitest.managers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mtapitest.constants.MTConstants;
import mtapitest.enums.mtenums.PetStatus;
import mtapitest.framework.InMemoryCache;
import mtapitest.objects.Category;
import mtapitest.objects.Tags;
import mtapitest.objects.request.Pet;
import mtapitest.objects.request.User;
import mtapitest.utils.CommonUtils;

/***
 * Data Provider class responsible for generating and providing relevant test
 * data
 * 
 * @author shinumathew
 */
public class DataProvider {

	private static final Logger log = LoggerFactory.getLogger(DataProvider.class);
	CommonUtils commonUtils = new CommonUtils();

	public DataProvider() {
		this.commonUtils = new CommonUtils();
	}

	/***
	 * @return random user details every time
	 */
	@SuppressWarnings("unchecked")
	public User generateRandomUser() {
		User user = User.builder().id(this.commonUtils.generateRandomId())
				.username(this.commonUtils.generateRandomName("TestUser")).firstName("Test").lastName("User")
				.email(this.commonUtils.generateRandomEmail()).password("Sinudfkj")
				.phone(this.commonUtils.generateRandomMobile()).userStatus(0).build();
		InMemoryCache.set(user.getEmail(), user);
		List<String> users = (InMemoryCache.get(MTConstants.TEMPUSER) != null)
				? (List<String>) InMemoryCache.get(MTConstants.TEMPUSER)
				: new ArrayList<String>();
		users.add(user.getUsername());
		log.info("Random user generated");
		return user;
	}

	/***
	 * @return random pets every time
	 */
	@SuppressWarnings("unchecked")
	public Pet generateRandomPet(PetStatus status) {
		Pet pet = Pet.builder().id(BigInteger.valueOf(this.commonUtils.generateRandomId(7))).name(this.commonUtils.generateRandomPetName())
				.category(Category.builder().id(BigInteger.valueOf(this.commonUtils.generateRandomId()))
						.name(this.commonUtils.generateRandomName("TestCategory")).build())
				.photoUrls(Arrays.asList(new String[] { "/photos/pic1", "/photos/pic2" }))
				.tags(Arrays.asList(Tags.builder().id(BigInteger.valueOf(this.commonUtils.generateRandomId()))
						.name(this.commonUtils.generateRandomName("TestTag")).build()))
				.status(status.getValue()).build();
		InMemoryCache.set(pet.getName(), pet);
		List<String> pets = (InMemoryCache.get(MTConstants.TEMPPET) != null)
				? (List<String>) InMemoryCache.get(MTConstants.TEMPPET)
				: new ArrayList<String>();
		pets.add(pet.getName());
		return pet;
	}
}