package serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import model.Reading;

public class ReadingSerializer extends StdSerializer<Reading> {

	private static final long serialVersionUID = 1471100445894138559L;

	private boolean printParent = true;

	public ReadingSerializer() {
		this(null);
	}
  
	public ReadingSerializer(Class<Reading> t) {
		super(t);
	}

	public ReadingSerializer(boolean printParent) {
		this(null);
		this.printParent = printParent;
	}

	@Override
	public void serialize(Reading value, JsonGenerator jgen, SerializerProvider provider) 
		throws IOException, JsonProcessingException {
 
		jgen.writeStartObject();
		jgen.writeNumberField("id", value.getId());
		jgen.writeNumberField("name", value.getReading());
		jgen.writeStringField("atTime", value.getAtTime().toString());

		if (printParent) {
			// Write custom District object (which doesn't recurse through all other sensors etc)
			jgen.writeObjectFieldStart("sensor");
			jgen.writeNumberField("id", value.getSensor().getId());
			jgen.writeStringField("name", value.getSensor().getName());
			jgen.writeEndObject();
			// End object
		}

		jgen.writeEndObject();
	}
}
