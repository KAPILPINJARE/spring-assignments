package com.capgemini.bankapp.service.impl;

import java.util.List;

import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.dao.impl.BankAccountDaoImpl;
import com.capgemini.bankapp.exceptions.AccountNotFoundException;
import com.capgemini.bankapp.exceptions.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.service.BankAccountService;
import com.capgemini.bankapp.util.DbUtil;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;


@Service("service")
public class BankAccountServiceImpl implements BankAccountService
{	

	private BankAccountDao bankAccountDao;
	
	@Autowired
	public BankAccountServiceImpl(BankAccountDao bankAccountDao)
	{
		this.bankAccountDao = bankAccountDao;
	}

	@Override
	public double checkBalance(long accountId) throws AccountNotFoundException
	{
		double balance = bankAccountDao.getBalance(accountId);
		if(balance >= 0)
			return balance;
		else 
			throw new AccountNotFoundException("BankAccount doesnt exists...");
	}

	@Override
	public double withdraw(long accountId, double amount) throws LowBalanceException, AccountNotFoundException
	{
		double balance = bankAccountDao.getBalance(accountId);
		if(balance < 0)
			throw new AccountNotFoundException("BankAccount doesnt exists...");
		else if (balance - amount >= 0)
		{
			balance = balance - amount;
			bankAccountDao.updateBalance(accountId, balance);
			DbUtil.commit();
			return balance;
		} else 
			throw new LowBalanceException("account balance not sufficient..");
	}

	@Override
	public double deposit(long accountId, double amount) throws AccountNotFoundException
	{
		double balance = bankAccountDao.getBalance(accountId);
		if(balance < 0)
			throw new AccountNotFoundException("BankAccount doesnt exists...");
		balance = balance + amount;
		bankAccountDao.updateBalance(accountId, balance);
		DbUtil.commit();
		return balance;
	}

	@Override
	public boolean deleteBankAccount(long accountId) throws AccountNotFoundException
	{
		boolean result = bankAccountDao.deleteBankAccount(accountId);
		if(result)
		{
			DbUtil.commit();
			return result;
		}
			else
			throw new AccountNotFoundException("BankAccount doesnt exists...");
	}

	@Override
	public double fundTransfer(long fromAccount, long toAccount, double amount)
			throws LowBalanceException, AccountNotFoundException
	{
		double balance = 0;
		try
		{
			balance = withdrawForFundTransfer(fromAccount, amount);
			deposit(toAccount, amount);
		} catch (LowBalanceException | AccountNotFoundException e)
		{
			DbUtil.rollback();
			throw e;
		}
		return balance;
	}

	@Override
	public boolean addNewBankAccount(BankAccount account)
	{
		boolean result = bankAccountDao.addNewBankAccount(account);
		if(result)
			DbUtil.commit();
		return result;
	}

	@Override
	public List<BankAccount> findAllBankAccount()
	{
		return bankAccountDao.findAllBankAccounts();
	}

	@Override
	public BankAccount searchForAccount(long accountId) throws AccountNotFoundException
	{
		BankAccount account = (BankAccount) bankAccountDao.searchForAccount(accountId);
		if(account == null)
			throw new AccountNotFoundException("account doesnot exists..");
		return account;
	}

	@Override
	public boolean updateAccount(BankAccount account)
	{
		boolean result = bankAccountDao.updateAccount(account);
		if(result)
			DbUtil.commit();
		return result;
	}


	public double withdrawForFundTransfer(long accountId, double amount)
			throws LowBalanceException, AccountNotFoundException
	{
		double balance = bankAccountDao.getBalance(accountId);
		if(balance < 0)
			throw new AccountNotFoundException("BankAccount doesnt exists...");
		else if (balance - amount >= 0)
		{
			balance = balance - amount;
			bankAccountDao.updateBalance(accountId, balance);
			return balance;
		} else 
			throw new LowBalanceException("account balance not sufficient..");
	}

}
