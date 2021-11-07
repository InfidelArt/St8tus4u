package user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.InputMismatchException;

public class User implements UserInterface {
	
	private final int MAX_USERNAME_LENGTH = 24;
	private enum Genders {MALE, FEMALE, OTHER}
	private Genders gender;
	private String username;
	private String password;
	private int id;
	private double weight;
	private double length;
	private int age;
	private String name;
	
	
	public User(String username, String password, Object name, Object weight, Object length, Object age, String gender) throws InputMismatchException {
		setUsername(username);
		checkValidPassword(password);
	    setPassword(encryptPassword(password));	
		setGender(gender);
		
		if (name != null) {
			this.name = (String) name;
		}
		if (weight != null) {
			this.weight = (double) weight;
		}
		if (length != null) {
			this.length = (double) length;
		}
		if (age != null) {
			this.age = (int) age;
		}
		
		//setGender(gender);
	}
	/*
	 * Constructor to be used when the user has an ID, for example, by a Data Access Object when it has a generated user ID from a database.
	 */
	public User(int id, String username, String password, Object name, Object weight, Object length, Object age, String gender) {
		this(username, password, name, weight, length, age, gender);
		this.id = id;
	}
	public User(int id, User user) {
		this(user.getUsername(), user.getPassword(), user.getName(), user.getWeight(), user.getLength(), user.getAge(), user.getGender());
		setId(id);
	}
	
	@Override
	public void setUsername(String username) throws InputMismatchException {
		// TODO Auto-generated method stub
		if (checkValidUsername(username)) 
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
		if (encryptPassword(encryptPassword(password)).equals(getPassword())) {
			return true;
		} 
		else {
			return false;
		}
		
	}
	public String getPassword() {
		return this.password;
	}
	@Override
	public void setGender(String newGender) throws InputMismatchException {
		this.gender = genderInputToEnum(newGender);		
	}

	@Override
	public String getGender() {
		return this.gender.name().toLowerCase();
		
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getWeight() {
		return this.weight;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getLength() {
		return this.length;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return this.age;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return getUsername() + ", " + getName();
	}
	/**
	 * Checks if the given username for a new User object is valid, for example, that isn't too long or contains whitespaces.
	 */
	private boolean checkValidUsername(String username) {
		if (username.length() > MAX_USERNAME_LENGTH) 
			throw new InputMismatchException("Username is too long. A username can be a maximum of " + MAX_USERNAME_LENGTH + " characters.");
		if (username.contains(" "))
			throw new InputMismatchException("Username cannot contain spaces.");
		
		return true;
	}
	/**
	 * Checks if the given password for a new User object is valid, for example, that isn't too long or contains whitespaces.
	 */
	private boolean checkValidPassword(String password) {
		if (password==null) {
			throw new InputMismatchException("No Password was entered.");
		}
		if (password.contains(" "))
			throw new InputMismatchException("Password cannot contain spaces.");
		
		return true;
	}
	private String encryptPassword(String password) {
		String encryptedPassword = null;
		
		try {
			MessageDigest mD = MessageDigest.getInstance("MD5"); 
			mD.update(password.getBytes());
			byte[] bytes = mD.digest();
			
			StringBuilder stringBuilder = new StringBuilder();
			for (int i=0; i < bytes.length; i++) {
				stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			
			encryptedPassword = stringBuilder.toString();
			
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return encryptedPassword;
	}
	/**
	 * Checks if the given gender input is valid and returns an appropriate enum value
	 * @return A value of the Genders enum that reflects the given string.
	 */
	private Genders genderInputToEnum(String input) {
		
		for (Genders gender : Genders.values()) {
			if (gender.name().toLowerCase().equals(input.toLowerCase()))
				return gender;
		}
		
		throw new InputMismatchException("Invalid input for the gender parameter.");
	}
	
}
