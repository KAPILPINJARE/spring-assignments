package com.capgemini.bankapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate; 

import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.model.BankAccount;

public class BankAccountDaoImpl implements BankAccountDao
{
	private JdbcTemplate jdbcTemplate;	

	private DataSource dataSource;
 	private Connection connection;
 	public BankAccountDaoImpl(DataSource dataSource)
    	{
        	this.dataSource = dataSource;
		try{
			this.connection = dataSource.getConnection();
			connection.setAutoCommit(false);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
    	}
	
	public void setJdbc(JdbcTemplate jdbcTemplate)
    	{
        	this.jdbcTemplate = jdbcTemplate;
    	}
	
	
	@Override
	public double getBalance(long accountId)
	{
		String query = "SELECT ACCOUNT_BALANCE FROM BANKACCOUNTS WHERE ACCOUNT_ID = " + accountId;
		double balance = -1;
		//Connection connection = DbUtil.getConnetion();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet result = preparedStatement.executeQuery())
		{
			if (result.next())
				balance = result.getDouble(1);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return balance;
	}


	@Override
	public int updateBalance(long accountId, double newBalance)
	{
		String query = "UPDATE BANKACCOUNTS SET ACCOUNT_BALANCE = '"+newBalance+"' WHERE ACCOUNT_ID = '"+accountId+"' ";
		return jdbcTemplate.update(query);
	}

	
	@Override
	public boolean deleteBankAccount(long accountId)
	{
		String query = "DELETE FROM BANKACCOUNTS WHERE ACCOUNT_ID = " + accountId;
		if(jdbcTemplate.update(query) == 1)
			return true;
		else
			return false;
	}

	@Override
	public boolean addNewBankAccount(BankAccount account)
	{
		String query = "INSERT INTO BANKACCOUNTS (CUSTOMER_NAME,ACCOUNT_TYPE,ACCOUNT_BALANCE) VALUES ('"+account.getAcccoutHolderName()+"','"+account.getAccountType()+"','"+account.getAccountBalance()+"')";
		if(jdbcTemplate.update(query) == 1)
			return true;
		else
			return false;  
	}

	@Override
	public List<BankAccount> findAllBankAccounts()
	{
		String query = "SELECT * FROM BANKACCOUNTS";
		List<BankAccount> accounts = new ArrayList<BankAccount>();

		//Connection connection = DbUtil.getConnetion();
		try (PreparedStatement statement = connection.prepareStatement(query);
				ResultSet result = statement.executeQuery())
		{
			while (result.next())
			{
				long accountId = result.getLong(1);
				String accountHolderName = result.getString(2);
				String accountType = result.getString(3);
				double accountBalance = result.getDouble(4);

				BankAccount account = new BankAccount(accountId, accountHolderName, accountType, accountBalance);
				accounts.add(account);
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public BankAccount searchForAccount(long accountId)
	{
		String query = "SELECT * FROM BANKACCOUNTS WHERE ACCOUNT_ID = " + accountId;
		BankAccount account = null;
		//Connection connection = DbUtil.getConnetion();
		try (PreparedStatement statement = connection.prepareStatement(query);
				ResultSet result = statement.executeQuery())
		{
			if (result.next())
				account = new BankAccount(result.getLong(1), result.getString(2), result.getString(3),
						result.getDouble(4));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return account;
	}
	
	@Override
	public boolean updateAccount(BankAccount account)
	{
		String query = "UPDATE BANKACCOUNTS SET CUSTOMER_NAME = '"+account.getAcccoutHolderName()+"',ACCOUNT_TYPE = '"+account.getAccountType()+"' WHERE ACCOUNT_ID = '"+account.getAccountId()+"'";
		if(jdbcTemplate.update(query) == 1)
			return true;
		else
			return false;
	}
	
	public void commit()
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

	public void rollback()
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

}
