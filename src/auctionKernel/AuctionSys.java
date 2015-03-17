package auctionKernel;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AuctionSys
{

	//USED FOR IMPORTING TEST DATA ##IGNORE
	Scanner s;
	PrintWriter p; // CHANGE TO BUFFERED WRITER AT SOME POINT
	Scanner keyIn = new Scanner(System.in);
	private boolean loggedIn = false;
	
	public List<Auction> allAuctions = new LinkedList<Auction>();
	List<User> users = new ArrayList<User>(); 
	int lineCount = 0;
	

	public void placeAuction(Seller seller, Item item, double startPrice, double reservePrice, 
			LocalDateTime startDate, LocalDateTime endDate, char status) {
		allAuctions.add(new Auction (seller,item,startPrice,reservePrice,startDate,endDate, status)); 
	}	

	public void browseAuction() {
		//Need to check the auction status before displaying
		int i = 1;
		for(Auction a : allAuctions) {
			String reserve = "Reserve not met";
			if (a.getreserveMet()){
				reserve = "Reserve met";
			}
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			//LocalDateTime dateTime = a.getCloseDate().format("yyyy-MM-dd HH:mm");
			//String remaining = 
			if (a.getCloseDate().isAfter(LocalDateTime.now())) {
				System.out.printf(i + ". " +a.getItem().getDescription() + " - £" + a.getCurrentBid() + 
						" Ends: " + a.getCloseDate() +" " + reserve + "\n"); // Browse format for when the proper auction object/list is done
				i++;
			}
		}
		/* Browse format: 
		 * TV SET - £100 - Ends: 30/03/2015, reserve not met  */
	}
	public int activeAuctionCount() {
		int active = 0;
		for (Auction a : allAuctions) {
			active++;
		}
		return active;
	}
	

	
	
	
	/*	Seller's class responsibility to hold/fetch items, 
	 *  but unable to find way to return multiple objects
	 *  inside seller class to not break encapsulation.
	 */
	public void browseItems(Seller seller) {
		System.out.println("You currently have the following items:");
		for(Item i : seller.getItems()) {
			if (i.isSold() == false) {
				System.out.println(i.getDescription());
			}
		}
	}
	
	public Auction getAuctionByDescription(String desc) {
		for (Auction a : allAuctions) {
			if (a.getItem().getDescription() == desc) {
				return a;
			}
		}
		return null;
	}
	
	public Seller getSellerByUsername(String uname) {
		for(User x : users) {
			if (x.getUsername() == uname) {
				return (Seller) x;
			}
		}
		return null;
	}
	
	public void addBuyer(String user, String pass) {
		users.add(new Buyer(user, pass));
	}
	
	public void addSeller(String user, String pass) {
		users.add(new Seller(user, pass));
	}
	



	// List auctions should display all auctions, at any status
	public void listAuctions() {
		
	}
	
	
	// userIn takes an integer as a max value for a switch for a menu setup.
		// TODO : userIn could accept Q for quit at ANY TIME
		public int userIn(int max){
			int switcher = 0;
			String input;
			if (loggedIn){
				max += 1;
				System.out.println(max + ". Logout");
			}
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
			if(switcher == max){
				loggedIn = false;
			}
			return switcher;
	}

	// Price in waits for the next user input, then sticks in a loop until a valid number in price format is entered.
		public double priceIn() {
			String strIn;
			DecimalFormat priceFormat = new DecimalFormat("##.00");
			double price;
			boolean pricePicked = false;
			while (pricePicked == false) {
				strIn = keyIn.nextLine();
				try{
				price = Double.parseDouble(strIn);
				strIn = priceFormat.format(price);
				} catch (NumberFormatException e) {
					System.out.println("Please retry");
					continue;
					}
				price = Double.parseDouble(strIn);
				System.out.println("Is £" + price + " correct?");
				System.out.println("1. Yes");
				System.out.println("2. No, retry");
				if (userIn(2) == 1) {
					return price;
				}
				System.out.println("Enter amount: £(xx.xx)");
				continue;
			} 
			// Not sure how to return as an error instead of 0?
			// The method should never leave the loop without a price anyway
			return 0;
		}

	//First menu display call, would be run at the start of the program.
	public void startDisplay(){
		while(true) {
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

				break;
		}

		} }
	
	// TODO : Seller never detected!!!!!!!!!!
	private boolean readAccount(String username, String password) throws Exception {
		for (User a : users){
			if (username.equals(a.getUsername()) && a.checkPassword(password)) {
				System.out.println("Login Successful");
				if (a.getClass().isInstance(Seller.class)){
					return false;
				} else {
					return true;
				}
			}
		} 
		throw new ExCatcher("AccountNotFound");
	}

	// TODO add which user made the auction
	private void createAuctionDisplay() {
		String itemName, status, description;
		double startPrice, reservePrice;
		LocalDateTime startDate, endDate;
		boolean pricePicked = false;
		
		String tempInput;
	
		// Add new item name, price, reserve, close date > 7 days. Set as pending, then verify to activate
		System.out.println("Create an Auction:");
		System.out.println("What is the name of the item?");
		itemName = keyIn.nextLine();
		System.out.println("Give a short description of the item:");
		itemName = keyIn.nextLine();
		System.out.println("Item start price £(xx.xx):");
		startPrice = priceIn();
		System.out.println("Set item reserve price:");
		reservePrice = priceIn();
		
		System.out.println("Enter closing date (DDMMYYYY)");
		
		System.out.println("Auction pending, activate now?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		userIn(2);
		
		//placeAuction(username,itemName,startPrice,reservePrice,startDate,endDate,status,description);
	}

	private void loginDisplay() {
		String username,password;
		System.out.println("Enter username:");
		username = keyIn.nextLine();
		System.out.println("Enter password:");
		password = keyIn.nextLine();
		try {
			if (readAccount(username, password) == true){
				loggedIn = true;
				buyerloginDisplay();
			} else {
				loggedIn = true;
				sellerloginDisplay();
			}
		} catch (Exception e) {
			System.out.println("Username or password incorrect \n");
		}		
	}

	private void buyerloginDisplay(){
		int choice = 0;
		while(loggedIn) {
			System.out.println("Buyer Account Menu:");
			System.out.println("1. Browse auctions");
			System.out.println("2. View bids");
			System.out.println("3. Place bid");
			choice = userIn(3);
		
			// placebid takes you to browse -> select auction to place bid on
			switch(choice){
				case 1: browseAuction();
						break;
				case 2: 
						break;
				case 3: // maybe print browse auctions, then ask for a int return for the auction number?
						browseAuction();
						System.out.println("Enter auction number to bid on:");
						userIn(activeAuctionCount());
						break;
			}
		}
	}
	
	private void sellerloginDisplay() {
		int choice = 0;
		while (loggedIn){
			System.out.println("Seller Account Menu:");
			System.out.println("1. Start new auction");
			System.out.println("2. View my current auctions");
			System.out.println("3. View pending auctions");
			System.out.println("4. Add new item to stock");
			choice = userIn(4);
		
			switch(choice){
				case 1: createAuctionDisplay();
						break;
				case 2: 
						break;
				case 3: 
						break;
				case 4: 
						break;
			}
		}
	}
	
	// adds a new user, they cant type return to cancel account creation.
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
		
		switch(choice) {
		case 1: addBuyer(crUsername, crPassword);
				System.out.println("Buyer Account Created");
				buyerloginDisplay();	
				break;
		case 2: addSeller(crUsername, crPassword);
				System.out.println("Seller Account Created");
				sellerloginDisplay();
				break;
		}
	}	
}
