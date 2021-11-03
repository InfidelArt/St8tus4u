package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import db.DataEntryException;
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
		// TODO the application doesn't really need this method, but fill it our anyway if there's time
		
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
					// user_data.name, user_data.weight, user_data.length, user_data.age,
					"SELECT users.user_id, user_data.password, user_data.name, user_data.weight, user_data.length, user_data.age, user_data.gender "
				  + "FROM users "
				  + "INNER JOIN user_data ON users.user_id=user_data.user_id "
				  + "WHERE username='" + username + "';"
					);
			
			if (resultSet.next()) {
				User retrievedUser = new User(resultSet.getInt(1), username, resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
				return retrievedUser;
			} else {
				throw new InputMismatchException("No such user in the database.");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Failed to retrieve user data.");
		}
		
	}
	
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		List<User> usersList = new ArrayList<>();
		
		try {
			ResultSet resultSet = dbConnectionManager.excecuteQuery(""
					+ "SELECT users.user_id, users.username, user_data.password, user_data.name, user_data.weight, user_data.length, user_data.age user_data.gender "
					+ "FROM users "
					+ "INNER JOIN user_data ON users.user_id=user_data.user_id;"
					);
			while (resultSet.next()) {				
				usersList.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usersList;
	}

	@Override
	public User save(User user) throws DataEntryException {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = dbConnectionManager.prepareStatement(
					"INSERT INTO users(username) VALUES (?) RETURNING user_id;"
					);
		    preparedStatement.setString(1, user.getUsername());
		    preparedStatement.execute();
		    resultSet = preparedStatement.getResultSet();
		    resultSet.next();
		    int generatedId = resultSet.getInt(1);
		    
		    preparedStatement = dbConnectionManager.prepareStatement(
		    		"INSERT INTO user_data(user_id, password, gender) "
		    	  + "VALUES (?, ?, ?);"
		    		);
		    preparedStatement.setInt(1, generatedId);
		    preparedStatement.setString(2, user.getPassword());
		    preparedStatement.setString(3, user.getGender());
		    preparedStatement.execute();
		    
		    return new User(generatedId, user);
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new DataEntryException("Could not enter user into database.");
		}
		
	}

	@Override
	public void update(User user) throws DataEntryException {
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = dbConnectionManager.prepareStatement(""
					+ "UPDATE user_data "
					+ "SET password=?, weight=?, gender=? "
					+ "WHERE user_id=?"
					);
			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setDouble(2, user.getWeight());
			preparedStatement.setString(3, user.getGender());
			preparedStatement.setInt(4, user.getId());
			preparedStatement.execute();
			System.out.println("Successfully updated user " + user.getUsername() + ".");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataEntryException("Could not update user information.");
		}
		
	}

	@Override
	public void delete(User t) {
		// Deleting users is not within the scope of this application
		
	}
	public boolean checkIfUserExists(String username) {
		List<String> users = getUsernameList();
		
		for(String user : users) {
			if (user.toLowerCase().equals(username.toLowerCase())) {
				return true;
			}
		}
		
		return false;
	}
	
	private List<String> getUsernameList() {
		List<String> usernames = new ArrayList<>();
		try {
			ResultSet resultSet = dbConnectionManager.excecuteQuery(
					"SELECT username FROM users;"
					);
			while (resultSet.next()) {
				usernames.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return usernames;
	}

}
