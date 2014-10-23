<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page isErrorPage="true" %>
<%
	String error = (String) request.getAttribute("errorMessage");
%>
<div style="color: red;">
	<%=error%>
</div>