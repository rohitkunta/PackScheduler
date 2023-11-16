package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * A arraylist for queue which implements the IQueue interface.
 * 
 * @param <E> receives the data type.
 * 
 * @author Nalin
 * @author Rohit
 */
public class ArrayQueue<E> implements Queue<E> {

	/** to store the list */
	private ArrayList<E> list;

	/** stores the capacity */
	private int capacity;

	/** stores the size */
	private int size;

	/**
	 * A public constructor.
	 * 
	 * @param capacity receives the capacity to be set.
	 */
	public ArrayQueue(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity);
	}

	@Override
	public void enqueue(E element) {
		if (size == capacity || element == null || list.contains(element)) {
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
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		} else {
			this.capacity = capacity;
		}
	}
}
