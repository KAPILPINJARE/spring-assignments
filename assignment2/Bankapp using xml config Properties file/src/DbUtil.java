package com.capgemini.bankapp.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil
{

	private static String driverClassName;
	private static String dburl;
	private static String username;
	private static String password;

	static Connection connection;

	public static Connection getConnetion(Properties properties)
	{
		
		driverClassName = properties.getProperty("driverClassName");
        	dburl = properties.getProperty("dburl");
	        username = properties.getProperty("username");
		password = properties.getProperty("password");

		try
		{
			Class.forName(driverClassName);
			if (connection == null)
			{
				connection = DriverManager.getConnection(dburl, username, password);
				connection.setAutoCommit(false);
			}
		} catch (ClassNotFoundException e)
		{
			System.out.println("Driver Connection not fount");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return connection;

	}

	public static void commit()
	{
		try
		{
			if (connection != null)
				connection.commit();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public static void rollback()
	{
		try
		{
			if (connection != null)
				connection.rollback();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

//	static
//	{
//		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("dbConfig.properties")))
//		{
//			Properties properties = new Properties();
//			properties.load(bufferedReader);
//
//			driverClassName = properties.getProperty("driverClassName");
//			dburl = properties.getProperty("dburl");
//			username = properties.getProperty("username");
//			password = properties.getProperty("password");
//		} catch (FileNotFoundException e)
//		{
//			e.printStackTrace();
//		} catch (IOException e)
//		{
//			e.printStackTrace();
//		}
//	}

}
