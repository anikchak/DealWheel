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
<title>DealWheel: Vendor Registration</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="commonResources/CommonJSCSSInclude"%>
<script type="text/javascript">
var pageContext = '<%=request.getContextPath()%>';

</script>
<script src="js/LandingPageJS.js" type="text/javascript"></script>
<script src="js/VendorRegistration.js" type="text/javascript"></script>

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
	session.setAttribute("currentPage","VendorRegistration");
	new CustomerControllerService().cleanBookings();
	%>
	<!-- Wrap all page content here -->
	<div id="wrap" style="min-height:85%;">
		<!-- Nav bar inclusion starts -->
		<%@ include file="commonResources/NavigationBar"%>
		<!-- Nav bar inclusion ends -->
		<!-- Begin page content -->

		<div class="container">
		<br>
		<br>
		<form action="${pageContext.request.contextPath}/VendorRegistration" method="post" role="form" id="registrationFormId">
		<!-- Start of Personal Details block -->
		<div class="panel-group vehicleDisplay" style="width:70%;" id="personalDetailsId">
    	<div class="panel panel-default ">
      		<div class="panel-heading">
      			<span style="font-weight: bold;font-size: 16px; color: #687074;text-transform:uppercase;">Personal Details</span>
      		</div>
      		<div class="panel-body">
      		<div class="row">
      		<p style="font-size: 11px;color: rgba(217, 83, 79, 1);float:right;margin-right:5px;">Fields marked (*) are mandatory.</p>
      		</div>
      		
      			<div class="form-group row">
      			<div class="col-sm-2 " style="">
    			<label for="email" style="color: rgba(217, 83, 79, 1);font-size:14px;">Username/Email</label>
    			</div>
    			<div class="col-sm-8 " >
    			<span class="" style= "font-size: 15px; color: #687074;text-transform:uppercase;" id="email" > <%= session.getAttribute("email") %> </span>
    			</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:5px;">
    			<label for="fullname" style="color: rgba(217, 83, 79, 1);font-size:14px;">Full Name*</label>
    			</div>
    			<div class="col-sm-10" style="">
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
					<input class="form-control" style= "font-size: 15px; color: #687074;text-transform:uppercase;" type="text" id="fullName" name="fullName" placeholder="Enter your name">
				</div>
    			<span style="color: rgba(217, 83, 79, 1);font-size:12px;display:none;" id="fullnameMandate">Field cannot be empty</span>
				
    			</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:0px;">
    			<label for="gender" style="color: rgba(217, 83, 79, 1);font-size:14px;">Gender</label>
    			</div>
    			<div class="col-sm-10" style="">
    			<input type="radio" name="gender" value="Male" class="" style= "color: #687074;text-transform:uppercase;"> Male</input>
    			<input type="radio" name="gender" value="Female" class="" style= "color: #687074;text-transform:uppercase;"> Female</input>
    			</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:5px;">
    			<label for="dob" style="color: rgba(217, 83, 79, 1);font-size:14px;">Date of Birth</label>
    			</div>
    			<div class="col-sm-10" style="">
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
					<input type="text" class="form-control" id="dob" name="dob" placeholder="Enter your date of birth (dd/mm/yyyy)" style= "font-size: 15px; color: #687074;text-transform:uppercase;">
				</div>
				</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:0px;">
    			<label for="primaryContact" style="color: rgba(217, 83, 79, 1);font-size:14px;">Primary Contact*</label>
    			</div>
    			<div class="col-sm-10" style="">
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></span>
					<input type="text" class="form-control" id="primaryContact" name="primaryContact" placeholder="Enter your primary contact" style= "font-size: 15px; color: #687074;text-transform:uppercase;">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:12px;display:none;" id="primaryContactMandate">Field cannot be empty</span>
				</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:0px;">
    			<label for="secondaryContact" style="color: rgba(217, 83, 79, 1);font-size:14px;">Secondary Contact</label>
    			</div>
    			<div class="col-sm-10" style="">
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-phone-alt"></span></span>
					<input type="text" class="form-control" id="secondaryContact" name="secondaryContact" placeholder="Enter your secondary contact" style= "font-size: 15px; color: #687074;text-transform:uppercase;">
				</div>
				</div>
  				</div>
      		</div>
    	</div>
		</div>
		<!-- End of Personal Details Block -->
		
		<!-- Start of Address Block -->
		<div class="panel-group vehicleDisplay" style="width:70%;" id="addressDetailsId">
    	<div class="panel panel-default ">
      		<div class="panel-heading">
      			<span style="font-weight: bold;font-size: 16px; color: #687074;text-transform:uppercase;">Adrress Details</span>
      		</div>
      		<div class="panel-body">
      		<div class="row">
      		<p style="font-size: 11px;color: rgba(217, 83, 79, 1);float:right;margin-right:5px;">Fields marked (*) are mandatory.</p>
      		</div>
      		
      			<div class="form-group row">
      			<div class="col-sm-2 " style="">
    			<label for="addr1" style="color: rgba(217, 83, 79, 1);font-size:12.5px;">Address Line 1* <span style="font-size:10px;">(or company name)</span></label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-list"></span></span>
					<input class="form-control" style= "font-size: 15px; color: #687074;text-transform: uppercase;" type="text" id="addr1" name="addr1" placeholder="Flat/House No, Floor, Building, Company Name">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:12px;display:none;" id="addr1Mandate">Field cannot be empty</span>
				</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:5px;">
    			<label for="addr2" style="color: rgba(217, 83, 79, 1);font-size:12.5px;">Address Line 2*</label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-list"></span></span>
					<input class="form-control" style= "font-size: 15px; color: #687074;text-transform: uppercase;" type="text" id="addr2" name="addr2" placeholder="Colony/Society, Street">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:12px;display:none;" id="addr2Mandate">Field cannot be empty</span>
				</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:5px;">
    			<label for="addr3" style="color: rgba(217, 83, 79, 1);font-size:13px;">Address Line 3</label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-list"></span></span>
					<input class="form-control" style= "font-size: 15px; color: #687074;text-transform: uppercase;" type="text" id="addr3" name="addr3" placeholder="Address line 3 (optional)">
				</div>
				</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:5px;">
    			<label for="locality" style="color: rgba(217, 83, 79, 1);font-size:14px;">Locality*</label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-map-marker"></span></span>
					<input class="form-control" style= "font-size: 15px; color: #687074;text-transform: uppercase;" type="text" id="locality" name="locality" placeholder="Locality">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:12px;display:none;" id="localityMandate">Field cannot be empty</span>
				</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:5px;">
    			<label for="city" style="color: rgba(217, 83, 79, 1);font-size:14px;">Town/City*</label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-map-marker"></span></span>
					<input class="form-control" style= "font-size: 15px; color: #687074;text-transform: uppercase;" type="text" name="city"  id="city" placeholder="Town/City">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:12px;display:none;" id="cityMandate">Field cannot be empty</span>
				</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:5px;">
    			<label for="state" style="color: rgba(217, 83, 79, 1);font-size:14px;">State*</label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-map-marker"></span></span>
					<input class="form-control" style= "font-size: 15px; color: #687074;text-transform: uppercase;" type="text" name="state" id="state" placeholder="State">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:12px;display:none;" id="stateMandate">Field cannot be empty</span>
				</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="">
    			<label for="country" style="color: rgba(217, 83, 79, 1);font-size:14px;">Country</label>
    			</div>
    			<div class="col-sm-10 " >
    			<span class="" style= "font-size: 15px; color: #687074;text-transform:uppercase;" name="country"  id="country"> India</span><span style= "font-size: 12px; color: #687074;"> (Service available only in India)</span>
    			</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2 " style="padding-top:5px;">
    			<label for="pincode" style="color: rgba(217, 83, 79, 1);font-size:14px;">Pincode*</label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-screenshot"></span></span>
					<input class="form-control" style= "font-size: 15px; color: #687074;text-transform: uppercase;" type="text" id="pinCode" name="pinCode" placeholder="Pincode">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:12px; display:none;" id="pinCodeMandate">Field cannot be empty</span>
				</div>
  				</div>
  				
  			</div>
    	</div>
		</div>
		<!-- End of Address Block -->
		<div class="row text-center" >
    	 <button type="button" class="btn btn-info btn-md" id="" onclick="checkRequiredFieldsCondition()"><span class="glyphicon glyphicon-ok"></span> Register</button>
    	 <button type="button" class="btn btn-info btn-md" id="" style="background-color: rgba(217, 83, 79, 1);" onclick="resetFields()"><span class="glyphicon glyphicon-remove"></span> Reset</button>
    	</div>
		</form> <!-- end of Form -->
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
	<script src="js/VendorLoginSignUp.js" type="text/javascript"></script>
	<style>
		.panel-default{border-color:#85b213}
		.panel-default>.panel-heading{border-bottom:1px solid #85b213}
		.vehicleDisplay{padding-right:15px;padding-left:15px;margin-right:auto;margin-left:auto}@media (min-width:768px){.vehicleDisplay{width:750px}}@media (min-width:992px){.vehicleDisplay{width:750px}}@media (min-width:1200px){.vehicleDisplay{width:750px}}
	</style>
	
 </body>
</html>