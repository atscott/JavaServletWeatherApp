<%@ page import="controller.QuoteServlet"%>
<%@ page import="java.util.List"%>
<%@ page import="com.stockquote.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<link rel="stylesheet" type="text/css" href="css/stocks.css">
<form>
	Input stock symbols: <input name="stocks" type="text" /> <input
		type="submit" />
</form>
<br />
<%
	List<StockQuoteWrapper> stockQuoteWrapperList = (List<StockQuoteWrapper>) request
			.getAttribute("stockQuotes");

	if (stockQuoteWrapperList != null
			&& stockQuoteWrapperList.size() > 0) {
%>
<table class="stocks">
	<tr>
		<td class="header">Company</td>
		<td class="header">Stock</td>
		<td class="header">Last Traded Price</td>
	</tr>
	<tbody>
		<%
			for (StockQuoteWrapper q : stockQuoteWrapperList) {
					out.println("<tr>");
					out.println("<td>" + q.getName() + "</td>");
					out.println("<td>" + q.getSymbol() + "</td>");
					out.println("<td>" + q.getLastTracePriceOnly() + "</td>");
					out.println("</tr>");
				}
		%>
	</tbody>
</table>
<%
	} //stockQuoteWrapperList != null && size > 0
	List<String> failed = (List<String>) request
			.getAttribute("failedToRetrieve");
	if (failed != null && failed.size() > 0) {
		out.println("The following stocks could not be retrieved:<br/>");
		for (String ticker : failed) {
			out.println(ticker + "<br/>");
		}
	}
%>