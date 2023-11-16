
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Course object that keeps track of its name, title, section, credit hours,
 * instructor’s unity id, meeting days, start time, and end time.
 * 
 * @author : gasarvis
 */
public class Course extends Activity implements Comparable<Course> {

	/** Minimum name length of Course. */
	private static final int MIN_NAME_LENGTH = 4;
	/** Maximum name length of Course. */
	private static final int MAX_NAME_LENGTH = 8;

	//private static final int DIGIT_COUNT = 3;
	/** Section length for Course section identifier. */
	private static final int SECTION_LENGTH = 3;
	/** Minimum credit hours a Course can offer. */
	private static final int MIN_CREDITS = 1;
	/** Maximum credit hours a Course can offer. */
	private static final int MAX_CREDITS = 5;
	/** course name validator */
	CourseNameValidator course;
	/** course roll of courses */
	CourseRoll roll;

	/** Name of Course. */
	private String name;
	/** Section of Course. */
	private String section;
	/** Credit hours for Course. */
	private int credits;
	/** Unity id of Course's instructor. */
	private String instructorId;

	/**
	 * Constructs a Course object with values for all fields.
	 * 
	 * @param name         : Name of Course
	 * @param title        : Title of Course
	 * @param section      : Section of Course
	 * @param credits      : Credit hours for Course
	 * @param instructorId : Instructor's unity id
	 * @param enrollmentCap : Max amount of students allowed in the course
	 * @param meetingDays  : Meeting days for Course as series of chars
	 * @param startTime    : Start time for Course
	 * @param endTime      : End time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		course = new CourseNameValidator();
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		roll = new CourseRoll(this, enrollmentCap);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged. Uses Activity superclass.
	 * 
	 * @param name         : Name of Course
	 * @param title        : Title of Course
	 * @param section      : Section of Course
	 * @param credits      : Credit hours for Course
	 * @param instructorId : Instructor's unity id
	 * @param enrollmentCap : Max amount of students allowed in the course
	 * @param meetingDays  : Meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Gets the course's name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 5 or more
	 * than 8, does not contain a space between letter characters and number
	 * characters, has less than 1 or more than 4 letter characters, and not exactly
	 * three trailing digit characters, an IllegalArgumentException is thrown.
	 * 
	 * @param name : The name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {
		// Throw exception if the name is null
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Invalid course name.");
		}

		// Throw exception if the name contains less than 5 character or greater than 8
		// characters
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		try {
			//course.isValid(name);
			CourseNameValidator c = new CourseNameValidator();
			if(c.isValid(name)){
				this.name = name;
			} else {
				throw new IllegalArgumentException("Invalid course name.");
			}
			
		} catch(InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
	}

	/**
	 * Gets the course's section.
	 * 
	 * @return the section
	 */
	public String getSection() {
		return this.section;
	}

	/**
	 * Set's the course's section. If the section is null, length isn't 3, or not a
	 * digit, an IllegalArgumentException is thrown.
	 * 
	 * @param section : The section to set
	 * 
	 * @throws IllegalArgumentException if the section parameter is invalid
	 */
	public void setSection(String section) {
		// Throw exception if the section is null or length isn't 3
		if (section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}

		// Throw exception if the section is not a digit
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}

		this.section = section;
	}

	/**
	 * Gets the course's credits.
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return this.credits;
	}

	/**
	 * Sets the course's credits. If the credit hours are less than 1 or greater
	 * than 5, an IllegalArgumentException is thrown.
	 * 
	 * @param credits : The credits to set
	 * @throws IllegalArgumentException if the credits parameter is invalid
	 */
	public void setCredits(int credits) {
		// Throw exception if the credit hours are less than 1 or greater than 5
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}

		this.credits = credits;
	}

	/**
	 * Gets the course's instructor id.
	 * 
	 * @return the instructor id
	 */
	public String getInstructorId() {
		return this.instructorId;
	}

	/**
	 * Sets the course's instructor id. If instructor id is null or empty, an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param instructorId : The instructor id to set
	 * 
	 * @throws IllegalArgumentException if the instructorId parameter is invalid
	 */
	public void setInstructorId(String instructorId) {
		// Throw exception if instructor ID is null or empty
		if (instructorId == null || instructorId.length() == 0) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}

		this.instructorId = instructorId;
	}
	
	/**
	 * sets the meeting days and times of a course
	 * @param meetingDays meetingdays of the course
	 * @param startTime start time of the course
	 * @param endTime end time of the course
	 * @throws IllegalArgumentException if the meeting days or times are invalid
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// Throw exception if meetingDays is null or empty
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if ("A".equals(meetingDays)) {
			// Throw exception if meeting days is arranged and start and end time are not 0
			if (startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}

			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		} else {
			for (int i = 0; i < meetingDays.length(); i++) {
				char day = meetingDays.charAt(i);

				// Throw exception if meeting days contains anything other than letters or
				// contains invalid days
				if (!Character.isLetter(day) || !(day == 'M' || day == 'T' || day == 'W' || day == 'H' || day == 'F')) {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * checks to see if an activity is a duplicate of a course
	 * @param activity activity being checked
	 * @return true or false if the activity is a duplicate of the course
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		return activity instanceof Course && getName().equals(((Course) activity).getName());
	}

	/**
	 * 
	 * returns the short display array
	 * @return specific elements of the course in a string format. returns the name, section, title and meeting string
	 */
	@Override
	public String[] getShortDisplayArray() {
		String seats = String.valueOf(roll.getOpenSeats());
		return new String[] { this.name, this.section, getTitle(), getMeetingString(), seats };
	}

	/**
	 * returns the long display array
	 * @return all the elements of the course in a string format
	 */
	@Override
	public String[] getLongDisplayArray() {
		return new String[] { this.name, this.section, getTitle(), Integer.toString(this.credits), this.instructorId,
				getMeetingString(), "" };
	}
	/**
	 * returns the course roll
	 * @return roll course roll
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}

	/**
	 * the hash code of course
	 * @return result
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * the equals method for course
	 * @return true or false if they are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * the string method of course that returns the elements in string format
	 * @return string string of the course elements
	 */
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," 
		+ roll.getEnrollmentCap() + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," 
		+ roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}
	/**
	 * the compares to method that returns a negative 0 or positive depending on how they compare
	 * @return the result of them being compared
	 */
	@Override
	public int compareTo(Course o) {
		int result = getName().compareTo(o.getName());

		if (result == 0)
			result = getSection().compareTo(o.getSection());

		return result;
	}

}
