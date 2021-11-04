package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.DbConnectionManager;

import activity.Activity;
import activity.ActivitySnapshot;
import db.DataEntryException;

public class ActivityDao implements DaoInterface {
	
	DbConnectionManager dbConnectionManager;
	
	public ActivityDao() {
		dbConnectionManager = DbConnectionManager.getInstance();
	}
	
	@Override
	public Activity get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
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
			}
			
			preparedStatement.execute();
			
			
			// TODO continue here
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataEntryException("Could not enter activity into database.");
		}
		
		return null;
	}

	@Override
	public void update(Object t) throws DataEntryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Object t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object save(Object t) throws DataEntryException {
		// TODO Auto-generated method stub
		return null;
	}

}
