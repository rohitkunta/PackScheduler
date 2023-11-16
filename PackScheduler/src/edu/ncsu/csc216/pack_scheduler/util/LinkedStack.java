/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * Implements IStack Interface through ArrayList
 * 
 * @param <E> the element type being used in the LinkedStack
 */
public class LinkedStack<E> implements Stack<E> {

	/** LinkedAbstractList of Elements */
	private LinkedAbstractList<E> list;

	/** stores the capacity */
	private int capacity;

	/** stores the size */
	private int size;

	/**
	 * Constructor for LinkedStack Creates new LinkedList object
	 * 
	 * @param capacity the capacity to be set
	 */
	public LinkedStack(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
	}

	/**
	 * Adds the element to the top of the stack
	 * 
	 * @param element the element being added to the stack
	 * @throws IllegalArgumentException if there is no more room in the
	 *                                  stack(capacity has been reached)
	 */
	@Override
	public void push(E element) {
		if(size == capacity) {
			throw new IllegalArgumentException();
		}
		list.add(element);
		size++; 
	}

	/**
	 * Removes and returns the element at the top of the stack
	 * 
	 * @return the element once at the top of the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E pop() {
		if (list.size() == 0) {
			throw new EmptyStackException();
		}

		E item = list.remove(list.size() - 1);
		size--; 
		return item;
	}

	/**
	 * Returns true if the stack is empty (size is 0)
	 * 
	 * @return true if the stack is empty, false if the stack contains atleast one
	 *         element
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Returns the number of elements in the stack
	 * 
	 * @return the number of elements in the stack
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Sets the capacity of the stack
	 * 
	 * @param capacity the capacity of the stack to be set
	 * @throws IllegalArgumentException if the actual parameter is negative or if it
	 *                                  is less than the number of elements in the
	 *                                  stack
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException("Invalid ArrayStack Capacity");
		}

		this.capacity = capacity;

	}

}
