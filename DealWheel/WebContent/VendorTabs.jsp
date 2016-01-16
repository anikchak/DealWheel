<%@page import="model.Address"%>
<%@page import="services.utility.GenericConstant"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="commonResources/CommonJSCSSInclude"%>
</head>
<body>
<% User user = (User)session.getAttribute(GenericConstant.USER_MODEL);
	Address address = (Address)session.getAttribute(GenericConstant.ADDRESS_MODEL);
%>
<form method="post" id="logoutFormId" action="${pageContext.request.contextPath}/Logout">
<span style="float: right;"><input type="submit" value="Logout"></span>
<span style="float: right;"> Welcome <%=user.getUserEmail() %></span>
<a href="VendorHome.jsp">Home</a><br><a href="AddVehicle.jsp">Add a Vehicle</a><br><a href="VendorBookingHistory.jsp">Booking History</a><br>
<a href="VendorDisplayVehicles.jsp">Listed Vehicles</a><br><a href="VendorPaymentHistory.jsp">Payment History</a>
</form>
</body>
</html>