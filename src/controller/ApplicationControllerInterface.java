package controller;

import java.util.Map;

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


public interface ApplicationControllerInterface {
	
	/**
	 * Logs in to an account
	 * @param username
	 * @param password
	 * @return true if log in was successful, false if not.
	 */
	public boolean logIn(String username, String password); 
	
	public boolean registerNewAccount(String username, String password, String gender);
	
	public Map<String, String> getUserData();
	
	public Map<String, String>[] getUserActivities();
	
	public Map<String, String>[] getActivityData();
	
	public boolean addNewActivity(String nameOfActivity, String pathToCSVFile);
	
	public boolean removeActivity(String activityID);
	
	public boolean setName (String name);
	public boolean changeGender(String gender);
	public boolean setAge(String age);
	public boolean setWeight(String weight);
	public boolean setLength(String length);
	public boolean changePassword(String password);
	
	public boolean changeActivityName(String activityID);
	
	public boolean logOut();
}
