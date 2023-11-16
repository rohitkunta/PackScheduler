/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Class that reads in faculty records from a file and writes faculties records to
 * a file
 */
public class FacultyRecordIO {
	
	/**
	 * Reads Faculty Records from a file and loads them into a Doubly Linked List
	 * @param fileName name of the file being read from
	 * @return facultyList the Doubly Linked List that contains the faculty read from fileName
	 * @throws FileNotFoundException if the file cannot be found on the system
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException
	{
		Scanner file = new Scanner(new FileInputStream(fileName));
		LinkedList<Faculty> facultyRecords = new LinkedList<Faculty>();

		while (file.hasNextLine()) {
			try {
				facultyRecords.add(processFaculty(file.nextLine()));
			} catch (IllegalArgumentException e) {
				// undefined
			}
		}

		return facultyRecords;
	}
	
	/**
	 * Processes a string of a single faculty.
	 * @param line the String containing one faculty member's records
	 * @return faculty the faculty member that was processed 
	 * @throws IllegalArgumentException if it cannot properly create a faculty object
	 */
	private static Faculty processFaculty(String line)
	{
		Scanner lineReader = new Scanner(line);
		lineReader.useDelimiter(",");

		try {
			String first = lineReader.next();
			String last = lineReader.next();
			String id = lineReader.next();
			String email = lineReader.next();
			String hashPW = lineReader.next();
			int maxCourses = lineReader.nextInt();
			lineReader.close();

			return new Faculty(first, last, id, email, hashPW, maxCourses);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid faculty.");
		}
	}
	
	/**
	 * Writes a DoublyLinkedList of Faculty members to a specified file 
	 * @param fileName the name of the specified file
	 * @param facultyList the DoublyLinkedList of Faculty members being written from
	 * @throws IOException if cannot write to file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyList) throws IOException
	{
		PrintStream facultyWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < facultyList.size(); i++) {
			facultyWriter.println(facultyList.get(i).toString());
		}

		facultyWriter.close();
	}
	
	

}
