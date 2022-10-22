package mtapitest.objects.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mtapitest.objects.MTBaseObject;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MTCommonResponse extends MTBaseObject {
	private int code;
	private String type;
	private String message;
}
