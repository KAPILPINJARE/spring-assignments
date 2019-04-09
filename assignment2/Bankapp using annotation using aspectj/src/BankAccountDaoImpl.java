package com.capgemini.bankapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.util.DbUtil;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.annotation.Resource;

@Component("dao")
public class BankAccountDaoImpl implements BankAccountDao
{	
	
	Connection connection ;
	
	@Autowired
	public BankAccountDaoImpl(Connection connection)
	{
		this.connection = connection;
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
		String query = "UPDATE BANKACCOUNTS SET ACCOUNT_BALANCE = ? WHERE ACCOUNT_ID = ?";
		int result = 0;
		//Connection connection = DbUtil.getConnetion();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query))
		{
			preparedStatement.setDouble(1, newBalance);
			preparedStatement.setLong(2, accountId);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteBankAccount(long accountId)
	{
		String query = "DELETE FROM BANKACCOUNTS WHERE ACCOUNT_ID = " + accountId;
		int result;
		//Connection connection = DbUtil.getConnetion();
		try(PreparedStatement preparedStatement = connection.prepareStatement(query))
		{
			result = preparedStatement.executeUpdate();
			if (result == 1)
				return true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addNewBankAccount(BankAccount account)
	{
		String query = "INSERT INTO BANKACCOUNTS (CUSTOMER_NAME,ACCOUNT_TYPE,ACCOUNT_BALANCE) VALUES (?,?,?)";
		//Connection connection = DbUtil.getConnetion();
		try (PreparedStatement statement = connection.prepareStatement(query))
		{
			statement.setString(1, account.getAcccoutHolderName());
			statement.setString(2, account.getAccountType());
			statement.setDouble(3, account.getAccountBalance());
			if (statement.executeUpdate() == 1)
				return true;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
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

		String query = "UPDATE BANKACCOUNTS SET CUSTOMER_NAME = ?,ACCOUNT_TYPE = ? WHERE ACCOUNT_ID = ?";
		int update = 0;
		//Connection connection = DbUtil.getConnetion();
		try (PreparedStatement statement = connection.prepareStatement(query))
		{
			statement.setString(1, account.getAcccoutHolderName());
			statement.setString(2, account.getAccountType());
			statement.setLong(3, account.getAccountId());
			update = statement.executeUpdate();
			if (update == 1)
			{
				return true;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	
	

}
