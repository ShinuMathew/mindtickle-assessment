package mtapitest.enums.mtenums;

/***
 * Service endpoint names as per the config.xml. 
 * Note : The names should be same in this enum as in config.xml
 * 
 * @author shinumathew
 */
public enum MTServiceEndpoint {

	// USER SERVICE
	CREATEUSER("createuser"), GETUSER("getuser"), UPDATEUSER("updateuser"),

	// PET SERVICE
	CREATEPET("createpet"), GETPETBYSTATUS("getpetbystatus"), UPDATEPET("updatepet");

	private String value;

	private MTServiceEndpoint(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}