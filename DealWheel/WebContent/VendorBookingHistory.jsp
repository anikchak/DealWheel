<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Bookingshistory"%>
<%@page import="dao.BookingHistoryDAOImpl"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vendor Booking History</title>
</head>
<body>
<%@ include file="VendorTabs.jsp" %>
<% List<Object[]> bookingDetailList = new BookingHistoryDAOImpl<Bookingshistory>().getBookingDetailsForVendorId(user.getUserId()); %>
<form action="${pageContext.request.contextPath}/DeleteBookingForVendor" method="post">
<table border="1">
<th>Delete</th><th>Vehicle Name</th><th>Registration No</th><th>Booking Period</th><th>Total Duration</th><th>Booked On</th><th>Total Cost</th><th>Booking Status</th>
<% int itr = 0; long duration = 0, cost=0; String vehicle = "", period = "";
for(Object[] bookingDetail : bookingDetailList ){ 
vehicle = (((String)bookingDetail[0]) +" " +((String)bookingDetail[1]));
duration = ((((Date)bookingDetail[4]).getTime()-((Date)bookingDetail[3]).getTime())/ (1000 * 60 * 60 * 24));
period = (String)(new SimpleDateFormat("MM/dd/yyyy").format((Date)bookingDetail[3]).toString() + "to" + new SimpleDateFormat("MM/dd/yyyy").format((Date)bookingDetail[4]).toString());
cost = duration * ((Integer)bookingDetail[6]).longValue();
%>
<tr>
<td><input type="checkbox" name="check<%=itr%>" value="Yes"></td>
<td><%=vehicle %></td>
<td><%=(String)bookingDetail[2]%></td>
<td><%=period %></td>
<td><%=duration %></td>
<td><%= new SimpleDateFormat("MM/dd/yyyy").format((Date)bookingDetail[5])%></td>
<td><%=cost %></td>
<td><%=(String)bookingDetail[7] %></td>
</tr>
<tr><td colspan="8"><input type='hidden' name='arrayList'  value=<%=(String)bookingDetail[8] %>></td></tr>
<% itr ++;
} %>
</table>
<center><input type="submit" name="Delete" value="Delete"></center>
</form>
</html>