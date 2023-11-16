package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * Class for enrollment in a course Checks to see if a student can enroll in a
 * class or if it is filled
 * 
 * @author Rohan Agnihotri
 */
public class CourseRoll {
	/** cap for number of enrollment */
	private int enrollmentCap;
	/** minimum amount of enrollment */
	private static final int MIN_ENROLLMENT = 10;
	/** max amount of enrollment */
	private static final int MAX_ENROLLMENT = 250;
	/** roll of students for a course */
	private LinkedAbstractList<Student> roll;
	/** course for waitlist */
	private Course wl;
	/** linked queue to store the waitlisted students */
	private LinkedQueue<Student> waitlist;
	/** default size for waitlist */
	private static final int WAITLIST_SIZE = 10;

	/**
	 * constructor for the CourseRoll class sets the enrollment cap and creates the
	 * roll with the minimum number of enrollment
	 * 
	 * @param enrollmentCap cap for enrolling
	 * @param c             the course
	 * @throws IllegalArgumentException if the course is null.
	 */
	public CourseRoll(Course c, int enrollmentCap) {
		if (c != null) {
			roll = new LinkedAbstractList<Student>(enrollmentCap);
			setEnrollmentCap(enrollmentCap);
			waitlist = new LinkedQueue<Student>(WAITLIST_SIZE);
			wl = c;
		} else {
			throw new IllegalArgumentException();
		}

	}

	/**
	 * returns the enrollment cap
	 * 
	 * @return enrollmentCap
	 */
	public int getEnrollmentCap() {

		return enrollmentCap;
	}

	/**
	 * sets the enrollment cap if the enrollment cap is lower than the current size
	 * of roll then it will not be set
	 * 
	 * @param enrollmentCap cap for enrolling
	 * @throws IllegalArgumentException if the enrollmentCap is invalid
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap > MAX_ENROLLMENT || enrollmentCap < MIN_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		if (roll == null) {
			this.enrollmentCap = enrollmentCap;
		} else if (enrollmentCap < roll.size()) {
			throw new IllegalArgumentException();
		} else {
			roll.setCapacity(enrollmentCap);
			this.enrollmentCap = enrollmentCap;
		}

	}

	/**
	 * enrolls student in course if the student is invalid then an exception is
	 * thrown
	 * 
	 * @param s Student to be enrolled into the given course
	 * @throws IllegalArgumentException if the student is invalid
	 */
	public void enroll(Student s) {

		if (s == null || !canEnroll(s)) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < roll.size(); i++) {
			Student holder = roll.get(i);
			if (s.equals(holder)) {
				throw new IllegalArgumentException();
			}
		}
		if (getOpenSeats() == 0) {
			if (WAITLIST_SIZE == waitlist.size()) {
				throw new IllegalArgumentException("Full capacity.");
			} else {
				waitlist.enqueue(s);
			}
		} else {
			try {
				roll.add(s);
			} catch (Exception e) {
				throw new IllegalArgumentException();
			}
		}
	}

	/**
	 * removes student from roll if student is invalid then error is thrown
	 * 
	 * @param s student being removed
	 * @throws IllegalArgumentException if the student is invalid
	 */
	public void drop(Student s) {

		if (s == null) {
			throw new IllegalArgumentException();
		}
		if (roll.contains(s)) {
			roll.remove(s);
			Student w;
			if (waitlist.size() != 0) {
				w = waitlist.dequeue();
				roll.add(w);
				w.getSchedule().addCourseToSchedule(wl);
			}
		}
		if (waitlist.contains(s)) {
			LinkedQueue<Student> q = new LinkedQueue<Student>(10);
			while (!waitlist.isEmpty()) {
				Student z = waitlist.dequeue();
				if (!s.equals(z))
					q.enqueue(z);
			}
			waitlist = q;
		}
	}

	/**
	 * returns the number of open seats
	 * 
	 * @return the difference between enrollmentCap and the size of the roll
	 */
	public int getOpenSeats() {
		int size = roll.size();
		int seats = enrollmentCap - size;
		return seats;
	}

	/**
	 * returns true or false if a student can enroll or not
	 * 
	 * @param s student trying to enroll
	 * @return true if student can enroll, and false if student is already enrolled
	 *         or no more room
	 * 
	 * @throws IllegalArgumentException if the student is null.
	 */
	public boolean canEnroll(Student s) {
		if (s != null) {

			for (int i = 0; i < roll.size(); i++) {
				Student holder = roll.get(i);
				if (holder.equals(s)) {
					return false;
				}
			}
			return true;
		} else {
			throw new IllegalArgumentException();
		}

	}

	/**
	 * Getter method to get the number of students waitlisted
	 * 
	 * @return the number of students waitlisted
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}

}
