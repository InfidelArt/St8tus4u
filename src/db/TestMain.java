package db;

import java.io.IOException;
import javax.security.auth.login.FailedLoginException;
import controller.ApplicationController;
import date.InvalidDateException;
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
		
		
		
		controller.changeActivityName("25", "Maraton");
		
		
		
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