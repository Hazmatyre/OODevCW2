package auctionKernel;
import java.util.InputMismatchException;
import auctionKernel.*;
import java.util.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
			displayAuctions();
		} catch (FileNotFoundException e) { e.printStackTrace(); }	
	}
	
	public void displayAuctions(){
		for(int i = 0; i < lineCount; i++) {
			System.out.println(allAuctions.get(i).toString() + "\n");
		}
	}
	
	public void saveAuction() throws FileNotFoundException{
		try {
			p = new PrintWriter(new BufferedWriter(new FileWriter("auctions.txt", true)));
			p.println("\n" + allAuctions.get(0));
		} catch (IOException e) { e.printStackTrace(); }
		  finally { if(p != null) { p.close(); } }
	}
	
	public void placeAuction() {
	//adds to auction data structure
	}
	
	public void browseAuction() {
	//returns all open auctions in data structure
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
		String uName,uPass;
		int type;
		System.out.println("Auction login:");
		System.out.println("1. Buyer");
		System.out.println("2. Seller");
		type = userIn(2) - 1;
		// userin-1 because the type is stored as 0 and 1, not 1 and 2.
		
		System.out.println("Enter username:");
		uName = keyIn.nextLine();
		System.out.println("Enter password:");
		uPass = keyIn.nextLine();
		
	}
	public void buyerloginDisplay(){
		System.out.println("1. Browse auctions");
		System.out.println("2. View bids");
	}
	
	public boolean readAccount(String username) throws IOException {
		Scanner accIn = new Scanner(new FileReader("accounts.txt"));
		accIn.useDelimiter(", ");
		accIn.nextLine();
		while (accIn.hasNextLine()) {
		   if (accIn.next() == username ){
			   return false;
		   }
		}
		accIn.close();
		return true;
	}
	
	public void sellerloginDisplay() {
		System.out.println("1. Start new auction");
		System.out.println("2. View current auctions");
		System.out.println("3. View pending auctions");
		System.out.println("4. Add new item to stock");
	}
	
	// adds a new user, they can type return to cancel account creation.
	public void signupDisplay() {
		System.out.println("Sign up:");
		System.out.println("Enter new username or type \"return\" now to return to main menu");
		String crUsername = keyIn.next();
		if (crUsername == "return") {
			return;
		}
		System.out.println(crUsername + "chosen.");
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
