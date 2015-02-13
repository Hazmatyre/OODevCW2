package auctionKernel;

public abstract class User {
	protected String username, password;
	
	public User(String user, String pass) {
		setUsername(user);
		setPassword(pass);
	}
	
	public boolean checkPassword() {
		return false;
	}
	
	private void setUsername(String user) {
		this.username = user;
	}
	private void setPassword(String pass) {
		this.password = pass;
	}
	
}
