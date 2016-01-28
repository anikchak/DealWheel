<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="commonResources/CommonViewImports"%>
<%@page import="dao.VehicleDAOImpl"%>
<%@page import="dao.ListedVehicleDAOImpl"%>
<%@page import="dao.BookingHistoryDAOImpl"%>
<!DOCTYPE html>
<html>

<head>
<META Http-Equiv="Cache-Control" Content="no-cache"/>
<META Http-Equiv="Pragma" Content="no-cache"/>
<META Http-Equiv="Expires" Content="0"/>
<link href="css/LandingPageCSS.css" rel="stylesheet" type="text/css" />
<title>DealWheel: Welcome Vendor</title>
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
	String comingFromPage = (String)session.getAttribute("currentPage");
	if("ReviewBooking".equalsIgnoreCase(comingFromPage)){
		String lockedBookingId = (String)session.getAttribute("tempLockedVehicle");
		System.out.println("Landing Page-lockedBookingId: "+lockedBookingId);
		new CustomerControllerService().cleanBookingUsingTempBookingId(lockedBookingId);
	}
	session.setAttribute("currentPage","VendorHome");
	new CustomerControllerService().cleanBookings();
	%>
	<!-- Wrap all page content here -->
	<div id="wrap" style="min-height:85%;">
		<!-- Nav bar inclusion starts -->
		<%@ include file="commonResources/NavigationBar"%>
		<!-- Nav bar inclusion ends -->
		<!-- Begin page content -->
		<% 
		User user = (User)session.getAttribute(GenericConstant.USER_MODEL);
		Address address = (Address)session.getAttribute(GenericConstant.ADDRESS_MODEL);
		System.out.println(("0".equalsIgnoreCase(user.getUserSecondaryContact().toString())));
		%>
		<div class="container">
		<br>
		<br>
		<h3 class="text-center" style="color:#687074;text-transform: uppercase;">Welcome <%=user.getUserName() %></h3>
		<br>
  			<ul class="nav nav-tabs">
  				<li><a data-toggle="tab" href="#myProfile" style="color:#687074;">My Profile</a></li>
    			<li class="active"><a data-toggle="tab" href="#vehicleDetails" style="color:#687074;">My Vehicle Details</a></li>
    			<li><a data-toggle="tab" href="#bookingHistory" style="color:#687074;">Booking History</a></li>
    			<li><a data-toggle="tab" href="#paymentHistory" style="color:#687074;">Payment History</a></li>
  			</ul>
  		<!-- My Profile starts -->	
		<div class="tab-content">
    	<div id="myProfile" class="tab-pane fade ">
    	<%@include file="commonResources/VendorMyProfile" %>
		</div>
    	<!-- My Profile ends -->
    	<!-- My Vehicle Details start -->
    	<div id="vehicleDetails" class="tab-pane fade in active">
    		<!-- Display Listed Vehicles starts -->
    		<%@include file="commonResources/VendorDisplayListedVehicles" %>
			<!-- Display Listed Vehicles ends -->
			<!-- Add Vehicle Block Starts -->
			<%@include file="commonResources/AddVehicleBlock" %>
			<!-- Add Vehicle Modal Ends -->
    	</div>
    	<!-- My Vehicle Details end -->
    	<!-- My Booking History start -->
    	<div id="bookingHistory" class="tab-pane fade ">
    	<br><br>
    	 <% 
    		List<Object[]> bookingDetailList = new BookingHistoryDAOImpl<Bookingshistory>().getBookingDetailsForVendorId(user.getUserId());
    		if(bookingDetailList!=null && bookingDetailList.size()>0){
    	%>	
    	 <div class="table-responsive">
      	 <form action="${pageContext.request.contextPath}/CancelBookingForVendor" method="post" id="vendorBookingCancelForm">
      	 <table class="table table-hover">
      	 <thead>
      	  <tr>
        	<th class="text-center"><span style="color:#687074;font-size:12px;">Booking #</span></th>
        	<th class="text-center"><span style="color:#687074;font-size:12px;">Vehicle Details</span></th>
        	<th class="text-center"><span style="color:#687074;font-size:12px;">Registration #</span></th>
        	<th class="text-center"><span style="color:#687074;font-size:12px;"><span class="glyphicon glyphicon-calendar" style="color:#687074;font-size:12px;"></span> Booking Dates </span></th>
        	<th class="text-center"><span style="color:#687074;font-size:12px;">Booked On</span></th>
        	<th class="text-center"><span style="color:#687074;font-size:12px;">Amount Paid</span></th>
        	<th class="text-center"><span style="color:#687074;font-size:12px;">Security Deposited</span></th>
        	<th class="text-center"><span style="color:#687074;font-size:12px;">Current Status</span></th>
        	<th class="text-center"><span style="color:#687074;font-size:12px;">Action</span></th>
          </tr>
         </thead>
         <% 
    		for(Object[] bookingDetail : bookingDetailList ){ 
    			long duration = ((((Date)bookingDetail[4]).getTime()-((Date)bookingDetail[3]).getTime())/ (1000 * 60 * 60 * 24));
    	 %>
         <tbody class="text-center">
          <tr>
           <td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%= (String)bookingDetail[10]%></span></td>
           <td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%= (String)bookingDetail[0]%> <%= (String)bookingDetail[1]%></span></td>
           <td><span style="color:#85b213;font-size:11px;text-transform:uppercase;font-weight:bold;"><%=(String)bookingDetail[2]%></span></td>
           <td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%=(new SimpleDateFormat("dd/MM/yyyy").format((Date)bookingDetail[3]).toString())%> - <%=(new SimpleDateFormat("dd/MM/yyyy").format((Date)bookingDetail[4]).toString()) %></span></td>
           <td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%= new SimpleDateFormat("dd/MM/yyyy").format((Date)bookingDetail[5])%></span></td>
           <td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%= duration*((Integer)bookingDetail[6]).longValue()%></span></td>
           <td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%= ((Integer)bookingDetail[9]).longValue()%></span></td>
           <td>
           <%
           String status  = (String)bookingDetail[7];
           if("UPCOMING".equalsIgnoreCase(status)) 
           {
           %>
           <span class="label label-success">UPCOMING</span>
           <%
           }else if("COMPLETED".equalsIgnoreCase(status)){
        	%>
            <span class="label label-warning">COMPLETED</span>
           <%
           }else if("CANCELLED".equalsIgnoreCase(status)){
           %>
            <span class="label label-danger">CANCELLED</span>
           <%
           }else if("VENDORCANCELLED".equalsIgnoreCase(status)){
           %>
           <span class="label label-danger">VENDOR CANCELLED</span>
           <%
        	}
           %>
           </td>
           <td>
           <%
            if("UPCOMING".equalsIgnoreCase(status)) 
           {
           %>
           <button type="button" class="btn btn-xs btn-danger" data-dismiss="modal" style="background-color: rgba(217, 83, 79, 1);" onclick="vendorCancellation('<%=(String)bookingDetail[8] %>')">CANCEL</button>
           <%
        	}
           %>
           </td>
          </tr>
         </tbody>
         <%
    		}
          %>
         </table>
         <input type="hidden" name="bookingToCancel" id="bookingToCancel">
         
         <!-- Confirm Booking cancellation starts -->
         <div class="modal fade text-center" id="confirmVendorCancelId" role="dialog">
    		<div class="modal-dialog">
     		<!-- Modal content-->
      		<div class="modal-content">
        		<div class="modal-body">
          		<p style="color:#687074;font-weight:600;font-size:13.5px;text-transform:uppercase;">Sure you want to cancel this upcoming booking ?? This calls for penalty.</p>
          		<button type="button" class="btn btn-info btn-md" data-dismiss="modal" style="background-color: rgba(217, 83, 79, 1);" onclick="cancelVendorBooking('YES')">YES</button>
          		<button type="button" class="btn btn-info btn-md" data-dismiss="modal" style="background-color: #85b213;" onclick="cancelVendorBooking('NO')">NO</button>
        		</div>
      		</div>
    		</div>
    	 </div>
         <!-- Confirm Booking cancellation ends -->
         
         </form>
         </div>
         <%
 			}else{
    	 %>
         <br>
    	  <span style="color:rgba(217, 83, 79, 1);font-size:14px;text-transform:uppercase;">No booking records found.</span>
    	 <br>
         <%
    		}
         %>
    	</div>
    	<!-- My Booking History end -->
    	<!-- My Payment History start -->
    	<div id="paymentHistory" class="tab-pane fade ">
    		Payment History
    	</div>
    	<!-- My Payment History end -->
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

	<!-- Including Common JS -->
	<script>
	var propCities = '<%= CommonUtility.getValuesFromProperties("activeCities")%>';
	</script>
	<script src="js/CommonJS.js" type="text/javascript"></script>
	<script src="js/VendorHomeJS.js" type="text/javascript"></script>
	<style>
	  	.nav-tabs{border-bottom:1px solid #85b213}
		.nav-tabs > li.active > a, .nav-tabs > li.active > a:hover, .nav-tabs > li.active > a:focus{
		border-right:1px solid #85b213;
		border-left:1px solid #85b213;
		border-top:1px solid #85b213;
		}
		.panel-default{border-color:#85b213}
		.panel-default>.panel-heading{border-bottom:1px solid #85b213}
		.vehicleDisplay{padding-right:15px;padding-left:15px;margin-right:auto;margin-left:auto}@media (min-width:768px){.vehicleDisplay{width:750px}}@media (min-width:992px){.vehicleDisplay{width:750px}}@media (min-width:1200px){.vehicleDisplay{width:750px}}
	 </style>
	
	
 </body>
</html>