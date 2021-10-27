package serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import model.Sensor;

public class SensorSerializer extends StdSerializer<Sensor> {

	private static final long serialVersionUID = 2571188445894138559L;

	private boolean printParent = true;

	public SensorSerializer() {
		this(null);
	}
  
	public SensorSerializer(Class<Sensor> t) {
		super(t);
	}

	public SensorSerializer(boolean printParent) {
		this(null);
		this.printParent = printParent;
	}

	@Override
	public void serialize(Sensor value, JsonGenerator jgen, SerializerProvider provider) 
		throws IOException, JsonProcessingException {
 
		jgen.writeStartObject();
		jgen.writeNumberField("id", value.getId());
		jgen.writeStringField("name", value.getName());
		jgen.writeNumberField("latitude", value.getLatitude());
		jgen.writeNumberField("longitude", value.getLongitude());

		if (printParent) {
			// Write custom District object (which doesn't recurse through all other sensors etc)
			jgen.writeObjectFieldStart("district");
			jgen.writeNumberField("id", value.getDistrict().getId());
			jgen.writeStringField("name", value.getDistrict().getName());
			jgen.writeEndObject();
			// End object
		}

		jgen.writeObjectField("readings", value.getReadings());

		jgen.writeEndObject();
	}
}
