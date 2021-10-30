package controller;

import java.util.HashMap;
import java.util.InputMismatchException;

import javax.security.auth.login.FailedLoginException;

import gui.LoginFrame;
import gui.SignUpDialogueBox;
import session.SessionHandler;
import user.User;

/**
 * @author Jesper Johansson, Nikolas Mannai
 */
public class ApplicationController implements ApplicationControllerInterface {
	
	/*
	 * What must the controller be able to do?
	 * 
	 * A method for logging in
	 * A method for registering a new account
	 * A method to get data about the logged in user
	 * A method to get the list of the user's activities
	 * A method to get data about a specific activity
	 * A method to add a new activity
	 * A method to remove a chosen activity
	 * A method to change the name of an activity
	 * A method to log out
	 * 
	 * To do: Create a skeleton of the controller's methods and decide what parameters they should take (from the front-end)
	 * and what they return (from the back-end).
	 * When this is done it will be easy to divide the project.
	 */
	
	SessionHandler sessionHandler;
	
	public ApplicationController() {
		sessionHandler = new SessionHandler();
	}
	
	
	public static void main(String[] args) {
		new LoginFrame().setVisible(true);
		new SignUpDialogueBox().setVisible(true);
	}

	@Override
	public void logIn(String username, String password) throws FailedLoginException {		
		sessionHandler.logIn(username, password);		
	}

	@Override
	public void registerNewAccount(String username, String password, String gender) throws InputMismatchException {		
	
		sessionHandler.registerNewUser(username, password, gender);
		
	}

	@Override
	public String[] getUserData() {
		User user = sessionHandler.getLoggedInUser();
		String [] returnArray = {user.getUsername(), null, null, null, null, null, user.getGender().toLowerCase()};
		
		
		return returnArray;
	}

	@Override
	public String[][] getUserActivities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] getActivityData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNewActivity(String nameOfActivity, String pathToCSVFile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeActivity(String activityID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeGender(String gender) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setAge(int age) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setWeight(double weight) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setLength(double length) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePassword(String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeActivityName(String activityID, String newName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLoggedIn() {
		if (sessionHandler.getLoggedInUser() == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean logOut() {
		// TODO Auto-generated method stub
		return false;
	}

}
