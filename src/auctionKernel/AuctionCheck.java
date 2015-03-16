package auctionKernel;
import java.time.LocalDateTime;

public class AuctionCheck implements Runnable {
	private Thread t;
	public String name;
	private AuctionSys a;
	
	public AuctionCheck(String name, AuctionSys a){
		this.name = name;
		this.a = a;
	}

	public void checkAuctions(){ //Rename?
		for(Auction x : a.allAuctions){
			if(x.getCloseDate().isBefore(LocalDateTime.now())){
				if(x.getStatus() == '0' && x.getreserveMet() == true) {
					x.setStatus('2');
					x.setStatus('C');
					System.out.println(x.getItem().getDescription() + " SOLD"); // Working
				} else if ( x.getStatus() == '0' && x.getreserveMet() == false){
					x.setStatus('3');
					x.setStatus('C');
					System.out.println(x.getItem().getDescription() + " NOT SOLD"); // Working
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
				Thread.sleep(5000);
				checkAuctions();
		} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}
