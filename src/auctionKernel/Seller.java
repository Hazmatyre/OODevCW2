package auctionKernel;

import java.util.*;

@SuppressWarnings("serial")
public class Seller extends User implements Blockable {
	
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Auction> auctions = new ArrayList<Auction>();
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
	public void addItem(String description) {
			items.add(new Item(description));
	}
	public void addAuction(Auction a) {
		this.getAuctions().add(a);
	}

	public Item getItem(String description) {
		for(Item i : items) {
			if (i.getDescription() == description) {
				return i;
			}
		}
		return null;
	}
	public ArrayList<Auction> getAuctions() {
		return this.auctions;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
}
