package com.bmo.bean;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Envelope {
	private String name;
	private BigDecimal balance;
	private ArrayList<Transaction> transactionList;
	public Envelope(String name, BigDecimal balance){
		this.name = name;
		this.balance = balance;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setBalance(BigDecimal balance){
		this.balance = balance;
	}
	public BigDecimal getBalance(){
		return balance;
	}
	public void setTransactionList(ArrayList<Transaction> transactionList){
		this.transactionList = transactionList;
	}
	public ArrayList<Transaction> getTransactionList(){
		return transactionList;
	}
}
