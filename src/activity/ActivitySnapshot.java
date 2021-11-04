package activity;

import date.Date;
import time.Time;

public class ActivitySnapshot {
	
	private Date date;
	private int activityId;
	private Time time;
	private int elapsedTime;
	
	private double longitude;
	private double latitude;
	private double altitude;
	
	private double distance;
	private double heartRate;
	private double speed;
	private double cadence;
	
	public ActivitySnapshot(int activityId, Date date, Time time, int elapsedTime, double longitude, double latitude, double altitude, double distance, double heartRate, double speed, double cadence) {
		
		this.activityId = activityId;
		this.date = date;
		this.time = time;
		this.elapsedTime = elapsedTime;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.distance = distance;
		this.heartRate = heartRate;
		this.speed = speed;
		this.cadence = cadence;
		
	}
	public ActivitySnapshot(Time time, Date date, int elapsedTime, double longitude, double latitude, double altitude, double distance, double heartRate, double speed, double cadence) {
		
		this.time = time;
		this.date = date;
		this.elapsedTime = elapsedTime;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.distance = distance;
		this.heartRate = heartRate;
		this.speed = speed;
		this.cadence = cadence;
		
	}
	public ActivitySnapshot(Time time, int elapsedTime, double longitude, double latitude, double altitude, double distance, double heartRate, double speed, double cadence) {
		this.time = time;
		this.elapsedTime = elapsedTime;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.distance = distance;
		this.heartRate = heartRate;
		this.speed = speed;
		this.cadence = cadence;
	}
	
	public int getActivityId() {
		return this.activityId;
	}
	public Date getDate() {
		return this.date;
	}
	public Time getTime() {
		return this.time;
	}
	public int getElapsedTime() {
		return this.elapsedTime;
	}
	public double getLongitude() {
		return this.longitude;
	}
	public double getLatitude() {
		return this.latitude;
	}
	public double getAltitude() {
		return this.altitude;
	}
	public double getDistance() {
		return this.distance;
	}
	public double getHeartRate() {
		return this.heartRate;
	}
	public double getSpeed() {
		return this.speed;
	}
	public double getCadence() {
		return this.cadence;
	}
	
	public String toString() {
		return getTime() + ", " + getElapsedTime() + ", " + getLongitude() + ", " + getLatitude() + ", " + getAltitude() + ", " + getDistance() + ", " + getHeartRate() + ", " + getSpeed() + ", " + getCadence();
	}
	public String[] toArray() {
		return new String[] {getTime().toString(), String.valueOf(getElapsedTime()), String.valueOf(getLongitude()), String.valueOf(getLatitude()), String.valueOf(getAltitude()), String.valueOf(getDistance()), String.valueOf(getHeartRate()), String.valueOf(getSpeed()), String.valueOf(getCadence())};
	}
}
