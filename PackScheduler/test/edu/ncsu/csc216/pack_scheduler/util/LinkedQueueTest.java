package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class LinkedQueueTest<E> {

	@Test
	void testEnqueue() {

		LinkedQueue<String> list = new LinkedQueue<String>(10);

		assertTrue(list.isEmpty());

		String e = "1";
		String e1 = "2";
		String e2 = "3";
		String e3 = "4";
		String e4 = "5";

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
	void testDequeue() {

		LinkedQueue<String> list = new LinkedQueue<String>(5);
		String e = "1";
		String e1 = "2";
		String e2 = "3";
		String e3 = "4";
		String e4 = "5";

		list.enqueue(e);
		assertEquals(1, list.size());

		list.enqueue(e1);
		assertEquals(2, list.size());

		list.enqueue(e2);
		assertEquals(3, list.size());

		list.enqueue(e3);
		assertEquals(4, list.size());

		list.enqueue(e4);

		assertEquals(e, list.dequeue());
		assertEquals(e1, list.dequeue());
		assertEquals(e2, list.dequeue());
		assertEquals(e3, list.dequeue());
		assertEquals(e4, list.dequeue());

		assertThrows(NoSuchElementException.class, () -> list.dequeue());
	}

}
