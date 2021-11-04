package db;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import javax.security.auth.login.FailedLoginException;

import activity.Activity;
import activity.ActivitySnapshot;
import controller.ApplicationController;
import dao.UserDao;
import date.InvalidDateException;
import session.SessionHandler;
import time.InvalidTimeException;

public class TestMain {
	
	public static void main(String[] args) throws DataEntryException, IOException, InvalidTimeException, InvalidDateException {
		// TODO Auto-generated method stub
		ApplicationController controller = new ApplicationController();	
	
		try {
			controller.logIn("Test2", "aahaaa");
		} catch (FailedLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		controller.addNewActivity("Test activity 5", "test activity 3.csv");
		
		try {
			String[][] list = controller.getUserActivities();
			
			for (String[] thing : list) {
				System.out.println(Arrays.toString(thing));
			} 
			
		} catch (DataRetrievalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		/*
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
		*/
	}
	

}