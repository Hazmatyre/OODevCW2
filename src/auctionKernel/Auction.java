package auctionKernel;

import java.time.LocalDate;
import java.util.*;

public class Auction implements Blockable {
	
	private ArrayDeque<Bid> bids = new ArrayDeque<Bid>(); //Use as a stack!!
	private ArrayList<Buyer> buyers = new ArrayList<Buyer>();
	private Seller seller;
	private Item item;
	
	private double startPrice, reservePrice;
	private LocalDate startDate, closeDate;
	private char status;
	//U = under construction, P = pending
	//0 = started, 1 = blocked, 2 = sold, 3 = not sold
	//C = closed
	
	public void placeBid() {
		
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
	
	//Status primer
	private void statusPrimer() {
		System.out.println("Cannot block/unblock auction: ");
		switch (this.status) {
		case 'U':System.out.print("This auction's currently under construction.");
		case 'P':System.out.print("This auction's currently pending.");
		case 'C':System.out.print("This auction's already closed.");
		}
	}
	
	//Set and Gets
	public void setStartPrice(double price) {
		this.startPrice = price;
	}
	public void setReservePrice(double price) {
		this.reservePrice = price;
	}
	public void setCloseDate(LocalDate date) {
		this.closeDate = date;
	}
	public void setStartDate(LocalDate date) {
		this.closeDate = date;
	}
	public void setCloseDate(String date) {
		this.setStartDate(LocalDate.parse(date));
	}
	public void setStartDate(String date) {
		this.setCloseDate(LocalDate.parse(date));
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public double getStartPrice() {
		return this.startPrice;
	}
	public double getReservePrice() {
		return this.reservePrice;
	}
	public LocalDate getCloseDate() {
		return this.closeDate;
	}
	public LocalDate getStartDate() {
		return this.closeDate;
	}
	public char getStatus() {
		return this.status;
	}
	public Seller getSeller() {
		return this.seller;
	}
	
	
	
}
