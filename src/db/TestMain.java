package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DbConnectionManager dbConManagerSingleton = DbConnectionManager.getInstance();
		try {
			PreparedStatement preparedStatement = dbConManagerSingleton.prepareStatement("INSERT INTO users(username) VALUES ('jesper123');");
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
