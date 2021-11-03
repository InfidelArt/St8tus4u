package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import db.DbConnectionManager;

import activity.Activity;
import db.DataEntryException;

public class ActivityDao implements DaoInterface {
	
	DbConnectionManager dbConnectionManager;
	
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
	
	public Activity save(int userId, Object t) throws DataEntryException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = dbConnectionManager.prepareStatement(
					"INSERT INTO activity_list (user_id, activity_name, start_date, start_location, avarege_speed, avarege_heart_rate, total_time, start_time) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
					);
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
