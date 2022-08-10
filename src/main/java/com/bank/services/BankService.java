package com.bank.services;

import java.util.List;

import com.bank.daos.BankDAO;
import com.bank.daos.BankDAOImpl;
import com.bank.models.Bank;

public class BankService {
	
	BankDAO bankDAO = new BankDAOImpl();
	
	public Bank getSingleBank(int id) {
		return bankDAO.getBankById(id);
	}
	
	public boolean bankLogin(String user, String pass) {
		if (bankDAO.verifyAccount(user,pass) != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public BankDAO getBankDAO()
	{
		return bankDAO;
	}
	
	public List<Bank>bankShow() {
		return bankDAO.getAllAccounts();
	}

	public void addAccount(Bank bank) {
		bankDAO.insertAccount(bank);
		
	}
	
	public void deleteAccount(int id ) {
		bankDAO.deleteAccount(id);
	}
	
	public void depositUpdateBalance(String user, String pass, int depositUpdateBalance) {
		bankDAO.depositUpdateBalance(user, pass, depositUpdateBalance);
		
	}
	
	public void withdrawUpdatBalance(String user, String pass, int withdrawUpdateBalance) {
		bankDAO.withdrawUpdateBalance(user, pass, withdrawUpdateBalance);
	}
	
	
}
