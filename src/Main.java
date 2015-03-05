import java.io.FileNotFoundException;

import auctionKernel.Auction;
import auctionKernel.AuctionSys;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AuctionSys as = new AuctionSys();
		as.insertTestData();
		try {
			as.saveAuction();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}