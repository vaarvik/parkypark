package no.parkypark.model;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonStorage<U> implements IStorage<U> {
	private final File file;
	private final Class<U> contentClass;

	// Hack: Class<U>.. https://stackoverflow.com/questions/3437897/how-do-i-get-a-class-instance-of-generic-type-t
	public JsonStorage(Class<U> contentClass, String filePath) {
		this.file = new File(filePath);
		this.contentClass = contentClass;
	}

	@Override
	public List<U> read() {
		ObjectMapper mapper = new ObjectMapper();
		List<U> list = null;

		try {
			JavaType type = mapper.getTypeFactory().constructParametricType(List.class, this.contentClass);
			list = mapper.readValue(this.file, type);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void write(List<U> array) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(this.file, array);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
