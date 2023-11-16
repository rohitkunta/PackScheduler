package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class InvalidTransitionExceptionTest {

	/**
	 * Test method for InvalidTransitionException with String parameter.
	 */
	@Test
	void testInvalidTransitionExceptionString() {
		InvalidTransitionException it = new InvalidTransitionException("Custom exception message");
		assertEquals("Custom exception message", it.getMessage());
	}

	/**
	 * Test method for InvalidTransitionException without parameter.
	 */
	@Test
	void testInvalidTransitionException() {
		InvalidTransitionException it = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", it.getMessage());
	}

}
