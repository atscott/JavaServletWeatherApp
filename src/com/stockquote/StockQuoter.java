/**
 * @author urbain
 * 
 * Retrieve stock quotes using YQL
 * http://developer.yahoo.com/yql
 * 
 */
package com.stockquote;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;


public class StockQuoter {
	
	//"http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22YHOO%22%2C%22AAPL%22%2C%22GOOG%22%2C%22MSFT%22)&format=json&diagnostics=true&env=http%3A%2F%2Fdatatables.org%2Falltables.env&callback=cbfunc"

	/**
	 * @author urbain
	 * Retrieve stock quotes using YQL
	 * @param List of stock symbols
	 * @returns List<StockQuoteWrapper>
	 */
	public static List<StockQuoteWrapper> getStockQuote(List<String> stockList) throws Exception {
		
		List<StockQuoteWrapper> stockQuoteWrapperList = new ArrayList<StockQuoteWrapper>();
		
		if( stockList == null || stockList.size()==0 ) {
			throw new Exception("null or empty stocklist");
		}
		String stockString = "\""+stockList.get(0).toUpperCase()+"\"";
		for(int i=1; i<stockList.size(); i++) {
			stockString += ","+"\""+stockList.get(i).toUpperCase()+"\"";
		}
		String yql = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(" + 
					   URLEncoder.encode(stockString, "UTF-8") + 
					   ")&format=json&diagnostics=true&env=http%3A%2F%2Fdatatables.org%2Falltables.env";

		HttpTransport httpTransport = new NetHttpTransport();
		HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
		JSONParser parser = new JSONParser();
		GenericUrl url = new GenericUrl( yql );
		HttpRequest request = requestFactory.buildGetRequest(url);
		HttpResponse httpResponse = request.execute();
		String httpResponseString = httpResponse.parseAsString();
		//System.out.println( httpResponseString );
		
		JSONObject jsonObject = (JSONObject)parser.parse( httpResponseString );
		//System.out.println(jsonObject);
		
		JSONObject query = (JSONObject) jsonObject.get("query");
		JSONObject results = (JSONObject) query.get("results");
		
		if( stockList.size() == 1 ) {
			JSONObject stockQuote = (JSONObject) results.get("quote");
            String name = (String) ((JSONObject) stockQuote).get("Name");
            String symbol = (String) ((JSONObject) stockQuote).get("Symbol");
            String lastTradePriceOnly = (String) ((JSONObject) stockQuote).get("LastTradePriceOnly");
            if(Double.parseDouble(lastTradePriceOnly) > 0){
	            StockQuoteWrapper stockQuoteWrapper= new StockQuoteWrapper(name, symbol, lastTradePriceOnly);
	            stockQuoteWrapperList.add(stockQuoteWrapper);
            }
        			
		}
		else {
			JSONArray quote = (JSONArray) results.get("quote");
			for (Object stockQuote : quote) {
	            String name = (String) ((JSONObject) stockQuote).get("Name");
	            String symbol = (String) ((JSONObject) stockQuote).get("Symbol");
	            String lastTradePriceOnly = (String) ((JSONObject) stockQuote).get("LastTradePriceOnly");
	            if(Double.parseDouble(lastTradePriceOnly) > 0){
		            StockQuoteWrapper stockQuoteWrapper= new StockQuoteWrapper(name, symbol, lastTradePriceOnly);
		            stockQuoteWrapperList.add(stockQuoteWrapper);
	            }
	        }
		}
		return stockQuoteWrapperList;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		List<String> stockList = new ArrayList<String>();
		stockList.add("GOOG");
		stockList.add("AAPL");
		stockList.add("QCOM");
		try {
			List<StockQuoteWrapper> stockQuoteWrapperList  = StockQuoter.getStockQuote(stockList);
			for( StockQuoteWrapper q : stockQuoteWrapperList ) {
				System.out.println( q.getName() + ", " + q.getSymbol() + ", " + q.getLastTracePriceOnly() );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
