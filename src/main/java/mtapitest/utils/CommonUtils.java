package mtapitest.utils;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {

	private static final Logger log = LoggerFactory.getLogger(CommonUtils.class);

	public int generateRandomId() {
		return (int) Math.ceil(Math.random() * 10000);
	}

	public int generateRandomId(int length) {
		return (int) (Math.pow(10, length) + Math.ceil(Math.random() * Math.pow(10, length)));
	}

	public String generateRandomName(String prefix) {
		return prefix + (int) Math.ceil(Math.random() * 10000);
	}

	public String generateRandomEmail() {
		return "test.user" + (int) Math.ceil(Math.random() * 10000) + "@kmail.com";
	}

	public String generateRandomMobile() {
		return "90" + (int) Math.ceil(Math.random() * 100000000);
	}

	public String generateRandomPetName() {
		log.info("Generating random pet name");
		return Arrays.asList("Doggie", "Kitty").get((int) Math.floor(Math.random()))
				+ (int) Math.ceil(Math.random() * 10000);
	}
}
