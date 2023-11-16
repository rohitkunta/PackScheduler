
package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Object representing a catalog of Course objects for a university
 * creates a catalog that stores courses and allows users to add, edit, or remove courses
 * can also save or load the catalog
 * @author Noah Clouser
 */
public class CourseCatalog {

	/** Sorted course catalog. */
	private SortedList<Course> catalog;

	/**
	 * Constructs an empty catalog.
	 */
	public CourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Constructs a new empty catalog. Can be used to reset the previous catalog.
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Loads course records into the catalog.
	 * 
	 * @param fileName : pathname of a given file
	 * 
	 * @throws IllegalArgumentException if FileNotFoundException is caught
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Invalid file name. FileNotFoundException was thrown.");
		}
	}

	/**
	 * Adds a Course with the given parameter fields to the catalog.
	 * 
	 * @param name          : name of the Course
	 * @param title         : title of the COurse
	 * @param section       : section number of the Course
	 * @param credits       : number of credit hours Course has
	 * @param instructorId  : unity ID of Course instructor
	 * @param enrollmentCap : max amount of students allowed in the course
	 * @param meetingDays   : String representing the days the Course meets
	 * @param startTime     : start time of Course in military time
	 * @param endTime       : end time of course in military time
	 * 
	 * @return true if the Course is added and false if the Course already exists in
	 *         the catalog
	 * @throws IllegalArgumentException if the course is invalid
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		boolean holder = false;
		Course c = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
		for(int i = 0; i < catalog.size(); i++) {
			if(c.isDuplicate(catalog.get(i)) && c.getSection().equals(catalog.get(i).getSection())) {
				holder = true;
				break;
			}
			
		}
		if(holder) {
			return false;
		}
		try {
			catalog.add(new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime));
			return true;
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Course is invalid");
		}
	}

	/**
	 * Removes a Course with the given parameter fields from the catalog.
	 * 
	 * @param name    : name of the Course
	 * @param section : section number of the Course
	 * 
	 * @return true if the Course is removed and false if the Course does not
	 *         already exists in the catalog
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				catalog.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets a Course from the catalog with the given parameter name and section.
	 * 
	 * @param name    : name of the Course
	 * @param section : section number of the Course
	 * 
	 * @return Course from catalog
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;
	}

	/**
	 * Getter for catalog field.
	 * 
	 * @return catalog field
	 */
	public SortedList<Course> getCatalog() {
		return this.catalog;
	}

	/**
	 * Gives the name, section, title, and meeting information for Courses in the
	 * catalog.
	 * 
	 * @return shortDisplayArray of the course catalog
	 */
	public String[][] getCourseCatalog() {
		String[][] catalogArray = new String[catalog.size()][4];
		for (int i = 0; i < catalog.size(); i++) {
			Course c = catalog.get(i);
			catalogArray[i] = c.getShortDisplayArray();
		}
		return catalogArray;
	}

	/**
	 * Saves the catalog course records to a given file.
	 * 
	 * @param fileName : pathname of a given file
	 * 
	 * @throws IllegalArgumentException if file cannot be written to
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved");
		}
	}

}
