package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import javax.security.auth.login.FailedLoginException;

import activity.Activity;
import activity.ActivitySnapshot;
import date.InvalidDateException;
import db.DataEntryException;
import db.DataRetrievalException;
import gui.GraphFrame;
import gui.LoginFrame;
import gui.MainFrame;
import gui.SignUpFrame;
import gui.UserSettingsFrame;
import session.SessionHandler;
import time.InvalidTimeException;
import user.User;

/**
 * @author Jesper Johansson, Nikolas Mannai
 */
public class ApplicationController implements ApplicationControllerInterface {

	/*
	 * What must the controller be able to do?
	 * 
	 * A method for logging in A method for registering a new account A method to
	 * get data about the logged in user A method to get the list of the user's
	 * activities A method to get data about a specific activity A method to add a
	 * new activity A method to remove a chosen activity A method to change the name
	 * of an activity A method to log out
	 * 
	 * To do: Create a skeleton of the controller's methods and decide what
	 * parameters they should take (from the front-end) and what they return (from
	 * the back-end). When this is done it will be easy to divide the project.
	 */

	SessionHandler sessionHandler;
	public ApplicationController controller;
	public LoginFrame loginFrame;
	public SignUpFrame signUpFrame;
	public MainFrame mainFrame;
	public UserSettingsFrame userSettings;

	public ApplicationController() throws IOException, InvalidTimeException, InvalidDateException {
		controller = this;
		sessionHandler = new SessionHandler();
		loginFrame = new LoginFrame(controller);
		loginFrame.setVisible(true);
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				System.out.print("User logged out successfully");
				logOut();
			}
		}, "Logout user on close"));

	}

	public static void main(String[] args) throws IOException, InvalidTimeException, InvalidDateException {
		new ApplicationController();
	}

	@Override
	public void logIn(String username, String password) throws FailedLoginException {
		sessionHandler.logIn(username, password);
		try {
			mainFrame = new MainFrame(controller);
			mainFrame.setVisible(true);
		} catch (IOException | InvalidTimeException | InvalidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Move this under sessionHandler after you are done
	}

	@Override
	public void registerNewAccount(String username, char[] arrayPassword, String gender)
			throws InputMismatchException, DataEntryException {
		StringBuilder sb = new StringBuilder();
		for (char subArray : arrayPassword) {
			sb.append(subArray);
		}
		String password = sb.toString();
		// TODO make it so database uses encrypted password (must change login method
		// and user class)
		sessionHandler.registerNewUser(username, password, gender);
		loginFrame.setVisible(true);
	}

	@Override
	public String[] getUserData() {
		User user = sessionHandler.getLoggedInUser();
		String[] returnArray = { user.getUsername(), user.getName(), String.valueOf(user.getWeight()),
				String.valueOf(user.getLength()), String.valueOf(user.getAge()), "302.0",
				user.getGender().toLowerCase() };

		return returnArray;
	}

	@Override
	public String[][] getUserActivities() throws DataRetrievalException {
		ArrayList<Activity> activities = sessionHandler.getUserActivities();

		String[][] returnString = new String[activities.size()+1][8];
		returnString[0] = new String[] {"0", "                      No activity", "", "", "","","",""};
		
		for (int i = 1; i < activities.size()+1; i++) {
			returnString[i] = activities.get(i-1).toStringArray();
		}

		return returnString;

	}

	@Override
	public String[][] getActivityData(int id) throws IOException, DataRetrievalException {
		ArrayList<ActivitySnapshot> list = sessionHandler.getActivityLog(id);
		String[][] returnArray = new String[list.size()][8];
		for (int i = 0; i < list.size(); i++) {
			returnArray[i] = list.get(i).toArray();
		}
		return returnArray;
	}

	public String[][] getActivityData() throws IOException, InvalidTimeException, InvalidDateException {
		ArrayList<ActivitySnapshot> list = sessionHandler.importLog("test activity.csv");
		String[][] returnArray = new String[list.size()][8];
		for (int i = 0; i < list.size(); i++) {
			returnArray[i] = list.get(i).toArray();
		}
		return returnArray;
	}

	@Override
	public void addNewActivity(String nameOfActivity, String pathToCSVFile)
			throws IOException, InvalidTimeException, InvalidDateException, DataEntryException {
		ArrayList<ActivitySnapshot> log = sessionHandler.importLog(pathToCSVFile);
		Activity activity = new Activity(nameOfActivity, log);

		sessionHandler.addActivity(activity);
	}

	@Override
	public void removeActivity(String activityID) throws DataEntryException {
		int id = Integer.parseInt(activityID);
		sessionHandler.removeActivity(id);
	}

	public void removeActivity(int activityID) throws DataEntryException {
		sessionHandler.removeActivity(activityID);
	}

	public void updateUser(String password, String gender) throws DataEntryException {
		sessionHandler.updateUser(new User(sessionHandler.getLoggedInUser().getId(),
				sessionHandler.getLoggedInUser().getUsername(), password, null, null, null, null, gender));
	}

	@Override
	public void changeActivityName(String activityID, String newName) throws DataEntryException {
		try {
			sessionHandler.updateActivityName(Integer.parseInt(activityID), newName);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	public void setName(String name) throws DataEntryException {
		sessionHandler.getLoggedInUser().setName(name);
		sessionHandler.updateUser(sessionHandler.getLoggedInUser());
		mainFrame.update();
	}

	public void setWeight(double weight) throws DataEntryException {
		sessionHandler.getLoggedInUser().setWeight(weight);
		sessionHandler.updateUser(sessionHandler.getLoggedInUser());
		mainFrame.update();
	}

	public void setLength(double length) throws DataEntryException {
		sessionHandler.getLoggedInUser().setLength(length);
		sessionHandler.updateUser(sessionHandler.getLoggedInUser());
		mainFrame.update();
	}

	public void setAge(int age) throws DataEntryException {
		sessionHandler.getLoggedInUser().setAge(age);
		sessionHandler.updateUser(sessionHandler.getLoggedInUser());
		mainFrame.update();
	}

	public void setGender(String gender) throws DataEntryException {
		sessionHandler.getLoggedInUser().setGender(gender);
		sessionHandler.updateUser(sessionHandler.getLoggedInUser());
		mainFrame.update();
	}

	public void setUsername(String username) throws DataEntryException {
		sessionHandler.getLoggedInUser().setUsername(username);
		sessionHandler.updateUser(sessionHandler.getLoggedInUser());
		mainFrame.update();
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
		signUpFrame = new SignUpFrame(controller);
		signUpFrame.setVisible(true);

	}

	public void openUserSettings() {
		userSettings = new UserSettingsFrame(controller);
		userSettings.setVisible(true);
	}

	public void showGraph(int activityId) throws IOException, InvalidTimeException, InvalidDateException {
		GraphFrame graphFrame = new GraphFrame(controller, activityId);
	}
}
