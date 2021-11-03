package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;

import javax.security.auth.login.FailedLoginException;

import db.DataEntryException;
import gui.LoginFrame;
import gui.MainFrame;
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
	public ApplicationController controller;
	public LoginFrame loginFrame;
	public SignUpDialogueBox signUpFrame;
	public MainFrame mainFrame;
	
	
	public ApplicationController() {
		controller = this;
		sessionHandler = new SessionHandler();
		loginFrame = new LoginFrame(controller);
		loginFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ApplicationController();
	}

	@Override
	public void logIn(String username, String password) throws FailedLoginException {				
		new MainFrame(controller).setVisible(true);
		sessionHandler.logIn(username, password);	
	}

	@Override
	public User registerNewAccount(String username, String password, String gender) throws DataEntryException {		
		
		User newUser = sessionHandler.registerNewUser(username, password, gender);
		return newUser;
	}

	@Override
	public String[] getUserData() {
		User user = sessionHandler.getLoggedInUser();
		String [] returnArray = {user.getUsername(), user.getName(), String.valueOf(user.getWeight()), String.valueOf(user.getLength()), String.valueOf(user.getAge()), user.getGender().toLowerCase()};
		
		
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
	public void removeActivity(String activityID) {
		// TODO Auto-generated method stub
		
	}
	public void updateUser(String password, String gender) throws DataEntryException {
		sessionHandler.updateUser(new User(sessionHandler.getLoggedInUser().getId(), sessionHandler.getLoggedInUser().getUsername(), password, gender));
	}
	@Override
	public boolean changeActivityName(String activityID, String newName) {
		// TODO Auto-generated method stub
		return false;
	}
	public void setWeight(double weight) throws DataEntryException {
		sessionHandler.getLoggedInUser().setWeight(weight);
		sessionHandler.updateUser(sessionHandler.getLoggedInUser()); 
	}
	public void setLength(double length) throws DataEntryException {
		sessionHandler.getLoggedInUser().setLength(length);
		sessionHandler.updateUser(sessionHandler.getLoggedInUser());
	}
	public void setAge(int age) throws DataEntryException {
		sessionHandler.getLoggedInUser().setAge(age);
		sessionHandler.updateUser(sessionHandler.getLoggedInUser());
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
	public String[][] getAllUsers() {
		List<User> userList = sessionHandler.getAllUsers();
		String[][] retrievedUsers = new String[userList.size()][4];
		
		for (int i = 0; i <= userList.size() - 1; i++) {
			retrievedUsers[i][0] = String.valueOf(userList.get(i).getId());
			retrievedUsers[i][1] = userList.get(i).getUsername();
			retrievedUsers[i][2] = userList.get(i).getPassword();
			retrievedUsers[i][3] = userList.get(i).getGender();
		}
		
		return retrievedUsers;
	}
	
	@Override
	public void signUp() {
		// TODO
		
	}

	@Override
	public void openSignUpWindow() {
		loginFrame.setVisible(false);
		signUpFrame = new SignUpDialogueBox(controller);
		signUpFrame.setVisible(true);
		
	}

	@Override
	public void registerNewAccount(String username, char[] password, String gender) throws InputMismatchException {
		// TODO Auto-generated method stub
		
	}
}
