package com.stockquote;

import org.json.simple.JSONObject;

public class StockQuoteWrapper {
	
	private String name;
	private String symbol;
	private String lastTracePriceOnly;
	
	public StockQuoteWrapper(String name, String symbol,
			String lastTracePriceOnly) {
		super();
		this.name = name;
		this.symbol = symbol;
		this.lastTracePriceOnly = lastTracePriceOnly;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getLastTracePriceOnly() {
		return lastTracePriceOnly;
	}

	public void setLastTracePriceOnly(String lastTracePriceOnly) {
		this.lastTracePriceOnly = lastTracePriceOnly;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
