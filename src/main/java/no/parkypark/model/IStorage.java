package no.parkypark.model;
import java.util.List;

public interface IStorage<T> {
	List<T> read();
	void write(List<T> array);
}
