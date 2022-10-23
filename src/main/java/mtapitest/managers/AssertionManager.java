package mtapitest.managers;

import java.util.Arrays;
import java.util.List;

import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import mtapitest.enums.HttpStatusCode;
import mtapitest.objects.request.Pet;

/***
 * General assertion manager class to handle basic, general and complex
 * assertions, making it reusable across the framework
 * 
 * @author shinumathew
 *
 */
public class AssertionManager {

	private static final Logger log = LoggerFactory.getLogger(AssertionManager.class);

	public void assertStatusSuccess(int statusCode) {
		Assert.assertEquals(statusCode, HttpStatusCode.SUCCESS.getStatusCodeValue());
		log.info(String.format("Response status code : %s validated successfully", statusCode));
	}

	public void assertStatusCreated(int statusCode) {
		Assert.assertEquals(statusCode, HttpStatusCode.CREATED.getStatusCodeValue());
		log.info(String.format("Response status code : %s validated successfully", statusCode));
	}

	public void assertStatusBadRequest(int statusCode) {
		Assert.assertEquals(statusCode, HttpStatusCode.BAD_REQUEST.getStatusCodeValue());
		log.info(String.format("Response status code : %s validated successfully", statusCode));
	}

	public void assertStatusUnauthorised(int statusCode) {
		Assert.assertEquals(statusCode, HttpStatusCode.UNAUTHORISED.getStatusCodeValue());
		log.info(String.format("Response status code : %s validated successfully", statusCode));
	}

	public void assertStatusForbidden(int statusCode) {
		Assert.assertEquals(statusCode, HttpStatusCode.FORBIDDEN.getStatusCodeValue());
		log.info(String.format("Response status code : %s validated successfully", statusCode));
	}

	public void assertStatusNotFound(int statusCode) {
		Assert.assertEquals(statusCode, HttpStatusCode.NOT_FOUND.getStatusCodeValue());
		log.info(String.format("Response status code : %s validated successfully", statusCode));
	}
	
	public void assertStatusServerError(int statusCode) {
		Assert.assertEquals(statusCode, HttpStatusCode.SERVER_ERROR.getStatusCodeValue());
		log.info(String.format("Response status code : %s validated successfully", statusCode));
	}

	public void assertCommonMTResponse(int statusCode, int expectedCode) {
		Assert.assertEquals(statusCode, expectedCode);
		log.info("Response body code validated successfully");
	}

	public void assertResponseSchema(String responseBody, String expectedUser, JSONCompareMode compareMode) {
		JSONAssert.assertEquals(expectedUser, responseBody, compareMode);
	}
	
	public void assertPetResponse(Pet actualValue, Pet expectedValue) {
		Assert.assertEquals(actualValue.getName(), expectedValue.getName());
		Assert.assertEquals(actualValue.getStatus(), expectedValue.getStatus());
		Assert.assertEquals(actualValue.getId(), expectedValue.getId());
	}
	
	public void assertPetEmptyResponse(List<Pet> actualValue) {
		Assert.assertTrue(actualValue.size() == 0);
	}
	
	public void assertExceptionFailures(Exception ex) {
		StringBuilder sb = new StringBuilder();
		sb.append("Test failed due to an unhandled exception.\n" + ex.getMessage());
		sb.append("\n" + Arrays.toString(ex.getStackTrace()));
		log.error(sb.toString());
		Assert.fail(sb.toString());
	}

}
