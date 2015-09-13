<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="services.utility.MessageBundle" %>
<%@ page import="services.utility.GenericConstant" %>
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
if(session.getAttribute(GenericConstant.USERNAME) == null){
    
   RequestDispatcher rd = request.getRequestDispatcher(GenericConstant.NAV_TO_LOGIN_PAGE);
   rd.forward(request, response);
}
	if(session.getAttribute(GenericConstant.SESSIONID)!=null)
    sessionID = (String) session.getAttribute(GenericConstant.SESSIONID);
	
	if(session.getAttribute(GenericConstant.USERNAME)!=null)
    userName = (String) session.getAttribute(GenericConstant.USERNAME);
	
	if(session.getAttribute("BookingOrderId")!=null)
	    confirmedBookingId = (String) session.getAttribute("BookingOrderId");
%>
<h3><%=userName %>, your confirmed booking id is: </h3><h1><%=confirmedBookingId %></h1>
<a href="${pageContext.request.contextPath}/LandingPage.jsp">Click here for another booking</a>

<form method="post" id="logoutFormId" action="${pageContext.request.contextPath}/Logout">
		<br>
		<input type="submit" value="Logout"/>
</form>
</body>
</html>