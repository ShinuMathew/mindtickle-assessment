package mtapitest.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import mtapitest.enums.mtenums.MTService;
import mtapitest.enums.mtenums.MTServiceEndpoint;
import mtapitest.objects.config.Endpoint;

/***
 * Service class for creating and making Pet service request
 * 
 * @author shinumathew
 *
 */
public class PetService extends Service {
	
	private static final Logger log = LoggerFactory.getLogger(PetService.class);

	public PetService() throws Exception {
		this.serviceConfig = this.configHelper.getServiceConfig(this.serviceConfigs, MTService.PET);
		setDefaultRequestContext();
		log.info("User service initialised");
	}
	
	@Override
	public void setDefaultRequestContext() {
		this.protocol = this.serviceConfig.getProtocol();
		this.host = this.serviceConfig.getHost();
		log.info("User service default header set");
	}	
	
	public Response createPet(String requestBody) throws Exception {
		log.info("Calling createPets...");
		Endpoint endpoint = this.configHelper.getServiceEndpoint(this.serviceConfig, MTServiceEndpoint.CREATEPET);
		RequestSpecification request = reqBuilder.builder().setBaseURI(this.protocol + "://" + this.host)
				.setEndpoint(endpoint.getPath()).setHeaders(defaultHeaders).setBody(requestBody)
				.setMethod(endpoint.getMethod()).buildRequest();
		return reqBuilder.sendRequest(request);
	}
	
	public Response getPet(String status) throws Exception {
		log.info("Calling getPet...");
		Endpoint endpoint = this.configHelper.getServiceEndpoint(this.serviceConfig, MTServiceEndpoint.GETPETBYSTATUS);
		RequestSpecification request = reqBuilder.builder().setBaseURI(this.protocol + "://" + this.host)
				.setEndpoint(endpoint.getPath())
				.setHeaders(defaultHeaders)
				.addQueryParam("status", status)
				.setMethod(endpoint.getMethod()).buildRequest();
		return reqBuilder.sendRequest(request);
	}
	
	public Response updatePet(String requestBody) throws Exception {
		log.info("Calling updatePet...");
		Endpoint endpoint = this.configHelper.getServiceEndpoint(this.serviceConfig, MTServiceEndpoint.UPDATEPET);
		RequestSpecification request = reqBuilder.builder().setBaseURI(this.protocol + "://" + this.host)
				.setEndpoint(endpoint.getPath()).setHeaders(defaultHeaders)
				.setBody(requestBody).setMethod(endpoint.getMethod()).buildRequest();
		return reqBuilder.sendRequest(request);
	}
	
}
