package user;

/**
 * Interface for any user type, with methods for all the required fields for a user in the database.
 * @author jesper.v.johansson@gmail.com
 */
public interface UserInterface {
	void setUsername(String username);
	String getUsername();
	void setPassword(String password);
	/**
	 * @param password The password to verify
	 * @return true if the given password matches the user's, false if not.
	 */
	boolean verifyPassword(String password);
	void setGender(String gender);
	String getGender();
}
