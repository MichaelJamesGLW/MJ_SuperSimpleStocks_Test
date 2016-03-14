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
        	log.info("START SUPER SIMPLE STOCKS DEMO =================");
        	log.info( "\n" );
            
            @SuppressWarnings("unchecked")
    		Map<String, Stock> stockData = context.getBean("stockData", Map.class);
            for (Stock stock: stockData.values()) {
            	for (int i=rangeMin; i <= rangeMax; i++) {
            		Integer quantity = MakeRandomInteger(rangeMin, rangeMax);
            		Double price = MakeRandomDouble(rangeMin, rangeMax);
            		stock.buy(quantity, price);
            		log.info( stock.getCode() + ": BOUGHT " + quantity + " shares at £" + price);
            		quantity = MakeRandomInteger(rangeMin, rangeMax);
            		price = MakeRandomDouble(rangeMin, rangeMax);
            		stock.sell(quantity, price);
            		log.info( stock.getCode() + ": SOLD   " + quantity + " shares at £" + price);
            		Thread.sleep(1000);
            	}
            	log.info( "\n" );
            	log.info( "Final calculations for " + stock.getCode() + ":");
            	log.info( stock.getCode() + "- Dividend: " + stock.dividend(stock.getPrice()));
            	log.info( stock.getCode() + "- P/E Ratio: " + stock.PERatio(stock.getPrice()));
            	log.info( stock.getCode() + "- Price: £" + stock.getPrice());
            	log.info( stock.getCode() + "- Volume Weighted Stock Price: £" + stock.getVolumeWeightedStockPrice());
            	log.info( "=======================");
            	log.info( "\n" );
            }
            log.info( "GBCE All Share Index: " + Stock.getGBCEallShareIndex(stockData));
        	log.info( "\n" );
        	log.info( "=======================");
        	log.info( "\n" );
        	log.info( "End of demo.");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
    
    private static Double MakeRandomDouble(Integer min, Integer max) {
    	Random r = new Random();
    	return min + (max - min) * r.nextDouble();
    }
    private static Integer MakeRandomInteger(Integer min, Integer max) {
    	return MakeRandomDouble(min, max).intValue();
    }
}
