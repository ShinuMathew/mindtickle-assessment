package mtapitest.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.seleniumhq.jetty9.http.HttpHeader;
import mtapitest.enums.HTTPHeaderValue;
import mtapitest.framework.MTTestFramework;
import mtapitest.framework.RequestBuilder;
import mtapitest.managers.ConfigManager;
import mtapitest.objects.config.ServiceConfig;

/***
 * Parent/base service class for all default and common operations
 * 
 * @author shinumathew
 *
 */
public class Service extends MTTestFramework implements IBaseService {

	protected List<ServiceConfig> serviceConfigs;
	protected RequestBuilder reqBuilder;
	protected ConfigManager configHelper;
	protected ServiceConfig serviceConfig;
	protected String host;
	protected String protocol;
	protected Map<String, Object> defaultHeaders;
	protected Map<String, Object> defaultCookies;

	public Service() throws Exception {
		this.reqBuilder = new RequestBuilder();
		this.configHelper = new ConfigManager();
		this.serviceConfigs = this.configHelper.loadServicesFromConfig();
		setDefaultCookies();
		setDefaultHeaders();
	}

	@Override
	public void setDefaultRequestContext() {
		throw new UnsupportedOperationException("Service class has no request context");
	}

	@Override
	public void setDefaultHeaders() {
		this.defaultHeaders = new HashMap<String, Object>();
		this.defaultHeaders.put(HttpHeader.ACCEPT.asString(), HTTPHeaderValue.APPLICATION_JSON.getStatusCodeValue());
		this.defaultHeaders.put(HttpHeader.CONTENT_TYPE.asString(),
				HTTPHeaderValue.APPLICATION_JSON.getStatusCodeValue());
	}

	@Override
	public void setDefaultCookies() {
		this.defaultCookies = new HashMap<String, Object>();
	}

	// Dependency injected for User and Pet services

}
