package com.bmo.bean;

import java.math.BigDecimal;

public class Transaction {
	private String activity;
	private BigDecimal amount;
	private String date;
	public Transaction(String activity, BigDecimal amount, String date){
		this.activity = activity;
		this.amount = amount;
		this.date = date;
	}
	public void setActivity(String activity){
		this.activity = activity;
	}
	public String getActivity(){
		return activity;
	}
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}
	public BigDecimal getAmount(){
		return amount;
	}
	public void setDate(String date){
		this.date = date;
	}
	public String getDate(){
		return date;
	}
}
