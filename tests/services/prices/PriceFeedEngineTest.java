package services.prices;

import junit.framework.Assert;

import org.junit.Test;

public class PriceFeedEngineTest {
	
	@Test
	public void testPriceFeedEngineStarts() {
		PriceFeedEngine pe = PriceFeedEngine.getPriceFeedEngine();	
		Assert.assertNotNull(pe);
	}

}
