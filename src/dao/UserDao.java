package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

import db.DbConnectionManager;
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
		/*
		User returnUser = null;
		try {
			ResultSet resultSetUserData = dbConnectionManager.excecuteQuery("SELECT user_id, password, gender FROM user_data WHERE user_id="+ id + ";");
			ResultSet resultSetUsers = dbConnectionManager.excecuteQuery("SELECT username FROM users WHERE id=" + id + ";");
			
			returnUser = new User(resultSetUsers.getString(1), resultSetUserData.getString(2), getGender(resultSetUserData.getString(3)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		return null;
	}
	/**
	 * Gets data about a user by their username, if you don't know their ID.
	 * @param username
	 * @return a new User object with the retrieved data from the database
	 */
	public User get(String username) throws InputMismatchException, RuntimeException {
		
		try {
			ResultSet resultSet = dbConnectionManager.excecuteQuery(
					"SELECT users.user_id, user_data.password, user_data.gender"
				  + "FROM users"
				  + "INNER JOIN user_data ON users.user_id=user_data.user_id"
				  + "WHERE username='" + username + "';"
					);
			
			if (resultSet.next()) {
				User retrievedUser = new User(resultSet.getInt(1), username, resultSet.getString(2), resultSet.getString(3));
				return retrievedUser;
			} else {
				throw new InputMismatchException("No such user in the database.");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Failed to retrieve user data.");
		}
		
		
		/*
		 * TODO use JOIN instead of making multiple queries to the DB
		 */
		
		/*
		User returnUser = null;
		try {
			ResultSet resultSetUsers = dbConnectionManager.excecuteQuery("SELECT user_id, username FROM users WHERE username='" + username + "';");
			if( !resultSetUsers.next()) {
				throw new NoSuchElementException("The user with username " + username + " doesen't exist in database");
			}
			else {
				int userID = resultSetUsers.getInt(1);
				ResultSet resultSetUserData = dbConnectionManager.excecuteQuery("SELECT user_id, password, gender FROM user_data WHERE user_id="+ userID + ";");
				if(!resultSetUserData.next()) 
					throw new NoSuchElementException("The user with id " + userID + "doesn't exist in database");				
				else				
					returnUser = new User(username, resultSetUserData.getString(2), getGender(resultSetUserData.getString(3)));
			}
			int userID = resultSetUsers.getInt(1);
			
			dbConnectionManager.close();
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}
	
	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(User user) throws RuntimeException {
		
		/*
		 * TODO Use the PreparedStatement class and sql TRANSACTION to group everything into one call.
		 * https://stackoverflow.com/questions/175066/sql-server-is-it-possible-to-insert-into-two-tables-at-the-same-time
		 */
		
		try {
			dbConnectionManager.excecuteQuery("INSERT INTO users(username) VALUES ('TestUser8');");
			System.out.println("Success1");
			dbConnectionManager.excecuteQuery("INSERT INTO user_data(user_id, password, gender) "
											+ "VALUES ((select user_id from users where username='TestUser8'), 'test222', 'male');");
		} catch(SQLException e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("Failed to connect to the database");
		}
		
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
