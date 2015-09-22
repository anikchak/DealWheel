<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="services.CustomerControllerService" %>
    <%@ page import="java.util.List" %> 
    <%@ page import="model.Bookingshistory" %> 
    <%@ page import="model.Vehicle" %>
    <%@ page import="model.User" %>
    <%@ page import="model.Address" %>
    <%@ page import="services.utility.GenericConstant" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="java.util.Arrays" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Bookings</title>
</head>
<body>
<h3>Welcome <%=session.getAttribute(GenericConstant.USERNAME) %> <br><br></h3>
<%
List<String[]> completed = new ArrayList<String[]>();
List<String[]> upComing = new ArrayList<String[]>();
List<String[]> Cancelled = new ArrayList<String[]>();
@SuppressWarnings("unchecked")
List<Object[]> resultSet = (List<Object[]>)session.getAttribute("MyBookingHistory");

	    int i1,j,k;
		i1=1;j=1;k=0;
		
		for(Object[] o: resultSet)
		{
	         
	         
	         Bookingshistory bh = (Bookingshistory)o[0];
			 Vehicle vh = (Vehicle)o[1];
		     User us = (User) o[2];
		     Address as = (Address) o[3];
		     
		      System.out.println("stasts" +bh.getBkngStatus()+vh.getVhclName());
		      
		     if ("UPCMNG".equals(bh.getBkngStatus()))
		     {
		    	 String comp[] = new String[7];
		    	 comp[0] = vh.getVhclName();
		    	 comp[1] = (String)bh.getBkngFromDate().toString();
		    	 comp[2] = (String)bh.getBkngToDate().toString();
		    	 comp[3] = (String) vh.getVhclRegistrationNo();
		    	 comp[4] = (String)  us.getUserName();
		    	 comp[5] = (String) as.getAddrLocality();
		    	 comp[6] = Integer.toString(i1);
			     i1++;
			     
			     System.out.println(comp[0]);
			     upComing.add(comp);
		     }
		    	 
		     
		     else if ("CMPLTD".equals(bh.getBkngStatus()))
		     {
		    	 String comp[] = new String[7];
		    	     comp[0] = vh.getVhclName();
				     comp[1] = (String)bh.getBkngFromDate().toString();
				     comp[2] = (String)bh.getBkngToDate().toString();
				     comp[3] = (String) vh.getVhclRegistrationNo();
				     comp[4] = (String)  us.getUserName();
				     comp[5] = (String) as.getAddrLocality();
				     comp[6] = Integer.toString(j);
				     j++;
				     
				     		     
				     completed.add(comp);
		     }
		    	 
		     
		     else if ("CANCLD".equals(bh.getBkngStatus())) 
		     {
		    	 String comp[] = new String[7];
		    	 comp[0] = vh.getVhclName();
			     comp[1] = (String)bh.getBkngFromDate().toString();
			     comp[2] = (String)bh.getBkngToDate().toString();
			     comp[3] = (String) vh.getVhclRegistrationNo();
			     comp[4] = (String)  us.getUserName();
			     comp[5] = (String) as.getAddrLocality(); 
			     comp[6] = Integer.toString(k);
			     k++;
			     
			     Cancelled.add(comp);
		     }
		 
		}
		
		
%>

<h3> Completed Bookings</h3>
<% if (completed.isEmpty()) { %>
<center><b><h4>No Completed Bookings !!!</h4></b></center><br><br><br>
<%} else {  %>
<table border=1>
<tr>
<th> S.No </th>
<th> Vehicle Name</th>
<th> Start Date </th>
<th> End Date </th>
<th> Vehicle Registration Number </th>
<th> Dealer</th>
<th> Location </th>
</tr>

<% for(String[] o: completed){ %>
<tr>
<td> <%=o[6] %> </td>
<td> <%=o[0] %> </td>
<td> <%=o[1] %> </td>
<td> <%=o[2] %> </td>
<td> <%=o[3] %> </td>
<td> <%=o[4] %> </td>
<td> <%=o[5] %> </td>
</tr>

<%} %>
<%} %>
</table><br><br><br>


<h3>Upcoming Bookings</h3>
<% if (upComing.isEmpty()) { %>
<center><h4><b>No Upcoming Bookings !!!</b></h4></center><br><br><br>
<%} else {  %>
<table border=1>
<tr>
<th> S.No </th>
<th> Vehicle Name</th>
<th> Start Date </th>
<th> End Date </th>
<th> Vehicle Registration Number </th>
<th> Dealer</th>
<th> Location </th>
</tr>

<% for(String[] o: upComing){ 
System.out.println(o[0]);
%>
<tr>
<td> <%=o[6] %> </td>
<td> <%=o[0] %> </td>
<td> <%=o[1] %> </td>
<td> <%=o[2] %> </td>
<td> <%=o[3] %> </td>
<td> <%=o[4] %> </td>
<td> <%=o[5] %> </td>
</tr>

<%} %>
<%} %>
</table><br><br><br>

<h3>Cancelled Bookings</h3>
<% if (Cancelled.isEmpty()) { %>
<center><h4><b>No Cancelled Bookings</b></h4></center><br><br><br>
<%} else {  %>
<table border=1>
<tr>
<th> S.No </th>
<th> Vehicle Name</th>
<th> Start Date </th>
<th> End Date </th>
<th> Vehicle Registration Number </th>
<th> Dealer</th>
<th> Location </th>
</tr>

<% for(String[] o: Cancelled){ %>
<tr>
<td> <%=o[6] %> </td>
<td> <%=o[0] %> </td>
<td> <%=o[1] %> </td>
<td> <%=o[2] %> </td>
<td> <%=o[3] %> </td>
<td> <%=o[4] %> </td>
<td> <%=o[5] %> </td>
</tr>

<%} %>
<%} %>

</table><br><br><br>




</body>
</html>