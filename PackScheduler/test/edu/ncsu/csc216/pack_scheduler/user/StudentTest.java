package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*; 

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.Test;

/**
 * Tests Student.java
 * 
 * @author Grant Goldsmith
 * @author Greydon Sarvis
 * @author Noah Clouser
 */
class StudentTest {
	

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
	
	//This is a block of code that is executed when the StudentTest object is
	//created by JUnit.  Since we only need to generate the hashed version
	//of the plaintext password once, we want to create it as the StudentTest object is
	//constructed.  By automating the hash of the plaintext password, we are
	//not tied to a specific hash implementation.  We can change the algorithm
	//easily.
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
	
	/** Example student with 18 credit hours*/
	Student s1 = new Student(firstName, lastName, id, email, password, 18);
	/** Example student without credit hours variable*/
	User s2 = new Student(firstName, lastName, id, email, password);
	
	
	

	/**
	 * Tests valid student constructor with max credits
	 */
	@Test
	void testValidStudentWithMaxCredits() {
		assertEquals("first,last,flast,first_last@ncsu.edu," + password + ",18", s1.toString());
	}

	/**
	 * Tests valid student without max credits
	 */
	@Test
	void testtestValidStudentWithoutMaxCredits() {
		assertEquals("first,last,flast,first_last@ncsu.edu," + password + ",18", s2.toString());
	}

	/**
	 * Tests valid email addresses
	 */
	@Test
	void testSetEmailValid() {
		assertEquals("first_last@ncsu.edu", s1.getEmail());
		assertEquals("first_last@ncsu.edu", s2.getEmail());
	}
	
	/**
	 * Tests invalid email addresses
	 */
	@Test
	void testSetEmailInvalid() {
		
		// Testing to make sure the email is not null, empty, does not contain @ or .
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, null, password));
		assertEquals("Invalid email", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "", password));
		assertEquals("Invalid email", e2.getMessage());
		
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "first_lastncsu.edu", password));
		assertEquals("Invalid email", e3.getMessage());
		
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "first_last@ncsuedu", password));
		assertEquals("Invalid email", e4.getMessage());
		
		Exception e5 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, null, password, 18));
		assertEquals("Invalid email", e5.getMessage());
		
		Exception e6 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "", password, 18));
		assertEquals("Invalid email", e6.getMessage());
		
		Exception e7 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "first_lastncsu.edu", password, 18));
		assertEquals("Invalid email", e7.getMessage());
		
		Exception e8 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "first_last@ncsuedu", password, 18));
		assertEquals("Invalid email", e8.getMessage());
		
		//Testing to see if the . comes before the @ in the email
		Exception e9 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, "first_last.ncsu@edu", password, 18));
		assertEquals("Invalid email", e9.getMessage());
	}

	/**
	 * Tests for correct hashCode password is set to password
	 */
	@Test
	void testSetpasswordValid() {
		assertEquals(password, s1.getPassword());
		assertEquals(password, s2.getPassword());
	}
	
	/**
	 * Tests for incorrect hashCode password is set to password
	 */
	@Test
	void testSetpasswordInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, null));
		assertEquals("Invalid password", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, ""));
		assertEquals("Invalid password", e2.getMessage());
		
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, null, 18));
		assertEquals("Invalid password", e3.getMessage());
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, "", 18));
		assertEquals("Invalid password", e4.getMessage());
	}

	/**
	 * Tests valid integer values for maxCredits
	 */
	@Test
	void testSetMaxCreditsValid() {
		assertEquals(18, s1.getMaxCredits());
	}
	
	/**
	 * Tests invalid integer values for maxCredits
	 */
	@Test
	void testSetMaxCreditsInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, password, 2));
		assertEquals("Invalid max credits", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, lastName, id, email, password, 24));
		assertEquals("Invalid max credits", e2.getMessage());
	}

	/**
	 * Tests valid first names for student
	 */
	@Test
	void testSetFirstNameValid() {
		
		assertEquals("first", s1.getFirstName());
		assertEquals("first", s2.getFirstName());

	}
	
	/**
	 * Tests invalid first names for student
	 */
	@Test
	void testSetFirstNameInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(null, lastName, id, email, password));
		assertEquals("Invalid first name", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student("", lastName, id, email, password));
		assertEquals("Invalid first name", e2.getMessage());
		
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Student(null, lastName, id, email, password, 18));
		assertEquals("Invalid first name", e3.getMessage());
		
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Student("", lastName, id, email, password, 18));
		assertEquals("Invalid first name", e4.getMessage());
	}

	/**
	 * Test valid last name for student
	 */
	@Test
	void testSetLastNameValid() {

		assertEquals("last", s1.getLastName());
		assertEquals("last", s2.getLastName());
		
	}
	
	/**
	 * Test invalid last name for student
	 */
	@Test
	void testSetLastNameInvalid() {

		Exception e1 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, null, id, email, password));
		assertEquals("Invalid last name", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, "", id, email, password));
		assertEquals("Invalid last name", e2.getMessage());
		
		Exception e3 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, null, id, email, password, 18));
		assertEquals("Invalid last name", e3.getMessage());
		
		Exception e4 = assertThrows(IllegalArgumentException.class,
				() -> new Student(firstName, "", id, email, password, 18));
		assertEquals("Invalid last name", e4.getMessage());
		
	}

	/**
	 * Tests equals method works for all course fields
	 */
	@Test
	void testEqualsObject() {
		User student1 = new Student(firstName, lastName, id, email, password);
		User student2 = new Student(firstName, lastName, id, email, password);
		User student3 = new Student(firstName, "different", id, email, password);
		User student4 = new Student("different", lastName, id, email, password);
		
		// Test for equality in both directions
		assertEquals(student1, student2);
		assertEquals(student2, student1);

		// Test for each of the fields
		assertNotEquals(student1, student3);
		assertNotEquals(student1, student4);
	}

	/**
	 * Test toString() method.
	 */
	@Test
	public void testToString() {
		User student = new Student(firstName, lastName, id, email, password);
		assertEquals("first,last,flast,first_last@ncsu.edu," + password + ",18", student.toString());
	}
	
	/**
	 * Tests hashCode works correctly
	 */
	@Test
	void testHashCode() {
		User student1 = new Student(firstName, lastName, id, email, password);
		User student2 = new Student(firstName, lastName, id, email, password);
		User student3 = new Student(firstName, "different", id, email, password);
		User student4 = new Student("different", lastName, id, email, password);
		
		// Test for the same hash code for the same values
		assertEquals(student1.hashCode(), student2.hashCode());

		// Test for each of the fields
		assertNotEquals(student1.hashCode(), student3.hashCode());
		assertNotEquals(student1.hashCode(), student4.hashCode());
	}

	/**
	 * Tests compareTo method, checks to make sure a student will be ordered by last name first, then first name, and then id
	 */
	@Test
	void compareToTest() {
		Student student1 = new Student(firstName, lastName, id, email, password);
		Student student2 = new Student(firstName, lastName, id, email, password);
		Student student3 = new Student("Greg", "Davis", "gdavis", "gdavis@ncsu.edu", password);
		Student student4 = new Student("Jim", "Johnson", "jjohnso", "jjohnso@ncsu.edu", password);
		Student student5 = new Student("Jonathon", "Miller", "jmiller", "jmiller@ncsu.edu", password);
		Student student6 = new Student("David", "Miller", "dmiller", "dmiller@ncsu.edu", password);
		Student student7 = new Student("David", "Miller", "dmiller1", "dmiller1@ncsu.edu", password);
		Student student8 = new Student("David", "Millers", "dmillers", "dmillers@ncsu.edu", password);
		Student student9 = new Student("Jimmy", "Johnson", "jjohnso1", "jjohnso1@ncsu.edu", password);
		
		assertEquals(0, student1.compareTo(student2));
		assertEquals(0, student2.compareTo(student1));
		
		assertTrue(0 > student3.compareTo(student4));
		assertTrue(0 < student5.compareTo(student6));
		assertTrue(0 > student6.compareTo(student7));
		assertTrue(0 > student7.compareTo(student8));
		assertTrue(0 > student4.compareTo(student9));
		
	}
}
