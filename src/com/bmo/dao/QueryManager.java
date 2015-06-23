package com.bmo.dao;

import java.util.ResourceBundle;

public class QueryManager {
	private static ResourceBundle properties = ResourceBundle.getBundle("com.bmo.dao.queries");
	public static String getQuery(String query){
		return properties.getString(query);
	}
}
