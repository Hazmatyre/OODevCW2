package auctionKernel;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Auction implements Blockable {
	
	private ArrayDeque<Bid> bids = new ArrayDeque<Bid>(); // NOTE : Use as a stack!!
	private ArrayList<Buyer> buyers = new ArrayList<Buyer>();
	private Seller seller;
	private Item item;
	private double startPrice, reservePrice;
	private LocalDateTime startDate, closeDate;
	private char status;
	//U = under construction, P = pending
	//0 = started, 1 = blocked, 2 = sold, 3 = not sold
	//C = closed
	
	DecimalFormat df = new DecimalFormat("#.00");
	
	public Auction(Seller seller, Item item, double startPrice, double reservePrice, 
			LocalDateTime startDate, LocalDateTime closeDate, char status){
		this.seller = seller;
		this.item = item;
		this.startPrice = startPrice;
		this.reservePrice = reservePrice;
		this.startDate = startDate;
		this.closeDate = closeDate;
		this.status = status;
	}
	
	// Note : Constructor overload to handle dates as strings just in case.
	public Auction(Seller seller, String itemDescription, double startPrice, double reservePrice,
			String startDate, String closeDate, char status) {
		this.seller = seller;
		this.item = new Item(itemDescription);
		this.startPrice = startPrice;
		this.reservePrice = reservePrice;
		this.startDate = LocalDateTime.parse(startDate);
		this.closeDate = LocalDateTime.parse(closeDate);
		this.status = status;
	}
	
	public void placeBid(double amount, Buyer who) {
		bids.push(new Bid(amount, who));
	}
	public void verify() {
		this.setStatus('0');
	}
	public void close() {
		this.setStatus('C');
	}
	@Override
	public boolean isBlocked() {
		if (this.getStatus() == '1')
			return true;
		else
			return false;
	}
	@Override
	public void setBlocked() {
		if (this.getStatus() == '0') {
			this.setStatus('1');
		}
		else {
			statusPrimer();
		}	
	}
	public void setUnblocked() {
		if (this.getStatus() == '1') {
			this.setStatus('0');
		}
		else {
			statusPrimer();
		}
	}
	public double getCurrentBid() {
		return bids.peek().getAmount();
	}
	public void statusPrimer() {
		System.out.println("Cannot block/unblock auction: ");
		switch (this.status) {
			case 'U':System.out.print("This auction's currently under construction.");
			case 'P':System.out.print("This auction's currently pending.");
			case 'C':System.out.print("This auction's already closed.");
		}
	}
	
	
	//Sets & Gets
	public Seller getSeller(){
		return this.seller;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Item getItem() {
		return this.item;
	}
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
	public LocalDateTime getStartDate() {
		return this.startDate;
	}
	public LocalDateTime getCloseDate() {
		return this.closeDate;
	}
	public char getStatus() {
		return this.status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public boolean getreserveMet() {
		if (!bids.isEmpty() && this.reservePrice < bids.peek().getAmount()) {
			return true;
		}
		return false;
	}

	@Override
	public String toString(){
		return this.getItem().getDescription() + " " +  this.getSeller().getUsername() + " " + df.format(this.getStartPrice()) + " " 
				+ df.format(this.getReservePrice()) + " " + this.getStartDate() + " " 
				+ this.getCloseDate() + " " + this.getStatus();
	}	
	
}
