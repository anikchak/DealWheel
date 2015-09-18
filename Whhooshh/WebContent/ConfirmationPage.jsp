<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="services.utility.MessageBundle" %>
<%@ page import="services.utility.GenericConstant" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booking Confirmed</title>
</head>
<body>
<%
String pagecontext = request.getContextPath();
String userName = null;
String sessionID = null;
String confirmedBookingId=null;
if(session.getAttribute("LoggedInUserDetailsObject")!=null){
List<User> validUserDetails = (List<User>)session.getAttribute("LoggedInUserDetailsObject");
if(validUserDetails!=null & validUserDetails.size()>0){
	for(User u : validUserDetails){
		userName = u.getUserEmail();
	}
}
}
	if(session.getAttribute(GenericConstant.SESSIONID)!=null)
    sessionID = (String) session.getAttribute(GenericConstant.SESSIONID);
	/*
	if(session.getAttribute(GenericConstant.USERNAME)!=null)
    userName = (String) session.getAttribute(GenericConstant.USERNAME);
	*/
	if(session.getAttribute("BookingOrderId")!=null)
	    confirmedBookingId = (String) session.getAttribute("BookingOrderId");
%>
<span style="float: right;"> Welcome <span style="font-size: 20px;font-weight: bold;color: BLUE;"><%=userName.toUpperCase() %></span></span>
<br>
<br>

<h3><%=userName.toUpperCase() %>, your confirmed booking id is: </h3><h1><%=confirmedBookingId %></h1>
<a href="${pageContext.request.contextPath}/LandingPage.jsp">Click here for another booking</a>

<form method="post" id="logoutFormId" action="${pageContext.request.contextPath}/Logout">
		<br>
		<input type="submit" value="Logout"/>
</form>

</body>
</html>