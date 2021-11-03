package activity;

import java.util.ArrayList;

import date.Date;
import time.Time;

public class Activity {
	
	private int id;
	private String activityName;
	private Date startDate;
	private String startLocation;
	private double avaregeSpeed;
	private double avaregeHeartRate;
	private Time totalTime;
	private Time startTime;
	
	private ArrayList<ActivitySnapshot> activityLog;
	
	public Activity (int id, String activityName, ArrayList<ActivitySnapshot> activityLog) {
		this.id = id;
		this.activityName = activityName;
		this.activityLog = activityLog;
		
		this.avaregeSpeed = calculateAvaregeSpeed();
		this.avaregeHeartRate = calculateAvaregeHeartRate();
		
		this.startDate = createStartDate();
		
		this.totalTime = createTotalTime();
		this.startTime = createStartTime();
		
		// TODO calculate avareges from activityLog
	}
	
	public Activity (int id, String activityName, String startLocation, ArrayList<ActivitySnapshot> activityLog) {
		this(id, activityName, activityLog);
		this.startLocation = startLocation;
	}
	
	public ArrayList<ActivitySnapshot> getActivityLog() {
		return this.activityLog;
	}
	public int getId() {
		return this.id;
	}
	public Date getStartDate() {
		return this.startDate;
	}
	public String getStartLocation() {
		return this.startLocation;
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
	
	private double calculateAvaregeSpeed() {
		double total = 0;
		for (ActivitySnapshot snapshot : activityLog) {
			total = total + snapshot.getSpeed();
		}
		double avarege = total / activityLog.size();
		
		return avarege;
	}
	private double calculateAvaregeHeartRate() {
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
	
}
