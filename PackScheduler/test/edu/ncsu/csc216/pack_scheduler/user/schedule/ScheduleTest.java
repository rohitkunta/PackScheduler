/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Test file for Schedule.java
 */
class ScheduleTest {
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course Roll enrollment cap */
	private static final int ENROLLMENTCAP = 100;
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/**
	 * Tests the constructor method to ensure the ArrayList
	 */
	@Test
	void testSchedule() {
		Schedule s = new Schedule();
		assertEquals("My Schedule", s.getTitle());

	}

	/**
	 * Unit tests for addCourseToSchedule method. Tests error throws in method.
	 */
	@Test
	void testAddCourseToSchedule() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENTCAP, MEETING_DAYS, START_TIME, END_TIME);
		Course k = new Course("CSC316", "other class", "005", 3, "instructor", ENROLLMENTCAP, "MW", 1400, 1530);
		Schedule s = new Schedule();
		assertTrue(s.addCourseToSchedule(c));
		Exception e = assertThrows(NullPointerException.class, () -> s.addCourseToSchedule(null));
		assertEquals(null, e.getMessage());
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.addCourseToSchedule(c));
		assertEquals("You are already enrolled in CSC216", e1.getMessage());
		Exception e2 = assertThrows(IllegalArgumentException.class,
				() -> s.addCourseToSchedule(k));
				assertEquals("The course cannot be added due to a conflict.", e2.getMessage());
	}

	/**
	 * Unit test method for removeCourseFromSchedule to ensure courses are removed from the schedule list
	 */
	@Test
	void testRemoveCourseFromSchedule() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENTCAP, MEETING_DAYS, START_TIME, END_TIME);
		Course k = new Course("CSC316", "other class", "005", 3, "instructor", 100, "MW", 1400, 1530);
		Schedule s = new Schedule();
		s.addCourseToSchedule(c);
		assertTrue(s.removeCourseFromSchedule(c));
		assertFalse(s.removeCourseFromSchedule(k));
	}

	/**
	 * Unit test method for reset schedule to ensure schedule is set to its default parameters
	 */
	@Test
	void testResetSchedule() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 50, MEETING_DAYS, START_TIME, END_TIME);
		Schedule s = new Schedule();
		s.addCourseToSchedule(c);
		s.setTitle("whoo");
		assertEquals("whoo", s.getTitle());
		s.resetSchedule();
		assertEquals("My Schedule", s.getTitle());
	}

	/**
	 * Unit test method for getScheduledCourse to ensure a 2D String array is properly formatted to return the schedule
	 */
	@Test
	void testGetScheduledCourse() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, 70, MEETING_DAYS, START_TIME, END_TIME);
		Schedule s = new Schedule();
		s.addCourseToSchedule(c);
		String[] sda = c.getShortDisplayArray();
		String[][] holder = s.getScheduledCourses();
		assertEquals(NAME, holder[0][0]);
		assertEquals(SECTION, holder[0][1]);
		assertEquals(TITLE, holder[0][2]);
		assertEquals(sda[3], holder[0][3]);
	}
	

	/**
	 * Unit test method for setTitle to ensure the setter method is working properly for the field title.
	 */
	@Test
	void testSetTitle() {
		Schedule s = new Schedule();
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> s.setTitle(null));
		assertEquals("Title cannot be null", e1.getMessage());
		s.setTitle(TITLE);
		assertEquals(TITLE, s.getTitle());
	}

	/**
	 * Unit test method for getTitle to ensure the getter method is working properly for the field title.
	 */
	@Test
	void testGetTitle() {
		Schedule s = new Schedule();
		assertEquals("My Schedule", s.getTitle());
	}

}
