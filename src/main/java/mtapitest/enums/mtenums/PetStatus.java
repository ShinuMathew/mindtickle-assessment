package mtapitest.enums.mtenums;

/***
 * Pet statuses, valid and invalid
 * 
 * @author shinumathew
 *
 */
public enum PetStatus {

	AVAILABLE("available"), PENDING("pending"), SOLD("sold"), INVALID("invalid");
	
	private String value;
	
	PetStatus(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
