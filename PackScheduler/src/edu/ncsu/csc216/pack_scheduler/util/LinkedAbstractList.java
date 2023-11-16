package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * LinkedAbstractList object, customized LinkedList from the abstractList
 * package
 * 
 * @author Noah
 * @author Rohan
 * @author Nalin
 * 
 * @param <E> Generic type parameter for the LinkedList
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	/** an instance of the inner class */
	private ListNode front;
	/** Field containing the number of elements in the list */
	private int size;
	/** Maximum amount of elements allowed in the list */
	private int capacity;

	/** back of the linked list */
	private ListNode back;

	/**
	 * Constructor method, initializes front, size, and capacity of the list
	 * 
	 * @param capacity The max amount of listNodes currently allowed in the list
	 */
	public LinkedAbstractList(int capacity) {
		size = 0;
		front = null;
		this.back = null;
		setCapacity(capacity);
	}

	/**
	 * Setter method for field capacity
	 * 
	 * @param capacity Max amount of listNodes currently allowed in the list
	 * @throws IllegalArgumentException if any of the parameters are invalid.
	 */
	public void setCapacity(int capacity) {
		if (capacity <= 0 || capacity < size()) {
			throw new IllegalArgumentException();
		} else {
			this.capacity = capacity;
		}
	}

	/**
	 * Allows data in the listNode at the given index to be edited.
	 * 
	 * @param idx Specific index of the node to be edited
	 * @param e   element to be set in the given node
	 * @return The old data at the replaced index
	 * @throws IllegalArgumentException  if any of the parameters are invalid.
	 * @throws IndexOutOfBoundsException if any of the parameters are invalid.
	 * @throws NullPointerException      if the element is null.
	 */
	public E set(int idx, E e) {
		ListNode current = front;
		if (size == capacity) {
			throw new IllegalArgumentException();
		} else if (e == null) {
			throw new NullPointerException();
		} else if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int j = 0; j < size; j++) { // Checks if the new element is a duplicate of any node in the list
			if (e == current.data) {
				throw new IllegalArgumentException();
			}
			current = current.next;
		}
		if (idx == 0) {
			E element = get(idx);
			front.data = e;
			return element;
		}
		current = front;
		E element = get(idx);
		for (int i = 0; i < idx - 1; i++) {
			current = current.next;

		}
		current.next.data = e;
		return element;
	}

	/**
	 * Overridden method that adds an element at the given index
	 * 
	 * @param idx     Index in the list where the element will be added
	 * @param element the new element that will be added to the list
	 * @throws IllegalArgumentException  if any of the parameters are invalid.
	 * @throws IndexOutOfBoundsException if any of the parameters are invalid.
	 * @throws NullPointerException      if the element is null.
	 */
	@Override
	public void add(int idx, E element) {
		if (element == null) {
			throw new NullPointerException();
		} else if (idx < 0 || idx > size()) {
			throw new IndexOutOfBoundsException();
		}

		if (contains(element)) {
			throw new IllegalArgumentException();
		}
		if (size == capacity) {
			throw new IllegalArgumentException();
		}
		
		if (idx == 0) {
			front = new ListNode(element, front);
			if (size == 0) {
				back = front;
			}
			size++;
		} else if (idx == size) {
			if (size == 0) {
				front = new ListNode(element);
				back = front;
			} else {
				back.next = new ListNode(element);
				back = back.next;
			}
			size++;
		} else {
			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			current.next = new ListNode(element, current.next);
			size++;
		}
	}

	/**
	 * Removes the listNode at the given index. Connects the listNode before and
	 * after the index together to remove.
	 * 
	 * @param idx Index of the listNode to be removed.
	 * @return The removed listNode's data
	 * @throws IndexOutOfBoundsException if any of the parameters are invalid.
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E e = get(idx);
		if (idx == 0) {
			front = front.next;
			if (size == 1) {
				back = null;
			}
		} else {
			ListNode current = front;
			for (int i = 0; i < idx - 1; i++) {
				current = current.next;
			}
			current.next = current.next.next;
			if (idx == size - 1) {
				back = current;
			}
		}
		size--;
		return e;
	}

	/**
	 * Returns the number of listNodes are in the linkedList
	 * 
	 * @return Private field size
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Getter method for a listNode's data
	 * 
	 * @param index Index of the listNode to return
	 * @return The listNode's data
	 * @throws IndexOutOfBoundsException if any of the parameters are invalid.
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;

		}
		return current.data;
	}

	/**
	 * Inner class object of the individual nodes in a linkedList
	 */
	private class ListNode {
		/** instance of E */
		private E data;
		/** instance of ListNode */
		private ListNode next;

		/**
		 * ListNode constructor with data parameters
		 * 
		 * @param data Data of the new node
		 */
		public ListNode(E data) {
			this.data = data;
		}

		/**
		 * ListNode constructor with data and next parameter
		 * 
		 * @param data Data of the new node
		 * @param next Value of the next listNode to point to
		 */
		public ListNode(E data, ListNode next) {
			this(data);
			this.next = next;
		}
	}
}
