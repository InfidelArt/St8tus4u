package dao;

import java.util.List;

import db.DataEntryException;

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
	/**
	 * @return The same object with the generated ID from the database.
	 * @throws DataEntryException 
	 */
	T save(T t) throws DataEntryException;
	void update(T t) throws DataEntryException;
	void delete(T t);
}
