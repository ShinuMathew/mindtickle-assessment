package mtapitest.enums.mtenums;

public enum PetStatus {

	AVAILABLE("available"), PENDING("pending"), SOLD("sold");
	
	private String value;
	
	PetStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
