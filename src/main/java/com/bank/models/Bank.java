package com.bank.models;

public class Bank {
	private int account_number;
	private String account_user;
	private String account_pass;
	private String account_first;
	private String account_last;
	private String account_job;
	private int account_balance;
	
	//THIS IS THE CONSTRUCTOR SECTION
	
	public Bank(int account_number, String account_user, String account_pass, String account_first, String account_last,
			String account_job, int account_balance) {
		super();
		this.account_number = account_number;
		this.account_user = account_user;
		this.account_pass = account_pass;
		this.account_first = account_first;
		this.account_last = account_last;
		this.account_job = account_job;
		this.account_balance = account_balance;
	}


	public Bank() {
		super();
	}
	
	//PART OF THE CONSTRUCTOR SECTION - BUT REMOVING ACCOUNT NUMBER SINCE WE WILL AUTO GENERATING WHEN CREATING NEW ACCOUNT 

	public Bank(String account_user, String account_pass, String account_first, String account_last, String account_job,
			int account_balance) {
		super();
		this.account_user = account_user;
		this.account_pass = account_pass;
		this.account_first = account_first;
		this.account_last = account_last;
		this.account_job = account_job;
		this.account_balance = account_balance;
	}


	//THIS IS THE GETTER/SETTER SECTION
	
	public int getAccount_number() {
		return account_number;
	}


	public void setAccount_number(int account_number) {
		this.account_number = account_number;
	}


	public String getAccount_user() {
		return account_user;
	}


	public void setAccount_user(String account_user) {
		this.account_user = account_user;
	}


	public String getAccount_pass() {
		return account_pass;
	}


	public void setAccount_pass(String account_pass) {
		this.account_pass = account_pass;
	}


	public String getAccount_first() {
		return account_first;
	}


	public void setAccount_first(String account_first) {
		this.account_first = account_first;
	}


	public String getAccount_last() {
		return account_last;
	}


	public void setAccount_last(String account_last) {
		this.account_last = account_last;
	}


	public String getAccount_job() {
		return account_job;
	}


	public void setAccount_job(String account_job) {
		this.account_job = account_job;
	}


	public int getAccount_balance() {
		return account_balance;
	}


	public void setAccount_balance(int account_balance) {
		this.account_balance = account_balance;
	}
	
	//THIS IS THE HASCODE/EQUALS SECTION

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + account_balance;
		result = prime * result + ((account_first == null) ? 0 : account_first.hashCode());
		result = prime * result + ((account_job == null) ? 0 : account_job.hashCode());
		result = prime * result + ((account_last == null) ? 0 : account_last.hashCode());
		result = prime * result + account_number;
		result = prime * result + ((account_pass == null) ? 0 : account_pass.hashCode());
		result = prime * result + ((account_user == null) ? 0 : account_user.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bank other = (Bank) obj;
		if (account_balance != other.account_balance)
			return false;
		if (account_first == null) {
			if (other.account_first != null)
				return false;
		} else if (!account_first.equals(other.account_first))
			return false;
		if (account_job == null) {
			if (other.account_job != null)
				return false;
		} else if (!account_job.equals(other.account_job))
			return false;
		if (account_last == null) {
			if (other.account_last != null)
				return false;
		} else if (!account_last.equals(other.account_last))
			return false;
		if (account_number != other.account_number)
			return false;
		if (account_pass == null) {
			if (other.account_pass != null)
				return false;
		} else if (!account_pass.equals(other.account_pass))
			return false;
		if (account_user == null) {
			if (other.account_user != null)
				return false;
		} else if (!account_user.equals(other.account_user))
			return false;
		return true;
	}

	//THIS IS THE TOSTRING SECTION

	@Override
	public String toString() {		
		
		//return "Bank [account_number=" + account_number + ", account_user=" + account_user + ", account_pass="
		//+ account_pass + ", account_first=" + account_first + ", account_last=" + account_last
		//+ ", account_job=" + account_job + ", account_balance=" + account_balance + "]";
		
		return "Account #" + account_number + ": The account owner is " + account_first + " " + account_last + " that currently work as a " + account_job + 
		". The account currently holds: $" + account_balance + ". Their current username is: " + account_user + " and password is: " + account_pass + "." ;
		
	}
	
	
}

	