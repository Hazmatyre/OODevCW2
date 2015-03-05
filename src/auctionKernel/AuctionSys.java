package auctionKernel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class AuctionSys {
	//Figure out what data structure to store auction and user
	
	//USED FOR IMPORTING TEST DATA ##IGNORE
	Scanner s;
	ArrayList<Auction> allAuctions = new ArrayList<Auction>();
	int lineCount = 0;
	
	public void insertTestData(){
		try {
			s = new Scanner(new BufferedReader(new FileReader("auctions.txt")));
			while(s.hasNextLine()) { allAuctions.add(new Auction(s.next(), s.nextDouble(), s.nextDouble(), s.next(), s.next(), s.next())); lineCount++;}
			
			for(int i = 0; i < lineCount; i++) {
			System.out.println(allAuctions.get(i).toString() + "\n");
			}
		} catch (FileNotFoundException e) { e.printStackTrace(); }	
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
	
}
