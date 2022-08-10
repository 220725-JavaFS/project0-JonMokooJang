package com.bank.controllers;

import java.util.List;
import java.util.Scanner;

import com.bank.models.Bank;
import com.bank.services.BankService;

public class BankMenuController {	
	
	private BankService bankService = new BankService ();
	private Scanner scan = new Scanner(System.in);
	
	Bank currentUser;
	
	public void bankMenu() {
		String choice = "";
		menuLoop:
		while (!choice.equals("0")) {
			System.out.println("Hello welcome to the Bank Menu! What would you like to do today?" 
					+ "\n1. Login as a customer"
					+ "\n2. Create new bank account"
					+ "\n3. Login as an employee"
					+ "\n0. Exit program");
			
			choice = scan.nextLine();
			
			switchChoice: //THIS ALLOWS TO TARGET THIS SWITCH STATEMENT INSTEAD OF OTHER POSSIBLE SWITCH STATEMENT OR THE OVERALL STATEMENT IN THE CLASS
			switch (choice) {
				case "1":
				seeCustomer(); //GOES TO THE LOGIN SCREEN
				break switchChoice;
			case "2":
				seeCreation(); //GOES TO THE ACCOUNT CREATION SCREEN
				break switchChoice;
			case "3":
				seeAdmin(); //GOES TO THE ADMIN SCREEN
				break switchChoice;
			case "0":
				break switchChoice;
			default:
				System.out.println("Sorry that is a not a valid input. Please try again.");
				break switchChoice;
			}
		}
	}
	
	//THIS SECTION IS WHERE CUSTOMER LOGIN THEIR USER AND PASS
	
	public void seeCustomer() {
		
		String user = ""; //test
		String pass = ""; //pass
		
		System.out.println("Please enter your username.");
		user = scan.nextLine();
		
		System.out.println("Please enter your password.");
		pass = scan.nextLine();
	
		
		if (bankService.bankLogin(user,pass)) {
			System.out.println("Login successful");
			
			//set the current bank account user here.
			currentUser = bankService.getBankDAO().verifyAccount(user, pass);
			
			seeAccount(); //GOES TO CUSTOMER ACCOUNT SCREEN
		} else {
			
			String choice = "";
			System.out.println("Login unsuccessful. Press 1 to try again or 2 to exit login");
			
			choice = scan.nextLine();
			
			switchChoiceThree:
			switch (choice) {
				case "1":
					seeCustomer(); //GOES BACK TO LOGIN/PASSWORD SCREEN
					break switchChoiceThree;
				case "2": //GOES BACK TO MAIN MENU
					bankMenu();
					break switchChoiceThree;
				default: 
					System.out.println("Sorry that is not a valid input. Please try again.");
					break switchChoiceThree;
			}
		}
			
	}
	
	public void seeAccount () {
		String choice = "";
		while (!choice.equals("0")) {
			System.out.println("Welcome User! What would you like to do today?" 
					+ "\n1. See account detail"
					+ "\n2. Deposit"
					+ "\n3. Withdraw"
					+ "\n0. Back");
			
			choice = scan.nextLine();
			
			switchChoiceFour: //THIS ALLOWS TO TARGET THIS SWITCH STATEMENT INSTEAD OF OTHER POSSIBLE SWITCH STATEMENT OR THE OVERALL STATEMENT IN THE CLASS
			switch (choice) {
				case "1":
				seeAccDetail();
				break switchChoiceFour;
			case "2":
				seeDeposit();
				break switchChoiceFour;
			case "3":
				seeWithdraw();
				break switchChoiceFour;
			case "0":
				bankMenu();
				break switchChoiceFour;
			default:
				System.out.println("Sorry that is a not a valid input. Please try again.");
				break switchChoiceFour;
			}
		}
	}
	
	public void seeAccDetail () {
		String choice = "";
		
		System.out.println("Hello " + currentUser.getAccount_first() + " " + currentUser.getAccount_last() + ", your current balance is: " + currentUser.getAccount_balance() + ". Press 1 to go back."); //Debugger at seeAccDetail: get detail data of the currentUser
		
		choice = scan.nextLine();
		
		switchChoiceAccDetail: 
			switch(choice) {
				case "1":
					seeAccount();
					break switchChoiceAccDetail;
				default:
					System.out.println("Sorry that is not a valid input. Please try again.");
					break switchChoiceAccDetail;
			}
		
	}
	
	public void seeDeposit () {
		String choice = "";
		int depositNewBalance = currentUser.getAccount_balance();
		String user = currentUser.getAccount_user();
		String pass = currentUser.getAccount_pass();
				
		System.out.println("How much do you want to deposit today?");
		int answer = scan.nextInt();
		
		depositNewBalance = depositNewBalance + answer;
		
		bankService.depositUpdateBalance(user, pass, depositNewBalance);
		
		currentUser = bankService.getBankDAO().verifyAccount(user, pass); //RESET TO SEE CHANGE
		
		System.out.println("Your new balance is: " + depositNewBalance + "." + " Press 1 to go back.");
		
		scan.nextLine();
		choice = scan.nextLine();
		
		switchChoiceSeeDeposit: 
			switch(choice) {
				case "1":
					seeAccount();
					break switchChoiceSeeDeposit;
				default: 
					System.out.println("Sorry this is not a valid input. Please try again.");
					break switchChoiceSeeDeposit;
			}
		
	}
	
	public void seeWithdraw () {
		String choice = "";
		int withdrawNewBalance = currentUser.getAccount_balance();
		String user = currentUser.getAccount_user();
		String pass = currentUser.getAccount_pass();
				
		System.out.println("How much do you want to withdraw today?");
		int answer = scan.nextInt();
		
		withdrawNewBalance = withdrawNewBalance - answer;
		
		if (withdrawNewBalance < 0) {
			System.out.println("Sorry you have insufficient funds to proceed with the transaction.");
			scan.nextLine();
			seeWithdraw();
		} else {
			bankService.withdrawUpdatBalance(user, pass, withdrawNewBalance);
			
			currentUser = bankService.getBankDAO().verifyAccount(user, pass); //RESET USER TO UPDATE CHANGE
			
			System.out.println("Your new balance is: " + withdrawNewBalance + "." + " Press 1 to go back.");
			
			scan.nextLine();
			choice = scan.nextLine();
			
			switchChoiceSeeDeposit: 
				switch(choice) {
					case "1":
						seeAccount();
						break switchChoiceSeeDeposit;
					default: 
						System.out.println("Sorry this is not a valid input. Please try again.");
						break switchChoiceSeeDeposit;
				}
		}
		
	}
	
	//THIS SECTION IS WHERE THE ACCOUNT CREATION SCREEN WILL BE

	public void seeCreation () {
		
		String choice = "";
		
		while (!choice.equals("0")) {
			System.out.println("Welcome to the account creation menu! Press 1 to continue or 2 to go back.");
			choice = scan.nextLine();
			
			switchChoiceSeeCreation: 
				switch(choice) {
					case "1":
						createBankMenu();
						break switchChoiceSeeCreation;
					case "2": 
						bankMenu();
						break switchChoiceSeeCreation;
					default: 
						System.out.println("Sorry this is not a valid input. Please try again.");
						break switchChoiceSeeCreation;
				}
		}
	}
	
	public void createBankMenu() {
		System.out.println("Alright, lets get started! Please fill in the following questions: ");
		
		Bank bank = new Bank();
		
		System.out.println("What will you like your user name to be?");
		bank.setAccount_user(scan.nextLine());
		
		System.out.println("What will you look your password to be?");
		bank.setAccount_pass(scan.nextLine());
		
		System.out.println("What is your first name?");
		bank.setAccount_first(scan.nextLine());
		
		System.out.println("What is your last name?");
		bank.setAccount_last(scan.nextLine());
		
		System.out.println("What is your current occupation?");
		bank.setAccount_job(scan.nextLine());
		
		System.out.println("How much will you be able to put for your opening deposit? (MINIMUM DEPOSIT IS $100)");
		int depositeAnswer = scan.nextInt();
		if (depositeAnswer < 100 ) {
			System.out.println("Sorry your opening deposit must be greater than $100. Sending you back to the main menu.");
			scan.nextLine();
			bankMenu();
		} else {
			bank.setAccount_balance(depositeAnswer);
		}
		
		scan.nextLine();
		bankService.addAccount(bank);
		
		seeIntermediate();
	}
	
	
	public void seeIntermediate () {
		String choice = "";
		
		System.out.println("Thank you for registering with our bank! What would you like to now? "
				+ "\n1. Return back to menu"
				+ "\n2. Login as a customer");
		
		choice = scan.nextLine();
		
		switchChoiceFive: 
			switch(choice) {
				case "1":
					bankMenu();
					break switchChoiceFive;
				case "2": 
					seeCustomer();
					break switchChoiceFive;
				default: 
					System.out.println("Sorry that is not a valid input. Please try again.");
					break switchChoiceFive;
			}
	}
	
	//THIS SECTION IS WHERE THE ADMIN LOGIN THEIR USER AND PASS
	
	public void seeAdmin() {
		String data_user = "admin";
		String data_pass = "password";
		
		String user = "";
		String pass = "";
		
		System.out.println("Please enter your username."); 
		user = scan.nextLine();
		
		System.out.println("Please enter your password."); 
		pass = scan.nextLine();
		
		if (user.equalsIgnoreCase(data_user) && pass.equalsIgnoreCase(data_pass)) {
			System.out.println("Login successful");
			seeInsideAdmin(); //GOES TO CUSTOMER ACCOUNT SCREEN
		} else {
			String choice = "";
			System.out.println("Login unsuccessful. Press 1 to try again or 2 to exit login");
			choice = scan.nextLine();
			switchChoiceThree:
			switch (choice) {
				case "1":
					seeAdmin(); //GOES BACK TO LOGIN/PASSWORD SCREEN
					break switchChoiceThree;
				case "2": //GOES BACK TO MAIN MENU
					bankMenu();
					break switchChoiceThree;
				default: 
					System.out.println("Sorry that is not a valid input. Please try again.");
					break switchChoiceThree;
			}
		}
	}
	
	public void seeInsideAdmin () {
		String choice = "";
		while(!choice.equals("0")) {
			System.out.println("Hello administrator! What would you like to do?"
					+ "\n1. See customer account information."
					+ "\n2. Remove a customer account."
					+ "\n0. Go back");
			
			choice = scan.nextLine();
			
			switchChoiceTwo: 
				switch(choice) {
					case "1":
						seeBank(); //GOES TO ACCOUNT INFORMATION SCREEN
						break switchChoiceTwo;
					case "2":
						removeBank();
						break switchChoiceTwo;
					case "0":
						bankMenu();
						break switchChoiceTwo;
					default: 
						System.out.println("Sorry that is not a valid input. Please try again.");
						break switchChoiceTwo;
				}
		}
	}
	
	//THIS IS WHERE THE ADMIN CAN SEE ACCOUNT INFORMATION

	public void seeBank() {
		
		String answer = "";
		while(!answer.equalsIgnoreCase("0")) { //ALOWS OUR STATEMENT TO CONTINUOUSLY LOOP
			System.out.println("Which bank account do you want to see? Please give an ID number or type 'all' to see all accounts. Press 0 to exit.");
			
			answer = scan.nextLine();
			
			if (answer.equalsIgnoreCase("all")) {
				List<Bank> list = bankService.bankShow();
				for(Bank a : list) { //ENHANCED FOR LOOP TO LIST ACCOUNT ONE AT A TIME
					System.out.println(a);
				}
			} else if (answer.equalsIgnoreCase("0")) {
				return;
			} else {
				int id = 0;
				try {
					id = Integer.parseInt(answer);
				} catch (NumberFormatException e) {
					System.out.println("That is not a valid id, please try again");
					continue;
				}
				
				Bank bank = bankService.getSingleBank(id);
				System.out.println("Here is your acount info: \n " + bank);
			}
		}
		
	}
	
	public void removeBank() {
		String answer = "";
		
		while(!answer.contentEquals("0")) {
			System.out.println("Which bank account do you want to delete? Please give an ID number or press 0 to go back.");
			answer = scan.nextLine();
			
			if (answer.equalsIgnoreCase("0")) {
				return;
			} else {
				int id = 0;
				try {
					id = Integer.parseInt(answer);
				} catch (NumberFormatException e) {
					System.out.println("That is not a valid id, please try again");
					continue;
				}
				
				bankService.deleteAccount(id);
				System.out.println("Account deleted.");
			}
		}
	}
}








