package session;

import java.util.InputMismatchException;

import javax.security.auth.login.FailedLoginException;

import dao.UserDao;
import user.Genders;
import user.User;

public class SessionHandler {
	UserDao userDao;
	User loggedInUser;
	
	public SessionHandler() {
		userDao = new UserDao();
		loggedInUser = null;
	}
	
	public User logIn(String username, String password) throws FailedLoginException {
		User returnUser = null;
		try {
			returnUser = userDao.get(username);
		} catch (Exception e) {
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
	public void registerNewUser(String username, String password, Genders gender) throws InputMismatchException{
		if(username.length() > 24) {
			throw new InputMismatchException("Username is too long. Max length is 24 characters.");
		} else if (password.length() > 24) {
			throw new InputMismatchException("Password is too long. Max length is 24 characters.");
		} else {
			User newUser = new User(username, password, gender);
			userDao.save(newUser);
			System.out.println("Successfully registered new user " + newUser.getUsername() + ".");
		}		
	}

}
