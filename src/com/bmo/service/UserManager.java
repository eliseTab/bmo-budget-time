package com.bmo.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bmo.dao.JdbcManager;
import com.bmo.dao.QueryManager;
import com.bmo.util.PasswordUtil;


public class UserManager {
	private JdbcManager jdbcManager;
	public UserManager(JdbcManager jdbcManager){
		this.jdbcManager = jdbcManager;
	}
	public int getUserId(String username){
		Connection connection = jdbcManager.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;		
		int userId = 0;
		try {
			statement = connection.prepareStatement(QueryManager.getQuery("selectUserId"));
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				userId = resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcManager.closeConnection(statement, resultSet, connection);
		}
		return userId;
	}
	public boolean addUser(String username, String password){
		String passwordHash = "";
		try {
			passwordHash = PasswordUtil.getPasswordHash(password);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
			e1.printStackTrace();
		}
		Connection connection = jdbcManager.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;		
		boolean userAdded = false;
		try {
			statement = connection.prepareStatement(QueryManager.getQuery("insertUser"));
			statement.setString(1, username);
			statement.setString(2, passwordHash);
			statement.executeUpdate();
			userAdded = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcManager.closeConnection(statement, resultSet, connection);
		}
		return userAdded;
	}
	public boolean usernameExists(String username){
		Connection connection = jdbcManager.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean usernameExists = false;
		try {
			statement = connection.prepareStatement(QueryManager.getQuery("selectUserCount"));
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				if(resultSet.getInt(1) >= 1)
					usernameExists = true;
				else
					usernameExists = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcManager.closeConnection(statement, resultSet, connection);
		}
		return usernameExists;
	}
	public boolean matchingPasswords(String username, String password){
		Connection connection = jdbcManager.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String storedPassword = "";
		boolean match = false;
		try {
			statement = connection.prepareStatement(QueryManager.getQuery("selectPasswordHash"));
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				storedPassword = resultSet.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcManager.closeConnection(statement, resultSet, connection);
		}
		try {
			match = PasswordUtil.comparePasswords(password, storedPassword);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return match;
	}
	
	/*for testing
	public static void main(String[] args){
		JdbcManager jdbcManager = new JdbcManager();
		UserManager userManager = new UserManager(jdbcManager);
		//System.out.println("User is authorized? " + userManager.isUserAuthorized("eliseraina", "password"));
		//System.out.println("User inserted? " + userManager.addUser("elise", "elise"));
		System.out.println("Userid of elise: " + userManager.getUserId("elise"));
	}
	/**/
}
