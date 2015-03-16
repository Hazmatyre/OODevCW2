package auctionKernel;

import java.time.LocalDateTime;

public class AuctionCheck implements Runnable {
	private Thread t;
	public String name;
	private AuctionSys a;
	private Auction auction;
	
	public AuctionCheck(String name, AuctionSys a){
		this.name = name;
		this.a = a;
		System.out.println("Creating" + name);
	}

	public void checkAuctions(){ //Rename?
		for(int i = 0; i < a.allAuctions.size()-1; i++){
				 if(auction.getCloseDate().isAfter(LocalDateTime.now())){auction.setStatus('C');}
			System.out.println(a.allAuctions.get(i));
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
		System.out.println("Running" + name);
		try {
				//System.out.println("Thread: " + name);
				checkAuctions();
				Thread.sleep(50);
		} catch (InterruptedException e) { e.printStackTrace(); }
	}
}
