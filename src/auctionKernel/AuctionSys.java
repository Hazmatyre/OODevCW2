package auctionKernel;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class AuctionSys {
	
	Scanner keyIn = new Scanner(System.in);
	private final DateTimeFormatter formatDT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	private boolean loggedIn = false;
	private User currentUser; 
	public List<Auction> allAuctions = new LinkedList<Auction>();
	List<User> users = new ArrayList<User>(); 	

	// browseAuction prints all auctions which are currently labelled as "started"
	public void browseAuction() {
		int aucNumber = 1;
		for(Auction a : allAuctions) {
			String reserve = "Reserve not met";
			if (a.getreserveMet()){
				reserve = "Reserve met";
			}
			if (a.getCloseDate().isAfter(LocalDateTime.now()) && a.getStatus() == '0') {
				System.out.printf(aucNumber + ". " +a.getItem().getDescription() + " - £" + a.getCurrentBid() + 
						" Ends: " + formatDT.format(a.getCloseDate()) +" " + reserve + "\n"); // Browse format for when the proper auction object/list is done
				aucNumber++;
			}
		}
	}
	
	public int activeAuctionCount() {
		int active = 0;
		for (Auction a : allAuctions) {
			if (a.getCloseDate().isAfter(LocalDateTime.now())) {
				active++;
			}
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
	
	// priceIn waits for the next user input, then sticks in a loop until a valid number in price format is entered.
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
		return 0;
	}

	/** userIn takes the amount of menu options and returns the selected number. If the user is logged in
	 * the method will add a last option to logout, which quits to 
	 * @param max an integer of value to the amount of menu options.
	 * @return int from 1->max 
	 */
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
			currentUser = null;
			loggedIn = false;
		}
		return switcher;
	}
	
	/** readAccount takes the users details, then logs them in as a buyer or seller by returning true or false respectively.
	 * @param username
	 * @param password
	 * @return boolean
	 * @throws ExCatcher This is a custom exception for when an account is not found
	 */
	private boolean readAccount(String username, String password) throws ExCatcher {
		for (User a : users){
			if (username.equals(a.getUsername()) && a.checkPassword(password)) {
				System.out.println("Login Successful");
				if (a.getClass() == Seller.class){
					return false;
				} else {
					return true;
				}
			}
		} 
		throw new ExCatcher("AccountNotFound");
	}
	// Note: Method below is public for testing purposes to be accessed from main!
	public void placeAuction(Seller seller, Item item, double startPrice, double reservePrice, 
			LocalDateTime startDate, LocalDateTime endDate, char status) {
		Auction a = new Auction(seller,item,startPrice,reservePrice,startDate,endDate, status);
		allAuctions.add(a); 
		seller.addAuction(a);
	}
	
	// Access to the start of the system from startDisplay.
	public void startDisplay(){
		while(true) {
		System.out.println("Alex's Amazing Auctions!");
		System.out.println("1. Browse current auctions");
		System.out.println("2. Login");
		System.out.println("3. Sign up");
		System.out.println("4. Exit");
		int choice = userIn(4);
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
		}
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
				currentUser = getBuyerByUsername(username);
				buyerloginDisplay();	
			} else {
				loggedIn = true;
				currentUser = getSellerByUsername(username);
				if (!getSellerByUsername(username).isBlocked()) {
					sellerloginDisplay();
				} else {
					System.out.println("Your account is currently blocked.");
				}
			}
		} catch (ExCatcher E) {
			System.out.println("Username or password incorrect \n");
		}		
	}

	private void buyerloginDisplay(){
		Buyer currentBuyer = (Buyer)currentUser;
		int choice = 0;
		while(loggedIn) {
			System.out.println("Buyer Account Menu:");
			System.out.println("1. Browse auctions");
			System.out.println("2. Place bid");
			choice = userIn(2);
		
			// placebid takes you to browse -> select auction to place bid on
			switch(choice){
			case 1: browseAuction();
					break;
			case 2: // maybe print browse auctions, then ask for a int return for the auction number?
					browseAuction();
					System.out.println("Enter item description:");
					Auction auction = getAuctionByDescription(keyIn.nextLine());
					try {
					System.out.println(auction.getItem().getDescription() + ", Current Bid £" + auction.getCurrentBid());
					} catch (NullPointerException e) {
						System.out.println("Item not found, please try again!");
						break;
					}
					System.out.println("Enter your bidding price:");
					System.out.println("You can bid: ");
					System.out.println("1. £" + (auction.getUpperBidInc() + auction.getCurrentBid()));
					System.out.println("2. £" + (auction.getLowerBidInc() + auction.getCurrentBid()));
					int bidChoice = userIn(2);
					if (bidChoice == 1) {
						auction.placeBid(true, currentBuyer);	
						System.out.println("Bid Successful");
					} else {
						auction.placeBid(false, currentBuyer);
						System.out.println("Bid Successful");
					}
					break;
			}
		}
	}
	
	private void sellerloginDisplay() {
		Seller currentSeller = (Seller)currentUser;
		int choice = 0;
		if (currentSeller.isBlocked()){
			return;
		}
		while (loggedIn){
			System.out.println("Seller Account Menu:");
			System.out.println("1. Start new auction");
			System.out.println("2. View my current auctions");
			System.out.println("3. Authorise pending auctions");
			System.out.println("4. Add new item to stock");
			choice = userIn(4);
		
			switch(choice){
			case 1: createAuctionDisplay();
					break;
			case 2: System.out.println("Current Active Auctions: ");
					for (Auction a : currentSeller.getAuctions()) {
						if (a.getStatus() == '0') {
							String reserve = "Reserve not met";
							if (a.getreserveMet()){
								reserve = "Reserve met";
							}
							System.out.printf(a.getItem().getDescription() + " - £" + a.getCurrentBid() + 
									" Ends: " + a.getCloseDate() +" " + reserve + "\n");
						}
					}
					break;
			case 3: System.out.println("Pending Auctions:");
					for (Auction a : currentSeller.getAuctions()) {
						if (a.getStatus() == 'P') {
							String reserve = "Reserve not met";
							if (a.getreserveMet()){
								reserve = "Reserve met";
							}
							System.out.printf(a.getItem().getDescription() + " - £" + a.getCurrentBid() + 
									" Ends: " + a.getCloseDate() +" " + reserve + "\n");
						}
					}
					System.out.println("Enter name of the item to begin auction:");
					try {
					getAuctionByDescription(keyIn.nextLine()).setStatus('0');
					} catch (NullPointerException e) {
						System.out.println("Item not found");
						break;
					}
					System.out.println("Auction active!");
					break;
			case 4: itemToStock();
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
	
	private void createAuctionDisplay() {
		Seller currentSeller = (Seller)currentUser;
		String description, dateIn, timeIn, dateTime;
		char status = 'U';
		Item currentItem = null;
		double startPrice = 1, reservePrice = 0;
		LocalDateTime closeDate = null;
		boolean itemPicked = false;
		boolean closePicked = false;
	
		System.out.println("Create an Auction: ");
		if (!currentSeller.getItems().isEmpty()) {
			while(itemPicked == false) {
				System.out.println("Your Items:");
				for (Item i : currentSeller.getItems()) {
					System.out.println(i.getDescription());
				}
				System.out.println("Enter item name");
				description = keyIn.nextLine();
				for (Item i : currentSeller.getItems()) {
					if (i.getDescription().equals(description)) { 
						currentItem = currentSeller.getItem(description);
						System.out.println("Item Selected");
						itemPicked = true;
						break;
					}
				}
			}
		} else {
			System.out.println("Item list empty, go to create items?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			int pick = userIn(2);
			if (pick == 1) {
				itemToStock();
				createAuctionDisplay();
				return;
			} else {
				return;
			} 
		}
		
		while (startPrice > reservePrice) {
			System.out.println("Item start price £(xx.xx):");
			startPrice = priceIn();
			System.out.println("Set item reserve price:");
			reservePrice = priceIn();
			if (reservePrice == 0) {
				break;
			} else if (startPrice > reservePrice) {
				System.out.println("Reserve cannot be less than start price!");
			}
		}
		
		while (closePicked == false) {		
			System.out.println("Enter closing date within 7 days (yyyy-MM-dd)");
			dateIn = keyIn.nextLine();
			System.out.println("Enter auction end time (HH:mm) e.g: 15:30");
			timeIn = keyIn.nextLine();
		
			dateTime = (dateIn + " " + timeIn);
			try {
			closeDate = LocalDateTime.parse(dateTime, formatDT);
				if (closeDate.isBefore(LocalDateTime.now().plusDays(7)) && closeDate.isAfter(LocalDateTime.now())) {
					System.out.println("Date accepted: " + dateTime);
					break;
				}
			} catch (DateTimeParseException e) {
				System.out.println("Please enter a valid time in the formats shown");
				continue;
			}
			System.out.println("Date not within range, please try again!");
		}		
		status = 'P';
		placeAuction(currentSeller,currentItem,startPrice,reservePrice,LocalDateTime.now(),closeDate,status);
	}

	private void itemToStock(){
		String description;
		Seller currentSeller = (Seller)currentUser;
		System.out.println("Enter the name of your item.");
		description = keyIn.nextLine();
		currentSeller.addItem(description);
		System.out.println("Item added to your stock.");
	}
	public void addBuyer(String user, String pass) {
		users.add(new Buyer(user, pass));
	}
	public void addSeller(String user, String pass) {
		users.add(new Seller(user, pass));
	}
	public Buyer getBuyerByUsername(String uname) {
		for(User x : users) {
			if (x.getUsername().equals(uname)) {
				return (Buyer) x;
			}
		}
		return null;
	}
	public Seller getSellerByUsername(String uname) {
		for(User x : users) {
			if (x.getUsername().equals(uname)) {
				return (Seller) x;
			}
		}
		return null;
	}
	public Auction getAuctionByDescription(String desc) {
		for (Auction a : allAuctions) {
			if (a.getItem().getDescription().equals(desc)) {
				return a;
			}
		}
		return null;
	}	
}
