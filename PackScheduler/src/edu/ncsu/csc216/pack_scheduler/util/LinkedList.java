package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.ListIterator;

/**
 * A linked list used for faculty class and used for its implementation.
 * 
 * @param <E> the data type of the elements in the list.
 */
public class LinkedList<E> extends AbstractSequentialList<E> {

	/** to maintain the front of the list */
	private ListNode front;
	/** to maintain the back of the list */
	private ListNode back;
	/** maintains the size of the list */
	private int size;

	/**
	 * A public constructor for the class linked list
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void add(int idx, E e) {

	}

	@Override
	public ListIterator<E> listIterator(int index) {
		LinkedListIterator lli = new LinkedListIterator(index);
		return lli;
	}

	/**
	 * Inner class object of the individual nodes in a linkedList
	 */
	private class ListNode {
		/** instance of E */
		private E data;
		/** instance of ListNode */
		private ListNode next;
		/** previous instance of the list */
		private ListNode prev;

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
		 * @param prev The previous value.
		 * @param next Value of the next listNode to point to
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this(data);
			this.prev = prev;
			this.next = next;
		}
	}

	private class LinkedListIterator implements ListIterator<E> {

		/** the previous list node */
		private ListNode previous;
		/** the next listnode */
		private ListNode next;
		/** the previous index */
		private int previousIndex;
		/** the next index */
		private int nextIndex;
		/** the last listnode used */
		private ListNode lastRetrieved;

		/**
		 * A public constructor for initializing.
		 * 
		 * @param index the index to iterate through
		 */
		public LinkedListIterator(int index) {
			if (index < 0 || index > size) {
				throw new IndexOutOfBoundsException();
			} else {
				previous = front;
				next = front.next;
				for (int i = 0; i < index; i++) {
					previous = next;
					next = previous.next;
				}
				previousIndex = index - 1;
				nextIndex = index;
				lastRetrieved = null;
			}
		}

		@Override
		public boolean hasNext() {
			try {
				return next() != null;
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		public E next() {
			return this.next.data;
		}

		@Override
		public boolean hasPrevious() {
			try {
				return previous() != null;
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		public E previous() {
			return this.previous.data;
		}

		@Override
		public int nextIndex() {
			return this.nextIndex;
		}

		@Override
		public int previousIndex() {
			return this.previousIndex;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub

		}

		@Override
		public void set(E e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void add(E e) {
			// TODO Auto-generated method stub

		}

	}

}
