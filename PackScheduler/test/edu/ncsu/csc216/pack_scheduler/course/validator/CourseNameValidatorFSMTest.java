package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CourseNameValidatorFSMTest {

	@Test
	void testIsValid() throws InvalidTransitionException {
		CourseNameValidatorFSM f = new CourseNameValidatorFSM();
		Exception e = assertThrows(InvalidTransitionException.class,
				() -> f.isValid("CS*216"));
		assertEquals(e.getMessage(), "Course name can only contain letters and digits.");
		assertTrue(f.isValid("E115"));
		Exception e1 = assertThrows(InvalidTransitionException.class,
				() -> f.isValid("115"));
		assertEquals(e1.getMessage(), "Course name must start with a letter.");
		assertTrue(f.isValid("MA201"));
		assertTrue(f.isValid("CSC216"));
		assertTrue(f.isValid("HEST101"));
		Exception e2 = assertThrows(InvalidTransitionException.class,
				() -> f.isValid("MAMAM115"));
		assertEquals(e2.getMessage(), "Course name cannot start with more than 4 letters.");
		Exception e3 = assertThrows(InvalidTransitionException.class,
				() -> f.isValid("CSC2A7"));
		assertEquals(e3.getMessage(), "Course name must have 3 digits.");
		Exception e4 = assertThrows(InvalidTransitionException.class,
				() -> f.isValid("CSC21A"));
		assertEquals(e4.getMessage(), "Course name must have 3 digits.");
		Exception e5 = assertThrows(InvalidTransitionException.class,
				() -> f.isValid("CSC2177"));
		assertEquals(e5.getMessage(), "Course name can only have 3 digits.");
		assertTrue(f.isValid("CSC216A"));
		Exception e6 = assertThrows(InvalidTransitionException.class,
				() -> f.isValid("CSC216AA"));
		assertEquals(e6.getMessage(), "Course name can only have a 1 letter suffix.");
		Exception e7 = assertThrows(InvalidTransitionException.class,
				() -> f.isValid("CSC217A7"));
		assertEquals(e7.getMessage(), "Course name cannot contain digits after the suffix.");
		
		
		
		
		
	}

}
