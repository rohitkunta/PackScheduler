/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

/**
 * POJO Faculty class that represents an individual faculty record. 
 * Similar to Student, but a Faculty object have a certain number of courses they can teach within a semester. (1-3 inclusive)	
 */
public class Faculty extends User {
	
	/**number of courses the faculty is teaching*/
	private int maxCourses;
	/**MINIMUM number of courses a faculty can teach*/
	private static final int MIN_COURSES = 1;
	/**MAXIMUM number of courses a faculty can teach*/
	private static final int MAX_COURSES = 3;
	
	
	/**
	 * Constructor for Faculty object
	 * @param firstName the first name of the faculty member
	 * @param lastName the last name of the faculty member
	 * @param id the id of the faculty member
	 * @param email the email of the faculty member
	 * @param password the password of the faculty member
	 * @param maxCourses the maxCourses the faculty member is teaching
	 */
	public Faculty(String firstName, String lastName, String id, String email, String password, int maxCourses) {
		super(firstName, lastName, id, email, password);
		setMaxCourses(maxCourses);
	}

	/**
	 * Returns the maxCourses the Faculty is teaching
	 * @return the maxCourses the number or courses the Faculty is teaching
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Sets the maxCourses the faculty member is teaching
	 * @param maxCourses the maxCourses to set
	 * @throws IllegalArgumentException if the maxCourses is higher than 3 or less than 1.
	 */
	public void setMaxCourses(int maxCourses) {
		if(maxCourses > MAX_COURSES || maxCourses < MIN_COURSES)
		{
			throw new IllegalArgumentException("Invalid number of Courses.");
		}
		
		this.maxCourses = maxCourses;
	}

	/**
	 * Returns String representation of the faculty member.
	 * @return output the string representation of the faculty member
	 */
	@Override
	public String toString()
	{
		String output = "" + getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCourses;
		return output;
	}
	
	
	/**
	 * Generates a hash code value for the Faculty object based on the maxCourses
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * Returns true if the number of courses taught between 2 faculty objects are equal
	 * - false if they are not equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}
	
	
	

}
