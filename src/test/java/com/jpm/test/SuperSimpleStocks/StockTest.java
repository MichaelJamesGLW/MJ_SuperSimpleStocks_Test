package com.jpm.test.SuperSimpleStocks;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class StockTest {

	@Test
	public void testDividend() {
        Stock stockYHOO = new Stock("YHOO", Stock.Type.Common, 23.0, 0.0, 60.0);
        Stock stockJPM = new Stock("JPM", Stock.Type.Preferred, 8.0, 0.2, 100.0);
		Double dividendALE = stockYHOO.dividend(1.0);
		Double expectedDividendALE = stockYHOO.getLastDividend()/1.0;
		assertEquals(expectedDividendALE, dividendALE, 0.0);
		Double dividendGIN = stockJPM.dividend(1.0);
		Double expectedDividendGIN = stockJPM.getFixedDividend()*stockJPM.getParValue()/1.0;
		assertEquals(expectedDividendGIN, dividendGIN, 0.0);
	}

	@Test
	public void testPERatio() {
        Stock stockALE = new Stock("YHOO", Stock.Type.Common, 23.0, 0.0, 60.0);
        Double peRatioALE = stockALE.PERatio(1.0);
        Double expectedPeRatioALE = 1.0/stockALE.getLastDividend();
        assertEquals(expectedPeRatioALE, peRatioALE, 0.0);
	}

	@Test
	public void testBuy() {
		Stock stockALE = new Stock("YHOO", Stock.Type.Common, 23.0, 0.0, 60.0);
		stockALE.buy(1, 10.0);
		assertEquals(10.0, stockALE.getPrice(), 0.0);
	}

	@Test
	public void testSell() {
		Stock stockALE = new Stock("YHOO", Stock.Type.Common, 23.0, 0.0, 60.0);
		stockALE.sell(1, 10.0);
		assertEquals(10.0, stockALE.getPrice(), 0.0);
	}

	@Test
	public void testGetPrice() {
		Stock stockALE = new Stock("YHOO", Stock.Type.Common, 23.0, 0.0, 60.0);
		stockALE.sell(1, 10.0);
		stockALE.buy(1, 11.0);
		assertEquals(11.0, stockALE.getPrice(), 0.0);
	}

	@Test
	public void testCalculateVolumeWeightedStockPrice() {
		Stock stockALE = new Stock("YHOO", Stock.Type.Common, 23.0, 0.0, 60.0);
		stockALE.sell(2, 10.0);
		stockALE.buy(2, 10.0);		
		Double volumeWeightedStockPrice = stockALE.getVolumeWeightedStockPrice();
		assertEquals(10.0, volumeWeightedStockPrice, 0.0);
		Date now = new Date();
		Date startTime = new Date(now.getTime() - (15 * 60 * 1000));
		stockALE.getTrades().put(startTime, new Trade(Trade.Type.Buy, 1, 20.0));
		volumeWeightedStockPrice = stockALE.getVolumeWeightedStockPrice();
		assertEquals(10.0, volumeWeightedStockPrice, 0.0);
	}
}
