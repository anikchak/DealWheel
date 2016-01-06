<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="commonResources/CommonViewImports"%>
<!DOCTYPE html">
<html>
<head>
<title>Driveholic: Booking Summary</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="commonResources/CommonJSCSSInclude"%>

<style>
.navbar-default .navbar-nav>.open>a, .navbar-default .navbar-nav>.open>a:focus,
	.navbar-default .navbar-nav>.open>a:hover {
	background-color: #85b213;
}
</style>
</head>
<body>
<%
//allow access only if session exists

String pagecontext = request.getContextPath();
String userName = null;
String sessionID = null;
long tempBookingId = 0;
session.setAttribute("currentPage", "ReviewBooking");
if(session.getAttribute("LoggedInUserDetailsObject")!=null){
List<User> validUserDetails = (List<User>)session.getAttribute("LoggedInUserDetailsObject");
if(validUserDetails!=null & validUserDetails.size()>0){
	for(User u : validUserDetails){
		userName = u.getUserEmail();
	}
}
}

if(userName == null){
	response.sendRedirect(pageContext+"/BookingError.jsp");
}
%>

<!-- Wrap all page content here -->
	<div id="wrap" style="min-height: 85%;">
		<!-- Nav bar inclusion starts -->
		<%@ include file="commonResources/NavigationBar"%>
		<!-- Nav bar inclusion ends -->
		<%
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
	
			String fetchSelectedVehicle = (String)request.getParameter("fetchVehicleSelected");
		    if(fetchSelectedVehicle!=null && !"".equalsIgnoreCase(fetchSelectedVehicle)){
			session.setAttribute("tempLockedVehicle", fetchSelectedVehicle);
			List<Object[]> vehicleDetailsList = (List<Object[]>)CommonUtility.fetchVehicleUsingTempBooking(fetchSelectedVehicle);
			if(vehicleDetailsList!=null && vehicleDetailsList.size()==1){
				Vehicle v = (Vehicle)((Object)vehicleDetailsList.get(0)[0]);
				ListedVehicle lv = (ListedVehicle)((Object)vehicleDetailsList.get(0)[1]);
				Bookingshistory bh = (Bookingshistory)((Object)vehicleDetailsList.get(0)[2]);
				Address a = (Address)((Object)vehicleDetailsList.get(0)[3]);
				User u = (User)((Object)vehicleDetailsList.get(0)[4]);
%>

	<!-- Begin page content -->
		<div class="container" id="bookingSummaryMainBlockId">
			
			<div style="border:1px solid #85b213; border-radius:4px;">
				<div class="row text-center ">
				<br>
				<span style="color: #687074; font-weight:bold;text-transform:uppercase;font-size:17.5px;padding-top:20px;text-decoration:underline;">Booking Summary</span>
				<br/><br/>
				</div>
				<div class="row container text-center">
				<p style="color: #687074; text-transform:uppercase;font-size:12px;">Proceed with Booking before your vehicle zooms away</p> 
					<div class="progress" style="width:90%; margin-left: 5%;">
    				<div class="progress-bar progress-bar-success" role="progressbar" id="progressBarId" aria-valuenow="60" aria-valuemin="0" 
         			aria-valuemax="100" style="width: 100%;">
    			</div>
			</div>
				</div>
				<div class="row text-center">
				<img class="" src="http://www.kcls.org/images/loaders/inspiroo_logo_loader_pop.gif" 
									alt="Logo" style="width:100px;height:100px;"/>
				<span style="color: #687074; font-weight:500;text-transform:uppercase;font-size:14px;" id="vehicleMakeId"><%=lv.getLvclMake() %></span>
				<span style="color: #687074; font-weight:bold;text-transform:uppercase;font-size:16.5px;" id="vehicleNameId"><%=lv.getLvclName() %></span>
				<br>
				<hr style="border-color:#85b213;width:50%;">
				</div>
				<div class="row text-center">
					<span class="glyphicon glyphicon-calendar" style="color:#85b213;font-size:16px;"></span> 
    						<span style="color: #687074; font-weight:500;text-transform:uppercase;" id="startDateDetailsId">
    							<span style="font-size:13.5px;"><%=sdtDay%>, </span>
    							<span style="font-size:18px;font-weight:600;"><%=sdtDateNum%> </span>
    							<span style="font-size:16px;"><%=sdtMonth%>'<%=sdtYear%></span>
    						</span>
    					<span style="padding-left:15px;padding-right:15px;color: #687074;"> -- <span style="font-weight:bold;font-size:15px;">To</span> --  </span>
    				  	<span class="glyphicon glyphicon-calendar" style="color:#85b213;font-size:16px;"></span> 
    						<span style="color: #687074; font-weight:500;text-transform:uppercase;" id="endDateDetailsId">
    							<span style="font-size:13.5px;"><%=endtDay%>, </span>
    							<span style="font-size:18px;font-weight:600;"><%=endtDateNum%> </span>
    							<span style="font-size:16px;"><%=endtMonth%>'<%=endtYear%></span>
    						</span>
    				<hr style="border-color:#85b213;width:50%;">	
    			</div>
    			<div class="row text-center">
    				<span class="glyphicon glyphicon-map-marker" style="color:#85b213;font-size:18px;"></span> 
    				<span style="color: #687074;font-size:12.8px;text-transform:uppercase;" id="addressDetailsId">
    				<%=a.getAddrLine1() %>,
    				<%=((a.getAddrLine2()!=null && (!a.getAddrLine2().isEmpty() || "null".equalsIgnoreCase(a.getAddrLine2())))?(a.getAddrLine2()+","):"") %>  
    				<%=((a.getAddrLine3()!=null && (!a.getAddrLine3().isEmpty() || "null".equalsIgnoreCase(a.getAddrLine3()) ))?(a.getAddrLine3()+","):"") %><br/>
    				<span style="font-weight:bold;"><%=a.getAddrLocality() %></span>, 
    				<span style="font-size:16px; font-weight:bold;"><%=a.getAddrCity() %></span> - <%=a.getAddrPinCode() %>, <br/>
    				<%=a.getAddrState() %>, 
    				<%=a.getAddrCountry() %> 
    				</span>
    			  <hr style="border-color:#85b213;width:50%;">
    			</div>
    			<div class="row " style="">
    				<span style="color:#687074;font-weight:500;font-size:13.5px;text-transform:uppercase;margin-left:31%;">Per Day Cost = </span><span id="perDayCostId" style="color:#687074;font-weight:bold;font-size:15.5px;"><%=v.getVhclPerDayCost() %></span><br>
    				<span style="color:#687074;font-weight:500;font-size:13.5px;text-transform:uppercase;margin-left:31%;">Refundable Security Deposit = </span><span id="securityDepositId" style="color:#687074;font-weight:bold;font-size:15.5px;"><%=v.getVhclSecurityDeposit() %></span><br>
    				<span style="color:#687074;font-weight:500;font-size:13.5px;text-transform:uppercase;margin-left:31%;">Number of Booking Days = </span><span id="noOfDaysId" style="color:#687074;font-weight:bold;font-size:15.5px;"><%=noOfDays %></span><br>
    				<span style="color:#687074;font-weight:500;font-size:13.5px;text-transform:uppercase;margin-left:31%;">Payable Amount <span style="color: rgba(217, 83, 79, 1);font-size:12px;">(No. of Days * Per Day Cost)</span> = </span><span id="payableAmount" style="color:#687074;font-weight:bold;font-size:15.5px;"><%=(noOfDays*v.getVhclPerDayCost())%></span><br>
    				<hr style="border-color:#85b213;width:50%;">
    			</div>
    			<div class="row text-center" >
    				<button type="button" class="btn btn-info btn-md" id="paymentBtnId" onclick="proceedWithPayment()">Proceed to Payment</button>
    				<button type="button" class="btn btn-info btn-md" id="modifyBtnId" style="background-color: rgba(217, 83, 79, 1);" onclick="modifySearchCriteriaInReview()">+ Modify Search</button>
    				<hr style="border-color:#fff;width:50%;">
    			</div>
			</div>
			<!-- Modify Search Criteria Starts-->
			<div id="reviewModifyDivId" style="display:none;">
				<br>
				<%@ include file="commonResources/SearchCriteriaLayout"%>
				<hr style="border-color:#fff;width:50%;">
			</div>
			<!-- Modify Search Criteria ends -->
		</div>
		
<%			   }
		}
%>

	<!-- Footer inclusion starts -->
	<%@ include file="commonResources/Footer"%>
	<!-- Footer inclusion ends -->
	
</div><!--/wrap-->
		

	<ul class="nav pull-right scroll-top">
		<li>
			<a href="#" title="Scroll to top">
				<span class="glyphicon glyphicon-menu-up"></span>
			</a>
		</li>
	</ul>
	
  <!-- Time out Modal starts-->
  <div class="modal fade text-center" id="sessionTimeOutModalId" role="dialog">
    <div class="modal-dialog">
     <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-body">
          <p style="color:#687074;font-weight:500;font-size:13.5px;text-transform:uppercase;">Oopss..!! Your session has timed out.</p>
          <button type="button" class="btn btn-info btn-md" data-dismiss="modal" style="background-color: rgba(217, 83, 79, 1);" onclick="sessionTimedOut()">OK</button>
        </div>
      </div>
    </div>
  </div>
  <!-- Time out Modal ends -->
  <!-- Navigate to confirmation page starts -->
  <form method="post" id="confirmationSummaryFormId" action="${pageContext.request.contextPath}/ConfirmationSummary">
	<!-- <input type="hidden" name="vehicleDetail" id="vehicleDetailId"></input> -->
		<input type="hidden" value="<%=fetchSelectedVehicle%>" name="tempSelectedVehicle" id="tempSelectedVehicleId"></input>
		<input type="hidden" name="orderLocationName" id="orderLocationId"></input>
  </form>
  <!-- Navigate to confirmation page ends -->
  <!-- Logout form -->
	<form method="post" id="logoutFormId" action="${pageContext.request.contextPath}/Logout">
		<input type="hidden" value="<%=fetchSelectedVehicle%>" name="fetchSelectedVehicle" id="fetchSelectedVehicleId"></input>
	</form>
  <!-- Logout form ends -->
  
	<!-- Including Modal Windows for Signup and Login/Logout -->
	<%@ include file="commonResources/CommonModalDivBlocks"%>

	<script>
	var propCities = '<%= CommonUtility.getValuesFromProperties("activeCities")%>';
	</script>
	<script src="js/ReviewBookingJS.js" type="text/javascript"></script>
	<!-- Including Common JS -->
	<script src="js/CommonJS.js" type="text/javascript"></script>

</body>
</html>