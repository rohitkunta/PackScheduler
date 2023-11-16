package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * A linkedlist for queue which implements the IQueue interface.
 * 
 * @param <E> receives the data type.
 * 
 * @author Nalin
 * @author Rohit
 */
public class LinkedQueue<E> implements Queue<E> {

	/** to store the list */
	private LinkedAbstractList<E> list;

	/** stores the capacity */
	private int capacity;

	/** stores the size */
	private int size;

	/**
	 * A public constructor.
	 * 
	 * @param capacity receives the capacity to be set.
	 */
	public LinkedQueue(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
	}

	@Override
	public void enqueue(E element) {
		if (element == null || size == capacity || contains(element)) {
			throw new IllegalArgumentException();
		} else {
			list.add(element);
			size++;
		}
	}

	@Override
	public E dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			size--;
			return list.remove(0);
		}
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size) {
			throw new IllegalArgumentException("Invalid LinkedQueue Capacity");
		}
		this.capacity = capacity;
	}

	/**
	 * Checks if the element is already there.
	 * 
	 * @param element the element to be checked.
	 * @return returns true if the element already exists.
	 */
	public boolean contains(E element) {
		return list.contains(element);
	}
}
