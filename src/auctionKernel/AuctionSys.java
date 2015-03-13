package auctionKernel;
import java.time.LocalDateTime;
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
	
	public void placeAuction(String username, String itemName, double startPrice, double reservePrice, LocalDateTime startDate, LocalDateTime endDate, String status, String description) {
			allAuctions.add(new Auction (username,itemName,startPrice,reservePrice,startDate,endDate, status, description)); 
	}
	// matt needs to finish
	public void createAuctionDisplay() {
		
	}
	
	public void browseAuction() {
		//Need to check the auction status before displaying
		for(Auction a : allAuctions) {
			if (a.getCloseDate().isBefore(LocalDateTime.now())) {
				System.out.printf(a.getItemName() + "£" + a.getCurrentBid()); // Browse format for when the proper auction object/list is done
			}
		}
		/* Browse format: 
		 * TV SET - £100 - Ends: 30/03/2015, reserve not met  */
	}
	
	private Auction getAuctionByDescription(String desc) {
		for (Auction a : allAuctions) {
			if (a.getItem().getDescription() == desc) {
				return a;
			}
		}
		return null;
	}
	
	public void setupAccount() {
	//creates new User
	}
	

	// userIn takes an integer as a max value for a switch for a menu setup.
	// TODO : userIn could accept Q for quit at ANY TIME
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
				browseAuction();
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
	
	
	private void loginDisplay() {
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

	private int readAccount(String username, String password) throws IOException {
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
	
	private void buyerloginDisplay(){
		System.out.println("1. Browse auctions");
		System.out.println("2. View bids");
		System.out.println("3. Place bid");
		// placebid takes you to browse -> select auction to place bid on
		userIn(3);
	}
	
	private void sellerloginDisplay() {
		System.out.println("1. Start new auction");
		System.out.println("2. View current auctions");
		System.out.println("3. View pending auctions");
		System.out.println("4. Add new item to stock");
		userIn(4);
	}
	
	// adds a new user, they can type return to cancel account creation.
	private void signupDisplay() {
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
