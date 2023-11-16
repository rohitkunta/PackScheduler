
package edu.ncsu.csc216.pack_scheduler.course;

import java.util.Arrays;

/**
 * Abstract class to be inherited by any object that could be considered an
 * activity in the WolfScheduler project.
 * contains the common methods that the sub classes share 
 * @author Rohan Agnihotri
 */
public abstract class Activity implements Conflict {

	/** Largest hour in military time. */
	private static final int UPPER_HOUR = 23;
	/** Largest minute in military and standard time */
	private static final int UPPER_MINUTE = 59;
	/** Largest hour in standard time. */
	private static final int STANDARD_UPPER_HOUR = 12;
	/** Noon in standard time */
	private static final int STANDARD_NOON = 1200;

	/** Title of Course. */
	private String title;
	/** Meeting days for Course as a series of chars. */
	private String meetingDays;
	/** Starting time of Course */
	private int startTime;
	/** Ending time of Course */
	private int endTime;

	/**
	 * Constructor assigns attributes to all objects that are considered an
	 * activity.
	 * 
	 * @param title       : title of activity
	 * @param meetingDays : days that the activity meets
	 * @param startTime   : start time of activity
	 * @param endTime     : end time of activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Gets the course's title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the course's title. If the title is null or empty, an
	 * IllegalArgumentException is thrown.
	 * 
	 * @param title : The title to set
	 * @throws IllegalArgumentException if the title parameter is invalid
	 */
	public void setTitle(String title) {
		// Throw exception if the name is null or empty
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}

		this.title = title;
	}

	/**
	 * Gets the course's meeting days.
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return this.meetingDays;
	}

	/**
	 * Gets the course's start time.
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return this.startTime;
	}

	/**
	 * Gets the courses end time.
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return this.endTime;
	}

	/**
	 * Returns a string that tells the meeting day and time.
	 * 
	 * @return meeting day and time string
	 */
	public String getMeetingString() {
		// If course is arranged, there is no start and end time
		if ("A".equals(meetingDays)) {
			return "Arranged";
		}

		String startMeridian;
		String endMeridian;

		if (this.startTime < STANDARD_NOON) {
			startMeridian = "AM";
		} else {
			startMeridian = "PM";
		}

		if (this.endTime < STANDARD_NOON) {
			endMeridian = "AM";
		} else {
			endMeridian = "PM";
		}

		return this.meetingDays + " " + (militaryToStandard(this.startTime) / 100) + ":"
				+ String.format("%02d", militaryToStandard(this.startTime) % 100) + startMeridian + "-"
				+ (militaryToStandard(this.endTime) / 100) + ":"
				+ String.format("%02d", militaryToStandard(this.endTime) % 100) + endMeridian;
	}

	/**
	 * Converts from military time to standard time.
	 * 
	 * @param time : The time to be converted
	 * 
	 * @return time in military time to standard time
	 */
	private int militaryToStandard(int time) {
		int hour = time / 100;

		if (hour > STANDARD_UPPER_HOUR) {
			hour -= STANDARD_UPPER_HOUR;
		}

		if (hour == 0) {
			hour += STANDARD_UPPER_HOUR;
		}

		return hour * 100 + time % 100;
	}

	/**
	 * Sets the activity's meeting days and time.
	 * 
	 * @param meetingDays : The meeting days to be set
	 * @param startTime   : The start time to be set
	 * @param endTime     : The end time to be set
	 * @throws IllegalArgumentException if they meeting day or tiem is invalid
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		char[] meetingDaysArray = meetingDays.toCharArray();
		Arrays.sort(meetingDaysArray);

		for (int i = 0; i < meetingDaysArray.length - 1; i++) {
			// Throw exception if meeting days contains any duplicates
			if (meetingDaysArray[i] == meetingDaysArray[i + 1]) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}

		// Throw exception if end time is before start time
		if (startTime > endTime) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		int startHour = startTime / 100;
		int startMin = startTime % 100;
		int endHour = endTime / 100;
		int endMin = endTime % 100;

		// Throw exception if start hour isn't between 0 and 23
		if (startHour < 0 || startHour > UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		// Throw exception if start minute isn't between 0 and 59
		if (startMin < 0 || startMin > UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		// Throw exception if end hour isn't between 0 and 23
		if (endHour < 0 || endHour > UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		// Throw exception if end minute isn't between 0 and 59
		if (endMin < 0 || endMin > UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Helper method checks an Activity object's meetingDays field to see if two
	 * Activities meet on the same day.
	 * 
	 * @param activity : Activity object to compare meeting days with
	 * 
	 * @return true if two activities meet on the same day
	 * returns false if they arent the same days
	 */
	private boolean meetsOnSameDay(Activity activity) {
		if("A".equals(this.meetingDays) && meetingDays.equals(activity.getMeetingDays())) {
			return false;
		}
		
		char[] charMeetingDays = (this.meetingDays + activity.getMeetingDays()).toCharArray();
		Arrays.sort(charMeetingDays);

		for (int i = 0; i < charMeetingDays.length - 1; i++) {
			if (charMeetingDays[i] == charMeetingDays[i + 1]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Tests to see if an activity is a duplicate.
	 * 
	 * @param activity : Activity to be checked if it is a duplicate
	 * 
	 * @return true if activity object is a duplicate
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Constructs and returns a short display array used to populate the rows of the
	 * course catalog and student schedule.
	 * 
	 * @return short display array
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Constructs and returns a long display array used to display the final
	 * schedule.
	 * 
	 * @return long display array
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * checks to see if there is any conflict between the activities
	 * if there is throw exception
	 * @throws ConflictException if there is a conflict
	 * 
	 */
	@Override
	public void checkConflict(Activity activity) throws ConflictException {
		if (this.meetsOnSameDay(activity)) {
			if(this.startTime <= activity.getStartTime() && activity.getStartTime() <= this.endTime) {
				throw new ConflictException();
			}
			else if(this.startTime <= activity.getEndTime() && activity.getEndTime() <= this.endTime) {
				throw new ConflictException();
			}
			else if(this.startTime > activity.getStartTime() && activity.getEndTime() > this.endTime) {
				throw new ConflictException();
			}
		}
	}

	/**
	 * hashcode for the activity class
	 * @return the result of hashing
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * 
	 * equals method for the activity class
	 * @return true or false if they are equal or not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
