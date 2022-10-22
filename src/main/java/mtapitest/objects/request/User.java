package mtapitest.objects.request;

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
public class User extends MTBaseObject {
	private int id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone;
	private int userStatus;
}
