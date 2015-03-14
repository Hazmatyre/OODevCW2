package auctionKernel;

public class Item {
	private String description;
	private boolean sold;
	
	public Item(String description) {
		this.description = description;
		this.sold = false;
	}
	
	//Set & Gets
	public void setDescription(String desc) {
		this.description = desc;
	}
	public String getDescription() {
		return this.description;
	}
	public void setSold() {
		this.sold = true;
	}
	public boolean isSold() {
		return this.sold;
	}
}
