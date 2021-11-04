package activity;

import java.util.ArrayList;

import date.Date;
import date.InvalidDateException;
import time.InvalidTimeException;
import time.Time;

public class Activity {
	
	private int id;
	private String activityName;
	private Date startDate;
	private Object startLocation;
	private double avaregeSpeed;
	private double avaregeHeartRate;
	private Time totalTime;
	private Time startTime;
	
	private ArrayList<ActivitySnapshot> activityLog;
	
	public Activity (String activityName, ArrayList<ActivitySnapshot> activityLog) {
		this.activityName = activityName;
		this.activityLog = activityLog;
		
		this.avaregeSpeed = calculateAvaregeSpeed();
		this.avaregeHeartRate = calculateAvaregeHeartRate();
		
		this.startDate = createStartDate();
		
		this.totalTime = createTotalTime();
		this.startTime = createStartTime();
	}
	
	public Activity (int id, String activityName, ArrayList<ActivitySnapshot> activityLog) {
		this(activityName, activityLog);
		this.id = id;
		
		
		// TODO calculate avareges from activityLog
	}
	
	public Activity (int id, String activityName, String startLocation, ArrayList<ActivitySnapshot> activityLog) {
		this(id, activityName, activityLog);
		this.startLocation = startLocation;
	}
	public Activity (String activityName, String startLocation, ArrayList<ActivitySnapshot> activityLog) {
		this(activityName, activityLog);
		this.startLocation = startLocation;
	}
	
	public Activity(int id, String activity_name, String start_date, Object startLocation, double avarege_speed, double avarege_heart_rate, String total_time, String start_time) {
		this.id = id;
		if (activity_name != null) {
			this.setStartLocation((String) activityName);
		}
		setActivityName(activity_name);
		this.avaregeSpeed = avarege_speed;
		this.avaregeHeartRate = avarege_heart_rate;
		try {
			this.startDate = new Date(start_date);
			this.startTime = new Time(start_time);
			this.totalTime = new Time(total_time);
		} catch (InvalidTimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setStartLocation(String location) {
		this.startLocation = location;		
	}

	public ArrayList<ActivitySnapshot> getActivityLog() {
		return this.activityLog;
	}
	public String getActivityName() {
		return this.activityName;
	}
	public void setActivityName(String name) {
		this.activityName = name;
	}
	public int getId() {
		return this.id;
	}
	public Date getStartDate() {
		return this.startDate;
	}
	public String getStartLocation() {
		return (String) this.startLocation;
	}
	public double getAvaregeSpeed() {
		return this.avaregeSpeed;
	}
	public double getAvaregeHeartRate() {
		return this.avaregeHeartRate;
	}
	public Time getTotalTime() {
		return this.totalTime;
	}
	public Time getStartTime() {
		return this.startTime;
	}
	
	private double calculateAvaregeSpeed() { // TODO round down to 2 decimals
		double total = 0;
		for (ActivitySnapshot snapshot : activityLog) {
			total = total + snapshot.getSpeed();
		}
		double avarege = total / activityLog.size();
		
		return avarege;
	}
	private double calculateAvaregeHeartRate() { // TODO round to 2 decimals
		double total = 0;
		for (ActivitySnapshot snapshot : activityLog) {
			total = total + snapshot.getHeartRate();
		}
		double avarege = total / activityLog.size();
		
		return avarege;
	}
	private Time createStartTime() {
		return activityLog.get(0).getTime();
	}
	private Time createTotalTime() {
		return activityLog.get(activityLog.size()-1).getTime();
	}
	private Date createStartDate() {
		return activityLog.get(0).getDate();
	}
	public String[] toStringArray() {	
		return new String[] {Integer.toString(id), getActivityName(), getStartDate().toString(), getStartLocation(), getAvaregeSpeed()+"", getAvaregeHeartRate()+"", getTotalTime().toString(), getStartTime().toString()};
		
	}
}
