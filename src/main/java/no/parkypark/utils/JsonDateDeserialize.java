package no.parkypark.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.LongNode;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class JsonDateDeserialize extends JsonDeserializer<LocalDateTime> {
	@Override
	public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
		LocalDateTime date;
		ObjectCodec oc = jp.getCodec();
		LongNode node = oc.readTree(jp);
		long epoch = node.longValue();

		// The date is stored/returned from frontend as milliseconds but LocalDateTime can only handle the timestamp in seconds.
		date = LocalDateTime.ofEpochSecond(epoch / 1000, 0, ZoneOffset.UTC);

		return date;
	}
}
