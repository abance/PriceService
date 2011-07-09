package services.prices;

public interface PriceFeed {

	Price getLastPrice();
	
	void addListener(PriceFeedListener listener);
	
	/**
	 * Notify listeners of a price update
	 * @param price
	 */
	void notifyListeners(Price price);
	
	void startFeed();
	
	void stopFeed();
}
