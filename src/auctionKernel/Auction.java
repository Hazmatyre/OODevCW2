package auctionKernel;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Auction implements Blockable {
	
	private ArrayDeque<Bid> bids = new ArrayDeque<Bid>(); //Use as a stack!!
	private ArrayList<Buyer> buyers = new ArrayList<Buyer>();
	private Seller seller;
	private String itemName, description;
	private Item item;
	DecimalFormat df = new DecimalFormat("#.00"); 
	
	private double startPrice, reservePrice;
	private LocalDateTime startDate, closeDate; //CHANGED TO STRING ---- CHANGE BACK BEFORE SUBMITTING
	private String status;
	//U = under construction, P = pending
	//0 = started, 1 = blocked, 2 = sold, 3 = not sold
	//C = closed
	
	public Auction(Seller seller, String itemName, Double startPrice, Double reservePrice, LocalDateTime startDate, LocalDateTime closeDate, String status, String description){ //Constructor
		this.seller = seller;
		this.itemName = itemName;
		this.startPrice = startPrice;
		this.reservePrice = reservePrice;
		this.startDate = startDate;
		this.closeDate = closeDate;
		this.status = status;
		this.description = description;
	}
	
	public void placeBid() {
		
	}
	
	public void verify() {
		this.setStatus("0");
	}
	
	public void close() {
		this.setStatus("C");
	}
	
	@Override
	public boolean isBlocked() {
		if (this.getStatus() == "1")
			return true;
		else
			return false;
	}
	
	@Override
	public void setBlocked() {
		if (this.getStatus() == "0") {
			this.setStatus("1");
		}
		else {
			statusPrimer();
		}	
	}
	public void setUnblocked() {
		if (this.getStatus() == "1") {
			this.setStatus("0");
		}
		else {
			statusPrimer();
		}
	}
	public LocalDateTime stringToDate(String date) {
		return LocalDateTime.parse(date);
	}
	
	//Status primer
	private void statusPrimer() {
		System.out.println("Cannot block/unblock auction: ");
		switch (this.status) {
		case "U":System.out.print("This auction's currently under construction.");
		case "P":System.out.print("This auction's currently pending.");
		case "C":System.out.print("This auction's already closed.");
		}
	}
	
	//Set and Gets
	public void setStartPrice(double price) {
		this.startPrice = price;
	}
	public double getStartPrice() {
		return this.startPrice;
	}
	public void setReservePrice(double price) {
		this.reservePrice = price;
	}
	public double getReservePrice() {
		return this.reservePrice;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getStartDate() {
		return this.startDate;
	}
	
	public LocalDateTime getCloseDate() {
		return this.closeDate;
	}
	public String getStatus() {
		return this.status;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Item getItem() {
		return this.item;
	}
	public Seller getSeller(){
		return this.seller;
	}

	
	@Override
	public String toString(){
		return this.getSeller() + " " + df.format(this.getStartPrice()) + " " + df.format(this.getReservePrice()) + " " + this.getStartDate() + " " + this.getCloseDate() + " " + this.getStatus();
	}	
	
}
