package auctionKernel;

import java.time.LocalDateTime;

public class Bid {
	private double amount;
	private Buyer who;
	private LocalDateTime when;
	
	public Bid(double amount, Buyer who) {
		this.amount = amount;
		this.who = who;
		when = LocalDateTime.now();
	}
	
	//Sets & Gets
	// NOTE : No sets because bids should never ever be modified.
	public double getAmount() {
		return this.amount;
	}
	public Buyer getWho() {
		return this.who;
	}
	public LocalDateTime getWhen() {
		return this.when;
	}	
}
