package services.prices;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PriceFeedEngine implements PriceFeed{
	
	private static final String[] defaultInstruments = new String[]{"RDSA.L",
																	"BP.L",
																	"HSBA.L",
																	"GSK.L",
																	"VOD.L",
																	"RBS.L"};
	
	private ConcurrentHashMap<String, Price> priceMap = new ConcurrentHashMap<String, Price>();
	
	private Set<PriceFeedListener> users = Collections.synchronizedSet(new HashSet<PriceFeedListener>());
	
	private final Object lock = new Object();
	
	//ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	
	private PriceFeedEngine() {

		
	}
	
	private static class Engine {
		public static final PriceFeedEngine INSTANCE = new PriceFeedEngine();
	}
	
	public static PriceFeedEngine getPriceFeedEngine() {
		return Engine.INSTANCE;
	}

	@Override
	public Price getLastPrice() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addListener(PriceFeedListener listener) {
		// TODO Auto-generated method stub
		
		synchronized (lock) {
			users.add(listener);
		}
		
	}

	@Override
	public void startFeed() {
		initialisePrices();
		
	}

	@Override
	public void stopFeed() {
		// TODO Auto-generated method stub		
	}
	
	/**
	 * Add some dummy prices into a map
	 */
	private void initialisePrices() {
		
		//Local class only intended to be used for set up dummy prices.
		class Generate {
			Random rand = new Random();
			
			Price getPrice(String name) {
				Price p = new Price();
				
				p.setName(name);
				p.setBid(rand.nextDouble()*100);
				p.setOffer(rand.nextDouble()*100);
				return p;
			}
		}
		
		Generate gen = new Generate();
		
		for (String s : defaultInstruments) {
			Price p = gen.getPrice(s);
			priceMap.put(s, p);
			notifyListeners(p);
		}
		
		
	}

	@Override
	public void notifyListeners(Price price) {
		synchronized(lock){
			Iterator<PriceFeedListener> iter = users.iterator();
			
			while (iter.hasNext()) {
				PriceFeedListener l = iter.next();
				l.onPriceUpdate(price);
				
			}
		}
		
	}
	
	
	
	
	
	
	

}
