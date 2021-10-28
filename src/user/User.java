package user;

public class User implements UserInterface {
	
	private Genders gender;
	private String username;
	private String password;
	
	public User(String username, String password, Genders gender) {
		setUsername(username);
		setPassword(password);
		setGender(gender);
	}
	
	@Override
	public void setUsername(String username) {
		// TODO Auto-generated method stub
		this.username = username;
	}

	@Override
	public String getUsername() {
		return this.username;
		
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		this.password = password;
	}

	@Override
	public boolean verifyPassword(String password) {
		if (password.equals(getPassword())) {
			return true;
		} 
		else {
			return false;
		}
		
	}
	private String getPassword() {
		return this.password;
	}
	@Override
	public void setGender(Genders gender) {
		this.gender = gender;
		
	}

	@Override
	public Genders getGender() {
		return this.gender;
		
	}

	public String toString() {
		return getUsername() + ", " + getGender().name().toLowerCase();
	}
}
