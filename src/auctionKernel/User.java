package auctionKernel;

public abstract class User {
	protected String username, password;
	
	public User(String user, String pass) {
		setUsername(user);
		setPassword(pass);
	}
	
	
	public void createAccount (String crUsername, String crPassword) {
		
	}
	
	// Keep check password in for now, might add more advanced password utility.
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
