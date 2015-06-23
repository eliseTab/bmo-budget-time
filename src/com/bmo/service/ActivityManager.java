package com.bmo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bmo.dao.JdbcManager;
import com.bmo.dao.QueryManager;

public class ActivityManager {
	private JdbcManager jdbcManager;
	public ActivityManager(JdbcManager jdbcManager){
		this.jdbcManager = jdbcManager;
	}
	public int getActivityId(String activityName){
		Connection connection = jdbcManager.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;		
		int activityId = 0;
		try {
			statement = connection.prepareStatement(QueryManager.getQuery("selectActivityId"));
			statement.setString(1, activityName);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				activityId = resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcManager.closeConnection(statement, resultSet, connection);
		}
		return activityId;
	}
}
