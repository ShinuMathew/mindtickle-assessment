package mtapitest.unittests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.internal.http.Method;

import mtapitest.enums.mtenums.MTService;
import mtapitest.enums.mtenums.MTServiceEndpoint;
import mtapitest.managers.ConfigManager;
import mtapitest.objects.config.Endpoint;
import mtapitest.objects.config.ServiceConfig;


/***
 * @author shinumathew User - Negative scenarios are covered here
 */
public class ConfigTest {

	private ConfigManager configManager;
	private List<ServiceConfig> serviceConfigs;
	private ServiceConfig userServiceConfig;

	@BeforeTest
	private void testSetup() throws Exception {
		this.configManager = new ConfigManager();
	}

	@Test(priority = 1)
	private void loadServiceConfig() throws Exception {
		this.serviceConfigs = this.configManager.loadServicesFromConfig();
		Assert.assertNotNull(this.serviceConfigs);
	}

	@Test(priority = 2, dependsOnMethods = "loadServiceConfig" )
	private void getServiceConfig() throws Exception {
		this.userServiceConfig = this.configManager.getServiceConfig(this.serviceConfigs, MTService.USER);
		Assert.assertNotNull(this.userServiceConfig);
		Assert.assertEquals(this.userServiceConfig.getName(), MTService.USER);
	}
	
	@Test(priority = 3, dependsOnMethods = "getServiceConfig" )
	private void getServiceEndpoint() throws Exception {
		Endpoint createUserEndpoint = this.configManager.getServiceEndpoint(userServiceConfig, MTServiceEndpoint.CREATEUSER);
		Assert.assertNotNull(createUserEndpoint);
		Assert.assertEquals(createUserEndpoint.getMethod(), Method.POST);
	}

}
