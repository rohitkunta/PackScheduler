
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
/**
 * JUnit tests for CourseCatalpg.java
 */
class CourseCatalogTest {

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
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	/** Course meeting date and time */
	private static final String MEETING_STRING = "TH 1:30PM-2:45PM";
	/** Course Roll enrollment cap */
	private static final int ENROLLMENTCAP = 100;
	/**
	 * Resets course_records.txt for use in other tests.
	 */
	@Before
	public void setUp() throws Exception {
		// Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Test method for CourseCatalog.CourseCatalog()
	 */
	@Test
	void testCourseCatalog() {
		// Test with invalid file. Should have an empty catalog and schedule.
		CourseCatalog cc = new CourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);
		cc.saveCourseCatalog("test-files/actual_empty_export.txt");
		
	}

	/**
	 * Test method for CourseCatalog.newCourseCatalog()
	 */
	@Test
	void testNewCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile("test-files/course_records.txt");
		assertEquals(13, cc.getCatalog().size());
		// Ensure that there were courses in catalog before reset

		cc.newCourseCatalog();
		assertEquals(0, cc.getCatalog().size());
		cc.saveCourseCatalog("test-files/actual_empty_export.txt");
	
	}
	/**
	 * Test method for CourseCatalog.loadCoursesFromFile() with valid file
	 */
	@Test
	void testLoadCoursesFromFile() {
		// Test valid course catalog text file
		CourseCatalog cc1 = new CourseCatalog();
		cc1.loadCoursesFromFile("test-files/course_records.txt");
		assertEquals(13, cc1.getCatalog().size());
		cc1.saveCourseCatalog("test-files/actual_course_records.txt");
	

		// Test invalid course catalog text file
		CourseCatalog cc2 = new CourseCatalog();
		Exception e = assertThrows(IllegalArgumentException.class,
				() -> cc2.loadCoursesFromFile("test-files/nonexistent.txt"));
		assertEquals(e.getMessage(), "Invalid file name. FileNotFoundException was thrown.");
	}

	/**
	 * Test method for CourseCatalog.addCourseToCatalog()
	 */
	@Test
	void testAddCourseToCatalog() {
		CourseCatalog cc = new CourseCatalog();

		// Test valid Student
		assertTrue(cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENTCAP, MEETING_DAYS, START_TIME,
				END_TIME));
		assertFalse(cc.addCourseToCatalog(NAME, "ah", SECTION, CREDITS, "INSTRUCTOR_ID", ENROLLMENTCAP, MEETING_DAYS, 900, 1000));
		String[][] courseCatalog = cc.getCourseCatalog();
		assertEquals(1, courseCatalog.length);
		assertEquals(NAME, courseCatalog[0][0]);
		assertEquals(SECTION, courseCatalog[0][1]);
		assertEquals(TITLE, courseCatalog[0][2]);
		assertEquals(MEETING_STRING, courseCatalog[0][3]);
	}

	/**
	 * Test method for CourseCatalog.removeCourseFromCatalog()
	 */
	@Test
	void testRemoveCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();

		// Add students and remove
		cc.loadCoursesFromFile("test-files/course_records.txt");
		assertEquals(13, cc.getCourseCatalog().length);
		assertTrue(cc.removeCourseFromCatalog("CSC116", "002"));
		String[][] courseCatalog = cc.getCourseCatalog();
		assertEquals(12, courseCatalog.length);
		assertEquals("CSC116", courseCatalog[1][0]);
		assertEquals("003", courseCatalog[1][1]);
		assertEquals("Intro to Programming - Java", courseCatalog[1][2]);
	}

	/**
	 * Test method for CourseCatalog.getCourseFromCatalog()
	 */
	@Test
	void testGetCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();

		// Add students and pull from catalog
		cc.loadCoursesFromFile("test-files/course_records.txt");
		assertEquals(cc.getCourseFromCatalog("CSC216", "001"),
				new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENTCAP, MEETING_DAYS, START_TIME, END_TIME));
	}

	/**
	 * Test method for {CourseCatalog.getCourseCatalog()
	 */
	@Test
	void testGetCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		
		// Add students to catalog to get display array from
		cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", 100, "MW", 910, 1100);
		cc.addCourseToCatalog("CSC216", "Intro to Programming - Java", "002", 3, "spbalik", 50, "MW", 1120, 1310);
		cc.addCourseToCatalog("CSC316", "Intro to Programming - Java", "003", 3, "tbdimitr", 75, "TH", 1120, 1310);
		
		// Construct expected display array
		String[][] courseCatalog = {{"CSC116", "001", "Intro to Programming - Java", "MW 9:10AM-11:00AM", "100"},
									{"CSC216", "002", "Intro to Programming - Java", "MW 11:20AM-1:10PM", "50"},
									{"CSC316", "003", "Intro to Programming - Java", "TH 11:20AM-1:10PM", "75"}};
		
		// Assert that each element in each array are equivalent
		assertEquals(cc.getCourseCatalog()[0][0], courseCatalog[0][0]);
		assertEquals(cc.getCourseCatalog()[0][1], courseCatalog[0][1]);
		assertEquals(cc.getCourseCatalog()[0][2], courseCatalog[0][2]);
		assertEquals(cc.getCourseCatalog()[0][3], courseCatalog[0][3]);
		assertEquals(cc.getCourseCatalog()[0][4], courseCatalog[0][4]);
		assertEquals(cc.getCourseCatalog()[1][0], courseCatalog[1][0]);
		assertEquals(cc.getCourseCatalog()[1][1], courseCatalog[1][1]);
		assertEquals(cc.getCourseCatalog()[1][2], courseCatalog[1][2]);
		assertEquals(cc.getCourseCatalog()[1][3], courseCatalog[1][3]);
		assertEquals(cc.getCourseCatalog()[1][4], courseCatalog[1][4]);
		assertEquals(cc.getCourseCatalog()[2][0], courseCatalog[2][0]);
		assertEquals(cc.getCourseCatalog()[2][1], courseCatalog[2][1]);
		assertEquals(cc.getCourseCatalog()[2][2], courseCatalog[2][2]);
		assertEquals(cc.getCourseCatalog()[2][3], courseCatalog[2][3]); 
		assertEquals(cc.getCourseCatalog()[2][4], courseCatalog[2][4]); 
	}

	/**
	 * Test method for CourseCatalog.saveCourseCatalog()
	 */
	@Test
	void testSaveCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		assertEquals(0, cc.getCatalog().size());
		cc.loadCoursesFromFile("test-files/course_records.txt");
		cc.saveCourseCatalog("test-files/actual_course_records.txt");
	
	}


}
