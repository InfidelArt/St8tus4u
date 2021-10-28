package session;

import javax.security.auth.login.FailedLoginException;

import dao.UserDao;
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
	public void registerNewUser(String username, String passsword, String gender) {
		
	}
	
}
