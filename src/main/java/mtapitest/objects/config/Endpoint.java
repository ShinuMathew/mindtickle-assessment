package mtapitest.objects.config;

import com.jayway.restassured.internal.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mtapitest.enums.mtenums.MTServiceEndpoint;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endpoint {

	private MTServiceEndpoint name;
	private String path;
	private Method method;
	private boolean isDefaultHeader;	
}
