package com.bmo.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bmo.bean.Envelope;
import com.bmo.dao.JdbcManager;
import com.bmo.dao.QueryManager;

public class TransactionManager {
	private JdbcManager jdbcManager;
	public TransactionManager(JdbcManager jdbcManager){
		this.jdbcManager = jdbcManager;
	}
	public boolean addTransaction(int userId, int activityId, int envelopeId, BigDecimal amount, String note){
		Connection connection = jdbcManager.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean transactionAdded = false;
		try {
			statement = connection.prepareStatement(QueryManager.getQuery("insertTransaction"));
			statement.setInt(1, userId);
			statement.setInt(2, activityId);
			statement.setInt(3, envelopeId);
			statement.setBigDecimal(4, amount);
			statement.setString(5, note);
			statement.executeUpdate();
			transactionAdded = true;
		} catch(Exception e){
			e.printStackTrace();
			transactionAdded = false;
		} finally {
			jdbcManager.closeConnection(statement, resultSet, connection);
		}
		return transactionAdded;
	}
	/*for testing
	public static void main(String[] args){
		JdbcManager jdbcManager = new JdbcManager();
		TransactionManager tranManager = new TransactionManager(jdbcManager);
		System.out.println("added transaction? " + tranManager.addTransaction(1, 1, 1, new BigDecimal(50), "test"));
	}
	/**/
}
