package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Exception that indicated that there is a conflict between a staged Activity
 * object to be added to a schedule and an Activity object within said schedule.
 * 
 * @author gasarvis
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for ConflictExceptiion given exception message is provided.
	 * 
	 * @param message : message for exception
	 */
	public ConflictException(String message) {
		// call the Exception parent class
		super(message);
	}

	/**
	 * Default constructor for ConflictException given no parameters.
	 */
	public ConflictException() {
		// call the parameterized constructor
		this("Schedule conflict.");
	}

}
