package edu.ncsu.csc216.pack_scheduler.user;

/**
 * user class
 * creates user objects that have 
 * first name, last name, id, email, and password
 * has getters, setters, hash, and equals methods
 */
public abstract class User {

	/** First name of the student */
	private String firstName;
	/** Last name of the student */
	private String lastName;
	/** Unity ID of the student */
	private String id;
	/** email of the student */
	private String email;
	/** Password of the student that is already hashed */
	private String password;

	/**
	 * constructor of the user class
	 * @param firstName firstname
	 * @param lastName lastname
	 * @param id id 
	 * @param email email
	 * @param password password
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	/**
	 * Getter method for field firstName
	 * 
	 * @return Returns field firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Getter method for field lastName
	 * 
	 * @return Returns field lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Getter method for field id
	 * 
	 * @return Returns field id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Getter method for field email
	 * 
	 * @return Returns field email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the field email
	 * 
	 * @param email : student's email address
	 * 
	 * @throws IllegalArgumentException throws error "Invalid email" if string does
	 *                                  not contain "@", ".", or the "." comes
	 *                                  before "@"
	 */
	public void setEmail(String email) {
		if (email == null || email.length() == 0 || !email.contains("@") || !email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		}
	
		int atIndex = -1;
		int periodIndex = -1;
		char[] string = email.toCharArray();
		for (int i = 0; i < email.length(); i++) {
			if (string[i] == '@') {
				atIndex = i;
			}
			if (string[i] == '.') {
				periodIndex = i;
			}
		}
		if (periodIndex < atIndex) {
			throw new IllegalArgumentException("Invalid email");
		}
	
		this.email = email;
	}

	/**
	 * Getter method for field password
	 * 
	 * @return Returns field password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets field pashPW
	 * 
	 * @param password : hashed password given from user
	 * 
	 * @throws IllegalArgumentException throws error "Invalid password" if password
	 *                                  is null or has length 0
	 */
	public void setPassword(String password) {
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Invalid password");
		}
	
		this.password = password;
	}

	/**
	 * Sets field firstName
	 * 
	 * @param firstName : Student's first name
	 * 
	 * @throws IllegalArgumentException if firstName is null or length is 0
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.length() == 0) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Sets field lastName
	 * 
	 * @param lastName : Student's last name
	 * 
	 * @throws IllegalArgumentException if lastName is null or length is 0
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.length() == 0) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * 
	 * Sets field id
	 * 
	 * @param id : Student's Unity ID
	 * 
	 * @throws IllegalArgumentException Throws error "Invalid id" if field id is
	 *                                  null or length 0
	 */
	protected void setId(String id) {
		if (id == null || id.length() == 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * hash code of user
	 * @return result of hashing
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * equals code of user
	 * @return if they are equal or not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}