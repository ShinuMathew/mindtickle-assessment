package mtapitest.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MTException extends Exception {

	public static Logger logger = LoggerFactory.getLogger(MTException.class);
	private static final long serialVersionUID = 1008993387152513310L;

	private String message;
	private String scope;

	public MTException(String message, String scope) {
		this.message = message;
		this.scope = scope;
		logger.error(message);
	}

	public MTException(String message) {
		this(message, null);
	}

}
