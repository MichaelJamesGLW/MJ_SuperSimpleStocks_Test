#SuperSimpleStocks

##Requirements

Provide working source code that will:
* For each stock, 
	* Given any price as input, calculate the dividend yield
    * Given any price as input, calculate the P/E Ratio
    * Record a trade, with time stamp, quantity of shares, buy or sell indicator and 
    * Calculate Volume Weighted Stock Price based on trades in past 15 minutes
* Calculate the GBCE All Share Index using the geometric mean of prices for all stocks traded price

##Constraints & Notes
* Written in one of these languages: *Java*, C#, C++, Python
* No database or GUI is required, all data need only be held in memory
* No prior knowledge of stock markets or trading is required – all formulas are provided below.

##ProjectThis project was coded in Java using Eclipse Mars, with the M2E Maven extension
###Unit tests
* mvn test -> to execute the unit tests.

###Main application
* java -jar target/sssmarket-0.0.1-SNAPSHOT.jar

##Classes
* com.jpm.test.SuperSimpleStocks.Stock - Stock operations
* com.jpm.test.SuperSimpleStocks.Trade - Trade operations
* com.jpm.test.SuperSimpleStocks.Application - Demo application

##Test Classes
* com.jpm.test.SuperSimpleStocks.StockTest - Test for Stock operations
* com.jpm.test.SuperSimpleStocks.GBCETest - Test for GBCE All Share Index