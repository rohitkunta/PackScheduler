package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Exception class
 * Deals with invalid transition exception
 * creates the message for ITE exception
 * @author Noah Clouser
 * @author Rohan
 * @author Nalin
 */
public class InvalidTransitionException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for InvalidTransitionException given exception message is provided.
	 * 
	 * @param message : message for exception
	 */
	public InvalidTransitionException(String message) {
		// call the Exception parent class
		super(message);
	}

	/**
	 * Default constructor for InvalidTransitionException given no parameters.
	 */
	public InvalidTransitionException() {
		// call the parameterized constructor
		this("Invalid FSM Transition.");
	}
}
