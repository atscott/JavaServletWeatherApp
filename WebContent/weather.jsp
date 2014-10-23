<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Weather.*"%>
<%@ page import="java.util.Iterator"%>
<link rel="stylesheet" type="text/css" href="css/weather.css">
<form action="/Lab7MVC/CmdServlet">
	<input type="hidden" name="cmd" value="weather"> Zip Code: <input
		name="zip" type="text" /> <input type="submit" />
</form>
<br />
<%
	ForecastData data = (ForecastData) request
			.getAttribute("WeatherData");

	if (data != null) {
%>
<div id="Identification">
	Weather forecast for
	<%=data.Geo.City%>,
	<%=data.Geo.State%></div>

<%
	Iterator<Forecast> itr = data.iterator();
		while (itr.hasNext()) {
			Forecast forecast = itr.next();
%>

<div class="forecast">
	<span class="date"><%=forecast.Date%> </span> <br />

	<table>
		<tr>
			<td class="image-cell"><img src=<%=forecast.PictureURL%> /></td>
			<td><b><%=forecast.High%></b> | <%=forecast.Low%> &degF</td>
		</tr>
		<tr>
			<td colspan="2"><%=forecast.Description%></td>

		</tr>
		<tr>
			<td colspan="2" style="font-size: 9pt;"><%=forecast.ChanceOfPrecip%>
				chance of precipitation.<br />Accumulation totaling <%=forecast.PrecipitationInches%>
				inches.</td>
		</tr>
	</table>
</div>
<%
	} //iterator
	} //data != null
	if (request.getAttribute("error") != null) {
		out.write((String) request.getAttribute("error"));
	}
%>
