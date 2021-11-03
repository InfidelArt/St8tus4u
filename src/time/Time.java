package time;

public class Time {
	
	private int hour;
	private int minute;
	private int second;
	
	public Time(int hour, int minute, int second) throws InvalidTimeException {
		setHour(hour);
		setMinute(minute);
		setSecond(second);
	}
	public Time(String time) throws InvalidTimeException {
		String[] timeValues = time.split(":");
		System.out.println(timeValues[0]);
		setHour(Integer.parseInt(timeValues[0]));
		setMinute(Integer.parseInt(timeValues[1]));
		setSecond(Integer.parseInt(timeValues[2]));
	}
	
	public int getHour() {
		return this.hour;
	}
	public int getMinute() {
		return this.minute;
	}
	public int getSecond() {
		return this.second;
	}
	public String getHourString() {
		return toValidTimeString(this.hour);
	}
	public String getMinuteString() {
		return toValidTimeString(this.minute);
	}
	public String getSecondString() {
		return toValidTimeString(this.second);
	}
	
	
	public void setHour(int hour) throws InvalidTimeException {
		if (hour < 0 || hour > 23) {
			throw new InvalidTimeException("Invalid hour: " + hour);
		}
		else {
			this.hour = hour;
		}
	}
	public void setMinute(int minute) throws InvalidTimeException {
		if (minute < 0 || minute > 59) {
			throw new InvalidTimeException("Invalid minute: " + minute);
		}
		else {
			this.minute = minute;
		}
	}
	public void setSecond(int second) throws InvalidTimeException {
		if (second < 0 || second > 59) {
			throw new InvalidTimeException("Invalid second: " + second);
		}
		else {
			this.second = second;
		}
	}
	private String toValidTimeString(int timeInt) {
		if (timeInt < 10) {
			return "0" + timeInt;
		}
		else {
			return Integer.toString(timeInt);
		}
	}
	public String toString() {
		return getHourString() + ":" + getMinuteString() + ":" + getSecondString();
	}
	
}
