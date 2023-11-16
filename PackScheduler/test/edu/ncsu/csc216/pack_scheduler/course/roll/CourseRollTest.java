package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

class CourseRollTest {

	/** minimum amount of enrollment */
	private static final int MIN_ENROLLMENT = 10;
	/** max amount of enrollment */
	private static final int MAX_ENROLLMENT = 250;

	/** Test Student's first name. */
	private String firstName = "first";
	/** Test Student's last name */
	private String lastName = "last";
	/** Test Student's id */
	private String id = "flast";
	/** Test Student's email */
	private String email = "first_last@ncsu.edu";
	/** course for testing */
	private Course c1 = new Course("CSC226", "Intro to java", "002", 4, "abida", 10, "A");

	/**
	 * Tests the constructor of the course roll class
	 */
	@Test
	void testCourseRoll() {
		CourseRoll c = new CourseRoll(c1, 15);
		assertEquals(15, c.getEnrollmentCap());
		assertEquals(15, c.getOpenSeats());
	}

	/**
	 * tests getting the enrollment cap
	 */
	@Test
	void testGetEnrollmentCap() {
		CourseRoll c = new CourseRoll(c1, 15);
		assertEquals(15, c.getEnrollmentCap());
	}

	/**
	 * tests setting the enrollment cap
	 */
	@Test
	void testSetEnrollmentCap() {
		CourseRoll c = new CourseRoll(c1, 15);
		Student s1 = new Student(firstName, lastName, id, email, "password", 12);
		Student st2 = new Student("Karl", lastName, "k1", email, "password", 12);
		assertEquals(15, c.getEnrollmentCap());
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> c.setEnrollmentCap(MAX_ENROLLMENT + 1));
		assertEquals(null, e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> c.setEnrollmentCap(MIN_ENROLLMENT - 1));
		assertEquals(null, e2.getMessage());
		c.enroll(s1);
		c.enroll(st2);
		Exception e3 = assertThrows(IllegalArgumentException.class, () -> c.setEnrollmentCap(1));
		assertEquals(null, e3.getMessage());
		c.setEnrollmentCap(50);
		assertEquals(50, c.getEnrollmentCap());
	}

	/**
	 * tests erolling a student in a course
	 */
	@Test
	void testEnroll() {
		CourseRoll c = new CourseRoll(c1, 11);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> c.enroll(null));
		assertEquals(null, e1.getMessage());
		//Example student with 18 credit hours
		Student s1 = new Student(firstName, lastName, id, email, "password", 12);
		Student s2 = new Student(firstName, lastName, id, email, "password", 12);
		c.enroll(s1);
		assertEquals(c.getOpenSeats(), 10);
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> c.enroll(s2));
		assertEquals(null, e2.getMessage());
		
		CourseRoll cr = new CourseRoll(c1, 10);
		Student st1 = new Student("Carl", lastName, "c1", email, "password", 12);
		Student st2 = new Student("Karl", lastName, "k1", email, "password", 12);
		Student st3 = new Student("Qarl", lastName, "q1", email, "password", 12);
		Student st4 = new Student("Ron", lastName, "r1", email, "password", 12);
		Student st5 = new Student("Bon", lastName, "b1", email, "password", 12);
		Student st6 = new Student("Smon", lastName, "s1", email, "password", 12);
		Student st7 = new Student("Don", lastName, "d1", email, "password", 12);
		Student st8 = new Student("Pon", lastName, "p1", email, "password", 12);
		Student st9 = new Student("Ping", lastName, "p2", email, "password", 12);
		Student st10 = new Student("Pong", lastName, "p3", email, "password", 12);
		cr.enroll(st1);
		cr.enroll(st2);
		cr.enroll(st3);
		cr.enroll(st4);
		cr.enroll(st5);
		cr.enroll(st6);
		cr.enroll(st7);
		cr.enroll(st8);
		cr.enroll(st9);
		cr.enroll(st10);
//		cr.enroll(st11);
		

	}

	/**
	 * tests dropping a student from the course roll
	 */
	@Test
	void testDrop() {
		CourseRoll c = new CourseRoll(c1, 10);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> c.drop(null));
		assertEquals(null, e1.getMessage());
		Student s1 = new Student(firstName, lastName, "nnagar1", email, "password", 12);
		Student s2 = new Student(firstName, lastName, "nnagar2", email, "password", 12);
		Student s3 = new Student(firstName, lastName, "nnagar3", email, "password", 12);
		Student s4 = new Student(firstName, lastName, "nnagar4", email, "password", 12);
		Student s5 = new Student(firstName, lastName, "nnagar5", email, "password", 12);
		Student s6 = new Student(firstName, lastName, "nnagar6", email, "password", 12);
		Student s7 = new Student(firstName, lastName, "nnagar7", email, "password", 12);
		Student s8 = new Student(firstName, lastName, "nnagar8", email, "password", 12);
		Student s9 = new Student(firstName, lastName, "nnagar9", email, "password", 12);
		Student s10 = new Student(firstName, lastName, "nnagar10", email, "password", 12);
		Student s11 = new Student(firstName, lastName, "nnagar11", email, "password", 12);
		c.enroll(s1); 
		c.enroll(s2); 
		c.enroll(s3); 
		c.enroll(s4); 
		c.enroll(s5); 
		c.enroll(s6); 
		c.enroll(s7); 
		c.enroll(s8); 
		c.enroll(s9);
		c.enroll(s10); 
		
		assertEquals(0, c.getOpenSeats()); 
		
		c.enroll(s11);
		
		assertEquals(1, c.getNumberOnWaitlist());
		
		c.drop(s1);
		
		assertEquals(0, c.getNumberOnWaitlist());
		
		
	}

	/**
	 * tests getting the open seats
	 */
	@Test
	void testGetOpenSeats() {
		CourseRoll c = new CourseRoll(c1, 11);
		Student s1 = new Student(firstName, lastName, id, email, "password", 12);
		c.enroll(s1);
		assertEquals(c.getOpenSeats(), 10);
		c.drop(s1);
		assertEquals(c.getOpenSeats(), 11);
	}

	/**
	 * tests getting if a student can enroll
	 */
	@Test
	void testCanEroll() {
		CourseRoll c = new CourseRoll(c1, 11);
		Student s1 = new Student(firstName, lastName, id, email, "password", 12);
		Student s2 = new Student(firstName, lastName, id, email, "password", 12);

		assertTrue(c.canEnroll(s1));
		c.canEnroll(s1);
		c.enroll(s1);
		assertFalse(c.canEnroll(s2));
	}
	
	/**
	 * Test drop 
	 */
	@Test
	void testDrop1() {
		CourseRoll c = new CourseRoll(c1, 11);
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> c.drop(null));
		assertEquals(null, e1.getMessage());
		Student s1 = new Student(firstName, lastName, "nnagar1", email, "password", 12);
		Student s2 = new Student(firstName, lastName, "nnagar2", email, "password", 12);
		Student s3 = new Student(firstName, lastName, "nnagar3", email, "password", 12);
		Student s4 = new Student(firstName, lastName, "nnagar4", email, "password", 12);
		Student s5 = new Student(firstName, lastName, "nnagar5", email, "password", 12);
		Student s6 = new Student(firstName, lastName, "nnagar6", email, "password", 12);
		Student s7 = new Student(firstName, lastName, "nnagar7", email, "password", 12);
		Student s8 = new Student(firstName, lastName, "nnagar8", email, "password", 12);
		Student s9 = new Student(firstName, lastName, "nnagar9", email, "password", 12);
		Student s10 = new Student(firstName, lastName, "nnagar10", email, "password", 12);
		Student s11 = new Student(firstName, lastName, "nnagar11", email, "password", 12);
		c.enroll(s1); 
		c.enroll(s2); 
		assertThrows(IllegalArgumentException.class, () -> c.enroll(s2));
		c.enroll(s3); 
		c.enroll(s4); 
		c.enroll(s5); 
		c.enroll(s6); 
		c.enroll(s7); 
		c.enroll(s8); 
		c.enroll(s9);
		c.enroll(s10); 
		
		assertEquals(1, c.getOpenSeats()); 
		
		c.enroll(s11);
		
		//assertEquals(1, c.getNumberOnWaitlist());
		
		assertThrows(IllegalArgumentException.class, () -> c.canEnroll(null));
		
		
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(null, 10));
		
		assertThrows(IllegalArgumentException.class, () -> c.setEnrollmentCap(MIN_ENROLLMENT));
		
		Student s12 = new Student(firstName, lastName, "nnagar12", email, "password", 12);
		Student s13 = new Student(firstName, lastName, "nnagar22", email, "password", 12);
		Student s14 = new Student(firstName, lastName, "nnagar32", email, "password", 12);
		Student s15 = new Student(firstName, lastName, "nnagar42", email, "password", 12);
		Student s16 = new Student(firstName, lastName, "nnagar25", email, "password", 12);
		Student s17 = new Student(firstName, lastName, "nnagar62", email, "password", 12);
		Student s18 = new Student(firstName, lastName, "nnagar72", email, "password", 12);
		Student s19 = new Student(firstName, lastName, "nnagar82", email, "password", 12);
		Student s20 = new Student(firstName, lastName, "nnagar29", email, "password", 12);
		Student s21 = new Student(firstName, lastName, "nnagar120", email, "password", 12);
		Student s22 = new Student(firstName, lastName, "nnagar121", email, "password", 12);
		
		c.enroll(s12); 
		c.enroll(s13); 
		c.enroll(s14); 
		c.enroll(s15); 
		c.enroll(s16); 
		c.enroll(s17); 
		c.enroll(s18);
		c.enroll(s19); 
		c.enroll(s20); 
		c.enroll(s21); 
		assertThrows(IllegalArgumentException.class, () -> c.enroll(s22));  
		
		
		
	}

}
