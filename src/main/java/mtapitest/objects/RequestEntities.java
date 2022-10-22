package mtapitest.objects;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestEntities {

	private String baseUri;
	private String endpoint;
	private String requestBody;
	private Map<String, Object> headers;
	private Map<String, Object> cookies;
	private Map<String, Object> params;
	private String method;
	private String body;
	private Map<String, Object> queryParams;
	
}
