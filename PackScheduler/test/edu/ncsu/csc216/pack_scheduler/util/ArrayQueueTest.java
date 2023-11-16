package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class ArrayQueueTest<E> {

	@Test
	@SuppressWarnings("unchecked")
	void testEnqueue() {

		ArrayQueue<E> list = new ArrayQueue<E>(5);
		E e = (E) new Object();
		E e1 = (E) new Object();
		E e2 = (E) new Object();
		E e3 = (E) new Object();
		E e4 = (E) new Object();

		list.enqueue(e);
		assertEquals(1, list.size());

		list.enqueue(e1);
		assertEquals(2, list.size());

		list.enqueue(e2);
		assertEquals(3, list.size());

		list.enqueue(e3);
		assertEquals(4, list.size());

		list.enqueue(e4);

		assertThrows(IllegalArgumentException.class, () -> list.enqueue(e));
	}

	@Test
	@SuppressWarnings("unchecked")
	void testDequeue() {

		ArrayQueue<E> list = new ArrayQueue<E>(5);
		E e = (E) new Object();
		E e1 = (E) new Object();
		E e2 = (E) new Object();
		E e3 = (E) new Object();
		E e4 = (E) new Object();

		list.enqueue(e);
		assertEquals(1, list.size());

		list.enqueue(e1);
		assertEquals(2, list.size());

		list.enqueue(e2);
		assertEquals(3, list.size());

		list.enqueue(e3);
		assertEquals(4, list.size());

		list.enqueue(e4);
		assertEquals(5, list.size());

		assertEquals(e, list.dequeue());
		assertEquals(e1, list.dequeue());
		assertEquals(e2, list.dequeue());
		assertEquals(e3, list.dequeue());
		assertEquals(e4, list.dequeue());

		assertThrows(NoSuchElementException.class, () -> list.dequeue());
	}

}
