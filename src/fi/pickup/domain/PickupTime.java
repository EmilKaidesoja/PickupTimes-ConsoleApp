package fi.pickup.domain;

import java.text.ParseException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;


public class PickupTime {
	private String Id;
	private DateTime timestamp;
	private int pickupTime;
	
	public PickupTime(String id, String timestamp, String pickupTime) throws ParseException {
		this.Id = id;
		this.timestamp = parseDate(timestamp);
		this.pickupTime = parseInt(pickupTime);
	}

	@Override
	public String toString() {
		return "PickupTime [Id=" + Id + ", timestamp=" + timestamp + ", pickupTime=" + pickupTime + "]";
	}

	private int parseInt(String pickupTime) {
		return Integer.parseInt(pickupTime);
	}

	private DateTime parseDate(String timestamp) throws ParseException {
		DateTimeFormatter fmt = ISODateTimeFormat.dateTimeNoMillis();
		return fmt.parseDateTime(timestamp);
	}

	public DateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public int getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(int pickupTime) {
		this.pickupTime = pickupTime;
	}
}
