package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.UserDao;

public class TestMain {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DbConnectionManager dbConManagerSingleton = DbConnectionManager.getInstance();
		
		UserDao userDao = new UserDao();
		// PreparedStatement preparedStatement = dbConManagerSingleton.prepareStatement("INSERT INTO users(username) VALUES ('jesper123');");
		// preparedStatement.execute();
		System.out.println(userDao.get("jesperj3").toString());
	}

}
