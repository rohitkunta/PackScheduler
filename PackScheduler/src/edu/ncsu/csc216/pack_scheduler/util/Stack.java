/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Stack Interface that will delegate to either ArrayList or LinkedList
 * @param <E> the type of element the Stack will have
 */
public interface Stack<E> {
	
	
	/**
	 * Adds the element to the top of the stack
	 * @param element the element being added to the stack
	 * @throws IllegalArgumentException if there is no more room in the stack(capacity has been reached)
	 */
	void push(E element);

	/**
	 * Removes and returns the element at the top of the stack
	 * @return the element once at the top of the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	E pop() throws EmptyStackException;
	
	/**
	 * Returns true if the stack is empty (size is 0)
	 * @return true if the stack is empty, false if the stack contains atleast one element
	 */
	boolean isEmpty();
	
	
	/**
	 * Returns the number of elements in the stack
	 * @return the number of elements in the stack
	 */
	int size();
	
	/**
	 * Sets the capacity of the stack
	 * @param capacity the capacity of the stack to be set
	 * @throws IllegalArgumentException if the actual parameter is negative or 
	 * if it is less than the number of elements in the stack
	 */
	void setCapacity(int capacity);
	
}
