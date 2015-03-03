package auctionKernel;

import java.util.*;

public class Seller extends User implements Blockable {
	
	ArrayList<Item> items = new ArrayList<Item>();
	ArrayList<Auction> auctions = new ArrayList<Auction>();
	private boolean blocked = false;
	private boolean sold = false;
	
	public Seller(String user, String pass) {
		super(user, pass);
	}
	
	@Override
	public boolean isBlocked() {
		return this.blocked;
	}
	
	@Override
	public void setBlocked() {
		this.blocked = true;
	}
	
	public boolean getSold() {
		return this.sold;
	}
	
	public void setSold() {
		this.sold = true;
	}
}
