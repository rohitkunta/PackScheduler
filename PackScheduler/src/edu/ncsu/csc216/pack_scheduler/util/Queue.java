package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * A queue interface to be implemented for arraylist and linkedlist.
 * 
 * @param <E> receives the datatype
 * 
 * @author Nalin
 * @author Rohit
 */
public interface Queue<E> {

	/**
	 * To add an element to the queue.
	 * 
	 * @param element receives the element to be added.
	 * @throws IllegalArgumentException if the size is equal to the capacity.
	 */
	void enqueue(E element);

	/**
	 * A method to remove the element.
	 * 
	 * @return returns the removed element.
	 * @throws NoSuchElementException if the stack is empty.
	 */
	E dequeue();

	/**
	 * A method to check if the queue is empty or not.
	 * 
	 * @return returns a boolean value true or false accordingly.
	 */
	boolean isEmpty();

	/**
	 * A method to get the size of the queue.
	 * 
	 * @return returns the size of the queue.
	 */
	int size();

	/**
	 * A setter method to set the capacity.
	 * 
	 * @param capacity to be set.
	 * @throws IllegalArgumentException if the capacity is invalid.
	 */
	void setCapacity(int capacity);

}
