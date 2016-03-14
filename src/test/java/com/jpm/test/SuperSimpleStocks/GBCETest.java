package com.jpm.test.SuperSimpleStocks;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class GBCETest {

	@Test
	public void testAllShareIndex() {
        HashMap<String, Stock> stocksList = new HashMap<String, Stock>();
        stocksList.put("YHOO", new Stock("YHOO", Stock.Type.Common, 0.0, 0.0, 100.0));
        stocksList.put("GOOG", new Stock("GOOG", Stock.Type.Common, 8.0, 0.0, 100.0));
        stocksList.put("AAPL", new Stock("AAPL", Stock.Type.Common, 23.0, 0.0, 60.0));
        stocksList.put("JPM", new Stock("JPM", Stock.Type.Preferred, 8.0, 0.2, 100.0));
        stocksList.put("ARM", new Stock("ARM", Stock.Type.Common, 13.0, 0.0, 250.0));
        for (Stock stock: stocksList.values()) 
        	for (int i=1; i <= 10; i++) {
        		stock.buy(1, 1.0);
        		stock.sell(1, 1.0);
        	}
        Double GBCEallShareIndex = Stock.getGBCEallShareIndex(stocksList);
        assertEquals(1.379729661461215, GBCEallShareIndex, 0.0);
	}

}
