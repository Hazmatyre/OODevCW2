package auctionKernel;

public class Auction implements Blockable {
	//Figure out what data structure for bids
	private String username;
	private double startPrice, reservePrice;
	private String startDate, closeDate; //CHANGED TO STRING ---- CHANGE BACK BEFORE SUBMITTING
	private String status;
	//U = under construction, P = pending
	//0 = started, 1 = blocked, 2 = sold, 3 = not sold
	//C = closed
	
	public Auction(String username, Double startPrice, Double reservePrice, String startDate, String closeDate, String status){ //Constructor
		this.username = username;
		this.startPrice = startPrice;
		this.reservePrice = reservePrice;
		this.startDate = startDate;
		this.closeDate = closeDate;
		this.status = status;
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
	public void setReservePrice(double price) {
		this.reservePrice = price;
	}
	public void setCloseDate(String date) {
		this.closeDate = date;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getStartPrice() {
		return this.startPrice;
	}
	public double getReservePrice() {
		return this.reservePrice;
	}
	public String getCloseDate() {
		return this.closeDate; 
	}
	public String getStartDate() {
		return this.startDate;
	}
	public String getStatus() {
		return this.status;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	@Override
	public String toString(){
		return this.getUsername() + " " + this.getStartPrice() + " " + this.getReservePrice() + " " + this.getStartDate() + " " + this.getCloseDate() + " " + this.getStatus();
	}	
	
}
