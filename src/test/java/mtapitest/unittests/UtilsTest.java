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
		String email = this.commonUtils.generateRandomEmail();
		Assert.assertTrue(!StringUtils.isEmpty(email));
	}

	@Test
	private void generateRandomId() {
		int id = this.commonUtils.generateRandomId(3);
		Assert.assertTrue(id > 1000);
	}
}
