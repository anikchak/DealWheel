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
		%>
		<div class="container">
		<br>
		<br>
		<h3 class="text-center" style="color:#687074;">My Profile Summary</h3>
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
    	<br><br>
    	<form action="${pageContext.request.contextPath}/VendorRegistration" method="post" role="form" id="updateDetailsFormId">
		<!-- Start of Personal Details block -->
		<div class="panel-group vehicleDisplay" style="width:70%;" id="personalDetailsId">
    	<div class="panel panel-default ">
      		<div class="panel-heading">
      			<span style="font-weight: bold;font-size: 16px; color: #687074;text-transform:uppercase;">Details</span>
      		</div>
      		<div class="panel-body">
      			<div class="row" id="editMsg" style="display:none;">
      			<p style="font-size: 11px;color: rgba(217, 83, 79, 1);float:right;margin-right:5px;">All fields are mandatory.</p>
      			</div>
      			<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:5px;">
    			<label for="fullname" style="color: rgba(217, 83, 79, 1);font-size:14px;">Full Name</label>
    			</div>
    			<div class="col-sm-10" style="">
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
					<span class="form-control" style= "font-size: 15px; color: #687074;text-transform: uppercase;" id="fullNameSpan"><%= user.getUserName()%></span>
					<input class="form-control" style= "font-size: 15px; color: #687074;display:none;text-transform: uppercase;" type="text" id="fullName" name="fullName" placeholder="Enter your name" value="<%= user.getUserName()%>">
				</div>
    			<span style="color: rgba(217, 83, 79, 1);font-size:12px;display:none;" id="fullnameMandate">Field cannot be empty</span>
    			</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:0px;">
    			<label for="primaryContact" style="color: rgba(217, 83, 79, 1);font-size:14px;">Primary Contact</label>
    			</div>
    			<div class="col-sm-10" style="">
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></span>
					<span class="form-control" style= "font-size: 15px; color: #687074;text-transform: uppercase;" id="primaryContactSpan" ><%=user.getUserPrimaryContact()%></span>
					<input type="text" class="form-control" style= "font-size: 15px; color: #687074;display:none;text-transform: uppercase;" id="primaryContact" name="primaryContact" value="<%=user.getUserPrimaryContact()%>" placeholder="Enter your primary contact">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:12px;display:none;" id="primaryContactMandate">Field cannot be empty</span>
				</div>
  				</div>
  				  				  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="">
    			<label for="addr1" style="color: rgba(217, 83, 79, 1);font-size:12.5px;">Address Line 1 <span style="font-size:10px;">(or company name)</span></label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-list"></span></span>
					<span class="form-control" style= "font-size: 15px; color: #687074;text-transform: uppercase;" id="addr1Span" ><%= address.getAddrLine1()%></span>
					<input class="form-control" style= "font-size: 15px; color: #687074;display:none;text-transform: uppercase;" type="text" id="addr1" name="addr1" value= "<%= address.getAddrLine1()%>" placeholder="Flat/House No, Floor, Building, Company Name">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:12px;display:none;" id="addr1Mandate">Field cannot be empty</span>
				</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:5px;">
    			<label for="addr2" style="color: rgba(217, 83, 79, 1);font-size:12.5px;">Address Line 2</label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-list"></span></span>
					<span class="form-control" style= "font-size: 15px; color: #687074;text-transform: uppercase;" id="addr2Span" ><%= address.getAddrLine2()%></span>
					<input class="form-control" style= "font-size: 15px; color: #687074;display:none;text-transform: uppercase;" type="text" id="addr2" name="addr2" value= "<%= address.getAddrLine2()%>" placeholder="Colony/Society, Street">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:12px;display:none;" id="addr2Mandate">Field cannot be empty</span>
				</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:5px;">
    			<label for="addr3" style="color: rgba(217, 83, 79, 1);font-size:12.5px;">Address Line 3</label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-list"></span></span>
					<span class="form-control" style= "font-size: 15px; color: #687074;text-transform: uppercase;" id="addr3Span" ><%= address.getAddrLine3()%></span>
					<input class="form-control" style= "font-size: 15px; color: #687074;display:none;text-transform: uppercase;" type="text" id="addr3" name="addr3" value= "<%= address.getAddrLine3()%>" placeholder="Address line 3 (optional)">
				</div>
				</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:5px;">
    			<label for="locality" style="color: rgba(217, 83, 79, 1);font-size:14px;">Locality</label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-map-marker"></span></span>
					<span class="form-control" style= "font-size: 15px; color: #687074;text-transform: uppercase;" id="localitySpan" ><%=address.getAddrLocality()%></span>
					<input class="form-control" style= "font-size: 15px; color: #687074;display:none;text-transform: uppercase;" type="text" id="locality" name="locality" value="<%=address.getAddrLocality()%>" placeholder="Locality">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:12px;display:none;" id="localityMandate">Field cannot be empty</span>
				</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:5px;">
    			<label for="city" style="color: rgba(217, 83, 79, 1);font-size:14px;">Town/City</label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-map-marker"></span></span>
					<span class="form-control" style= "font-size: 15px; color: #687074;text-transform: uppercase;"  id="citySpan"><%=address.getAddrCity()%></span>
					<input class="form-control" style= "font-size: 15px; color: #687074;display:none;text-transform: uppercase;" type="text" name="city"  id="city" value="<%=address.getAddrCity()%>" placeholder="Town/City">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:12px;display:none;" id="cityMandate">Field cannot be empty</span>
				</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:5px;">
    			<label for="state" style="color: rgba(217, 83, 79, 1);font-size:14px;">State</label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-map-marker"></span></span>
					<span class="form-control" style= "font-size: 15px; color: #687074;text-transform: uppercase;" id="stateSpan"><%=address.getAddrState()%></span>
					<input class="form-control" style= "font-size: 15px; color: #687074;display:none;text-transform: uppercase;" type="text" name="state" id="state" value="<%=address.getAddrState()%>" placeholder="State">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:12px;display:none;" id="stateMandate">Field cannot be empty</span>
				</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:5px;">
    			<label for="pincode" style="color: rgba(217, 83, 79, 1);font-size:14px;">Pincode</label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-screenshot"></span></span>
					<span class="form-control" style= "font-size: 15px; color: #687074;text-transform: uppercase;" id="pinCodeSpan" ><%=address.getAddrPinCode()%></span>
					<input class="form-control" style= "font-size: 15px; color: #687074;display:none;text-transform: uppercase;" type="text" id="pinCode" name="pinCode" value="<%=address.getAddrPinCode()%>" placeholder="Pincode">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:12px; display:none;" id="pinCodeMandate">Field cannot be empty</span>
				</div>
  				</div>
  				
      		</div>
      		<div class="row text-center" >
    	 	<button type="button" class="btn btn-info btn-md" id="saveBtn" onclick="saveChanges()" disabled><span class="glyphicon glyphicon-ok"></span> Save</button>
    	 	<button type="button" class="btn btn-info btn-md" id="cancelBtn" onclick="cancelOperation('alert')" style="background-color: rgba(217, 83, 79, 1);display:none;"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
    	 	<button type="button" class="btn btn-info btn-md" id="editBtn" style="background-color: rgba(217, 83, 79, 1);" onclick="editFields()"><span class="glyphicon glyphicon-pencil"></span> Edit</button>
    		</div>
    		<br>
    	</div>
    	</div>
		</form>
		<!-- Empty Error message pop-up starts-->
		<div class="modal fade text-center" id="cancelAlertModalId" role="dialog">
   		 <div class="modal-dialog">
    		 <!-- Modal content-->
     		 <div class="modal-content">
        		<div class="modal-body">
          		<p style="color:#687074;font-weight:bold;font-size:14px;text-transform:uppercase;">All your changes will be lost.</p>
          		<button type="button" class="btn btn-info btn-sm" data-dismiss="modal" onclick="cancelOperation('ok')" style="">Ok</button>
          		<button type="button" class="btn btn-info btn-sm" data-dismiss="modal" style="background-color: rgba(217, 83, 79, 1);">Cancel</button>
        		</div>
      		</div>
    	 </div>
  		</div>
	<!-- Empty Error message pop-up ends -->
    	</div>
    	<!-- My Profile ends -->
    	<!-- My Vehicle Details start -->
    	<div id="vehicleDetails" class="tab-pane fade in active">
    		vehicle details
    	</div>
    	<!-- My Vehicle Details end -->
    	<!-- My Booking History start -->
    	<div id="bookingHistory" class="tab-pane fade ">
    		Booking History
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