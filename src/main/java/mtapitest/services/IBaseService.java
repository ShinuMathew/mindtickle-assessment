package mtapitest.services;

/***
 * Base interface for all Service class implementations
 * @author shinumathew
 *
 */
public interface IBaseService {

	void setDefaultRequestContext();
	void setDefaultHeaders();
	void setDefaultCookies();
}
