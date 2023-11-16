package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Schedule class creates schedule object that has a title and an arraylist that
 * contains the scheduled courses
 * 
 * @author Noah Clouser
 * @author Rohan
 * @author Nalin
 */
public class Schedule {

	/** A custom ArrayList of Courses */
	private ArrayList<Course> schedule;
	/** The schedule's title */
	private String title;
	/** Default title used when creating a new Schedule object */
	private static final String DEFAULT_TITLE = "My Schedule";

	/**
	 * Constructor for Schedule object. Creates an empty array of courses and sets
	 * the title of the schedule.
	 */
	public Schedule() {
		schedule = new ArrayList<Course>();
		setTitle(DEFAULT_TITLE);
	}

	/**
	 * Add a new course to the end of the schedule list checks to see if the course
	 * is null, duplicate, or in conflict
	 * 
	 * @param course New potential course to be added to the list
	 * @return true if the course was added, otherwise false
	 * @throws IllegalArgumentException if course is a duplicate or in conflict
	 * @throws NullPointerException     if it is null
	 */
	public boolean addCourseToSchedule(Course course) {
		if (course == null) {
			throw new NullPointerException();
		}
		for (int i = 0; i < schedule.size(); i++) {
			Course holder = schedule.get(i);
			if (course.isDuplicate(holder)) {
				throw new IllegalArgumentException("You are already enrolled in " + holder.getName());
			}
			try {
				course.checkConflict(holder);
			} catch (Exception e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		schedule.add(course);
		return true;
	}

	/**
	 * Removes a course from the schedule list
	 * 
	 * @param course The course to be removed from the list
	 * @return true if the course was removed, otherwise false
	 */
	public boolean removeCourseFromSchedule(Course course) {
		if (course != null) {
			for (int i = 0; i < schedule.size(); i++) {
				Course holder = schedule.get(i);
				if (course.equals(holder)) {
					schedule.remove(i);
					return true;
				}
			}
		}

//		for(int i = 0; i < schedule.size(); i++) {
//			Course holder = schedule.get(i);
//			if(course.equals(holder)) {
//				schedule.remove(i);
//				return true;
//			}
//		}
		return false;
	}

	/**
	 * Creates a new schedule ArrayList and reset title to its default value
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
		setTitle(DEFAULT_TITLE);
	}

	/**
	 * Creates a 2D array of Course information to be returned. Each row should be a
	 * Course and the columns are name, section, title, and the meeting string
	 * 
	 * @return 2D array of the Course information
	 */
	public String[][] getScheduledCourses() {
		String[][] list = new String[schedule.size()][5];

		// sets each element
		for (int i = 0; i < schedule.size(); i++) {
			Course holder = schedule.get(i);
			// gets the short display array
			String[] sda = holder.getShortDisplayArray();
			// sets the values for each row in the 2D array
			list[i][0] = holder.getName();
			list[i][1] = holder.getSection();
			list[i][2] = holder.getTitle();
			list[i][3] = sda[3];
			list[i][4] = sda[4];
		}
		// returns the 2d Array that has the Name, Section, Title, and Meeting String
		return list;
	}

	/**
	 * Setter method for the field title
	 * 
	 * @param title Title of the schedule
	 * @throws IllegalArgumentException if the title is null
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null");
		}
		this.title = title;
	}

	/**
	 * Getter method for the field title
	 * 
	 * @return The string associated with title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Getter method for the total amount of credits in a given schedule
	 * @return Sum of all credits in the schedule
	 */
	public int getScheduleCredits() {
		int sum = 0;
		for (int i = 0; i < schedule.size(); i++) {
			sum += schedule.get(i).getCredits();
		}
		return sum;
	}

	/**
	 * Method checks if a given course can be added to the schedule
	 * @param course Course the method is examining if it can be added
	 * @return true if the course can be added, otherwise false
	 */
	public boolean canAdd(Course course) {
		if (course == null) {
			return false;
		} else {
			for (int i = 0; i < schedule.size(); i++) {
				if (course.isDuplicate(schedule.get(i))) {
					return false;
				} else {
					try {
						course.checkConflict(schedule.get(i));
					} catch (ConflictException e) {
						return false;
					}
				}
			}
			return true;
		}
	}
}
