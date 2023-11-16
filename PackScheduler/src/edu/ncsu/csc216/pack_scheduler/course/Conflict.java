package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Interface that handels conflicts within a schedule.
 * 
 * @author gasarvis
 */
public interface Conflict {

	/**
	 * Checks to see if there is a conflict between an incoming activity and the
	 * current schedule.
	 * 
	 * @param activity : Activity object to be tested for conflict
	 * 
	 * @throws ConflictException if conflict occurs
	 */
	void checkConflict(Activity activity) throws ConflictException;

}
