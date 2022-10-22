package mtapitest.enums.mtenums;

public enum MTService {

	/***
	 * Naming convention - SERVICE("service") to be followed strictly
	 */
	USER("user"), PET("pet");

	private String value;

	MTService(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
