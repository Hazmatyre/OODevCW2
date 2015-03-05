package auctionKernel;

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
	//Figure out what data structure to store auction and user
	
	//USED FOR IMPORTING TEST DATA ##IGNORE
	Scanner s;
	PrintWriter p; // CHANGE TO BUFFERED WRITER AT SOME POINT
	ArrayList<Auction> allAuctions = new ArrayList<Auction>();
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
	
}
