package auctionKernel;

@SuppressWarnings("serial")
public class ExCatcher extends Exception {
	public ExCatcher (String errorMsg) {
		super (errorMsg);
	}
}
