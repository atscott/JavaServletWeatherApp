<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>

	<%
		List<String> data = (List<String>) request.getAttribute("Data");
		for (String item : data) {
	%>
		<%=item %><br/>
	<%
		}
	%>
