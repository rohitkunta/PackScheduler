package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedAbstractListTest<E> {

	/** Capacity field used to construct a LinkedAbstractList */
	private int cap = 4;
	/** Invalid capacity field parameter */
	private int invalidCap = -1;

	/**
	 * Tests creating a new LinkedAbstractList and checks an invalid parameter
	 */
	@Test
	void testConstructor() {

		LinkedAbstractList<E> a = new LinkedAbstractList<E>(cap);
		assertEquals(0, a.size());

		assertThrows(IllegalArgumentException.class, () -> new LinkedAbstractList<E>(invalidCap));
	}

	/**
	 * Tests add method to ensure proper listNode insertions Error checking as well
	 * for IllegalArgument, NullPointer, and IndexOutOfBounds exceptions
	 */
	@Test
	@SuppressWarnings("unchecked")
	void testAdd() {
		LinkedAbstractList<E> a = new LinkedAbstractList<E>(cap);
		LinkedAbstractList<E> b = new LinkedAbstractList<E>(cap);
		E e = (E) new Object();
		E e1 = (E) new Object();
		E e2 = (E) new Object();
		E e3 = (E) new Object();
		int index = 0;

		a.add(index, e);
		assertEquals(1, a.size());
		assertEquals(e, a.get(index));
		index++;

		a.add(index, e1);
		assertEquals(2, a.size());
		assertEquals(e, a.get(0));
		assertEquals(e1, a.get(1));

		a.add(index, e2);
		assertEquals(3, a.size());
		assertEquals(e, a.get(0));
		assertEquals(e2, a.get(1));
		assertEquals(e1, a.get(2));
		index++;

		a.add(index, e3);
		assertEquals(4, a.size());
		assertEquals(e, a.get(0));
		assertEquals(e2, a.get(1));
		assertEquals(e3, a.get(2));
		assertEquals(e1, a.get(3));

		// assertThrows(IllegalArgumentException.class,
		// () -> a.add(1, e4)); // Exceeds capacity

		assertThrows(NullPointerException.class, () -> b.add(4, null)); // Null element

		b.add(0, e);
		assertThrows(IllegalArgumentException.class, () -> b.add(0, e)); // Duplicate element

		assertThrows(IndexOutOfBoundsException.class, () -> b.add(50, e3)); // Out of bounds index

	}

	@Test
	@SuppressWarnings("unchecked")
	void testRemove() {
		LinkedAbstractList<E> a = new LinkedAbstractList<E>(cap);
		E e = (E) new Object();
		E e1 = (E) new Object();
		E e2 = (E) new Object();
		int index = 0;

		a.add(index, e);
		assertEquals(1, a.size());

		index++;
		a.add(index, e1);
		assertEquals(2, a.size());
		assertEquals(e, a.get(0));

		a.add(index, e2);
		assertEquals(3, a.size());

		a.remove(2);
		assertEquals(2, a.size());

		a.remove(0);
		assertEquals(1, a.size());

		assertThrows(IndexOutOfBoundsException.class, () -> a.remove(90));
	}

	@Test
	void testSet() {
		LinkedAbstractList<Integer> a = new LinkedAbstractList<Integer>(cap);
		int index = 0;

		a.add(index, 1);
		assertEquals(1, a.size());

		index++;
		a.add(index, 2);
		assertEquals(2, a.size());
		assertEquals(1, a.get(0));

		a.add(index, 3);
		assertEquals(3, a.size());

		a.set(2, 4);
		assertEquals(3, a.size());

		a.set(1, 5);
		assertEquals(3, a.size());

		assertThrows(NullPointerException.class, () -> a.set(8, null));

		assertThrows(IndexOutOfBoundsException.class, () -> a.set(90, 1));

	}

	@SuppressWarnings("unchecked")
	@Test
	void testGet() {
		LinkedAbstractList<E> a = new LinkedAbstractList<E>(cap);
		E e = (E) new Object();
		E e1 = (E) new Object();
		E e2 = (E) new Object();
		E e3 = e;
		E e4 = e1;
		int index = 0;

		a.add(index, e);
		assertEquals(1, a.size());
		assertEquals(e3, a.get(0));
		index++;

		a.add(index, e1);
		assertEquals(2, a.size());
		assertEquals(e4, a.get(1));

		a.add(index, e2);
		assertEquals(3, a.size());

		assertThrows(IndexOutOfBoundsException.class, () -> a.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> a.get(3));

	}

}
