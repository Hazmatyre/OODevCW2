package auctionKernel;
import java.util.InputMismatchException;
import auctionKernel.*;
import java.util.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AuctionSys {

	//USED FOR IMPORTING TEST DATA ##IGNORE
	Scanner s;
	PrintWriter p; // CHANGE TO BUFFERED WRITER AT SOME POINT
	
	Scanner keyIn = new Scanner(System.in);
	
	List<Auction> allAuctions = new ArrayList<Auction>();
	List<User> users = new ArrayList<User>(); 
	int lineCount = 0;
	
	public void insertTestData(){
		try {
			s = new Scanner(new BufferedReader(new FileReader("auctions.txt")));
			while(s.hasNextLine()) { allAuctions.add(new Auction(s.next(), s.nextDouble(), s.nextDouble(), s.next(), s.next(), s.next())); lineCount++;}
		} catch (FileNotFoundException e) { e.printStackTrace(); }	
	}
	
	public void placeAuction() {
		try {
			allAuctions.add(new Auction(keyIn.next(), keyIn.nextDouble(), keyIn.nextDouble(), keyIn.next(), keyIn.next(), keyIn.next())); lineCount++;
			p = new PrintWriter(new BufferedWriter(new FileWriter("auctions.txt", true)));
			p.print("\n" + allAuctions.get(lineCount-1));
		} catch (IOException e) { e.printStackTrace(); }
		  finally { if(p != null) { p.close(); } }
	}
	
	public void browseAuction() {
		//Need to check the auction status before displaying
		for(int i = 0; i < lineCount; i++) {
			System.out.println(allAuctions.get(i).toString() + "\n");
		}
	}
	
	public void setupAccount() {
	//creates new User
	}
	
	// userIn takes an integer as a max value for a switch for a menu setup.
	// userIn takes an integer as a max value for a switch for a menu setup.
	public int userIn(int max){
		int switcher = 0;
		String input;
		while (switcher < 1 || switcher > max){
			input = keyIn.nextLine(); 
			try {
				switcher = Integer.parseInt(input);
			} catch (NumberFormatException userIn1) {
				System.out.println("Enter a number between 1 and " + max);
				continue;
			}
			if (switcher < 1 || switcher > max){
				System.out.println("Enter a number between 1 and " + max);
			}
		}
		return switcher;
}
	
	//First menu display call, would be run at the start of the program.
	public void startDisplay(){
		System.out.println("Alex's Amazing Auctions!");
		System.out.println("1. Browse current auctions");
		System.out.println("2. Login");
		System.out.println("3. Sign up");
		System.out.println("4. Exit");
		int choice = userIn(4);
		// Choices for each menu entry, needs finishing
		switch (choice) {
		case 1: System.out.println();
				listAuctions();
				break;
		case 2: System.out.println(); 
				loginDisplay();
				break;
		case 3: System.out.println();
				signupDisplay();
				break;
		case 4: System.exit(0);
		}
	}
	
	// List auctions needs to read auctions.txt, and only take auction data and present it nicely
	public void listAuctions() {
		
	}
	
	
	public void loginDisplay () {
		String username,password;
		int type = 2;

		System.out.println("Enter username:");
		username = keyIn.nextLine();
		System.out.println("Enter password:");
		password = keyIn.nextLine();
		
		try {
		 	type = readAccount(username, password);
		} catch (IOException e) {
		}
		switch (type) {
		case 0: buyerloginDisplay();
				break;
		case 1: sellerloginDisplay();
				break;
		case 2: System.out.println("username or password incorrect");
				break;
		}
		
	}

	public int readAccount(String username, String password) throws IOException {
		Scanner accIn = new Scanner(new FileReader("accounts.txt"));
		accIn.nextLine();
		String lineArr[];
		String lineStr;
		while (accIn.hasNextLine()) {
			lineStr = accIn.nextLine();
			lineArr = lineStr.split(" ");
			if (lineArr[1].equals(username) && lineArr[2].equals(password)){
			   accIn.close();
			   return Integer.parseInt(lineArr[0]);
			}
		}
		accIn.close();
		return 2;
	}
	
	public void buyerloginDisplay(){
		System.out.println("1. Browse auctions");
		System.out.println("2. View bids");
		userIn(2);
	}
	
	public void sellerloginDisplay() {
		System.out.println("1. Start new auction");
		System.out.println("2. View current auctions");
		System.out.println("3. View pending auctions");
		System.out.println("4. Add new item to stock");
		userIn(4);
	}
	
	// adds a new user, they can type return to cancel account creation.
	public void signupDisplay() {
		System.out.println("Sign up:");
		String crUsername = keyIn.next();
		System.out.println(crUsername + " chosen.");
		
		System.out.println("Enter your password: ");
		String crPassword = keyIn.next();
		
		System.out.println("Are you a buyer or seller?");
		System.out.println("1. Buyer");
		System.out.println("2. Seller");
		int choice = userIn(2);
		
		User u;
		
		switch(choice) {
		case 1: u = new Buyer(crUsername, crPassword);
				try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("accounts.txt", true)))) {
				    out.println("0, " + crUsername + ", " + crPassword);
				}catch (IOException e) { 
					System.out.println("Account file not found");
				}
				System.out.println("Buyer Account Created");
				buyerloginDisplay();	
				break;
		case 2: u = new Seller(crUsername, crPassword);
				try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("accounts.txt", true)))) {
				    out.println("1, " + crUsername + ", " + crPassword);
				}catch (IOException e) {
					System.out.println("Account file not found");
				}
				System.out.println("Seller Account Created");
				sellerloginDisplay();
				break;
		}
	}	
}
