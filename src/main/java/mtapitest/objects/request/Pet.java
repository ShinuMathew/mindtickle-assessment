package mtapitest.objects.request;

import java.math.BigInteger;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mtapitest.objects.Category;
import mtapitest.objects.MTBaseObject;
import mtapitest.objects.Tags;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet extends MTBaseObject {

	private BigInteger id;
	private String name;
	private Category category;
	private List<Tags> tags;
	private String status;
	private List<String> photoUrls;
}
