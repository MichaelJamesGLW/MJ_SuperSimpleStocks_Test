package com.jpm.test.SuperSimpleStocks;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/***
 * Generic stock handling object
 * 
 * @author MichaelJames
 *
 */
public class Stock {
	
	public static enum Type {
		Common, Preferred
	}
	
	/***
	 * Properties
	 */
	private String code;
	private Type type;
	private Double lastDividend;
	private Double fixedDividend;
	private Double parValue;
    private TreeMap<Date, Trade> tradeList;
    
    private Integer lastTimeInMins = 15;
    
	/***
	 * @constructor
	 * @param code - Company code
	 * @param type - Type of stock
	 * @param lastDividend - last dividend
	 * @param fixedDividend - fixed dividend
	 * @param parValue - value
	 */
	public Stock(String code, Type type, Double lastDividend, Double fixedDividend, Double parValue) {
		this.code = code;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
		this.tradeList = new TreeMap<Date, Trade>();
	}
	
	@Override
	public String toString() {
		String value = String.format("STOCK: \"%0s\" Type: %1s.\n", code, type);
		value += String.format("Last dividend: %0d\n", lastDividend);
		value += String.format("Fixed dividend: %0d\n", fixedDividend);
		value += String.format("Par value: %0d\n\n", parValue);
		return value;
	}

	/**
	 * Calculate dividend based on the specified price
	 * @param price price of stock
	 * @return Dividend based on price
	 */
	public Double dividend(Double price) {
		switch(this.getType()) {
			case Common:
				return lastDividend / price;
			case Preferred:
				return ( fixedDividend * parValue ) / price;
			default:
				return 0.0;
		}
	}
	
	/**
	 * Calculate P/E Ratio based on the specified price
	 * 
	 * @param price The price to use as a base to calculate the P/E ratio
	 * @return The P/E Ratio
	 */
	public Double PERatio(Double price) {
		return price / lastDividend;
	}

	/**
	 * Buy stock, specifying quantity and price
	 * 
	 * @param quantity The quantity of stock to buy
	 * @param price The price of the stock
	 */
	public void buy(Integer quantity, Double price) {
		this.tradeList.put(new Date(), new Trade(Trade.Type.Buy, quantity, price) );
	}

	/**
	 * Sell stock, specifying quantity and price
	 * 
	 * @param quantity The quantity of stock to sell
	 * @param price The price of the stock
	 */
	public void sell(Integer quantity, Double price) {
		tradeList.put(new Date(), new Trade(Trade.Type.Sell, quantity, price) );		
	}
	
	/**
	 * Return the current price of the stock based on the last trade price
	 * 
	 * @return The last trade price
	 */
	public Double getPrice() {
		return (tradeList.size() > 0) ? 
				tradeList.lastEntry().getValue().getPrice() : 0.0;
	}
	
	/**
	 * Calculate Volume Weighted Stock Price 
	 * 
	 * @return The Volume Weighted Stock Price
	 */
	public Double getVolumeWeightedStockPrice() {
		// Get all trades for from a certain point in time 
		// defined in minutes by "lastTimeInMins".
		Date minTime = new Date(new Date().getTime() - (lastTimeInMins * 60 * 1000));
		SortedMap<Date, Trade> trades = tradeList.tailMap(minTime);
		// Calculate Volume Weighted Stock Price, given these trades
		Double priceTotal = 0.0;
		Integer quantity = 0;
		for (Trade trade: trades.values()) {
			priceTotal += trade.getPrice() * trade.getQuantity();
			quantity += trade.getQuantity();
		}
		return ( priceTotal / quantity );
	}
	
	/**
	 * Calculate GBCE All Share Index for collection of stocks
	 * @param stocks Collection of stocks
	 * @return GBCE All Share Index
	 */
	public static Double getGBCEallShareIndex(Map<String, Stock> stocks) {
		Double allShareIndex = 0.0;
		for( Stock stock: stocks.values() )
			allShareIndex += stock.getPrice();
		return Math.pow( allShareIndex, 1.0 / stocks.size() );
	}
	
    /***
     * Properties
     */

	public String getCode() {
		return code;
	}

	public Type getType() {
		return type;
	}

	public Double getLastDividend() {
		return lastDividend;
	}

	public Double getFixedDividend() {
		return fixedDividend;
	}

	public Double getParValue() {
		return parValue;
	}

	public TreeMap<Date, Trade> getTrades() {
		return tradeList;
	}
}
