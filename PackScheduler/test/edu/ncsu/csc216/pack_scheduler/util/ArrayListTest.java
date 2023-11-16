package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArrayListTest<E> {

	@Test
	void testConstructor() {
		ArrayList<E> a  = new ArrayList<E>();
		assertEquals(0, a.size());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	void testAdd() {
		ArrayList<E> a  = new ArrayList<E>();
		E e = (E) new Object();
		E e1 = (E) new Object();
		E e2 = (E) new Object();
		E e3 = (E) new Object();
		int index = 0;
		
		a.add(index, e);
		assertEquals(1, a.size());
		
		index++;
		a.add(index, e1);
		assertEquals(2, a.size());
//		assertEquals(e, a.get(0));
		
		a.add(index, e2);
		assertEquals(3, a.size());
		
		assertThrows(NullPointerException.class,
				() -> a.add(4, null));
		
		assertThrows(IllegalArgumentException.class,
				() -> a.add(4, e1));
		
		assertThrows(IndexOutOfBoundsException.class,
				() -> a.add(50, e3));
		
	}
	
	@Test
	@SuppressWarnings("unchecked")
	void testRemove() {
		ArrayList<E> a  = new ArrayList<E>();
		E e = (E) new Object();
		E e1 = (E) new Object();
		E e2 = (E) new Object();
		int index = 0;
		
		a.add(index, e);
		assertEquals(1, a.size());
		
		index++;
		a.add(index, e1);
		assertEquals(2, a.size());
//		assertEquals(e, a.get(0));
		
		a.add(index, e2);
		assertEquals(3, a.size());
		
		a.remove(2);
		assertEquals(2, a.size());
		
		a.remove(0);
		assertEquals(1, a.size());
		
		assertThrows(IndexOutOfBoundsException.class,
				() -> a.remove(90));
	}
	@Test
	@SuppressWarnings("unchecked")
	void testSet() {
		ArrayList<E> a  = new ArrayList<E>();
		E e = (E) new Object();
		E e1 = (E) new Object();
		E e2 = (E) new Object();
		E e3 = (E) new Object();
		E e4 = (E) new Object();
		int index = 0;
		
		a.add(index, e);
		assertEquals(1, a.size());
		
		index++;
		a.add(index, e1);
		assertEquals(2, a.size());
//		assertEquals(e, a.get(0));
		
		a.add(index, e2);
		assertEquals(3, a.size());
		
		a.set(2, e3);
		assertEquals(3, a.size());
		
		a.set(1, e4);
		assertEquals(3, a.size());
		
		assertThrows(NullPointerException.class,
				() -> a.set(4, null));
		
		assertThrows(IndexOutOfBoundsException.class,
				() -> a.set(90, e1));
		
	}
	@Test
	@SuppressWarnings("unchecked")
	void testGet() {
		ArrayList<E> a  = new ArrayList<E>();
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
		
		
		assertThrows(IndexOutOfBoundsException.class,
				() -> a.get(-1));
		

	}
	
	

}
