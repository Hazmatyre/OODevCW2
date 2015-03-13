package auctionKernel;

import java.time.LocalDateTime;

// ToDo : Interface for LocalDateTime
public class Bid {
	private double amount;
	private Buyer who;
	private LocalDateTime when;
	
	//Sets & Gets
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getAmount() {
		return this.amount;
	}
	public void setWho(Buyer who) {
		this.who = who;
	}
	public Buyer getWho() {
		return this.who;
	}
	public void setWhen(LocalDateTime when) {
		this.when = when;
	}
	public LocalDateTime getWhen() {
		return this.when;
	}	
}
