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
		System.out.println(menu
				.getSellerByUsername("mramazon")
				.getItem("Bike")
				.getDescription());
		//menu.startDisplay();
		
		
		
	}

	
	
	
	
}
