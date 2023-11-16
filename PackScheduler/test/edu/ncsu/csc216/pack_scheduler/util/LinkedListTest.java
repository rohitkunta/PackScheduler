package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedListTest {

	@Test
	void testConstructor() {
		LinkedList<String> a  = new LinkedList<String>();
		assertEquals(0, a.size());
	}
	
	@Test
	void testAdd() {
		LinkedList<String> a  = new LinkedList<String>();
		int index = 0;
		
		a.add(index, "a");
		assertEquals(1, a.size());
		
		index++;
		a.add(index, "b");
		assertEquals(2, a.size());
//		assertEquals(e, a.get(0));
		
		a.add(index, "c");
		assertEquals(3, a.size());
		
		assertThrows(NullPointerException.class,
				() -> a.add(4, null));
		
		assertThrows(IllegalArgumentException.class,
				() -> a.add(4, "b"));
		
		assertThrows(IndexOutOfBoundsException.class,
				() -> a.add(50, "d"));
		
	}
	
	@Test
	void testRemove() {
		LinkedList<String> a  = new LinkedList<String>();
		int index = 0;
		
		a.add(index, "a");
		assertEquals(1, a.size());
		
		index++;
		a.add(index, "b");
		assertEquals(2, a.size());
//		assertEquals(e, a.get(0));
		
		a.add(index, "c");
		assertEquals(3, a.size());
		
		a.remove(2);
		assertEquals(2, a.size());
		
		a.remove(0);
		assertEquals(1, a.size());
		
		assertThrows(IndexOutOfBoundsException.class,
				() -> a.remove(90));
	}
	@Test
	void testSet() {
		LinkedList<String> a  = new LinkedList<String>();
		int index = 0;
		
		a.add(index, "a");
		assertEquals(1, a.size());
		
		index++;
		a.add(index, "b");
		assertEquals(2, a.size());
//		assertEquals(e, a.get(0));
		
		a.add(index, "c");
		assertEquals(3, a.size());
		
		a.set(2, "d");
		assertEquals(3, a.size());
		
		a.set(1, "e");
		assertEquals(3, a.size());
		
		assertThrows(NullPointerException.class,
				() -> a.set(4, null));
		
		assertThrows(IndexOutOfBoundsException.class,
				() -> a.set(90, "b"));
		
	}
	@Test
	void testGet() {
		LinkedList<String> a  = new LinkedList<String>();
		int index = 0;
		
		a.add(index, "a");
		assertEquals(1, a.size());
		assertEquals("a", a.get(0));
		
		index++;
		a.add(index, "b");
		assertEquals(2, a.size());
		assertEquals("b", a.get(1));
		
		a.add(index, "c");
		assertEquals(3, a.size());
		
		
		assertThrows(IndexOutOfBoundsException.class,
				() -> a.get(-1));
		

	}

}
