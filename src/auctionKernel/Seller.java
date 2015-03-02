package auctionKernel;

public class Seller extends User implements Blockable {
	private boolean blocked = false;
	
	public Seller(String user, String pass) {
		super(user, pass);
	}
	
	public boolean isBlocked() {
		return this.blocked;
	}
	
	@Override
	public void setBlocked() {
		this.blocked = true;
	}
}
