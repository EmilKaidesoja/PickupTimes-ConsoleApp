package fi.pickup.domain;

import org.joda.time.DateTime;

public class UserInput {
	private DateTime date;
	private int startTime;
	private int endTime;
	
	public UserInput(DateTime date, int startTime, int endTime) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public DateTime getDate() {
		return date;
	}
	public void setDate(DateTime date) {
		this.date = date;
	}
	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	
}
