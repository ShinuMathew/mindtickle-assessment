package mtapitest.framework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.internal.http.Method;
import com.jayway.restassured.response.Cookie;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import mtapitest.objects.RequestEntities;
import net.minidev.json.JSONObject;

/***
 * Builder class for building the request contexts
 * @author shinumathew
 *
 */
public class RequestBuilder {

	RequestSpecification request;
	Method method;
	RequestEntities requestEntities;

	private static final Logger log = LoggerFactory.getLogger(RequestBuilder.class);

	public RequestBuilder() {
	}

	public RequestBuilder builder() {
		this.requestEntities = new RequestEntities();
		this.request = RestAssured.given();
		return this;
	}

	public RequestBuilder setBaseURI(String baseUri) {
		this.request = this.request.baseUri(baseUri);
		this.requestEntities.setBaseUri(baseUri);
		this.request = RestAssured.given(this.request);
		return this;
	}

	public RequestBuilder setEndpoint(String endpoint) {
		this.request = this.request.basePath(endpoint);
		this.requestEntities.setEndpoint(endpoint);
		return this;
	}

	public RequestBuilder setHeaders(Map<String, Object> headers) {
		this.request = this.request.headers(headers);
		this.requestEntities.setHeaders(headers);
		return this;
	}

	public RequestBuilder addHeader(Header header) {
		this.request = this.request.header(header);
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(header.getName(), header.getValue());
		this.requestEntities.setHeaders(headers);
		return this;
	}

	public RequestBuilder addHeader(String name, String value) {
		this.request = this.request.header(name, value);
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put(name, value);
		this.requestEntities.setHeaders(headers);
		return this;
	}

	public RequestBuilder setCookies(Map<String, Object> cookies) {
		this.request = this.request.cookies(cookies);
		this.requestEntities.setCookies(cookies);
		return this;
	}

	public RequestBuilder addCookie(Cookie cookie) {
		this.request = this.request.cookie(cookie);
		Map<String, Object> cookies = new HashMap<String, Object>();
		cookies.put(cookie.getName(), cookie.getValue());
		this.requestEntities.setCookies(cookies);
		return this;
	}

	public RequestBuilder setReqParams(Map<String, Object> params) {
		this.request = this.request.params(params);
		this.requestEntities.setParams(params);
		return this;
	}

	public RequestBuilder addReqParam(String name, List<String> values) {
		this.request = this.request.param(name, values);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(name, values);
		this.requestEntities.setParams(params);
		return this;
	}

	public RequestBuilder addReqParam(String name, String value) {
		this.request = this.request.params(name, value);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(name, value);
		this.requestEntities.setParams(params);
		return this;
	}

	public RequestBuilder setQueryParams(Map<String, Object> queryParams) {
		this.request = this.request.queryParameters(queryParams);
		this.requestEntities.setQueryParams(queryParams);
		return this;
	}

	public RequestBuilder addQueryParam(String name, String value) {
		this.request = this.request.queryParameters(name, value);
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put(name, value);
		this.requestEntities.setQueryParams(queryParams);
		return this;
	}

	public RequestBuilder setBody(String reqBody) {
		this.request = this.request.body(reqBody);
		this.requestEntities.setBody(reqBody);
		return this;
	}

	public RequestBuilder setMethod(Method method) {
		this.method = method;
		this.requestEntities.setMethod(method.name());
		return this;
	}

	public RequestSpecification buildRequest() {
		return this.request;
	}

	public Response sendRequest(RequestSpecification request) {
		logRequestEntities();
		Response response;
		switch (this.method) {
		case GET:
			response = this.request.get();
			break;
		case POST:
			response = this.request.post();
			break;
		case PUT:
			response = this.request.put();
			break;
		case DELETE:
			response = this.request.delete();
			break;
		case PATCH:
			response = this.request.patch();
			break;
		default:
			throw new UnsupportedOperationException("Not a valid method");
		}
		logResponseEntities(response);
		return response;

	}

	/***
	 * Converts the requestEntities into a message for better logging of request
	 */
	private void logRequestEntities() {
		StringBuilder requestLogMessage = new StringBuilder();
		requestLogMessage.append("\n=====================================================================================================================================\n");
		requestLogMessage.append(this.requestEntities.getMethod().toUpperCase() +" : "+this.requestEntities.getBaseUri());
		requestLogMessage.append(this.requestEntities.getEndpoint());
		
		if (!ObjectUtils.isEmpty(this.requestEntities.getParams() )) {
			Map<String, Object> params = this.requestEntities.getParams();
			params.keySet().forEach(param -> {
				requestLogMessage.append("/");
				requestLogMessage.append(params.get(param));
			});
		}

		if (!ObjectUtils.isEmpty(this.requestEntities.getQueryParams())) {
			requestLogMessage.append("?");
			Map<String, Object> queryParams = this.requestEntities.getQueryParams();
			queryParams.keySet().forEach(queryParam -> {
				requestLogMessage.append(queryParam + "=" + queryParams.get(queryParam));
				requestLogMessage.append("&");
			});
		}
		requestLogMessage.append("\n");

		if (!ObjectUtils.isEmpty(this.requestEntities.getHeaders())) {
			JSONObject headerObj = new JSONObject();
			headerObj.putAll(this.requestEntities.getHeaders());
			requestLogMessage.append("Request headers : " + headerObj.toJSONString());
			requestLogMessage.append("\n");
		}

		if (!ObjectUtils.isEmpty(this.requestEntities.getCookies())) {
			JSONObject cookieObj = new JSONObject();
			cookieObj.putAll(this.requestEntities.getCookies());
			requestLogMessage.append("Request cookies : " + cookieObj.toJSONString());
			requestLogMessage.append("\n");
		}

		if (this.requestEntities.getBody() != null) {
			requestLogMessage.append("Request body :" + this.requestEntities.getBody());
			requestLogMessage.append("\n");
		}
		requestLogMessage.append("----------------------------------------------------------------------------------------------------------------------------------------");

		log.info(requestLogMessage.toString());
	}

	/***
	 * Converts the response into a message for better logging of response
	 */
	private void logResponseEntities(Response response) {
		StringBuilder responseEntities = new StringBuilder();
		responseEntities.append("\nResponse status : "+ response.statusCode());		
		responseEntities.append("\nResponse body : " + response.body().asString());
		responseEntities.append("\n=====================================================================================================================================");
		log.info(responseEntities.toString());		
	}

}
