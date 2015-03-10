import java.io.FileNotFoundException;
import auctionKernel.*;

public class Main {

	public static void main(String[] args) {
		/*
		AuctionSys as = new AuctionSys();
		as.insertTestData();
		try {
			as.saveAuction();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		*/
		
		AuctionSys menu = new AuctionSys();
		menu.startDisplay();
		
	}

}
