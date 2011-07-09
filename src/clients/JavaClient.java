package clients;

import services.prices.Price;
import services.prices.PriceFeedEngine;
import services.prices.PriceFeedListener;

public class JavaClient implements PriceFeedListener{
	
	public static void main(String[] args) {
		System.out.println("starting client");
		JavaClient m = new JavaClient();
		m.runClient();
	}


	private void runClient() {
		PriceFeedEngine pe = PriceFeedEngine.getPriceFeedEngine();
		
		pe.addListener(this);
		
		pe.startFeed();
		
	}

	@Override
	public void onPriceUpdate(Price price) {
		System.out.println(price.toString());
		
	}

}
