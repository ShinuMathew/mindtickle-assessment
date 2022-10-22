package mtapitest.framework;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mtapitest.constants.MTConstants;

/***
 * Entry point for MT API Test Framework. Acts as base class for all the
 * entities in the framework
 * 
 * @author shinumathew
 */
public class MTTestFramework {

	private static final Logger log = LoggerFactory.getLogger(MTTestFramework.class);

	public List<String> testCases;

	protected void getAllSectedTestCaseIds() {
		String caseIds = System.getProperty(MTConstants.CASEIDS);
		if (StringUtils.isEmpty(caseIds))
			log.info("Triggering run for all tests by default...");
		else {
			this.testCases = Arrays.asList(caseIds.split(","));
			log.info("Triggering run for the below tests : \n" + Arrays.toString(this.testCases.toArray()));
		}
	}

}
