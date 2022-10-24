package mtapitest.unittests;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import mtapitest.utils.CommonUtils;

public class UtilsTest {

	CommonUtils commonUtils;

	@BeforeTest
	private void setup() {
		commonUtils = new CommonUtils();
	}

	@Test
	private void generateRandomEmail() {
		String email1 = this.commonUtils.generateRandomEmail();
		Assert.assertTrue(!StringUtils.isEmpty(email1));
		String email2 = this.commonUtils.generateRandomEmail();
		Assert.assertNotEquals(email1, email2);
	}

	@Test
	private void generateRandomId() {
		int id1 = this.commonUtils.generateRandomId(3);		
		Assert.assertTrue(id1 > 1000);
		int id2 = this.commonUtils.generateRandomId(3);
		Assert.assertNotEquals(id1, id2);
	}
}
