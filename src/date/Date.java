package date;

public class Date {
	
	private int year;
	private int month;
	private int day;
	
	public Date(int year, int month, int day) throws InvalidDateException {
		setYear(year);
		setMonth(month);
		setDay(day);
	}
	public Date(String date) throws InvalidDateException {
		String[] timeValues = date.split("-");
		System.out.println(timeValues[0]);
		setYear(Integer.parseInt(timeValues[0]));
		setMonth(Integer.parseInt(timeValues[1]));
		setDay(Integer.parseInt(timeValues[2]));
	}
	
	public String getYear() {
		return toValidTimeString(this.year);
	}
	
	public String getMonth() {
		return toValidTimeString(this.month);
	}
	
	public String getDay() {
		return toValidTimeString(this.day);
	}
	
	public void setYear(int year) throws InvalidDateException {
		if (year < 0) {
			throw new InvalidDateException ("Invalid year input: " + year);
		}
		else {
			this.year = year;
		}
	}
	
	public void setMonth(int month) throws InvalidDateException {
		if (month < 1 || month > 12) {
			throw new InvalidDateException ("Invalid month input: " + month);
		}
		else {
			this.month = month;
		}
	}
	
	public void setDay(int day) throws InvalidDateException {
		if (day < 1 || day > 31) {
			throw new InvalidDateException("Invalid day input: " + day);
		}
		else {
			this.day = day;
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
		return getYear() + "-" + getMonth() + "-" + getDay();
	}
}
