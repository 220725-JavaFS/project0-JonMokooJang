package com.bank.daos;

import java.util.List;

import com.bank.models.Bank;

public interface BankDAO {
	
	public abstract Bank getBankById(int id);
	
	public List<Bank> getAllAccounts();
	
	public abstract Bank verifyAccount(String user, String pass);

	public abstract void insertAccount(Bank bank);
	
	public abstract void deleteAccount(int id);
	
	public abstract void depositUpdateBalance (String user, String pass, int depositUpdateBalance);

	public abstract void withdrawUpdateBalance(String user, String pass, int withdrawUpdateBalance);
}
