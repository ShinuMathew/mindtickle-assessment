package mtapitest.objects.config;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mtapitest.enums.mtenums.MTService;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceConfig {

	private MTService name;
	private String host;
	private String protocol;
	private List<Endpoint> endpoints;
}
