package mtapitest.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * Basic implementation of an in memory cache system to cache intermediate
 * response and objects
 * 
 * @author shinumathew
 *
 */
public class InMemoryCache {

	public static Logger logger = LoggerFactory.getLogger(InMemoryCache.class);

	private static ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<String, Object>();

	public static Object get(String key) {
		if (cache.containsKey(key)) {
			logger.info("CacheHit - Key : " + key);
			return cache.get(key);
		} else {
			logger.info("CacheMiss - Key : " + key);
			return null;
		}
	}

	public static void set(String key, Object value) {
		logger.info("CacheUpdate - Key : " + key + "\nValue : " + value.toString());
		cache.put(key, value);
	}

	public static List<String> keys() {
		return new ArrayList<String>(cache.keySet());
	}

	public static void flushAll() {
		logger.info("Clearing all caches...");
		cache.clear();
		logger.info("Cache cleared successfully!");
	}
}
