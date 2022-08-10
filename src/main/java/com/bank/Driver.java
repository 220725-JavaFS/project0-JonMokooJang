package com.bank;

import com.bank.controllers.BankMenuController;

public class Driver {

	public static void main(String[] args) {
		System.out.println("----------- BANK APPLICATION -----------");
		
		BankMenuController bmc = new BankMenuController();
		
		bmc.bankMenu();
		
		System.out.println("Thank you for using the application. See you again soon.");

	}

}
