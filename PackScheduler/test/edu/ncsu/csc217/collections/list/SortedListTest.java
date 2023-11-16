
package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests all methods from the SortedList library.
 * 
 * @author Grant Goldsmith
 * @author Greydon Sarvis
 * @author Noah Clouser
 */
public class SortedListTest {

	/**
	 * Tests the SortedList constructor.
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));

		// Test that the list grows by adding at least 11 elements
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		list.add("10"); 
		list.add("11");
		// Remember the list's initial capacity is 10
		assertEquals(11, list.size());

	}

	/**
	 * tests the add method for multiple locations and invalid inputs
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();

		// Test adding to the front, middle and back of the list
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));

		list.add("apple");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));

		list.add("apricot");
		assertEquals(3, list.size());
		assertEquals("apricot", list.get(1));
		assertEquals("banana", list.get(2));

		list.add("strawberry");
		assertEquals(4, list.size());
		assertEquals("strawberry", list.get(3));

		// Test adding a null element
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}

		// Test adding a duplicate element
		try {
			list.add("banana");
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());

		}
	}

	/**
	 * tests the get() method with invalid inputs
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();

		// Test getting an element from an empty list
		try {
			list.get(0);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}

		// Add some elements to the list
		list.add("apple");

		// Test getting an element at an index < 0
		try {
			list.get(-1);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}

		// Test getting an element at size
		try {
			list.get(list.size());
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}
	}

	/**
	 * Tests the remove() method for multiple locations and invalid inputs
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();

		// Test removing from an empty list
		try {
			list.remove(0);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}

		// Add some elements to the list - at least 4
		list.add("apple");
		list.add("apricot");
		list.add("banana");
		list.add("strawberry");

		// Test removing an element at an index < 0
		try {
			list.remove(-5);
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}

		// Test removing an element at size
		try {
			list.remove(list.size());
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}

		// Test removing a middle element
		assertEquals("banana", list.remove(2));
		assertEquals("apple", list.get(0));
		assertEquals("apricot", list.get(1));
		assertEquals("strawberry", list.get(2));

		// Test removing the last element
		assertEquals("strawberry", list.remove(2));
		assertEquals("apple", list.get(0));
		assertEquals("apricot", list.get(1));
		// Test removing the first element
		assertEquals("apple", list.remove(0));
		assertEquals("apricot", list.get(0));
		// Test removing the last element
		assertEquals("apricot", list.remove(0));
		assertEquals(0, list.size());
	}

	/**
	 * Tests the indexOf() method for multiple locations and invalid inputs
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();

		// Test indexOf on an empty list
		assertEquals(-1, list.indexOf("apple"));

		// Add some elements
		list.add("apple");
		list.add("oats");
		list.add("bread");

		// Test various calls to indexOf for elements in the list
		// and not in the list
		assertEquals(0, list.indexOf("apple"));
		assertEquals(1, list.indexOf("bread"));
		assertEquals(-1, list.indexOf("steak"));

		// Test checking the index of null
		try {
			list.indexOf(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(3, list.size());
		}
	}

	/**
	 * Tests clear() method to clear a list
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		// Add some elements
		list.add("apple");
		list.add("oats");
		list.add("bread");
		list.add("steak");

		// Clear the list
		list.clear();

		// Test that the list is empty
		assertEquals(0, list.size());
	}

	/**
	 * Test isEmpty() method
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();

		// Test that the list starts empty
		assertTrue(list.isEmpty());

		// Add at least one element
		list.add("apple");

		// Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * Test contains() method
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();

		// Test the empty list case
		assertFalse(list.contains("steak"));

		// Add some elements
		list.add("apple");
		list.add("apricot");

		// Test some true and false cases
		assertTrue(list.contains("apricot"));
		assertFalse(list.contains("steak"));
		assertTrue(list.contains("apple"));
		assertFalse(list.contains("bread"));
	}

	/**
	 * Tests equals() method
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// Make two lists the same and one list different
		list1.add("apple");
		list1.add("bread");
		list1.add("apricot");

		list2.add("apple");
		list2.add("bread");
		list2.add("apricot");

		list3.add("1");
		list3.add("2");
		list3.add("3");

		// Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		assertFalse(list1.equals(list3));
		assertFalse(list2.equals(list3));

	}

	/**
	 * Tests hashCode() mehtod
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// Make two lists the same and one list different
		list1.add("apple");
		list1.add("bread");
		list1.add("apricot");

		list2.add("apple");
		list2.add("bread");
		list2.add("apricot");

		list3.add("1");
		list3.add("2");
		list3.add("3");

		// Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
	}

}
