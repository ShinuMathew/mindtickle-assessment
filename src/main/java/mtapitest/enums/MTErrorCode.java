package mtapitest.enums;

public enum MTErrorCode {

	PAYLOAD_SERIALIZATION_FAILED("Unable to serialise the given class to json"),
	PAYLOAD_DESERIALIZATION_FAILED("Unable to deserialise the given json"),
	CONFIG_PARSER_FAILED("Config parser failed"),
	CONFIG_NOT_FOUND("No configuration found for %s in config.xml");

	String value;

	private MTErrorCode(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
