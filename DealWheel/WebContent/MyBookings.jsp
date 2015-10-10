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
    <%@ page import="java.text.SimpleDateFormat" %>
<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0);
//prevents caching at the proxy server
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Bookings</title>
<script type="text/javascript">
function confirm_decision(user_id){
	console.log("log");
    if(confirm("Are you sure you want to delete the booking?")) // this will pop up confirmation box and if yes is clicked it call servlet else return to page
   {  
        console.log(user_id);
        document.getElementById("DeleteId").value = user_id;
    document.location.href = '${pageContext.request.contextPath}/MyBookings?Delid='+user_id;
    
   }
    document.getElementById("Mybookings").submit();
    
   
    // }else{
      // return false;
   // }
   // return true;
 }
</script>
</head>
<body>
<h3>Welcome <%=session.getAttribute(GenericConstant.USERNAME) %> <br><br></h3>
<%
SimpleDateFormat fd=new SimpleDateFormat("dd-MM-yyyy");
List<String[]> completed = new ArrayList<String[]>();
List<String[]> upComing = new ArrayList<String[]>();
List<String[]> Cancelled = new ArrayList<String[]>();
@SuppressWarnings("unchecked")
List<Object[]> resultSet = (List<Object[]>)session.getAttribute("BookingHistory");

	
		//List<Object[]> resultSet = (Object[])displayMyBookings.get(i);
		
		
		
		int i1,j,k;
		i1=1;j=1;k=1;
		
		
		
		for(Object[] o: resultSet)
		{
	         
	         
	         Bookingshistory bh = (Bookingshistory)o[0];
			 Vehicle vh = (Vehicle)o[1];
		     User us = (User) o[2];
		     Address as = (Address) o[3];
		     
		      System.out.println("stasts" +bh.getBkngStatus()+vh.getVhclName());
		      
		     if ("UPCMNG".equals(bh.getBkngStatus()))
		     {
		    	 String comp[] = new String[8];
		    	 comp[0] = vh.getVhclName();
		    	 comp[1] = (String)fd.format(bh.getBkngFromDate()).toString();
		    	 comp[2] = (String)fd.format(bh.getBkngToDate()).toString();
		    	 comp[3] = (String) vh.getVhclRegistrationNo();
		    	 comp[4] = (String)  us.getUserName();
		    	 comp[5] = (String) as.getAddrLocality();
		    	 comp[6] = Integer.toString(i1);
		    	 comp[7] = (String)bh.getBkngSeq();
		    
			     i1++;
			     
			     System.out.println(comp[0]);
			     upComing.add(comp);
		     }
		    	 
		     
		     else if ("COMP".equals(bh.getBkngStatus()))
		     {
		    	 String comp[] = new String[8];
		    	     comp[0] = vh.getVhclName();
				     comp[1] = (String) fd.format(bh.getBkngFromDate()).toString();
				     comp[2] = (String)fd.format(bh.getBkngToDate()).toString();
				     comp[3] = (String) vh.getVhclRegistrationNo();
				     comp[4] = (String)  us.getUserName();
				     comp[5] = (String) as.getAddrLocality();
				     comp[6] = Integer.toString(j);
				     comp[7] = (String)bh.getBkngSeq();
				     j++;
				     
				     		     
				     completed.add(comp);
		     }
		    	 
		     
		     else if ("CANC".equals(bh.getBkngStatus())) 
		     {
		    	 String comp[] = new String[8];
		    	 comp[0] = vh.getVhclName();
			     comp[1] = (String)fd.format(bh.getBkngFromDate()).toString();
			     comp[2] = (String)fd.format(bh.getBkngToDate()).toString();
			     comp[3] = (String) vh.getVhclRegistrationNo();
			     comp[4] = (String)  us.getUserName();
			     comp[5] = (String) as.getAddrLocality(); 
			     comp[6] = Integer.toString(k);
			     comp[7] = (String)bh.getBkngSeq();
			     k++;
			     
			     Cancelled.add(comp);
		     }
		 
		}
		
		
%>

<h3> Completed Bookings</h3>
<% if (completed.isEmpty()) { %>
<center><b><h4>No Completed Bookings !!!</h4></b></center>
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
<center><h4><b>No Upcoming Bookings !!!</b></h4></center>
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
<th> Cancel Booking </th>
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
<td> 
<form id = "Mybookings" method="POST" ACTION="${pageContext.request.contextPath}/MyBookings">
<input type="hidden" name="DeleteId"  id="DeleteId" value="<%=o[7] %>" />
<input type="button" value="Cancel" onclick="confirm_decision('<%= o[7] %>')"/> 
</form>
</td>            

</tr>

<%} %>
<%} %>
</table><br><br><br>

<h3>Cancelled Bookings</h3>
<% if (Cancelled.isEmpty()) { %>
<center><h4><b>No Cancelled Bookings</b></h4></center>
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