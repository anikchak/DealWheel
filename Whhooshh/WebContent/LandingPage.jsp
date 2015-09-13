<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="services.CustomerControllerService" %>
<%@ page import="services.utility.MessageBundle" %>
<%@ page import="services.utility.GenericConstant" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Whhooshh Landing Page</title>
   
</head>
<body>
<%
new CustomerControllerService().cleanBookings();
session.setAttribute(GenericConstant.COMINGFROMPAGE, "LandingPage");
%>
	
	<table border=1 width="100%">
	<tr>
		<td>Space for logo</td>
		<td>Location<br>
		<a href="">How it works</a> &nbsp;&nbsp;&nbsp; <a href="">Tariffs</a> &nbsp;&nbsp;&nbsp;		<a href="">Policies</a> &nbsp;&nbsp;&nbsp;		<a href="">Offers</a>&nbsp;&nbsp;&nbsp;<br>
		All these above links will be a pop-up/Modal windows with close icon
		</td>
		<td>My Account - This will be a menu<br>
		<div id="myAccntInitial">
		Sign In - pop up - Initial Default option<br>
		Sign Up - pop up - Initial Default option<br>
		Forgot Password - pop up - Initial Default option 
		</div>
		<div id="myAccntLoggedInUser">
		My Bookings - Page<br>
		Account Settings - pop up/page<br>
		Verification Docs uploaded - pop up/page<br>
		Logout - pop up
		</div>
		</td>
		
	</tr>
	</table>
	<form method="post" id="searchFormId" action="${pageContext.request.contextPath}/Search">
	<div>
   		Space allocated for Search  - Enter Date here in yyyy-MMM-dd format
   		<br>
   		From Date:<input type="text" name="fromDate"/><br>
   		To Date: <input type="text" name="toDate"/><br>
   		<input type="submit" value="Search"/>
	</div>
	</form>
	
	<a href="${pageContext.request.contextPath}/Login.jsp">Login</a>
	
</body>
</html>