package auctionKernel;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.*;

public class AuctionSys {
	List<Auction> auctions = new LinkedList<Auction>();
	List<User> users = new ArrayList<User>(); 
	
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
		Scanner keyIn = new Scanner(System.in);
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
		System.out.println("Alex's Auctions!");
		System.out.println("1. Browse current auctions");
		System.out.println("2. Login");
		System.out.println("3. Sign up");
		int choice = userIn(3);
		
		Switch (choice) {
			case 1: 
				break;
			case 2: 
				break;
			case 3:
				break;
		}
			
			
		}
	}

}
