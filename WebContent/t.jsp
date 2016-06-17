<%@page import="java.net.ServerSocket"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<body>
	<% 
	ServerSocket myService;
	 
	try
	{
	myService = new ServerSocket(8080);
	System.out.println("successfully opened a socket");
	 
	}
	catch (Exception e) 
	{
	e.printStackTrace();
	System.out.println(e);
	}
	%>
</body>
</html>