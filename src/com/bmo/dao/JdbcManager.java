package com.bmo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JdbcManager {
	
	private ResourceBundle properties = ResourceBundle.getBundle("com.bmo.dao.jdbc");
	
	public JdbcManager() {
		try {
			Class.forName(properties.getString("jdbc.driver"));
		} catch (Exception e) {
			System.out.println("Cannot load JDBC Driver: " + e);
		}		
	}
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					properties.getString("jdbc.url"),
					properties.getString("jdbc.username"),
					properties.getString("jdbc.password"));
		} catch (Exception e) {
			System.out.println("Cannot connect to SQLServer.");
		}	
		return connection;
	}
	
	public void closeConnection(PreparedStatement statement, ResultSet resultSet, Connection connection){
		try {
			if (statement != null) {
				statement.close();
			}

			if (resultSet != null) {
				resultSet.close();
			}

			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*for testing database connection
	public static void main(String[] args){
		JdbcManager jdbcManager = new JdbcManager();
		Connection connection = jdbcManager.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(QueryManager.getQuery("selectPasswordHash"));
			statement.setString(1, "eliseraina");
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcManager.closeConnection(statement, resultSet, connection);
		}
	}
	/**/
}
