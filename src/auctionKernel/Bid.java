package auctionKernel;

import java.time.LocalDate;

public class Bid {
	private double amount;
	private Buyer who;
	private LocalDate when;
	
	//Sets & Gets
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setWho(Buyer who) {
		this.who = who;
	}
	public void setWhen(LocalDate when) {
		this.when = when;
	}
	public double getAmount() {
		return this.amount;
	}
	public Buyer getWho() {
		return this.who;
	}
	public LocalDate getWhen() {
		return this.when;
	}
	
}
