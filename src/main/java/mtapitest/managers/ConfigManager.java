package mtapitest.managers;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jayway.restassured.internal.http.Method;

import mtapitest.clients.XMLDocumentParser;
import mtapitest.constants.MTConstants;
import mtapitest.enums.mtenums.MTService;
import mtapitest.enums.mtenums.MTServiceEndpoint;
import mtapitest.framework.MTTestFramework;
import mtapitest.objects.config.Endpoint;
import mtapitest.objects.config.ServiceConfig;

/***
 * Deals with operations on config.xml. Serializing, deserializing and parsing
 * config for test run
 * 
 * @author shinumathew
 *
 */
public class ConfigManager extends MTTestFramework {

	public XMLDocumentParser xmlDocumentParser;
	public Node serviceNode;

	public ConfigManager() throws Exception {
		this.xmlDocumentParser = new XMLDocumentParser(Paths.get(MTConstants.CONFIG_PATH).toString());
	}

	/***
	 * Loads service config from config.xml.
	 * 
	 * @return List of ServiceConfig
	 * @throws Exception
	 */
	public List<ServiceConfig> loadServicesFromConfig() throws Exception {
		this.serviceNode = this.xmlDocumentParser.getNode(MTConstants.CONFIG_SERVICE);
		List<ServiceConfig> serviceConfigs = new ArrayList<ServiceConfig>();
		NodeList services = this.serviceNode.getChildNodes();
		for (int i = 0; i < services.getLength(); i++) {
			if (services.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Node serviceNode = services.item(i);
				NodeList endpointNodes = this.xmlDocumentParser.getSpecificChildNode(serviceNode, "endpoint")
						.getChildNodes();
				List<Endpoint> endpoints = new ArrayList<Endpoint>();
				for (int j = 0; j < endpointNodes.getLength(); j++) {
					if (endpointNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
						endpoints.add(Endpoint.builder()
								.name(MTServiceEndpoint.valueOf(endpointNodes.item(j).getNodeName().toUpperCase()))
								.method(Method.valueOf(endpointNodes.item(j).getAttributes().getNamedItem("method")
										.getTextContent().toUpperCase()))
								.path(this.xmlDocumentParser.getSpecificChildNode(endpointNodes.item(j), "path")
										.getTextContent())
								.isDefaultHeader(Boolean.parseBoolean(
										this.xmlDocumentParser.getSpecificChildNode(endpointNodes.item(j), "headers")
												.getAttributes().getNamedItem("default").getTextContent()))
								.build());
					}
				}

				ServiceConfig serviceConfig = ServiceConfig.builder()
						.name(MTService.valueOf(serviceNode.getNodeName().toUpperCase()))
						.host(this.xmlDocumentParser.getSpecificChildNode(serviceNode, "host").getTextContent())
						.protocol(this.xmlDocumentParser.getSpecificChildNode(serviceNode, "protocol").getTextContent())
						.endpoints(endpoints).build();
				serviceConfigs.add(serviceConfig);
			}
		}
		return serviceConfigs;
	}


	/***
	 * 
	 * @param serviceConfigs
	 * @param service
	 * @return Configuration for a specific service
	 * @throws Exception
	 */
	public ServiceConfig getServiceConfig(List<ServiceConfig> serviceConfigs, MTService service) throws Exception {
		return serviceConfigs.stream().filter(config -> config.getName().equals(service)).collect(Collectors.toList())
				.get(0);
	}
	
	/***
	 * Provides the required configuration for a given endpoint
	 * @param serviceConfig
	 * @param endpoint
	 * @return Configuration for an endpoint 
	 * @throws Exception
	 */
	public Endpoint getServiceEndpoint(ServiceConfig serviceConfig, MTServiceEndpoint endpoint) throws Exception {
		return serviceConfig.getEndpoints().stream().filter(ep -> ep.getName().equals(endpoint))
				.collect(Collectors.toList()).get(0);
	}
}
