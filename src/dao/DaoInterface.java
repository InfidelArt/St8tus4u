package dao;

import java.util.List;

// As this program uses multiple Data Access Objects, this is the unified interface for them.

/**
 * CRUD Interface for any Data Access Object.
 * 
 * @author jesper.v.johansson@gmail.com
 * @param <T> The specific class for the DAO
 */
public interface DaoInterface<T> {
	T get(int id);
	List<T> getAll();
	void save(T t);
	void update(T t);
	void delete(T t);
}
