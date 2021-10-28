package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import db.DbConnectionManager;
import user.Genders;
import user.User;

/**
 * Data access object for users
 * @author jesper.v.johansson@gmail.com
 */
public class UserDao implements DaoInterface<User> {
	DbConnectionManager dbConnectionManager;
	
	public UserDao() {
		dbConnectionManager = DbConnectionManager.getInstance();
	}
	
	@Override
	public User get(int id) {
		User returnUser = null;
		try {
			ResultSet resultSetUserData = dbConnectionManager.excecuteQuery("SELECT user_id, password, gender FROM user_data WHERE user_id="+ id + ";");
			ResultSet resultSetUsers = dbConnectionManager.excecuteQuery("SELECT username FROM users WHERE id=" + id + ";");
			
			returnUser = new User(resultSetUsers.getString(1), resultSetUserData.getString(2), getGender(resultSetUserData.getString(3)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnUser;
	}
	/**
	 * Gets data about a user by their username, if you don't know their ID.
	 * @param username
	 * @return a new User object with the retrieved data from the database
	 */
	public User get(String username) {
		// SELECT id, name, birth_year FROM students WHERE id=
		User returnUser = null;
		try {
			ResultSet resultSetUsers = dbConnectionManager.excecuteQuery("SELECT user_id, username FROM users WHERE username='" + username + "';");
			if( !resultSetUsers.next()) {
				throw new NoSuchElementException("The student with username " + username + " doesen't exist in database");
			}
			else {
				int userID = resultSetUsers.getInt(1);
				ResultSet resultSetUserData = dbConnectionManager.excecuteQuery("SELECT user_id, password, gender FROM user_data WHERE user_id="+ userID + ";");
				if(!resultSetUserData.next()) 
					throw new NoSuchElementException("The student with id " + userID + "doesn't exist in database");				
				else				
					returnUser = new User(username, resultSetUserData.getString(2), getGender(resultSetUserData.getString(3)));
			}
			int userID = resultSetUsers.getInt(1);
			
			dbConnectionManager.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return returnUser;
	}
	
	private Genders getGender(String column) {
		Genders userGender = null;
		if(column.equals("male")) {
			userGender = Genders.MALE;
		} else if (column.equals("female")) {
			userGender = Genders.FEMALE;
		} else {
			throw new RuntimeException("No such gender.");
		}
		
		return userGender;
	}
	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(User user) {
		
		
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}

}
