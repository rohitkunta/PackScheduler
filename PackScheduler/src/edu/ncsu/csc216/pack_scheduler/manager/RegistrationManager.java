package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * registration manager class deals with creating the registrar and holds the
 * methods that allows the user to manipulate the registrar has methods for
 * logging in and out of a registrar and getting the current user, student
 * directory, or course catalog
 * 
 * @author Noah Clouser
 * @author Rohan
 * @author Nalin
 */
public class RegistrationManager {

	/**
	 * Single instance of the Registration Manager that will be referenced
	 * throughout the program
	 */
	private static RegistrationManager instance;

	/** course catalog variable that holds a list of courses */
	private CourseCatalog courseCatalog;

	/** A directory of students, each student has a unique id */
	private StudentDirectory studentDirectory;

	/** private registrar user */
	private User registrar;

	/** current user */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** prop file */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * private constructor creates new Course Catalog creates new Student Directory
	 */
	private RegistrationManager() {

		courseCatalog = new CourseCatalog();
		studentDirectory = new StudentDirectory();
		createRegistrar();

	}

	/**
	 * method to create registrar takes in a properties file and creates a new
	 * registrar
	 * 
	 * @throws IllegalArgumentException throws exception if you cannot create
	 *                                  registrar
	 */
	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * The method to the password. hashes the password
	 * 
	 * @param pw receives the password
	 * @return returns the string of the password
	 * @throws IllegalArgumentException if cannot hash password
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * checks if an instance of the registration manager has been created or not if
	 * the current instance is null then create new manager
	 * 
	 * @return instance
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * returns course catalog
	 * 
	 * @return course catalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * returns the student directory
	 * 
	 * @return student directory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * sets the current user based on who is logging in
	 * 
	 * @param id       id of student trying to log in
	 * @param password of student trying to log in
	 * @return true if the id and password are valid
	 * @throws IllegalArgumentException if the user does not exist
	 */
	public boolean login(String id, String password) {
		Student s = studentDirectory.getStudentById(id);
		String localHashPW = hashPW(password);
		if (currentUser != null && !registrar.getId().equals(currentUser.getId())) {
			return false;
		} else if (registrar.getId().equals(id)) {
			if (registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
				return true;
			}
			return false;
		}

		else if (s != null) {
			if (s.getPassword().equals(localHashPW)) {
				currentUser = s;
				return true;
			}
			return false;
		}

		else {
			throw new IllegalArgumentException("User doesn't exist.");
		}
	}

	/**
	 * logs the user out sets the current user to null
	 */
	public void logout() {
		currentUser = null;
	}

	/**
	 * 
	 * returns the current user
	 * 
	 * @return current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * clears the catalog and directory creates new courseCatalog and
	 * studentDirectory
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
	}

	/**
	 * static registrat class that extends user contains a constructor with 5
	 * parameters that creates registrar objects
	 */
	private static class Registrar extends User {
		/**
		 * 
		 * Create a registrar user.
		 * 
		 * @param firstName first name
		 * @param lastName  last name
		 * @param id        id
		 * @param email     email
		 * @param hashPW    hashpw
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}

	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * 
	 * @param c Course to enroll in
	 * @return true if enrolled
	 * @throws IllegalArgumentException with the message the message "Illegal
	 *                                  Action".
	 */
	public boolean enrollStudentInCourse(Course c) {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			CourseRoll roll = c.getCourseRoll();

			if (s.canAdd(c) && roll.canEnroll(s)) {
				schedule.addCourseToSchedule(c);
				roll.enroll(s);
				return true;
			}

		} catch (IllegalArgumentException e) {
			return false;
		}
		return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * 
	 * @param c Course to drop
	 * @return true if dropped
	 * @throws IllegalArgumentException with the message the message "Illegal
	 *                                  Action".
	 */
	public boolean dropStudentFromCourse(Course c) {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			c.getCourseRoll().drop(s);
			return s.getSchedule().removeCourseFromSchedule(c);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * Resets the logged in student's schedule by dropping them from every course
	 * and then resetting the schedule.
	 * 
	 * @throws IllegalArgumentException with the message the message "Illegal
	 *                                  Action".
	 */
	public void resetSchedule() {
		if (!(currentUser instanceof Student)) {
			throw new IllegalArgumentException("Illegal Action");
		}
		try {
			Student s = (Student) currentUser;
			Schedule schedule = s.getSchedule();
			String[][] scheduleArray = schedule.getScheduledCourses();
			for (int i = 0; i < scheduleArray.length; i++) {
				Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
				c.getCourseRoll().drop(s);
			}
			schedule.resetSchedule();
		} catch (IllegalArgumentException e) {
			// do nothing
		}
	}
}