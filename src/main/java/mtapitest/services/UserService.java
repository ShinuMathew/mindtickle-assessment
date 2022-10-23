package mtapitest.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import mtapitest.enums.mtenums.MTService;
import mtapitest.enums.mtenums.MTServiceEndpoint;
import mtapitest.objects.config.Endpoint;

/***
 * Service class for creating and making User service request
 * 
 * @author shinumathew
 *
 */
public class UserService extends Service {

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	public UserService() throws Exception {		
		this.serviceConfig = super.configHelper.getServiceConfig(this.serviceConfigs, MTService.USER);
		setDefaultRequestContext();
		log.info("User service initialised");
	}

	@Override
	public void setDefaultRequestContext() {
		this.protocol = this.serviceConfig.getProtocol();
		this.host = this.serviceConfig.getHost();
		log.info("User service default header set");
	}

	public Response createUserWithArray(String requestBody) throws Exception {
		log.info("Calling createUserWithArray...");
		Endpoint endpoint = this.configHelper.getServiceEndpoint(this.serviceConfig, MTServiceEndpoint.CREATEUSER);
		RequestSpecification request = reqBuilder.builder().setBaseURI(this.protocol + "://" + this.host)
				.setEndpoint(endpoint.getPath()).setHeaders(defaultHeaders).setBody(requestBody)
				.setMethod(endpoint.getMethod()).buildRequest();
		return reqBuilder.sendRequest(request);
	}

	public Response getUser(String username) throws Exception {
		log.info("Calling getUser...");
		Endpoint endpoint = this.configHelper.getServiceEndpoint(this.serviceConfig, MTServiceEndpoint.GETUSER);
		RequestSpecification request = reqBuilder.builder().setBaseURI(this.protocol + "://" + this.host)
				.setEndpoint(String.format(endpoint.getPath(), username)).setHeaders(defaultHeaders)
				.setMethod(endpoint.getMethod()).buildRequest();
		return reqBuilder.sendRequest(request);
	}

	public Response updateUser(String username, String requestBody) throws Exception {
		log.info("Calling updateUser...");
		Endpoint endpoint = this.configHelper.getServiceEndpoint(this.serviceConfig, MTServiceEndpoint.UPDATEUSER);
		RequestSpecification request = reqBuilder.builder().setBaseURI(this.protocol + "://" + this.host)
				.setEndpoint(String.format(endpoint.getPath(), username)).setHeaders(defaultHeaders)
				.setBody(requestBody).setMethod(endpoint.getMethod()).buildRequest();
		return reqBuilder.sendRequest(request);
	}
}
