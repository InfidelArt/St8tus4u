package db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import javax.security.auth.login.FailedLoginException;

import controller.ApplicationController;
import dao.UserDao;

public class TestMain {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationController controller = new ApplicationController();
		try {
			controller.logIn("TestUser1", "test123");
		} catch (FailedLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Arrays.toString(controller.getUserData()));
		
		controller.registerNewAccount("TestUser5", "test321", "male");
	}

}
