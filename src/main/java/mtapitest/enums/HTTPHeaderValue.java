package mtapitest.enums;

public enum HTTPHeaderValue {
	
	APPLICATION_JSON("application/json"),
	APPLICATION_XML("application/xml");
	

	private String value;
	
	HTTPHeaderValue(String value) {
		this.value = value;
	}
	
	public String getStatusCodeValue() {
		return this.value;
	}

}
