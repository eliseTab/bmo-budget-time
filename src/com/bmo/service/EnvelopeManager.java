package com.bmo.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bmo.bean.Envelope;
import com.bmo.bean.Transaction;
import com.bmo.dao.JdbcManager;
import com.bmo.dao.QueryManager;

public class EnvelopeManager {
	JdbcManager jdbcManager;
	public EnvelopeManager(JdbcManager jdbcManager){
		this.jdbcManager = jdbcManager;
	}
	public int getEnvelopeId(String envelopeName){
		Connection connection = jdbcManager.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;		
		int envelopeId = 0;
		try {
			statement = connection.prepareStatement(QueryManager.getQuery("selectEnvelopeId"));
			statement.setString(1, envelopeName);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				envelopeId = resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcManager.closeConnection(statement, resultSet, connection);
		}
		return envelopeId;
	}
	public boolean envelopeExists(String envelopeName){
		Connection connection = jdbcManager.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;		
		boolean envelopeExists = false;
		try {
			statement = connection.prepareStatement(QueryManager.getQuery("selectEnvelopeCount"));
			statement.setString(1, envelopeName);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				if(resultSet.getInt(1) >= 1)
					envelopeExists = true;
				else
					envelopeExists = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcManager.closeConnection(statement, resultSet, connection);
		}
		return envelopeExists;
	}
	public boolean addEnvelope(String envelopeName){
		Connection connection = jdbcManager.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;		
		boolean envelopeAdded = false;
		try {
			statement = connection.prepareStatement(QueryManager.getQuery("insertEnvelope"));
			statement.setString(1, envelopeName);
			statement.executeUpdate();
			envelopeAdded = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcManager.closeConnection(statement, resultSet, connection);
		}
		return envelopeAdded;
	}
	public ArrayList<Envelope> getUserEnvelopes(String username){
		ArrayList<Envelope> envelopeList = new ArrayList<Envelope>();
		Connection connection = jdbcManager.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Envelope userEnvelope = null;
		try {
			statement = connection.prepareStatement(QueryManager.getQuery("selectUserEnvelopes"));
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				userEnvelope = new Envelope(resultSet.getString(1), resultSet.getBigDecimal(2));
				envelopeList.add(userEnvelope);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			jdbcManager.closeConnection(statement, resultSet, connection);
		}
		envelopeList = getTransactionList(username, envelopeList);
		return envelopeList;
	}
	public boolean addUserEnvelope(int userId, int envelopeId, BigDecimal amount){
		Connection connection = jdbcManager.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;		
		boolean userEnvelopeAdded = false;
		try {
			statement = connection.prepareStatement(QueryManager.getQuery("insertUserEnvelope"));
			statement.setInt(1, userId);
			statement.setInt(2, envelopeId);
			statement.setBigDecimal(3, amount);
			statement.executeUpdate();
			userEnvelopeAdded = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcManager.closeConnection(statement, resultSet, connection);
		}
		return userEnvelopeAdded;
	}
	public ArrayList<Envelope> getTransactionList(String username, ArrayList<Envelope> envelopeList){
		ArrayList<Transaction> transactionList = null;
		for(Envelope envelope: envelopeList){
			transactionList = new ArrayList<Transaction>();
			Connection connection = jdbcManager.getConnection();
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			Transaction transaction = null;
			try {
				statement = connection.prepareStatement(QueryManager.getQuery("selectTransactionList"));
				statement.setString(1, username);
				statement.setString(2, envelope.getName());
				resultSet = statement.executeQuery();
				while(resultSet.next()){
					transaction = new Transaction(resultSet.getString(1), resultSet.getBigDecimal(2), resultSet.getString(3));
					transactionList.add(transaction);
				}
			} catch(Exception e){
				e.printStackTrace();
			} finally {
				jdbcManager.closeConnection(statement, resultSet, connection);
			}
			envelope.setTransactionList(transactionList);
		}
		return envelopeList;
	}
	public BigDecimal getTotalBalance(ArrayList<Envelope> envelopeList){
		BigDecimal total = new BigDecimal("0");
		for(Envelope envelope: envelopeList){
			total = total.add(envelope.getBalance());
		}
		total.setScale(2);
		return total;
	}
	public boolean updateBalance(int userId, int envelopeId, BigDecimal amount, String activity){	
		Connection connection = jdbcManager.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean balanceUpdated = false;
		BigDecimal balance = getBalance(userId, envelopeId);
		if(activity.equalsIgnoreCase("Add Income") || activity.equalsIgnoreCase("Receive Funds"))
			balance = balance.add(amount);
		else if(activity.equalsIgnoreCase("Add Expense") || activity.equalsIgnoreCase("Transfer Funds"))
			balance = balance.subtract(amount);
		try {
			statement = connection.prepareStatement(QueryManager.getQuery("updateBalance"));
			statement.setBigDecimal(1, balance);
			statement.setInt(2, userId);
			statement.setInt(3, envelopeId);
			statement.executeUpdate();
			balanceUpdated = true;
		} catch(Exception e){
			e.printStackTrace();
			balanceUpdated = false;
		} finally {
			jdbcManager.closeConnection(statement, resultSet, connection);
		}
		return balanceUpdated;
	}
	public BigDecimal getBalance(int userId, int envelopeId){
		Connection connection = jdbcManager.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		BigDecimal balance = null;
		try {
			statement = connection.prepareStatement(QueryManager.getQuery("selectBalance"));
			statement.setInt(1, userId);
			statement.setInt(2, envelopeId);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				balance = resultSet.getBigDecimal(1);
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			jdbcManager.closeConnection(statement, resultSet, connection);
		}
		return balance;
	}
	public boolean removeEnvelope(int userId, int envelopeId){
		Connection connection = jdbcManager.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean envelopeRemoved = false;
		try {
			statement = connection.prepareStatement(QueryManager.getQuery("deleteEnvelope"));
			statement.setInt(1, userId);
			statement.setInt(2, envelopeId);
			statement.executeUpdate();
			envelopeRemoved = true;
		} catch(Exception e){
			e.printStackTrace();
			envelopeRemoved = false;
		} finally {
			jdbcManager.closeConnection(statement, resultSet, connection);
		}
		return envelopeRemoved;
	}
	/*for testing
	public static void main(String[] args){
		JdbcManager jdbcManager = new JdbcManager();
		EnvelopeManager envManager = new EnvelopeManager(jdbcManager);
		System.out.println("userEnvelopeAdded?: " + envManager.addUserEnvelope(1,5,new BigDecimal(40)));
	}
	/**/
}
