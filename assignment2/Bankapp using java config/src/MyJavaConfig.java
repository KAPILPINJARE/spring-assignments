package com.capgemini.bankapp.config;

import org.springframework.context.annotation.*;

import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

import com.capgemini.bankapp.service.impl.BankAccountServiceImpl;
import com.capgemini.bankapp.dao.impl.BankAccountDaoImpl;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.util.DbUtil;

@Configuration
public class MyJavaConfig
{
	@Bean
	public BankAccountServiceImpl getServiceImpl()
	{
		return new BankAccountServiceImpl(getDaoImpl());
	}
	
	@Bean
	public BankAccountDaoImpl getDaoImpl()
	{
		return new BankAccountDaoImpl(getDbConnection());
	}

	@Bean
	public Connection getDbConnection()
	{
		return DbUtil.getConnetion(getDatabaseDetails());
	}

	@Bean
	public Properties getDatabaseDetails()
	{
		FileReader reader = null;
		Properties databaseDetails = null;
		try
		{
			reader = new FileReader("src/dbConfig.properties");
			databaseDetails = new Properties();
			databaseDetails.load(reader);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return databaseDetails;
	}

	@Bean
	@Scope("prototype")
	public BankAccount getBankAccount()
	{
		return new BankAccount();
	}
}