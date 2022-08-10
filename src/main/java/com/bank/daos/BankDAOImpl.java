package com.bank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.bank.models.Bank;
import com.bank.utils.ConnectionUtil;

public class BankDAOImpl implements BankDAO{

	@Override
	public Bank getBankById(int id) {
		//WE ARE CONNECTING AND GETTING A STATEMENT FOR ID IN THE DATABASE
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM account WHERE account_number = " + id + ";";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql); //WE ARE HOLDING THE RESULT
			
			if (result.next()) { //USING IF SINCE WE ONLY NEED TO SELECT ONE QUERY
				Bank bank = new Bank(
						result.getInt("account_number"),
						result.getString("account_user"),
						result.getString("account_pass"),
						result.getString("account_first_name"),
						result.getString("account_last_name"),
						result.getString("account_job"),
						result.getInt("account_balance")
						);
					return bank;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Bank> getAllAccounts() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM account";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql); //WE ARE HOLDING THE RESULT
			
			List<Bank> bankList = new LinkedList<Bank>();
			
			while (result.next()) { //BECAUSE WE AREN'T SLECTING ONE QUERY BUT SET OF QUERIES
				Bank bank = new Bank(
						result.getInt("account_number"),
						result.getString("account_user"),
						result.getString("account_pass"),
						result.getString("account_first_name"),
						result.getString("account_last_name"),
						result.getString("account_job"),
						result.getInt("account_balance")
					);
				
				bankList.add(bank);
			}
			
			return bankList;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Bank verifyAccount(String user, String pass) { 
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM account WHERE account_user='"+ user + "' AND "+"account_pass='"+ pass+"'";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql); //WE ARE HOLDING THE RESULT
			
			while (result.next()) { 
				Bank bank = new Bank(
						result.getInt("account_number"),
						result.getString("account_user"),
						result.getString("account_pass"),
						result.getString("account_first_name"),
						result.getString("account_last_name"),
						result.getString("account_job"),
						result.getInt("account_balance")
					);
				return bank;
			}
			
			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return null;
	}

	@Override
	public void insertAccount(Bank bank) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO account (account_user, account_pass, account_first_name, account_last_name, account_job, account_balance)"
					+ "VALUES (?, ?, ?, ?, ?, ?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count = 0;
			statement.setString(++count, bank.getAccount_user());
			statement.setString(++count, bank.getAccount_pass());
			statement.setString(++count, bank.getAccount_first());
			statement.setString(++count, bank.getAccount_last());
			statement.setString(++count, bank.getAccount_job());
			statement.setInt(++count, bank.getAccount_balance());
			
			statement.execute();
			
			
		} catch (SQLException e ) {
			e.printStackTrace();
		}
		
	}
	

	@Override
	public void deleteAccount(int id) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM account WHERE account_number = " + id + ";";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.execute();
			
			
		} catch (SQLException e ) {
			e.printStackTrace();
		}
		
	}
		
	
	@Override
	public void depositUpdateBalance(String user, String pass, int depositUpdateBalance) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE account SET account_balance = " + depositUpdateBalance + " WHERE account_user = '"+ user + "' AND "+"account_pass='"+ pass+"'";
			
			PreparedStatement statement = conn.prepareStatement(sql);
				
			statement.execute();
			
		} catch (SQLException e ) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void withdrawUpdateBalance(String user, String pass, int withdrawUpdateBalance) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE account SET account_balance = " + withdrawUpdateBalance + " WHERE account_user = '"+ user + "' AND "+"account_pass='"+ pass+"'";
			
			PreparedStatement statement = conn.prepareStatement(sql);
				
			statement.execute();
			
		} catch (SQLException e ) {
			e.printStackTrace();
		}
	}

}




