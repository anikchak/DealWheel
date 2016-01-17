<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="commonResources/CommonViewImports"%>
<%@ page import="java.math.BigInteger"  %>
<%@ page import="java.text.DateFormat"  %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>

<head>
<link href="css/LandingPageCSS.css" rel="stylesheet" type="text/css" />
<title>DealWheel: My Bookings</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="commonResources/CommonJSCSSInclude"%>
<script type="text/javascript">
var pageContext = '<%=request.getContextPath()%>';
</script>
<script src="js/LandingPageJS.js" type="text/javascript"></script>

<style>
.navbar-default .navbar-nav > .open > a, .navbar-default .navbar-nav > .open > a:focus, .navbar-default .navbar-nav > .open > a:hover
	{
	background-color:#85b213;
	}

</style>
</head>

<body>
	<% 
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0);
	//prevents caching at the proxy server
	String comingFromPage = (String)session.getAttribute("currentPage");
	if("ReviewBooking".equalsIgnoreCase(comingFromPage)){
		String lockedBookingId = (String)session.getAttribute("tempLockedVehicle");
		System.out.println("MyBookings-lockedBookingId: "+lockedBookingId);
		new CustomerControllerService().cleanBookingUsingTempBookingId(lockedBookingId);
	}
	session.setAttribute("currentPage","MyBookings");
	new CustomerControllerService().cleanBookings();
	String userName = null;
	BigInteger userId = null;
	List<Object[]> myBookingsHistoryList = null;
	long tempBookingId = 0;
	session.setAttribute("currentPage", "ConfirmationPage");
	if(session.getAttribute("LoggedInUserDetailsObject")!=null){
	List<User> validUserDetails = (List<User>)session.getAttribute("LoggedInUserDetailsObject");
	if(validUserDetails!=null & validUserDetails.size()>0){
		for(User u : validUserDetails){
			userName = u.getUserEmail();
			userId = u.getUserId();
		}
	}
	}

	if(userName == null){
		response.sendRedirect(pageContext+"/BookingError.jsp");
	}
	else{
		myBookingsHistoryList = (List<Object[]>)CommonUtility.fetchMyBookingsHistory(userId);
		if(myBookingsHistoryList==null ){
			response.sendRedirect(pageContext+"/BookingError.jsp");
		}
	}
	
	
	%>
	<!-- Wrap all page content here -->
	<div id="wrap" style="min-height:85%;">
		<!-- Nav bar inclusion starts -->
		<%@ include file="commonResources/NavigationBar"%>
		<!-- Nav bar inclusion ends -->
		<!-- Begin page content -->

		<div class="container">
			<h3 class="text-center" style="color:#687074;">My Bookings History</h3>
  				<br>
  			<ul class="nav nav-tabs">
    			<li class="active" ><a data-toggle="tab" href="#upcoming" style="color:#687074;">Upcoming Rides</a></li>
    			<li><a data-toggle="tab" href="#completed" style="color:#687074;">Completed Rides</a></li>
    			<li><a data-toggle="tab" href="#cancelled" style="color:#687074;">Cancelled Rides</a></li>
  			</ul>

  <div class="tab-content">
    <div id="upcoming" class="tab-pane fade in active">
      <br>
       <%
         List<Object[]> upcomingBookingsList = new ArrayList();
         List<Object[]> cancelledBookingsList = new ArrayList();
         List<Object[]> completedBookingsList = new ArrayList();
         if(myBookingsHistoryList!=null && !myBookingsHistoryList.isEmpty()){
        	 for(Object[] o: myBookingsHistoryList){
        		 Bookingshistory bh = (Bookingshistory)o[0];
        		 if("UPCOMING".equalsIgnoreCase(bh.getBkngStatus())){
        			 upcomingBookingsList.add(o);
        		 }else if("CANCELLED".equalsIgnoreCase(bh.getBkngStatus())){
        			 cancelledBookingsList.add(o);
        		 }else if("COMPLETED".equalsIgnoreCase(bh.getBkngStatus())){
        			 completedBookingsList.add(o);
        		 }
        	 }
         }else if(myBookingsHistoryList==null ){
        	 response.sendRedirect(pageContext+"/BookingError.jsp");
         }
        		 //Populating upcoming bookings list
        		 if(upcomingBookingsList!=null && !upcomingBookingsList.isEmpty()){
        %>
        <div class="table-responsive">
      	<table class="table table-hover">
      	 <thead>
      	  <tr>
        	<th><span style="color:#687074;font-size:12px;">Booking #</span></th>
        	<th><span style="color:#687074;font-size:12px;">Vehicle Details</span></th>
        	<th><span style="color:#687074;font-size:12px;"><span class="glyphicon glyphicon-calendar" style="color:#687074;font-size:12px;"></span> Booking Dates </span></th>
        	<th><span style="color:#687074;font-size:12px;"><span class="glyphicon glyphicon-map-marker" style="color:#687074;font-size:12px;"></span> Pickup / Dropoff</span></th>
        	<th><span style="color:#687074;font-size:12px;">Amount Paid</span></th>
        	<th><span style="color:#687074;font-size:12px;">Security Deposit</span></th>
        	<th><span style="color:#687074;font-size:12px;">Current Status</span></th>
        	<th><span style="color:#687074;font-size:12px;">Action</span></th>
          </tr>
         </thead>
         <tbody>
        <%
                	 for(Object[] o: upcomingBookingsList){	 
                		 Bookingshistory bh = (Bookingshistory)o[0];
                		 Vehicle v = (Vehicle)o[1];
                		 ListedVehicle lv = (ListedVehicle)o[2];
                		 Address a = (Address)o[3];
                		 User u  = (User)o[4];
                		 User pDetails = (User)o[5];
                		 DateFormat inputFormatter = new SimpleDateFormat("dd-MMM-yy");
                		 String dateFrom = inputFormatter.format(bh.getBkngFromDate());
                		 String dateTo = inputFormatter.format(bh.getBkngToDate());
                		 long duration = bh.getBkngToDate().getTime() - bh.getBkngFromDate().getTime();
          				 long noOfDays = TimeUnit.MILLISECONDS.toDays(duration);
        %>
         	<tr>
         		<td><span style="color:#85b213;font-size:11px;text-transform:uppercase;font-weight:bold;"><%=bh.getBkngNumber() %></span></td>
         		<td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%=lv.getLvclMake() %> <%=lv.getLvclName() %></span></td>
         		<td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%=dateFrom %> - <%=dateTo %></span></td>
         		<td><span style="color:#687074;font-size:11px;text-transform:uppercase;">
         				<%=a.getAddrLine1() %>,
         				<%=((a.getAddrLine2()!=null && (!a.getAddrLine2().isEmpty() || "null".equalsIgnoreCase(a.getAddrLine2())))?(a.getAddrLine2()+","):"") %>  
    					<%=((a.getAddrLine3()!=null && (!a.getAddrLine3().isEmpty() || "null".equalsIgnoreCase(a.getAddrLine3()) ))?(a.getAddrLine3()+","):"") %>
    					<%=a.getAddrLocality() %>, <%=a.getAddrCity() %>, <%=a.getAddrPinCode() %>, <%=a.getAddrState() %>, <%=a.getAddrCountry() %>
    				</span>
         		</td>
         		<td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><span style='font-family:Arial;'>&#8377;</span><%=v.getVhclPerDayCost()*noOfDays %></span></td>
         		<td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><span style='font-family:Arial;'>&#8377;</span><%=v.getVhclSecurityDeposit() %></span></td>
         		<td><span class="label label-success">UPCOMING</span></td>
         		<td><button type="button" class="btn btn-info btn-xs" data-dismiss="modal" style="background-color: rgba(217, 83, 79, 1);" onclick="confirmCancellation('<%=bh.getBkngSeq()%>')">CANCEL</button></td>
         	</tr>
         	
         <%
        		 }
         %>
         </tbody>
      	</table>
       </div>
         <%
         	 }else if(upcomingBookingsList!=null && upcomingBookingsList.isEmpty()){
         %>
         <span style="color:rgba(217, 83, 79, 1);font-size:14px;text-transform:uppercase;">No Upcoming rides.</span> 
         <%
         	 }
          %>
         
    </div>
    <div id="completed" class="tab-pane fade">
      <br>
      <%
        //Populating upcoming bookings list
        if(completedBookingsList!=null && !completedBookingsList.isEmpty()){
       %>
      <div class="table-responsive">
      	<table class="table table-hover">
      	 <thead>
      	  <tr>
        	<th><span style="color:#687074;font-size:12px;">Booking #</span></th>
        	<th><span style="color:#687074;font-size:12px;">Vehicle Details</span></th>
        	<th><span style="color:#687074;font-size:12px;"><span class="glyphicon glyphicon-calendar" style="color:#687074;font-size:12px;"></span> Booking Dates </span></th>
        	<th><span style="color:#687074;font-size:12px;"><span class="glyphicon glyphicon-map-marker" style="color:#687074;font-size:12px;"></span> Pickup / Dropoff</span></th>
        	<th><span style="color:#687074;font-size:12px;">Amount Paid</span></th>
        	<th><span style="color:#687074;font-size:12px;">Security Deposit</span></th>
        	<th><span style="color:#687074;font-size:12px;">Current Status</span></th>
         </tr>
         </thead>
         <tbody>
          <%
                	 for(Object[] o: completedBookingsList){	 
                		 Bookingshistory bh = (Bookingshistory)o[0];
                		 Vehicle v = (Vehicle)o[1];
                		 ListedVehicle lv = (ListedVehicle)o[2];
                		 Address a = (Address)o[3];
                		 User u  = (User)o[4];
                		 User pDetails = (User)o[5];
                		 DateFormat inputFormatter = new SimpleDateFormat("dd-MMM-yy");
                		 String dateFrom = inputFormatter.format(bh.getBkngFromDate());
                		 String dateTo = inputFormatter.format(bh.getBkngToDate());
                		 long duration = bh.getBkngToDate().getTime() - bh.getBkngFromDate().getTime();
          				 long noOfDays = TimeUnit.MILLISECONDS.toDays(duration);
        %>
         	<tr>
         		<td><span style="color:#85b213;font-size:11px;text-transform:uppercase;font-weight:bold;"><%=bh.getBkngNumber() %></span></td>
         		<td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%=lv.getLvclMake() %> <%=lv.getLvclName() %></span></td>
         		<td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%=dateFrom %> - <%=dateTo %></span></td>
         		<td><span style="color:#687074;font-size:11px;text-transform:uppercase;">
         				<%=a.getAddrLine1() %>,
         				<%=((a.getAddrLine2()!=null && (!a.getAddrLine2().isEmpty() || "null".equalsIgnoreCase(a.getAddrLine2())))?(a.getAddrLine2()+","):"") %>  
    					<%=((a.getAddrLine3()!=null && (!a.getAddrLine3().isEmpty() || "null".equalsIgnoreCase(a.getAddrLine3()) ))?(a.getAddrLine3()+","):"") %>
    					<%=a.getAddrLocality() %>, <%=a.getAddrCity() %>, <%=a.getAddrPinCode() %>, <%=a.getAddrState() %>, <%=a.getAddrCountry() %>
    				</span>
         		</td>
         		<td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><span style='font-family:Arial;'>&#8377;</span><%=v.getVhclPerDayCost()*noOfDays %></span></td>
         		<td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><span style='font-family:Arial;'>&#8377;</span><%=v.getVhclSecurityDeposit() %></span></td>
         		<td><span class="label label-success">COMPLETED</span></td>
         	</tr>
         <%
        		 }
         %>
         </tbody>
      	</table>
      </div>
      <%
         	 }else if(completedBookingsList!=null && completedBookingsList.isEmpty()){
         %>
         <span style="color:rgba(217, 83, 79, 1);font-size:14px;text-transform:uppercase;">No Completed rides.</span> 
         <%
         	 }
         %>
    </div>
    <div id="cancelled" class="tab-pane fade">
    <br>
      <%
        //Populating upcoming bookings list
        if(cancelledBookingsList!=null && !cancelledBookingsList.isEmpty()){
       %>
      <div class="table-responsive">
      	<table class="table table-hover">
      	 <thead>
      	  <tr>
        	<th><span style="color:#687074;font-size:12px;">Booking #</span></th>
        	<th><span style="color:#687074;font-size:12px;">Vehicle Details</span></th>
        	<th><span style="color:#687074;font-size:12px;"><span class="glyphicon glyphicon-calendar" style="color:#687074;font-size:12px;"></span> Booking Dates </span></th>
        	<th><span style="color:#687074;font-size:12px;"><span class="glyphicon glyphicon-map-marker" style="color:#687074;font-size:12px;"></span> Pickup / Dropoff</span></th>
        	<th><span style="color:#687074;font-size:12px;">Refund Amount</span></th>
        	<th><span style="color:#687074;font-size:12px;">Current Status</span></th>
         </tr>
         </thead>
         <tbody>
          <%
                	 for(Object[] o: cancelledBookingsList){	 
                		 Bookingshistory bh = (Bookingshistory)o[0];
                		 Vehicle v = (Vehicle)o[1];
                		 ListedVehicle lv = (ListedVehicle)o[2];
                		 Address a = (Address)o[3];
                		 User u  = (User)o[4];
                		 User pDetails = (User)o[5];
                		 DateFormat inputFormatter = new SimpleDateFormat("dd-MMM-yy");
                		 String dateFrom = inputFormatter.format(bh.getBkngFromDate());
                		 String dateTo = inputFormatter.format(bh.getBkngToDate());
                		 long duration = bh.getBkngToDate().getTime() - bh.getBkngFromDate().getTime();
          				 long noOfDays = TimeUnit.MILLISECONDS.toDays(duration);
        %>
         	<tr>
         		<td><span style="color:#85b213;font-size:11px;text-transform:uppercase;font-weight:bold;"><%=bh.getBkngNumber() %></span></td>
         		<td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%=lv.getLvclMake() %> <%=lv.getLvclName() %></span></td>
         		<td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%=dateFrom %> - <%=dateTo %></span></td>
         		<td><span style="color:#687074;font-size:11px;text-transform:uppercase;">
         				<%=a.getAddrLine1() %>,
         				<%=((a.getAddrLine2()!=null && (!a.getAddrLine2().isEmpty() || "null".equalsIgnoreCase(a.getAddrLine2())))?(a.getAddrLine2()+","):"") %>  
    					<%=((a.getAddrLine3()!=null && (!a.getAddrLine3().isEmpty() || "null".equalsIgnoreCase(a.getAddrLine3()) ))?(a.getAddrLine3()+","):"") %>
    					<%=a.getAddrLocality() %>, <%=a.getAddrCity() %>, <%=a.getAddrPinCode() %>, <%=a.getAddrState() %>, <%=a.getAddrCountry() %>
    				</span>
         		</td>
         		<td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><span style='font-family:Arial;'>&#8377;</span><%=v.getVhclPerDayCost()*noOfDays %></span></td>
         		<td><span class="label label-danger">CANCELLED</span></td>
         	</tr>
         <%
        		 }
         %>
         </tbody>
      	</table>
      </div>
      <%
         	 }else if(cancelledBookingsList!=null && cancelledBookingsList.isEmpty()){
         %>
         <span style="color:rgba(217, 83, 79, 1);font-size:14px;text-transform:uppercase;">No Cancelled rides.</span> 
         <%
         	 }
         %>
    </div>
   </div>	
  	</div>
	</div>
	<!--/wrap-->
	<!-- Footer inclusion starts -->
	<%@ include file="commonResources/Footer"%>
	<!-- Footer inclusion ends -->
	<ul class="nav pull-right scroll-top">
		<li><a href="#" title="Scroll to top"><span
				class="glyphicon glyphicon-menu-up"></span></a></li>
	</ul>

	<!-- Including Modal Windows for Signup and Login -->
	<%@ include file="commonResources/CommonModalDivBlocks"%>
	
	<!-- Including confirm cancellation pop-up -->
	<div class="modal fade text-center" id="confirmCancelId" role="dialog">
    <div class="modal-dialog">
     <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-body">
          <p style="color:#687074;font-weight:600;font-size:13.5px;text-transform:uppercase;">Sure you want to cancel this upcoming booking ??</p>
          <button type="button" class="btn btn-info btn-md" data-dismiss="modal" style="background-color: rgba(217, 83, 79, 1);" onclick="cancelBooking('YES')">YES</button>
          <button type="button" class="btn btn-info btn-md" data-dismiss="modal" style="background-color: #85b213;" onclick="cancelBooking('NO')">NO</button>
        </div>
      </div>
    </div>
    </div>
	<!-- Confirm cancellation pop-up ends -->
	
	<!-- Form to submit cancel request starts -->
	<form method="post" action="${pageContext.request.contextPath}/MyBookings" id="confirmCancelFormId">
		<input type="hidden" name="tempBookingName" id="tempBookingId"/>
	</form>
	<!-- Form to submit cancel request ends -->
	<!-- Including Common JS -->
	<script>
	var propCities = '<%= CommonUtility.getValuesFromProperties("activeCities")%>';
	</script>
	<script src="js/CommonJS.js" type="text/javascript"></script>
	
	<script>
		
		function confirmCancellation(bookingSeq){
			$("#tempBookingId").val(bookingSeq);
			$("#confirmCancelId").modal({
				backdrop: 'static',
				keyboard: false
			});
		}
		function cancelBooking(val){
			if(val=='YES'){
				$("#confirmCancelFormId").submit();
			}else{
				$("#confirmCancelId").hide();
			}
		}
		
	</script>
	<style>
		.nav-tabs{border-bottom:1px solid #85b213}
		.nav-tabs > li.active > a, .nav-tabs > li.active > a:hover, .nav-tabs > li.active > a:focus{
		border-right:1px solid #85b213;
		border-left:1px solid #85b213;
		border-top:1px solid #85b213;
		}
	</style>
	
 </body>
</html>