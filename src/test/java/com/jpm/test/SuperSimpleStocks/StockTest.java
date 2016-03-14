package com.jpm.test.SuperSimpleStocks;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Test;

/***
 * Test of stock classes
 * @author MichaelJames
 */
public class StockTest {

	@Test
	public void testDividend() {
        Stock stockYHOO = new Stock("YHOO", Stock.Type.Common, 23.0, 0.0, 60.0);
        Stock stockJPM = new Stock("JPM", Stock.Type.Preferred, 8.0, 0.2, 100.0);
		Double dividendYHOO = stockYHOO.dividend(1.0);
		Double expectedDividendYHOO = stockYHOO.getLastDividend()/1.0;
		assertEquals(expectedDividendYHOO, dividendYHOO, 0.0);
		Double dividendJPM = stockJPM.dividend(1.0);
		Double expectedDividendGIN = stockJPM.getFixedDividend()*stockJPM.getParValue()/1.0;
		assertEquals(expectedDividendGIN, dividendJPM, 0.0);
	}

	@Test
	public void testPERatio() {
        Stock stockYHOO = new Stock("YHOO", Stock.Type.Common, 23.0, 0.0, 60.0);
        Double peRatioYHOO = stockYHOO.PERatio(1.0);
        Double expectedPeRatioYHOO = 1.0/stockYHOO.getLastDividend();
        assertEquals(expectedPeRatioYHOO, peRatioYHOO, 0.0);
	}

	@Test
	public void testBuy() {
		Stock stockYHOO = new Stock("YHOO", Stock.Type.Common, 23.0, 0.0, 60.0);
		stockYHOO.buy(1, 10.0);
		assertEquals(10.0, stockYHOO.getPrice(), 0.0);
	}

	@Test
	public void testSell() {
		Stock stockYHOO = new Stock("YHOO", Stock.Type.Common, 23.0, 0.0, 60.0);
		stockYHOO.sell(1, 10.0);
		assertEquals(10.0, stockYHOO.getPrice(), 0.0);
	}

	@Test
	public void testGetPrice() {
		Stock stockYHOO = new Stock("YHOO", Stock.Type.Common, 23.0, 0.0, 60.0);
		stockYHOO.sell(1, 9.0);
		stockYHOO.buy(1, 11.0);
		assertEquals(11.0, stockYHOO.getPrice(), 0.0);
	}

	@Test
	public void testCalculateVolumeWeightedStockPrice() {
		// Initial test after sell and buy
		Stock stockYHOO = new Stock("YHOO", Stock.Type.Common, 23.0, 0.0, 60.0);
		stockYHOO.sell(2, 10.0);
		stockYHOO.buy(2, 10.0);		
		Double volumeWeightedStockPrice = stockYHOO.getVolumeWeightedStockPrice();
		assertEquals(10.0, volumeWeightedStockPrice, 0.0);
		// Test after taking into account a trade from 15 mins ago
		Date now = new Date();
		Date startTime = new Date(now.getTime() - (15 * 60 * 1000));
		stockYHOO.getTrades().put(startTime, new Trade(Trade.Type.Buy, 1, 20.0));
		volumeWeightedStockPrice = stockYHOO.getVolumeWeightedStockPrice();
		assertEquals(13.333333333333334, volumeWeightedStockPrice, 0.0);
	}
}
