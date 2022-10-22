package mtapitest.enums.mtenums;

public enum MTServiceEndpoint {
	
	// USER SERVICE
	CREATEUSER("createuser"),
	GETUSER("getuser"),
	UPDATEUSER("updateuser"),
	
	// PET SERVICE
	CREATEPET("createpet"),
	GETPETBYSTATUS("getpetbystatus"),
	UPDATEPET("updatepet");
	
	private String value;
	
	private MTServiceEndpoint(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}

}
