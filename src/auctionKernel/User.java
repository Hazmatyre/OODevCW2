package auctionKernel;

@SuppressWarnings("serial")
public abstract class User extends Exception {
	protected String username, password;
	
	public User(String user, String pass) {
		setUsername(user);
		setPassword(pass);
	}
	public boolean checkPassword(String password) {
		if (password.equals(this.password)){
			return true;
		}
		return false;
	}
	private void setUsername(String user) {
		this.username = user;
	}
	private void setPassword(String pass) {
		this.password = pass;
	}

	public String getUsername() {
		return this.username;
	}
	
}
