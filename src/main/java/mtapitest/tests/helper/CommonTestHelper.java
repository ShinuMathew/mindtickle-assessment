package mtapitest.tests.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jayway.restassured.response.Response;

import mtapitest.constants.MTConstants;
import mtapitest.enums.mtenums.PetStatus;
import mtapitest.framework.EntityMapper;
import mtapitest.framework.InMemoryCache;
import mtapitest.framework.MTTestFramework;
import mtapitest.managers.AssertionManager;
import mtapitest.managers.DataProvider;
import mtapitest.objects.request.Pet;
import mtapitest.objects.request.User;
import mtapitest.objects.response.MTCommonResponse;
import mtapitest.services.PetService;
import mtapitest.services.UserService;

/***
 * Common test helper class for calling services, managers and data providers
 * 
 * @author shinumathew
 *
 */
public class CommonTestHelper extends MTTestFramework {

	private static final Logger log = LoggerFactory.getLogger(CommonTestHelper.class);

	protected DataProvider dataProvider;
	protected EntityMapper entityMapper;
	protected AssertionManager assertionManager;

	// ------- SERVICES ------- //
	protected UserService userService;
	protected PetService petService;

	public CommonTestHelper() throws Exception {
		this.dataProvider = new DataProvider();
		this.entityMapper = new EntityMapper();
		this.assertionManager = new AssertionManager();

		this.userService = new UserService();
		this.petService = new PetService();
	}

	// ---------- USER SERVICE TEST HELPERS ---------- //

	public Response createUsers(List<User> userList) {
		Response response = null;
		try {
			log.info("Creating user profile for user : "
					+ userList.stream().map(user -> user.getUsername()).collect(Collectors.joining(", ")));
			String reqBody = this.entityMapper.serializePayload(userList);
			response = this.userService.createUserWithArray(reqBody);
			assertionManager.assertStatusSuccess(response.statusCode());
		} catch (Exception e) {
			assertionManager.assertExceptionFailures(e);
		}
		return response;
	}

	private User getRandomUser() {
		log.info("Generating random user...");
		return this.dataProvider.generateRandomUser();
	}

	public User createAndValidateSingleUser() {
		List<User> userList = new ArrayList<User>();
		try {
			User user = getRandomUser();
			userList.add(user);
			Response response = createUsers(userList);
			MTCommonResponse responseObj = (MTCommonResponse) this.entityMapper
					.deSerializePayload(response.body().asString(), new MTCommonResponse());
			assertionManager.assertCommonMTResponse(responseObj.getCode(), 200);
			log.info("User profile created and validated successfully!");
		} catch (Exception e) {
			assertionManager.assertExceptionFailures(e);
		}
		return userList.get(0);
	}

	public List<User> createAndValidateMultipleUser(int count) {
		List<User> userList = new ArrayList<User>();
		try {
			for (int i = 0; i < count; i++)
				userList.add(getRandomUser());
			Response response = createUsers(userList);
			MTCommonResponse responseObj = (MTCommonResponse) this.entityMapper
					.deSerializePayload(response.body().asString(), new MTCommonResponse());
			assertionManager.assertCommonMTResponse(responseObj.getCode(), 200);
			log.info("User profiles created and validated successfully!");
		} catch (Exception e) {
			assertionManager.assertExceptionFailures(e);
		}
		return userList;
	}

	public void verifyCreatedUser(User user) {
		try {
			log.info("Fetching user profile for user : " + user.getUsername());
			Response response = this.userService.getUser(user);
			assertionManager.assertResponseSchema(response.body().asString(), this.entityMapper.serializePayload(user),
					JSONCompareMode.STRICT);
		} catch (Exception e) {
			assertionManager.assertExceptionFailures(e);
		}
	}

	public void verifyCreatedUsers(List<User> users) {
		users.forEach(this::verifyCreatedUser);
	}

	public User updateExistingUser() {
		User newUserData = getRandomUser();
		;
		try {
			User oldUserData = getTemporaryUser();
			Response response = this.userService.updateUser(oldUserData.getUsername(),
					this.entityMapper.serializePayload(newUserData));
			assertionManager.assertStatusSuccess(response.statusCode());
			MTCommonResponse responseObj = (MTCommonResponse) this.entityMapper
					.deSerializePayload(response.body().asString(), new MTCommonResponse());
			assertionManager.assertCommonMTResponse(responseObj.getCode(), 200);
		} catch (Exception e) {
			assertionManager.assertExceptionFailures(e);
		}
		return newUserData;
	}

	@SuppressWarnings("unchecked")
	private User getTemporaryUser() {
		User user = null;
		try {
			log.info("Fetching a user from cache...");
			ArrayList<String> tempUsers = (ArrayList<String>) InMemoryCache.get(MTConstants.TEMPUSER);
			if (ObjectUtils.isEmpty(tempUsers)) {
				log.info("No users found in cache. Creating a temporary user");
				log.info("Generating random user...");
				List<User> userList = new ArrayList<User>();
				user = this.dataProvider.generateRandomUser();
				userList.add(user);
				createUsers(userList);
			} else {
				Collections.shuffle(tempUsers);
				String username = tempUsers.get(0);
				user = (User) InMemoryCache.get(username);
				log.info("Found user : " + username + " from cache");
			}
		} catch (Exception e) {
			assertionManager.assertExceptionFailures(e);
		}
		return user;
	}

	// ---------- PET SERVICE TEST HELPERS ---------- //

	public Pet createNewPet() {
		Pet pet = null;
		try {
			pet = this.dataProvider.generateRandomPet(PetStatus.AVAILABLE);			
			this.createPet(pet);					
		} catch (Exception e) {
			assertionManager.assertExceptionFailures(e);
		}
		return pet;
	}
	
	public Response createPet(Pet pet) {
		Response response = null;
		try {
			String requestBody = this.entityMapper.serializePayload(pet);
			response = this.petService.createPet(requestBody);
			assertionManager.assertStatusSuccess(response.statusCode());
			assertionManager.assertResponseSchema(response.body().asString(), requestBody, JSONCompareMode.STRICT);
		} catch (Exception e) {
			assertionManager.assertExceptionFailures(e);
		}
		return response;
	}

	public Pet getPetByStatus(Pet expectedPet, PetStatus status) {
		Pet actualPet = null;
		try {
			Response response = this.petService.getPet(status.getValue());
			List<Pet> pets = this.entityMapper.getObjectMapper().readValue(response.body().asString(),
					new TypeReference<List<Pet>>() { });
			actualPet = pets.stream().filter(p -> p.getId().equals(expectedPet.getId())).collect(Collectors.toList()).get(0);
		} catch (Exception e) {
			assertionManager.assertExceptionFailures(e);
		}
		return actualPet;
	}
	
	public void verifyCreatedPet(Pet expectedPet) {
		try {
			log.info("Fetching details for pet : " + expectedPet.getName());
			Pet actualPet = getPetByStatus(expectedPet, PetStatus.valueOf(expectedPet.getStatus().toUpperCase()));
			assertionManager.assertResponseSchema(this.entityMapper.serializePayload(actualPet), this.entityMapper.serializePayload(expectedPet), JSONCompareMode.STRICT_ORDER);
			assertionManager.assertPetResponse(actualPet, expectedPet);
		} catch (Exception e) {
			assertionManager.assertExceptionFailures(e);
		}
	}
	
	public Pet updatePet(PetStatus status) {
		Pet pet = null;
		try {
			pet = this.getTemporaryPetData();
			pet.setStatus(status.getValue());
			String reqBody = this.entityMapper.serializePayload(pet);
			Response response = this.petService.updatePet(reqBody);
			assertionManager.assertStatusSuccess(response.statusCode());
			assertionManager.assertResponseSchema(response.body().asString(), reqBody, JSONCompareMode.STRICT);
		} catch (Exception e) {
			assertionManager.assertExceptionFailures(e);
		}
		return pet;
	}
	
	public void updateAndVerifyPet(PetStatus status) {
		Pet expectedPet = this.updatePet(status);
		Pet actualPet = getPetByStatus(expectedPet, PetStatus.valueOf(expectedPet.getStatus().toUpperCase()));
		assertionManager.assertPetResponse(actualPet, expectedPet);
	}

	
	@SuppressWarnings("unchecked")
	private Pet getTemporaryPetData() {
		Pet pet = null;
		try {
			log.info("Fetching a pet data from cache...");
			ArrayList<String> tempPets = (ArrayList<String>) InMemoryCache.get(MTConstants.TEMPPET);
			if (ObjectUtils.isEmpty(tempPets)) {
				log.info("No users found in cache. Creating a temporary pet data");
				log.info("Generating random pet data...");
				List<Pet> petList = new ArrayList<Pet>();
				pet = this.dataProvider.generateRandomPet(PetStatus.AVAILABLE);
				petList.add(pet);
				this.createPet(pet);
			} else {
				Collections.shuffle(tempPets);
				String petName = tempPets.get(0);
				pet = (Pet) InMemoryCache.get(petName);
				log.info("Found pet : " + petName + " from cache");
			}
		} catch (Exception e) {
			assertionManager.assertExceptionFailures(e);
		}
		return pet;
	}
}
