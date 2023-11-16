package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.Test;

class FacultyTest {

	/** Test Student's first name. */
	private String firstName = "first";
	/** Test Student's last name */
	private String lastName = "last";
	/** Test Student's id */
	private String id = "flast";
	/** Test Student's email */
	private String email = "first_last@ncsu.edu";
	/** Test Student's hashed password */
	private String password;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/**Constant number of maxCourses for all Faculty objects*/
	private static final int MAX_COURSES = 2;
	
	{
		try {
			String plaintextPW = "password";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(plaintextPW.getBytes());
			this.password = Base64.getEncoder().encodeToString(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			fail("An unexpected NoSuchAlgorithmException was thrown.");
		}
	}
	
	@Test
	void testHashCode() {
		Faculty faculty = new Faculty(firstName, lastName, id, email, password, MAX_COURSES);
		Faculty faculty2 = new Faculty(firstName, lastName, id, email, password, MAX_COURSES);
		Faculty faculty3 = new Faculty(firstName, lastName, id, email, password, 1);
		
		assertEquals(faculty.hashCode(), faculty2.hashCode());
		
		assertNotEquals(faculty.hashCode(), faculty3.hashCode());
		assertNotEquals(faculty2.hashCode(), faculty3.hashCode());
	}

	@Test
	void testEqualsObject() {
		Faculty faculty = new Faculty(firstName, lastName, id, email, password, MAX_COURSES);
		Faculty faculty2 = new Faculty(firstName, lastName, id, email, password, MAX_COURSES);
		Faculty faculty3 = new Faculty(firstName, lastName, id, email, password, 1);
		
		assertTrue(faculty.equals(faculty2));
		assertTrue(faculty.equals(faculty));
		
		assertFalse(faculty.equals(faculty3));
		assertFalse(faculty2.equals(faculty3));
		assertFalse(faculty2.equals(2));
		assertFalse(faculty2.equals("Hello world"));
		
	}

	@Test
	void testFaculty() {
		
		Faculty faculty = new Faculty(firstName, lastName, id, email, password, MAX_COURSES);
		
		assertEquals(2, faculty.getMaxCourses());
		assertEquals("first", faculty.getFirstName());
		assertEquals("last", faculty.getLastName());
	}

	@Test
	void testSetMaxCourses() {
		Faculty faculty = new Faculty(firstName, lastName, id, email, password, MAX_COURSES);
		
		faculty.setMaxCourses(1);
		
		assertEquals(1, faculty.getMaxCourses());
		
		faculty.setMaxCourses(3);
		
		assertEquals(3, faculty.getMaxCourses());
		
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> faculty.setMaxCourses(4));
		assertEquals("Invalid number of Courses.", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> faculty.setMaxCourses(0));
		assertEquals("Invalid number of Courses.", e2.getMessage());
		
	}

	@Test
	void testToString() {
		
		Faculty faculty = new Faculty(firstName, lastName, id, email, password, MAX_COURSES);
		
		assertEquals("first,last,flast,first_last@ncsu.edu," + password + ",2", faculty.toString());
		
		
	}

}
