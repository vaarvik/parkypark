package no.parkypark.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class JsonDateSerializer extends JsonSerializer <LocalDateTime> {

	@Override
	public void serialize(LocalDateTime date, JsonGenerator generator, SerializerProvider arg)
					throws IOException {
		generator.writeNumber(date.toEpochSecond(ZoneOffset.UTC));
	}

}