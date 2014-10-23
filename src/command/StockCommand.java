package command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stockquote.StockQuoteWrapper;
import com.stockquote.StockQuoter;

public class StockCommand implements Command {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws CommandException {
		List<String> stocks = this.getStockList(request.getParameter("stocks"));
		if (stocks.size() > 0) {
			request.setAttribute("attemptedToRetrieve", stocks);
			List<StockQuoteWrapper> quotes = getQuoteWrapperFromStringList(stocks);
			request.setAttribute("stockQuotes", quotes);
			List<String> failedTickers = determineFailedAttempts(stocks, quotes);
			request.setAttribute("failedToRetrieve", failedTickers);
		}
		return null;
	}

	private List<String> determineFailedAttempts(List<String> tickers,
			List<StockQuoteWrapper> quotes) {
		List<String> failed = new ArrayList<String>();
		for(String ticker : tickers){
			ticker = ticker.trim();
			boolean found = false;
			Iterator<StockQuoteWrapper> itr = quotes.iterator();
			while(itr.hasNext() && found == false){
				String quoteSymbol = itr.next().getSymbol();
				if(quoteSymbol.equalsIgnoreCase(ticker)){
					found = true;
				}
			}
			if(!found){
				failed.add(ticker);
			}
		}
		return failed;
		
	}

	/**
	 * Takes a list of stock strings and converts to list of stockquotewrapper
	 * 
	 * @param stockList
	 * @return the list of stocks as a List<StockQuoteWrapper>
	 */
	private List<StockQuoteWrapper> getQuoteWrapperFromStringList(
			List<String> stockList) {
		List<StockQuoteWrapper> stockQuoteWrapperList = Collections.emptyList();
		try {
			if (stockList.size() > 0) {
				stockQuoteWrapperList = StockQuoter.getStockQuote(stockList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stockQuoteWrapperList;
	}

	/**
	 * Gets
	 * 
	 * @param request
	 * @return
	 */
	private List<String> getStockList(String stocks) {
		List<String> stockList = Collections.emptyList();
		if (stocks != null && stocks.length() > 0) {
			String[] stockArray = stocks.split(",");
			stockList = new ArrayList<String>(Arrays.asList(stockArray));
		}
		return stockList;
	}

}
