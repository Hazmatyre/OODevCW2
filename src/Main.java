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
		menu.getSellerByUsername("ebay").addItem("Truck");
		menu.getSellerByUsername("kyle").addItem("Plane");
		
		//menu.placeAuction(seller, item, startPrice, reservePrice, startDate, endDate, status);
		
		menu.placeAuction(menu.getSellerByUsername("mramazon"), 
				menu.getSellerByUsername("mramazon").getItem("Bike"), 
				100.00, 150.00, LocalDateTime.now(), 
				LocalDateTime.now().plusSeconds(5), '0');
		
		menu.placeAuction(menu.getSellerByUsername("mramazon"), 
				menu.getSellerByUsername("mramazon").getItem("Car"), 
				200.00, 250.00, LocalDateTime.now(), 
				LocalDateTime.now().plusSeconds(10), '1');
		
		menu.placeAuction(menu.getSellerByUsername("mramazon"), 
				menu.getSellerByUsername("mramazon").getItem("Boat"), 
				300.00, 350.00, LocalDateTime.now(), 
				LocalDateTime.now().plusSeconds(15), '2');
		
		menu.placeAuction(menu.getSellerByUsername("ebay"), 
				menu.getSellerByUsername("ebay").getItem("Truck"), 
				400.00, 450.00, LocalDateTime.now(), 
				LocalDateTime.now().plusSeconds(20), '3');
		
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
		
		/*
		System.out.println(menu
			.getSellerByUsername("mramazon")
			.getItem("Car")
			.getDescription());
		
		*/
		menu.startDisplay();
		//menu.placeAuction("user1", "TV", 100.00, 150.00, 04/03/2015, 10/03/2015, "0", "POS");

		
	}

	
	
	
	
}
