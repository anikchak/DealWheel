<%@page import="dao.UserDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="commonResources/CommonViewImports"%>
<!DOCTYPE html>
<html>

<head>
<META Http-Equiv="Cache-Control" Content="no-cache"/>
<META Http-Equiv="Pragma" Content="no-cache"/>
<META Http-Equiv="Expires" Content="0"/>
<link href="css/LandingPageCSS.css" rel="stylesheet" type="text/css" />
<link href="css/ConfirmationPageCSS.css" rel="stylesheet" type="text/css" />
<title>BookMyRide: Booking Confirmation</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="commonResources/CommonJSCSSInclude"%>
<script type="text/javascript">
var pageContext = '<%=request.getContextPath()%>';
</script>

<style>
.navbar-default .navbar-nav > .open > a, .navbar-default .navbar-nav > .open > a:focus, .navbar-default .navbar-nav > .open > a:hover
	{
	background-color:#85b213;
	}

</style>
</head>

<body>
	<% session.setAttribute("currentPage","ConfirmationPage");
	//new CustomerControllerService().cleanBookings();
	String userName = null;
	Vehicle v = null; 
	Bookingshistory bh = null;
	String sessionID = null;
	String vendorName = null;
	String vendorEmail = null;
	String emailUserName = null;
	long tempBookingId = 0;
	session.setAttribute("currentPage", "ConfirmationPage");
	if(session.getAttribute("LoggedInUserDetailsObject")!=null){
	List<User> validUserDetails = (List<User>)session.getAttribute("LoggedInUserDetailsObject");
	if(validUserDetails!=null & validUserDetails.size()>0){
		for(User usr : validUserDetails){
			userName = usr.getUserEmail();
			emailUserName = usr.getUserName();
		}
	}
	}

	if(userName == null){
		response.sendRedirect(pageContext+"/BookingError.jsp");
	}
	
	SimpleDateFormat format = new SimpleDateFormat(GenericConstant.DATEFORMAT);
	long noOfDays = 0;
	String sdtDay=null,sdtMonth=null,sdtYear=null,sdtDateNum=null,endtDay=null,endtMonth=null,endtYear=null,endtDateNum = null;
	if (session.getAttribute(GenericConstant.FROMDATESTRING) != null && session.getAttribute(GenericConstant.TODATESTRING) != null) 
	{
		Date startDate = format.parse((String) session.getAttribute(GenericConstant.FROMDATESTRING));
		Date endDate = format.parse((String) session.getAttribute(GenericConstant.TODATESTRING));
		long duration = endDate.getTime() - startDate.getTime();
		noOfDays = TimeUnit.MILLISECONDS.toDays(duration);
		sdtDay = new SimpleDateFormat("EEE").format(startDate);
		sdtMonth = new SimpleDateFormat("MMM").format(startDate);
		sdtYear = new SimpleDateFormat("yy").format(startDate);
		sdtDateNum = new SimpleDateFormat("dd").format(startDate);
		endtDay = new SimpleDateFormat("EEE").format(endDate);
		endtMonth = new SimpleDateFormat("MMM").format(endDate);
		endtYear = new SimpleDateFormat("yy").format(endDate);
		endtDateNum = new SimpleDateFormat("dd").format(endDate);
	}
%>
	<!-- Wrap all page content here -->
	<div id="wrap" style="min-height:85%;">
		<!-- Nav bar inclusion starts -->
		<%@ include file="commonResources/NavigationBar"%>
		<!-- Nav bar inclusion ends -->
		
		<!-- Begin page content -->
		<div class="container">
			<%	
			String fetchSelectedVehicle = (String)session.getAttribute("tempBookingOrderId");
		    if(fetchSelectedVehicle!=null && !"".equalsIgnoreCase(fetchSelectedVehicle)){
			session.setAttribute("tempLockedVehicle", fetchSelectedVehicle);
			String bookingSequence = null;
			List<Object[]> vehicleDetailsList = (List<Object[]>)CommonUtility.fetchVehicleUsingTempBooking(fetchSelectedVehicle);
			if(vehicleDetailsList!=null && vehicleDetailsList.size()==1){
				v = (Vehicle)((Object)vehicleDetailsList.get(0)[0]);
				ListedVehicle lv = (ListedVehicle)((Object)vehicleDetailsList.get(0)[1]);
				bh = (Bookingshistory)((Object)vehicleDetailsList.get(0)[2]);
				Address a = (Address)((Object)vehicleDetailsList.get(0)[3]);
				User u = (User)((Object)vehicleDetailsList.get(0)[4]);
				bookingSequence = bh.getBkngSeq();
				vendorName = ((User)new UserDAOImpl<User>().findById(a.getUserId().toString())).getUserName();
				vendorEmail =  ((User)new UserDAOImpl<User>().findById(a.getUserId().toString())).getUserEmail();
			%>
			 	 <div id="emailSample" style="display:none">
    	 	 <%-- <span style="color:#687074;font-size:14px;" id="emailBody"><%=BODY%></span> --%>
    		 </div>
  			<div class="panel panel-default">
  			 <div class="panel-heading">
  		 	 <span style="color:#85b213;font-size:16px;font-weight: bold;">Congratulations..!!!</span> 
    	 	 <span style="color:#687074;font-size:14px;">Booking Successful </span>
    	 	 <span style="float:right">
    	 	 <span style="color:rgba(217, 83, 79, 1);">Booking Id:</span>
    	     <span style="color:#687074;font-size:18px;font-weight: bold;"><%=bh.getBkngNumber() %></span>
    	 	 </div>
  			</div>
  			<div class="panel-body" >
  			 <div class="row ">
    	 		<div class="col-md-4 col-sm-4 col-xs-4 text-center">
				<img class="" src="http://www.kcls.org/images/loaders/inspiroo_logo_loader_pop.gif" 
									alt="Logo" style="width:80px;height:80px;"/>
				<span style="color: #687074; font-weight:500;text-transform:uppercase;font-size:12px;" id="vehicleMakeId"><%=lv.getLvclMake() %></span>
				<span style="color: #687074; font-weight:bold;text-transform:uppercase;font-size:14.5px;" id="vehicleNameId"><%=lv.getLvclName() %></span>
				</div>
				<div class="col-md-4 col-sm-4 col-xs-4 text-center" >
					<span class="glyphicon glyphicon-calendar" style="color:#85b213;font-size:12px;"></span> 
    				<span style="color: #687074; font-weight:500;text-transform:uppercase;" id="startDateDetailsId">
    					<span style="font-size:11.5px;"><%=sdtDay%>, </span>
    					<span style="font-size:14px;font-weight:600;"><%=sdtDateNum%> </span>
    					<span style="font-size:12px;"><%=sdtMonth%>'<%=sdtYear%></span>
    				</span>
    				<br>
    				<span style="color: #687074;"> -- <span style="font-weight:bold;font-size:15px;">To</span> --  </span>
    				<br>
    				<span class="glyphicon glyphicon-calendar" style="color:#85b213;font-size:12px;"></span> 
    				<span style="color: #687074; font-weight:500;text-transform:uppercase;" id="endDateDetailsId">
    					<span style="font-size:11.5px;"><%=endtDay%>, </span>
    					<span style="font-size:14px;font-weight:600;"><%=endtDateNum%> </span>
    					<span style="font-size:12px;"><%=endtMonth%>'<%=endtYear%></span>
    				</span>
				</div>
				<div class="col-md-4 col-sm-4 col-xs-4 " >
					<span class="glyphicon glyphicon-map-marker" style="color:#85b213;font-size:14px;"></span> 
    				<span style="color: #687074;font-size:11px;text-transform:uppercase;" id="addressDetailsId" data-toggle="tooltip" title="Pickup Location" data-placement="bottom">
    				<%=a.getAddrLine1() %>,
    				<%=((a.getAddrLine2()!=null && (!a.getAddrLine2().isEmpty() || "null".equalsIgnoreCase(a.getAddrLine2())))?(a.getAddrLine2()+","):"") %>  
    				<%=((a.getAddrLine3()!=null && (!a.getAddrLine3().isEmpty() || "null".equalsIgnoreCase(a.getAddrLine3()) ))?(a.getAddrLine3()+","):"") %><br/>
    				<span style="font-weight:bold;"><%=a.getAddrLocality() %></span>, 
    				<span style="font-size:11px; font-weight:bold;"><%=a.getAddrCity() %></span> - <%=a.getAddrPinCode() %>, <br/>
    				<%=a.getAddrState() %>, 
    				<%=a.getAddrCountry() %> 
    				</span>
				</div>
				<br>
			</div>	
			<div class="row">
				<hr style="border-color:#85b213;width:90%;">
			</div>
			<div class="row">
			 <div class="row">
			    <span style="float:right;padding-right:5%;"><span style="color:#687074;font-size:12px;text-transform:uppercase;">Amount Paid = </span><span id="payableAmount" style="color:#687074;font-size:16px;font-weight:bold;"><span style='font-family:Arial;'>&#8377;</span><%=(noOfDays*v.getVhclPerDayCost())%></span></span><br>
			 </div>
			 <div class="row">
				<span style="float:right;padding-right:5%;"><span style="color:#687074;font-size:12px;text-transform:uppercase;">Refundable Security Deposit <span style="color:rgba(217, 83, 79, 1);font-size:10px;">(to be paid during vehicle pick-up)</span> = </span><span id="securityDepositId" style="color:#687074;font-size:16px;font-weight:bold;"><span style='font-family:Arial;'>&#8377;</span><%=v.getVhclSecurityDeposit() %></span></span><br>
			 </div>
			</div>
  			</div>	
  			<div>
  			<img alt="Booked" src="images/stamp.png" style="width:25%;height:25%;z-index: 1000;position: relative;margin-top: -130px;margin-left: 200px;" />
  			</div>
  			<%
  			}
  			}else{
  				response.sendRedirect(pageContext+"/BookingError.jsp");
  			}
  			%>
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
	
	<script>
	$(document).ready(function(){
		var list = "<%=v.getVhclRegistrationNo()%>,<%=sdtDay%> <%=sdtDateNum%> <%=sdtMonth%>'<%=sdtYear%>,<%=endtDay%> <%=endtDateNum%> <%=endtMonth%>'<%=endtYear%>,<%=vendorName%>";
		$.post(
				"TriggerEmail",
				{
					emailType : "CONFIRM_BOOKING_TO_USER",
					emailAddress : "<%=userName%>",
					list : list				
				},
				function(responseText) {
					
				});
		
		list = "<%=v.getVhclRegistrationNo()%>,<%=sdtDay%> <%=sdtDateNum%> <%=sdtMonth%>'<%=sdtYear%>,<%=endtDay%> <%=endtDateNum%> <%=endtMonth%>'<%=endtYear%>,<%=emailUserName%>";
		$.post(
			"TriggerEmail",
			{
				emailType : "CONFIRM_BOOKING_TO_VENDOR",
				emailAddress : "<%=vendorEmail%>",
				list : list				
			},
			function(responseText) {
				
			});
});
	</script>
	</body>
</html>