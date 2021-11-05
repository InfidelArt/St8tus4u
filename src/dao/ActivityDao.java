package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import db.DbConnectionManager;
import time.InvalidTimeException;
import time.Time;
import activity.Activity;
import activity.ActivitySnapshot;
import db.DataEntryException;
import db.DataRetrievalException;

public class ActivityDao {
	
	DbConnectionManager dbConnectionManager;
	
	public ActivityDao() {
		dbConnectionManager = DbConnectionManager.getInstance();
	}
	
	public ArrayList<ActivitySnapshot> get(int id) throws DataRetrievalException {
		
		ArrayList<ActivitySnapshot> list = new ArrayList<>();
		
		try {
			ResultSet resultSet = dbConnectionManager.excecuteQuery(
					"SELECT time, elapsed_time, longitude, latitude, altitude, distance, heart_rate, speed, cadence "
					+ "FROM activity_log "
					+ "WHERE activity_id=" + id + ";"
					);
			while (resultSet.next()) {
				ActivitySnapshot row = new ActivitySnapshot(new Time(resultSet.getString(1)), resultSet.getInt(2), resultSet.getDouble(3), resultSet.getDouble(4), resultSet.getDouble(5), resultSet.getDouble(6), resultSet.getDouble(7), resultSet.getDouble(8), resultSet.getDouble(9));
				list.add(row);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataRetrievalException("Could not retrieve activity log from database.");
		} catch (InvalidTimeException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public ArrayList<Activity> getAll(int userId) throws DataRetrievalException {
		ArrayList<Activity> list = new ArrayList<>();
		
		try {
			ResultSet resultSet = dbConnectionManager.excecuteQuery(
					"SELECT activity_id, activity_name, start_date, start_location, avarege_speed, avarege_heart_rate, total_time, start_time "
					+ "FROM activity_list "
					+ "WHERE user_id=" + userId +";"
					);
			while (resultSet.next()) {
				list.add(new Activity(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getDouble(6), resultSet.getString(7), resultSet.getString(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataRetrievalException("Could not get activities from database.");
		}
		
		
		return list;
	}
	
	public Activity save(int userId, Activity activity) throws DataEntryException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = dbConnectionManager.prepareStatement(
					"INSERT INTO activity_list (user_id, activity_name, start_date, start_location, avarege_speed, avarege_heart_rate, total_time, start_time) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING activity_id"
					);
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, activity.getActivityName());
			preparedStatement.setString(3, activity.getStartDate().toString());
			preparedStatement.setString(4, activity.getStartLocation());
			preparedStatement.setDouble(5, activity.getAvaregeSpeed());
			preparedStatement.setDouble(6, activity.getAvaregeHeartRate());
			preparedStatement.setString(7, activity.getTotalTime().toString());
			preparedStatement.setString(8, activity.getStartTime().toString());
			
			preparedStatement.execute();
			resultSet = preparedStatement.getResultSet();
			resultSet.next();
			int generatedId = resultSet.getInt(1);
			
			ArrayList<ActivitySnapshot> log = activity.getActivityLog();
			
			String valuesString = "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			for (int i = 1; i < log.size(); i++) {
				valuesString = valuesString + ", (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			} 
			valuesString = valuesString + ";";
			
			preparedStatement = dbConnectionManager.prepareStatement(
					"INSERT INTO activity_log (activity_id, time, elapsed_time, longitude, latitude, altitude, distance, heart_rate, speed, cadence) "
					+ valuesString
					);
			int row = 0;
			for (int i = 0; i < activity.getActivityLog().size() * 10; i = i + 10) {
				preparedStatement.setInt(i + 1, generatedId);
				preparedStatement.setString(i + 2, log.get(row).getTime().toString());
				preparedStatement.setInt(i + 3, log.get(row).getElapsedTime());
				preparedStatement.setDouble(i + 4, log.get(row).getLongitude());
				preparedStatement.setDouble(i + 5, log.get(row).getLatitude());
				preparedStatement.setDouble(i + 6, log.get(row).getAltitude());
				preparedStatement.setDouble(i + 7, log.get(row).getDistance());
				preparedStatement.setDouble(i + 8, log.get(row).getHeartRate());
				preparedStatement.setDouble(i + 9, log.get(row).getSpeed());
				preparedStatement.setDouble(i + 10, log.get(row).getCadence());
				
				row = row + 1;
			}
			
			preparedStatement.execute();
			
			
			// TODO continue here
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataEntryException("Could not enter activity into database.");
		}
		
		return null;
	}

	public void update(int id, String newName) throws DataEntryException {  
		// TODO only updates name of an activity right now because it's all the application needs, but should be expanded in the future to update a whole activity
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = dbConnectionManager.prepareStatement(
					"UPDATE activity_list "
					+ "SET activity_name=? "
					+ "WHERE activity_id=?"
					);
			preparedStatement.setString(1, newName);
			preparedStatement.setInt(2, id);
			preparedStatement.execute();
			System.out.println("Successfully updated activity " + newName);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataEntryException("Could not update activity data: " + e.getMessage());
		}
		
	}

	public void delete(int id) throws DataEntryException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = dbConnectionManager.prepareStatement(
					"DELETE FROM activity_list "
					+ "WHERE activity_id=" + id + ";"
					);
			preparedStatement.execute();
			
			preparedStatement = dbConnectionManager.prepareStatement(
					"DELETE FROM activity_log "
					+ "WHERE activity_id=" + id + ";"
					);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataEntryException("Could not delete the activity: " + e.getMessage());
		}		
	}

}
