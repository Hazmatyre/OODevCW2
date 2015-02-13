package auctionKernel;

public class Seller extends User {
	private boolean blocked = false;
	
	public Seller(String user, String pass) {
		super(user, pass);
	}
	
	public boolean isBlocked() {
		return this.blocked;
	}
	
	public void setBlocked(boolean arg) {
		this.blocked = arg;
	}
}
