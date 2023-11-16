
package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * POJO representing a student in registration system
 * 
 * @author Grant Goldsmith
 * @author Greydon Sarvis
 * @author Noah Clouser
 */
public class Student extends User implements Comparable<Student> {

	/** maximum amount of credits a student can take */
	public static final int MAX_CREDITS = 18;

	/** Max amount of credits the student is currently taking */
	private int maxCredits;
	
	/** instance of schedule */
	private Schedule sc = new Schedule();

	/**
	 * Creates object Student
	 * 
	 * @param firstName  : student's first name
	 * @param lastName   : student's last name
	 * @param id         : student's identification number
	 * @param email      : student's email address
	 * @param password   : student's password set as a hash
	 * @param maxCredits : maximum amount of credits a student can take
	 */
	public Student(String firstName, String lastName, String id, String email, String password, int maxCredits) {
		super(firstName, lastName, id, email, password);
		setMaxCredits(maxCredits);
	}

	/**
	 * Calls constructor to create object Student with 18 credits
	 * 
	 * @param firstName : student's first name
	 * @param lastName  : student's last name
	 * @param id        : student's identification number
	 * @param email     : student's email address
	 * @param password  : student's password set as a hash
	 */
	public Student(String firstName, String lastName, String id, String email, String password) {
		this(firstName, lastName, id, email, password, MAX_CREDITS);
	}

	/**
	 * Sets field maxCredits
	 * 
	 * @param maxCredits : max amount of credits a student is taking
	 * 
	 * @throws IllegalArgumentException throws error "Invalid credits" if maxCredits
	 *                                  is less than 3 or greater than constant
	 *                                  MAX_CREDITS
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}

		this.maxCredits = maxCredits;
	}

	/**
	 * Getter method for field maxCredits
	 * 
	 * @return Returns field maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * returns the string of all the elements of a student in comma list
	 * @return output commo list of elements of a student
	 */
	@Override
	public String toString() {
		String output = "" + getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + "," + maxCredits;
		return output;
	}

	/**
	 * Compares two student objects and orders them depending on last name, first
	 * name, then id
	 * 
	 * @param s : Student object being compared to the current student
	 * 
	 * @return -1 if Student s should be ordered before the current student, return
	 *         1 if the current student should be ordered before Student s, and
	 *         return 0 if the students are the same.
	 */
	public int compareTo(Student s) {
		
		// Initializes result to the value thrown back from comparing the two last names
		int result = this.getLastName().compareTo(s.getLastName());
		
		// If the last name is the same then check the value returned from comparing the two first names
		if(result == 0) {
			result = this.getFirstName().compareTo(s.getFirstName());
		}
		
		// If the last and first names are identical then check the id's for equality
		if(result == 0) {
			result = this.getId().compareTo(s.getId());
		}
		
		// Returns 1, -1, or 0 depending if the current student should be before, after, or is identical to Student s
		return result;
	}
	
	/**
	 * getter method for schedule
	 * @return returns the schedule
	 */
	public Schedule getSchedule() {
		return this.sc;
	}

	/**
	 * Generates a hashCode for student using maxCredits
	 * 
	 * @return Returns hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields
	 * 
	 * @param obj the Object to compare
	 * @return Returns true if the objects are the same on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;
	}	
	
	/**
	 * Method checks if a given course can be added to the student's schedule
	 * @param course Course the method is examining if it can be added
	 * @return true if the course can be added, otherwise false
	 */
	public boolean canAdd(Course course) {
		if(course == null) {
			return false;
		} else if(!getSchedule().canAdd(course)) {
			return false;
		} else {
			return (getSchedule().getScheduleCredits() + course.getCredits()) <= getMaxCredits();
		}
	}
}
