package session;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import javax.security.auth.login.FailedLoginException;

import activity.Activity;
import activity.ActivitySnapshot;
import dao.ActivityDao;
import dao.UserDao;
import date.Date;
import date.InvalidDateException;
import db.DataEntryException;
import time.InvalidTimeException;
import time.Time;
import user.User;

public class SessionHandler {
	UserDao userDao;
	ActivityDao activityDao;
	User loggedInUser;
	
	public SessionHandler() {
		userDao = new UserDao();
		activityDao = new ActivityDao();
		loggedInUser = null;
	}
	
	public User logIn(String username, String password) throws FailedLoginException {
		User returnUser = null;
		try {
			returnUser = userDao.get(username);
		} catch (RuntimeException e) {
			throw new FailedLoginException(e.getMessage());
		} 
		if (returnUser.verifyPassword(password)) {
			loggedInUser = returnUser;
			System.out.println("Logged in to user " + username + " successfully.");
		} else {
			throw new FailedLoginException("Wrong password.");
		}
		return returnUser;
	}
	public User getLoggedInUser() {
		return this.loggedInUser;
	}
	public User registerNewUser(String username, String password, String gender)  throws DataEntryException  {
		if (userDao.checkIfUserExists(username)) {
			throw new DataEntryException("User already exists in database.");
		}
		
		User newUser = new User(username, password, null, null, null, null, gender);	
		return userDao.save(newUser);
	}
	public List<User> getAllUsers() {
		return userDao.getAll();
	}
	
	public void updateUser(User user) throws DataEntryException {
		userDao.update(user);
	}
	
	public void addActivity (Activity activity) throws DataEntryException {
		activityDao.save(getLoggedInUser().getId(), activity);
	}
	
	public ArrayList<ActivitySnapshot> importLog (String pathToCSV) throws IOException, InvalidTimeException, InvalidDateException {
		
		ArrayList<ActivitySnapshot> log = new ArrayList<>();
		
		BufferedReader br = new BufferedReader(new FileReader(pathToCSV));
		String line;
		br.readLine();
		
		while ((line = br.readLine()) != null) {
			String[] values = line.split(";");
			Time time = new Time(values[1]);
			Date date = new Date(values[0]);
			log.add(new ActivitySnapshot(time, date, Integer.parseInt(values[2]), Double.parseDouble(values[3].replace(',', '.')), Double.parseDouble(values[4].replace(',', '.')), Double.parseDouble(values[5].replace(',', '.')), Double.parseDouble(values[6].replace(',', '.')), Double.parseDouble(values[7].replace(',', '.')), Double.parseDouble(values[8].replace(',', '.')), Double.parseDouble(values[9].replace(',', '.'))));
		}
				
		return log;
	}
}
