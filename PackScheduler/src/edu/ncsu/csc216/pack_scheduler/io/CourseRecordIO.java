
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 *  Reads Course records from text files. Writes a set of CourseRecords to a file.
 * 
 *  @author Sarah Heckman
 *  @author gasarvis
 */
public class CourseRecordIO {

    /**
     *  Reads course records from a file and generates a list of valid Courses.  Any invalid
     *  Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     *  a File NotFoundException is thrown.
     *  
     *  @param fileName : File to read Course records from
     * 
     *  @return a list of valid Courses
     * 
     *  @throws FileNotFoundException if the file cannot be found or read
     */
    public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
    	Scanner fileReader = new Scanner(new FileInputStream(fileName));
        SortedList<Course> courses = new SortedList<Course>();
        
        while (fileReader.hasNextLine()) {
            try {
                //Read the line, process it in readCourse, and get the object
                Course course = readCourse(fileReader.nextLine()); 
                boolean duplicate = false;
                
                for (int i = 0; i < courses.size(); i++) {
                    Course current = courses.get(i);
                    
                    if (course.getName().equals(current.getName()) &&
                            course.getSection().equals(current.getSection())) {
                        duplicate = true;
                        break;
                    }
                }
                
                if (!duplicate) {
                    courses.add(course);
                }
            } 
            catch (IllegalArgumentException e) {
                //The line is invalid b/c we couldn't create a course, skip it!
            }
        }
        
        fileReader.close();
        return courses;
    }

    /**
     *  Writes the given list of Courses to a text file. An exception is thrown if the text file can 
     *  not be written to.
     *  
     *  @param fileName : File to write schedule of Courses to
     *  @param courses : List of Courses to write
     *  
     *  @throws IOException if cannot write to file
     */
    public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
        PrintStream courseWriter = new PrintStream(new File(fileName));
        
        for (int i = 0; i < courses.size(); i++) {
        	courseWriter.println(courses.get(i).toString());
        }
        
        courseWriter.close();
    }
    
    /**
     *  Method creates a Course object from a formatted String from a text file containing all the 
     *  fields of a Course object. An exception is thrown if the String is improperly formatted.
     * 
     *  @param line : String that contains a Course's information from a text file
     *  
     *  @return Course object from input String from text file
     *  
     *  @throws IllegalArgumentException if course file is improperly formatted
     */
    private static Course readCourse(String line) {
    	Scanner lineReader = new Scanner(line);
    	lineReader.useDelimiter(",");
       
       	try {
       		// read in tokens for name, title, section, credits, instructorId, and meetingDays and store in local variables
       		String name = lineReader.next();
       		String title = lineReader.next();
       		String section = lineReader.next();
       		int credits = lineReader.nextInt();
       		String instructorId = lineReader.next();
       		int enrollmentCap = Integer.valueOf(lineReader.next());
       		String meetingDays = lineReader.next();
       		
       		// if meeting days is "A", use the constructor for augmented meeting days and return new course object
       		if ("A".equals(meetingDays)) {
       			if (lineReader.hasNext()) {
       				lineReader.close();
       				throw new IllegalArgumentException("Invalid course.");
       			}
       			else {
       				lineReader.close();
       				return new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays);
       			}
       		}
       		
       		// read in tokens for start and end time
       		int startTime = lineReader.nextInt();
       		int endTime = lineReader.nextInt();
       		
       		if (lineReader.hasNext()) {
       			lineReader.close();
   				throw new IllegalArgumentException("Invalid course.");
   			}
       		
       		// return new course object
       		lineReader.close();
       		return new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);
       } 
       catch (Exception e) {
    	   throw new IllegalArgumentException("");
       }
    }

}
