package controller;

import java.util.HashMap;
import java.util.InputMismatchException;

import javax.security.auth.login.FailedLoginException;

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
 */

/**
 * The interface for the controller of the St8tus4u application, intended to be used by a graphical user interface to interact with the model.
 * Until there is a logged in user, only the methods logIn(), registerNewAccount(), and isLoggedIn() will work.
 * @author Jesper Johansson, Nikolas Mannai
 */
public interface ApplicationControllerInterface {
	
	/**
	 * Log in to an account.
	 * @param username
	 * @param password
	 * @throws FailedLoginException 
	 */
	public void logIn(String username, String password) throws FailedLoginException; 
	
	/**
	 * Creates a new account and adds it to the database.
	 * @param username
	 * @param password
	 * @param gender Must be either "male" or "female", otherwise it will throw an error
	 */
	public void registerNewAccount(String username, String password, String gender);
	
	/**
	 * Gets info about a user
	 * @return An array with user data. It will have the following structure:
	 * [Username, Name, Weight, Length, Age, MaxHeartRate, Gender]
	 */
	public String[] getUserData();
	
	/**
	 * Gets a list of all the user's activities
	 * @return An array of arrays, where each array is an individual activity. An array will have the following structure:
	 * [ActivityID, ActivityName, StartDate, StartLocation, AvaregeSpeed, AvaregeHeartRate, TotalTime, StartTime]
	 */
	public String[][] getUserActivities();
	
	/**
	 * @return the log of a specific activity as an array of arrays, where each array contains data about a specific interval. An array will have the following structure:
	 * [Time, ElapsedTime, Longitude, Latitude, Altitude, Distance, HeartRate, Speed, Cadence]
	 */
	public String[][] getActivityData();
	
	/**
	 * Adds a new activity to the user's list of activities. 
	 * @param nameOfActivity
	 * @param pathToCSVFile
	 * @return true if the operation was successful, false if not.
	 */
	public boolean addNewActivity(String nameOfActivity, String pathToCSVFile);
	
	/**
	 * Removes an activity from the user's list of activities.
	 * @param activityID
	 * @return true if the operation was successful, false if not.
	 */
	public boolean removeActivity(String activityID);
	
	/**
	 * Adds or changes the name of the user.
	 * @param name
	 * @return true if the operation was successful, false if not.
	 */
	public boolean setName (String name);
	/**
	 * Changes the user's gender
	 * @param gender Must either be "male" or "female"
	 * @return true if the operation was successful, false if not.
	 */
	public boolean changeGender(String gender);
	/**
	 * @param age 
	 * @return true if the operation was successful, false if not.
	 */
	public boolean setAge(int age);
	/**
	 * @param weight
	 * @return true if the operation was successful, false if not.
	 */
	public boolean setWeight(double weight);
	/**
	 * @param length
	 * @return true if the operation was successful, false if not.
	 */
	public boolean setLength(double length);
	/**
	 * @param password
	 * @return true if the operation was successful, false if not.
	 */
	public boolean changePassword(String password);
	
	/**
	 * @param activityID
	 * @param newName
	 * @return true if the operation was successful, false if not.
	 */
	public boolean changeActivityName(String activityID, String newName);
	
	/**
	 * Checks to see if there is a user currently logged in on the application.
	 * @return true if there is a logged in user, false if not.
	 */
	public boolean isLoggedIn();
	/**
	 * @return true if the operation was successful, false if not.
	 */
	public boolean logOut();
	/**
	 * Creates a user Account
	 * @return true is operation was successful, false if not
	 */
	void signUp();
	/**
	 * Opens Sign Up Window
	 */
	void openSignUpWindow();

	void registerNewAccount(String username, char[] password, String gender) throws InputMismatchException;
}
