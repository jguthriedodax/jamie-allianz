package serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import model.location.District;

public class DistrictSerializer extends StdSerializer<District> {

	private static final long serialVersionUID = 2384204796267075297L;

	private boolean printParent = true;

	public DistrictSerializer() {
		this(null);
	}
  
	public DistrictSerializer(Class<District> t) {
		super(t);
	}

	public DistrictSerializer(boolean printParent) {
		this(null);
		this.printParent = printParent;
	}

	@Override
	public void serialize(District value, JsonGenerator jgen, SerializerProvider provider) 
		throws IOException, JsonProcessingException {
 
		jgen.writeStartObject();
		jgen.writeNumberField("id", value.getId());
		jgen.writeStringField("name", value.getName());

		if (printParent) {
			// Write custom City object (which doesn't recurse through all districts)
			jgen.writeObjectFieldStart("city");
			jgen.writeNumberField("id", value.getCity().getId());
			jgen.writeStringField("name", value.getCity().getName());
			jgen.writeEndObject();
		}

		jgen.writeObjectField("sensors", value.getSensors());

		jgen.writeEndObject();
	}
}
