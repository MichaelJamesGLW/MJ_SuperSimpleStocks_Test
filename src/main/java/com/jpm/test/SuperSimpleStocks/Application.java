package com.jpm.test.SuperSimpleStocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
public class Application 
{
	private static Log log = LogFactory.getLog(Application.class);
	private static Integer rangeMin = 1;
	private static Integer rangeMax = 10;
	
    @Bean
    Map<String, Stock> stockData() {
        HashMap<String, Stock> db = new HashMap<String, Stock>();
        db.put("YHOO", new Stock("YHOO", Stock.Type.Common, 1.5, 0.0, 100.0));
        db.put("GOOG", new Stock("GOOG", Stock.Type.Preferred, 8.0, 0.4, 110.0));
        db.put("AAPL", new Stock("AAPL", Stock.Type.Common, 15.5, 0.0, 60.0));
        db.put("JPM", new Stock("JPM", Stock.Type.Preferred, 8.0, 0.2, 100.0));
        db.put("JOE", new Stock("ARM", Stock.Type.Common, 14.0, 0.0, 250.0));
        return db;
    }

    /***
     * Main application.
     * @param args
     */
    public static void main( String[] args )
    {
        try {
            ApplicationContext context = 
                    new AnnotationConfigApplicationContext(Application.class);
            
            @SuppressWarnings("unchecked")
    		Map<String, Stock> stockData = context.getBean("stockData", Map.class);
            for (Stock stock: stockData.values()) {
            	log.debug( stock.getCode() + "- Dividend: " + stock.dividend(stock.getPrice()));
            	log.debug( stock.getCode() + "- P/E Ratio: " + stock.PERatio(stock.getPrice()));
            	for (int i=rangeMin; i <= rangeMax; i++) {
            		Integer quantity = MakeRandomInteger(rangeMin, rangeMax);
            		double price = MakeRandomDouble(rangeMin, rangeMax);
            		stock.buy(quantity, price);
            		log.debug( stock.getCode() + "- Bought " + i + " shares at #" + price);
            		quantity = MakeRandomInteger(rangeMin, rangeMax);
            		price = MakeRandomDouble(rangeMin, rangeMax);
            		stock.sell(i, price);
            		log.debug( stock.getCode() + "- Sold " + i + " shares at #" + price);
            		Thread.sleep(1000);
            	}
            	log.debug( stock.getCode() + "- Price: £" + stock.getPrice());
            	log.debug( stock.getCode() + "- Volume Weighted Stock Price: £" + stock.getVolumeWeightedStockPrice());
            }
            log.debug( "GBCE All Share Index: " + Stock.getGBCEallShareIndex(stockData));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
    
    private static double MakeRandomDouble(Integer min, Integer max) {
    	Random r = new Random();
    	return min + (max - min) * r.nextDouble();
    }
    private static Integer MakeRandomInteger(Integer min, Integer max) {
    	Random r = new Random();
    	return min + (max - min) * r.nextInt();
    }
}
