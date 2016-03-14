package com.jpm.test.SuperSimpleStocks;

/***
 * Record of a trade
 * @author MichaelJames
 */
public class Trade {
	
	private Type type;
	private Integer quantity;
	private Double price;
	
	public static enum Type {
		Buy, Sell
	}

	/***
	 * Constructor
	 * @param type - Type of trade (Buy or sell)
	 * @param quantity - number of items in trade
	 * @param price - price per item
	 */
	public Trade(Type type, Integer quantity, Double price) {
		this.type = type;
		this.quantity = quantity;
		this.price = price;
	}

	/***
	 * Show trade data
	 */
	@Override
	public String toString() {
		return String.format("TRADE: %0s: %1s at price £%2d\n", type, quantity, price);
	}
	
	/***
	 * Properties
	 */
	
	public Type getType() {
		return type;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Double getPrice() {
		return price;
	}	
}
