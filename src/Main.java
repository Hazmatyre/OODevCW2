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
		//menu.startDisplay();
		menu.insertTestData();
		menu.browseAuction();
		try {
			menu.placeAuction();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
