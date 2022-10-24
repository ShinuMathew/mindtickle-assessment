package mtapitest.framework;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import mtapitest.tests.helper.CommonTestHelper;

/***
 * Entry point for each test case
 * 
 * @author shinumathew
 *
 */
public class BaseTest extends MTTestFramework {

	private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

	protected CommonTestHelper commonTestHelper;

	@BeforeClass
	public void setup() throws Exception {
		log.info("Base test setup");
		this.commonTestHelper = new CommonTestHelper();
	}

	@BeforeMethod
	public void testMethodSetup(Method method) {
		log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		log.info("Test method : " + method.getName());
		log.info("Test case id : " + method.getAnnotation(Test.class).description());
		log.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

	}

	@AfterClass
	public void tearDown() throws Exception {
		this.commonTestHelper.clearCache();
	}

}
