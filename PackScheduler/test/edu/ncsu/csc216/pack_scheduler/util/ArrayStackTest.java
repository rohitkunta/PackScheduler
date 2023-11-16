package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

class ArrayStackTest {

	@Test
	void testArrayStack() {
		
		ArrayStack<Integer> stack = new ArrayStack<Integer>(5);
		
		assertEquals(0, stack.size());
		
		assertThrows(IllegalArgumentException.class, () -> new ArrayStack<Integer>(-1));
		
	}

	@Test
	void testPush() {

		ArrayStack<Integer> stack = new ArrayStack<Integer>(5);
		
		stack.push(1);
		stack.push(2);
		stack.push(3);
		
		assertEquals(3, stack.size());		
		
		stack.push(4);
		stack.push(5);
		
		assertThrows(IllegalArgumentException.class, () -> stack.push(6));
		
	}

	@Test
	void testPop() {
		
		ArrayStack<Integer> stack = new ArrayStack<Integer>(5);
		
		assertThrows(EmptyStackException.class, () -> stack.pop());
		
		stack.push(1);
		stack.push(2);
		stack.push(3);
		
		assertEquals(3, stack.pop());
		
	}

	@Test
	void testIsEmpty() {
		
		ArrayStack<Integer> stack = new ArrayStack<Integer>(5);
		
		assertTrue(stack.isEmpty());
		
		stack.push(1);
		stack.push(2);
		stack.push(3);
		
		assertFalse(stack.isEmpty());
	}

	@Test
	void testSetCapacity() {
		
		ArrayStack<Integer> stack = new ArrayStack<Integer>(5);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		
		assertThrows(IllegalArgumentException.class, () -> stack.setCapacity(1));
	}

}
