package mtapitest.framework;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import mtapitest.enums.MTErrorCode;
import mtapitest.exceptions.MTException;
import mtapitest.objects.MTBaseObject;

public class EntityMapper {

	public Logger logger = LoggerFactory.getLogger(EntityMapper.class);

	private ObjectMapper objMapper;

	public EntityMapper() {
		this.objMapper = new ObjectMapper();
	}

	public String serializePayload(Object payload) throws MTException {
		try {
			return this.objMapper.writeValueAsString(payload);
		} catch (JsonProcessingException ex) {
			throw new MTException(MTErrorCode.PAYLOAD_SERIALIZATION_FAILED.getValue() + "\n" + ex.getMessage());
		}
	}

	public MTBaseObject deSerializePayload(String json, MTBaseObject mapperObj) throws MTException {
		try {
			return this.objMapper.readValue(json, mapperObj.getClass());
		} catch (JsonProcessingException ex) {
			throw new MTException(MTErrorCode.PAYLOAD_DESERIALIZATION_FAILED.getValue() + "\n" + ex.getMessage());
		}
	}

	public List<MTBaseObject> deSerializeJsonArray(String json) throws MTException {
		try {
			return this.objMapper.readValue(json, new TypeReference<List<MTBaseObject>>() {
			});
		} catch (JsonProcessingException ex) {
			throw new MTException(MTErrorCode.PAYLOAD_DESERIALIZATION_FAILED.getValue() + "\n" + ex.getMessage());
		}
	}

	public ObjectMapper getObjectMapper() {
		return this.objMapper;
	}

}
