package auctionKernel;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Auction implements Blockable {
	
	private ArrayDeque<Bid> bids = new ArrayDeque<Bid>(); // NOTE : Use as a stack!!
	private Seller seller;
	private Item item;
	private double startPrice, reservePrice, lowerBidInc, upperBidInc;
	private LocalDateTime startDate, closeDate;
	private char status;
	//U = under construction, P = pending
	//0 = started, 1 = blocked, 2 = sold, 3 = not sold
	//C = closed
	
	private DecimalFormat df = new DecimalFormat("#.00");
	
	public Auction(Seller seller, Item item, double startPrice, double reservePrice, 
			LocalDateTime startDate, LocalDateTime closeDate, char status){
		this.seller = seller;
		this.item = item;
		this.startPrice = startPrice;
		this.reservePrice = reservePrice;
		this.startDate = startDate;
		this.closeDate = closeDate;
		this.status = status;
		
		this.lowerBidInc = (startPrice/10);
		this.upperBidInc = (startPrice/5);
		
	}
	
	/**placeBid accepts a boolean value of true to bid the upper increment or vice versa.
	 *  
	 * @param incUpOrLow
	 * @param who
	 * @throws ExCatcher
	 */
	public void placeBid(boolean incUpOrLow, Buyer who) {
		if (incUpOrLow = true) {
			bids.push(new Bid((getUpperBidInc() + getCurrentBid()), who));
		}
		else
			bids.push(new Bid((getLowerBidInc() + getCurrentBid()), who));
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
	public void statusPrimer() {
		System.out.println("Cannot block/unblock auction: ");
		switch (this.status) {
			case 'U':System.out.print("This auction's currently under construction.");
			case 'P':System.out.print("This auction's currently pending.");
			case 'C':System.out.print("This auction's already closed.");
		}
	}
	//Sets & Gets
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
	public void setStatus(char status) {
		this.status = status;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public void setReservePrice(double price) {
		this.reservePrice = price;
	}

	public void setStartPrice(double price) {
		this.startPrice = price;
	}	
	public double getUpperBidInc() {
		return this.upperBidInc;
	}
	
	public double getLowerBidInc() {
		return this.lowerBidInc;
	}

	public Bid getCurrentBidObject() {
		return bids.peek();
	}

	public Seller getSeller(){
		return this.seller;
	}

	public Item getItem() {
		return this.item;
	}
	public double getStartPrice() {
		return this.startPrice;
	}
	public double getCurrentBid() {
		if (bids.isEmpty()){
			return getStartPrice();
		}
		return bids.peek().getAmount();
	}

	public double getReservePrice() {
		return this.reservePrice;
	}
	public LocalDateTime getStartDate() {
		return this.startDate;
	}
	public LocalDateTime getCloseDate() {
		closeDate.format(DateTimeFormatter.ofPattern("dd::MMM::ss"));
		return closeDate;
	}
	public char getStatus() {
		return this.status;
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
