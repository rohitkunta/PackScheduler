package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Class that reads in student records from a file and writes student records to
 * a file
 * 
 * @author Grant Goldsmith
 * @author Greydon Sarvis
 * @author Noah Clouser
 */
public class StudentRecordIO {

	/**
	 * Reads student records from a file and generates a list of valid Students.
	 * 
	 * @param fileName : File to read Student records from
	 * 
	 * @return a list of valid Students
	 * 
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner file = new Scanner(new FileInputStream(fileName));
		SortedList<Student> studentRecords = new SortedList<Student>();

		while (file.hasNextLine()) {
			try {
				studentRecords.add(readStudent(file.nextLine()));
			} catch (IllegalArgumentException e) {
				// undefined
			}
		}

		return studentRecords;
	}

	/**
	 * Writes the given list of Students to a text file.
	 * 
	 * @param fileName         : File to write schedule of Students to
	 * @param studentDirectory : List of Students to write
	 * 
	 * @throws IOException if cannot write to file
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream studentWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
			studentWriter.println(studentDirectory.get(i).toString());
		}

		studentWriter.close();
	}

	/**
	 * Method creates a Student object from a formatted String.
	 * 
	 * @param line : String that contains a Students information from a text file
	 * 
	 * @return Student object from input String from text file
	 * 
	 * @throws IllegalArgumentException if student file is improperly formatted
	 */
	private static Student readStudent(String line) {
		Scanner lineReader = new Scanner(line);
		lineReader.useDelimiter(",");

		try {
			String first = lineReader.next();
			String last = lineReader.next();
			String id = lineReader.next();
			String email = lineReader.next();
			String hashPW = lineReader.next();
			int maxCredits = lineReader.nextInt();
			lineReader.close();

			return new Student(first, last, id, email, hashPW, maxCredits);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid student.");
		}
	}

}
