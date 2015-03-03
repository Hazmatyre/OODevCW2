package auctionKernel;
import java.util.InputMismatchException;
import auctionKernel.*;
import java.util.*;

public class AuctionSys {
	List<Auction> auctions = new LinkedList<Auction>();
	List<User> users = new ArrayList<User>(); 
	
	Scanner keyIn = new Scanner(System.in);
	
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
	public int userIn(int max){
		int switcher = 0;
		String input;
		while (switcher < 1 || switcher > max){
			input = keyIn.next(); 
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
		keyIn.close();
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
		System.out.println("Auction login:");
		System.out.println("1. Buyer");
		System.out.println("2. Seller");
		userIn(2);
	}
	public void buyerloginDisplay(){
		System.out.println("1. Browse auctions");
		System.out.println("2. View bids");
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
		// buyer
		case 1: u = new Buyer(crUsername, crPassword);
					// write buyer to account file (0, username, password)
					//buyerloginDisplay();
		// seller	
		case 2: u = new Seller(crUsername, crPassword);
					// write seller to account file (1, username, password)
					//sellerloginDisplay()
		}
	}	
}
