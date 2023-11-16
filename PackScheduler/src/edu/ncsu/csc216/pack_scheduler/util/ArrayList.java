package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * ArrayList Class
 * contains custom arraylist methods that other classes use
 * has add, set, remove, and get methods
 * @param <E> type of the array list
 */
public class ArrayList<E> extends AbstractList<E> {

	/** max list of the size */
	private static final int INIT_SIZE = 10;
	/** an array of type E */
	private E[] list;
	/** size of the list */
	private int size;

	/**
	 * constructor for the arraylist class
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE];
		size = 0;
	}
	/**
	 * adds element e to list at index idx
	 * @param idx index where it is added
	 * @param e element being added
	 * @throws IllegalArgumentException if element already exists
	 * @throws NullPointerException if the element is null
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public void add(int idx, E e) {
		if(e == null) {
			throw new NullPointerException();
		}
		for(int i = 0; i < size; i++) {
			if(list[i].equals(e)) {
				throw new IllegalArgumentException();
			}
		}
		if(idx >= 0 && idx <= size()) {
			if(size == INIT_SIZE) {
				growArray();
			}
			for(int i = size - 1; i > idx - 1; i--) {
				list[i + 1] = list[i];
			}
			list[idx] = e;
			size++;
			
		}
		else {
			throw new IndexOutOfBoundsException();
		}
//		if(idx == size) {
//			growArray();
//		}
//		for(int i = size - 1; i > idx - 1; i--) {
//			list[i + 1] = list[i];
//		}
//		list[idx] = e;
	}
	/**
	 * grows the array
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		
		E[] tempList = (E[]) new Object[size * 2];
		for(int i = 0; i < size; i++) {
			tempList[i] = list[i];
		}
		list = tempList;
	}
	/**
	 * removes element at idx 
	 * @param idx index of element
	 * @return e element being removed
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public E remove(int idx) {
	
		if(idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		else {
			E e = list[idx];
			list[idx] = null;
			for(int i = idx + 1; i < size; i++) {
				list[i - 1] = list[i];
			}
			list[size - 1] = null;
			size--;
			return e;
		}
		
		
	}
	/**
	 * set the element of a specific idx
	 * @param idx index where it is being added
	 * @param e element being added
	 * @return e1 return the element at the index
	 * @throws IllegalArgumentException if element already exists
	 * @throws NullPointerException if the element is null
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 * 
	 */
	public E set(int idx, E e) {
		
		if(size == 0) {
			throw new IndexOutOfBoundsException();
		}
		if(e == null) {
			throw new NullPointerException();
		}
		if(idx > size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		for(int i = 0; i < size(); i++) {
			if(list[i].equals(e)) {
				throw new IllegalArgumentException();
			}
		}
	
			E e1 = list[idx];
			list[idx] = e;
			return e1;
		
		
	}
	/**
	 * returns element at index
	 * @param idx index of element
	 * @return the element at the index
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	public E get(int idx) {
		if(idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		else {
			return list[idx];
		}
		
	}
	/**
	 * returns the size of the element
	 * @return size
	 */
	public int size() {
		return this.size;
	}



}
