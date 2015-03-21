package auctionKernel;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class AuctionCheck implements Runnable {
	private Thread t;
	public String name;
	private AuctionSys a;
	private DecimalFormat priceFormat = new DecimalFormat("#0.00");
	
	public AuctionCheck(String name, AuctionSys a){
		this.name = name;
		this.a = a;
	}
	
	// Periodically loops through every auction, sets finished ones to complete. Then notifies winners.
	public void checkAuctions(){ 
		for(Auction x : a.allAuctions){
			if(x.getCloseDate().isBefore(LocalDateTime.now())){ 
				if(x.getStatus() == '0' && x.getreserveMet() == true) {
					x.setStatus('2');
					x.setStatus('C');
					System.out.println("[ALERT] - " + x.getItem().getDescription() + " SOLD for £" + priceFormat.format(x.getCurrentBid()));
					
					if(a.getCurrentUser() != null){
						if(x.getCurrentBidObject().getWho().getUsername().equals(a.getCurrentUser().getUsername())){ // Checks whether the current user is equal to top bidder
							System.out.println("[ALERT] - " + "Congratulations " + a.getCurrentUser().getUsername() + " You have won item: " + x.getItem().getDescription());
							x.getItem().setSold();
						}
					}
				} else if ( x.getStatus() == '0' && x.getreserveMet() == false){
					x.setStatus('3');
					x.setStatus('C');
					System.out.println("[ALERT] - " + x.getItem().getDescription() + " Auction ended - no sale");
				}
			}
		}
	}

	public void start() throws InterruptedException{
		if(t == null){
			t = new Thread(this, name);
			t.start();
		}
	}
	
	@Override
	public void run() {
		while(true) {
		try {
				Thread.sleep(5000); // Sleep Thread for 5 seconds (Only check every 5 seconds)
				checkAuctions();
		} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}
