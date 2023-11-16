package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * A program to ensure a course's name at NCSU is in the correct format.
 * 1-4 letters in the prefix, 3 digits, and an optional letter suffix is permitted.
 * 
 * @author Noah Clouser
 * @author Rohan
 * @author Nalin
 */
public class CourseNameValidator {

	/** Integer field keeping track of the number of digits in a course name */
	private int digitCount = 0;
	/** Integer field keeping track of the number of letters in a course name */
	private int letterCount = 0;

	/** current state */
	private State currentState;

	/**
	 * Abstract class that contains the methods needed to transition a course's name inside the FSM
	 */
	public abstract class State {

		/**
		 * Abstract method that is passed to each State to be processed by each State individually when reading a letter
		 * 
		 * @throws InvalidTransitionException if there is an invalid transition when reading a letter
		 */
		public abstract void onLetter() throws InvalidTransitionException;

		/**
		 * Abstract method that is passed to each State to be processed by each State individually when reading a digit
		 * 
		 * @throws InvalidTransitionException if there is an invalid transition when reading a digit
		 */
		public abstract void onDigit() throws InvalidTransitionException;

		/**
		 * Throws an exception if anything other than a letter or digit is read
		 * @throws InvalidTransitionException if any character other than a letter or digit is read
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}

	/**
	 * The state indicating the program has read a letter from the courseName string. A course name must contain 1-4 letters in the prefix of the string.
	 */
	public class LetterState extends State {

		/** Maximum number of letters allowed before reading digits */
		private static final int MAX_PREFIX_LETTERS = 4;

		/**
		 * Verifies that the letterCount field does not exceed the max number of prefix letter. If true throw exception, otherwise update to a LetterState 
		 * and increase the letterCount field.
		 * @throws InvalidTransitionException because the string should not accept anymore letters after 4 have already been read
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (letterCount < MAX_PREFIX_LETTERS) {
				currentState = new LetterState();
				letterCount++;
			} else {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}

		/**
		 * Updates the State to a NumberState and updates the field digitCount.
		 */
		@Override
		public void onDigit() {
			currentState = new NumberState();
			digitCount++;
		}
	}

	/**
	 * The state indicating the program has its final letter and the course name should be complete. Throw an exception if any character is read.
	 */
	public class SuffixState extends State {

		/**
		 * checks to see if there are letters after the suffix
		 * @throws InvalidTransitionException because the string should not accept any other characters in the SuffixState
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}

		/**
		 * checks to see if there are numbers after the suffix
		 * @throws InvalidTransitionException because the string should not accept any other characters in the SuffixState
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
	}

	/**
	 * The state indicating the program has begun and is in the InitialState. A letter is only permitted to be read at this point 
	 * as the course's name must contain 1-4 letters first
	 */
	public class InitialState extends State {

		/**
		 * Updates the State to a LetterState and updates the field letterCount.
		 */
		@Override
		public void onLetter() {
			currentState = new LetterState();
			letterCount++;
		}

		/** 
		 * checks to see if the first character is a digit
		 * @throws InvalidTransitionException because a course name requires the string to start with a letter.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}

	/**
	 * The state indicating the program has read a digit from the courseName string. A course name must contain 3 digits after reading 1-4 letters. 
	 * An optional single letter is also permitted after a third digits is read.
	 */
	public class NumberState extends State {

		/** Required number of digits in a course's name */
		private static final int COURSE_NUMBER_LENGTH = 3;
		
		/**
		 * Updates the State to a SuffixState if 3 digits have already been read and updates the field letterCount.
		 * 
		 * @throws InvalidTransitionException if the number of digits in the course name does not equal 3
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (digitCount == COURSE_NUMBER_LENGTH) {
				currentState = new SuffixState();
			} else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}

		/**
		 * Updates the State to a NumberState and updates the field digitCount.
		 * 
		 * @throws InvalidTransitionException if the number of digits in the course name exceeds 3
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			if (digitCount == COURSE_NUMBER_LENGTH) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			} else {
				currentState = new NumberState();
				digitCount++;	
			}
		}
	}

//	/**
//	 * 
//	 */
//	public CourseNameValidator() {
//		
//	}

	/**
	 * Verifies the courseName of a course is in the valid format with no spaces in between the letters and numbers
	 * 
	 * @param courseName Name of the course being checked
	 * @return True if the courseName is valid, otherwise return false
	 * @throws InvalidTransitionException  if the courseName contains an invalid character in the string
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		currentState = new InitialState();
		letterCount = 0;
		digitCount = 0;
		boolean validEndState = false;
		char ch;
		for(int i = 0; i < courseName.length(); i++) {
			ch = courseName.charAt(i);
			if(Character.isLetter(ch)){
				currentState.onLetter();
			} else if(Character.isDigit(ch)) {
				currentState.onDigit();
			} else {
				currentState.onOther();
			}
		}
		if(digitCount == 3 && letterCount > 0 && letterCount < 5) {
			validEndState = true;
		}
		return validEndState;
	}
}
