package db;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.security.auth.login.FailedLoginException;

import activity.ActivitySnapshot;
import controller.ApplicationController;
import dao.UserDao;
import date.InvalidDateException;
import session.SessionHandler;
import time.InvalidTimeException;

public class TestMain {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationController controller = new ApplicationController();
		try {
			controller.logIn("Test2", "aahaaa");
		} catch (FailedLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SessionHandler sessionHandler = new SessionHandler();
		try {
			List<ActivitySnapshot> log = sessionHandler.importLog("test activity.csv");
			
			for (ActivitySnapshot line : log) {
				System.out.println(line.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidTimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
