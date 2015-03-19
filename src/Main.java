import java.time.LocalDateTime;

import auctionKernel.*;

public class Main {

	public static void main(String[] args) {

		AuctionSys menu = new AuctionSys();
		
		menu.addSeller("mramazon","123");
		menu.addSeller("ebay","123");
		menu.addSeller("kyle","123");
		menu.addSeller("dennis","123");
		menu.addSeller("conrad","123");
		menu.addSeller("squall","123");
		menu.addSeller("quistis","123");
		menu.addSeller("seifer","123");
		menu.addBuyer("matt","123");
		menu.addBuyer("alex","123");
		menu.addBuyer("haziq","123");

		menu.getSellerByUsername("mramazon").addItem("Bike");
		menu.getSellerByUsername("mramazon").addItem("Car");
		menu.getSellerByUsername("mramazon").addItem("Boat");
		menu.getSellerByUsername("ebay").addItem("Truck");
		menu.getSellerByUsername("kyle").addItem("Plane");
		menu.getSellerByUsername("dennis").addItem("Tank");
		menu.getSellerByUsername("conrad").addItem("GPU");
		menu.getSellerByUsername("squall").addItem("Pistol");
		menu.getSellerByUsername("squall").addItem("PC");
		
		//menu.getSellerByUsername("kyle").setBlocked();
				
		menu.placeAuction(menu.getSellerByUsername("mramazon"), 
				menu.getSellerByUsername("mramazon").getItem("Bike"), 
				100.00, 150.00, LocalDateTime.now().minusSeconds(5), 
				LocalDateTime.now(), '0');
		
		menu.placeAuction(menu.getSellerByUsername("mramazon"), 
				menu.getSellerByUsername("mramazon").getItem("Car"), 
				200.00, 250.00, LocalDateTime.now(), 
				LocalDateTime.now().plusSeconds(10), '0');
		
		menu.placeAuction(menu.getSellerByUsername("mramazon"), 
				menu.getSellerByUsername("mramazon").getItem("Boat"), 
				300.00, 350.00, LocalDateTime.now(), 
				LocalDateTime.now().plusSeconds(15), '2');
		
		menu.placeAuction(menu.getSellerByUsername("ebay"), 
				menu.getSellerByUsername("ebay").getItem("Truck"), 
				400.00, 450.00, LocalDateTime.now(), 
				LocalDateTime.now().plusSeconds(20), '0');
		
		menu.placeAuction(menu.getSellerByUsername("kyle"), 
				menu.getSellerByUsername("kyle").getItem("Plane"), 
				500.00, 550.00, LocalDateTime.now(), 
				LocalDateTime.now().plusSeconds(25), 'P');
		
		menu.placeAuction(menu.getSellerByUsername("dennis"), 
				menu.getSellerByUsername("dennis").getItem("Tank"), 
				600.00, 650.00, LocalDateTime.now(), 
				LocalDateTime.now().plusSeconds(30), 'C');
		
		menu.placeAuction(menu.getSellerByUsername("conrad"), 
				menu.getSellerByUsername("conrad").getItem("GPU"), 
				700.00, 750.00, LocalDateTime.now(), 
				LocalDateTime.now().plusSeconds(35), '0');
		
		menu.placeAuction(menu.getSellerByUsername("squall"), 
				menu.getSellerByUsername("squall").getItem("Pistol"), 
				800.00, 850.00, LocalDateTime.now(), 
				LocalDateTime.now().plusSeconds(40), '0');
		
		menu.placeAuction(menu.getSellerByUsername("squall"), 
				menu.getSellerByUsername("squall").getItem("PC"), 
				800.00, 850.00, LocalDateTime.now(), 
				LocalDateTime.now().plusSeconds(5), '0');
		
		menu.getAuctionByDescription("PC").placeBid(true, menu.getBuyerByUsername("matt"));
		
		try {
			AuctionCheck R1 = new AuctionCheck("Thread 1", menu);
			R1.start();
		} catch (InterruptedException e) { e.printStackTrace(); }
	
		menu.startDisplay();
		System.exit(0);
		
	}

}
