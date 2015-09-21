<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="services.CustomerControllerService" %>
<%@ page import="services.utility.MessageBundle" %>
<%@ page import="services.utility.GenericConstant" %>
<%@ page import="services.utility.CommonUtility" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
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
if(session.getAttribute("LoggedInUserDetailsObject")!=null){
List<User> validUserDetails = (List<User>)session.getAttribute("LoggedInUserDetailsObject");
if(validUserDetails!=null & validUserDetails.size()>0){
	for(User u : validUserDetails){
%>
<span style="float: right;"> Welcome <span style="font-size: 20px;font-weight: bold;color: BLUE;"><%=u.getUserEmail().toUpperCase() %></span></span>

<%		
	}
}
}
else{
%>
<span style="float: right;"> Welcome <span style="font-size: 20px;font-weight: bold;color: BLUE;font-family: Verdana;">Guest</span></span>
<%} %>	
<br>
<form method="post" id="logoutFormId" action="${pageContext.request.contextPath}/Logout">
<br>
<input type="submit" value="Logout" style="float: right;"/>
</form>
<br>
<br>	
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
	<br>
	<a href="${pageContext.request.contextPath}/POC.jsp">POC Page</a>
	<br>
	<a href="${pageContext.request.contextPath}/VendorLoginSignUp.jsp">Vendor mySpace</a>
	<br>
    <a href="${pageContext.request.contextPath}/MyBookings">My Bookings</a>
</body>
</html>