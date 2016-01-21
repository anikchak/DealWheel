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
<title>DealWheel: Welcome Vendors</title>
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
	session.setAttribute("currentPage","VendorLogin");
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
		<div class="vehicleDisplay" style="border:1px solid #85b213;border-radius:4px; width:40%;">
		<br>
		<!-- Vendor Login Div Starts -->
		
		 <form role="form" action="${pageContext.request.contextPath}/VendorLoginSignUp" method="post" id="loginFormId" >
		 <div id="vendorLoginDiv">
			<p class="text-center" style="font-size:16px;font-weight:bold;color:#687074" id="">Vendor Login</p>
			<hr style="border-color:#85b213;">
          	<div class="form-group text-center" id="login_Error_Login" name="login_Error_Login" style="display:none;">
              <label style="font-weight:bold;font-size:14px;" class="label label-danger"><span id="vendorLoginErrorMsgSpan"> Username or Password is incorrect.</span></label>
            </div>
            <div class="form-group " style="color:#687074;">
			  <label class="control-label" for="loginUsrname">Username</label>
			  <div class="input-group">
				<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
				<input type="text" class="form-control" name="loginEmail" id="loginEmail" placeholder="Enter email">
			</div>
			</div>
            <div class="form-group" style="color:#687074;">
              <label for="loginPassword" class="control-label">Password</label>
              <div class="input-group">
				<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
				<input type="password" class="form-control" name="loginPassword" id="loginPassword" placeholder="Enter password">
			  </div>
            </div>
            <button type="button" class="btn btn-danger btn-block " id="login" ><span class="glyphicon glyphicon-log-in"></span> Login</button>
          	<br>
           <div class="modal-footer" style="border-color: #85b213;">
          	<p><span style="color:#687074;">Not yet Registered? </span><a href="#" onclick="showVendorSignup()" style="color:#d9534f">Sign Up</a></p>
          	<p><span style="color:#687074;">Forgot </span><a href="#" style="color:#d9534f" onclick="showVendorForgotPassword()">Password?</a></p>
           </div>
          </div>
          <!-- Vendor Login Div Ends -->
          <!-- Vendor Signup Div Starts -->
          <div id="vendorSignupDiv" style="display:none;">
			<p class="text-center" style="font-size:16px;font-weight:bold;color:#687074" id="">Vendor Signup</p>
			<hr style="border-color:#85b213;">
          	<div class="form-group text-center" id="signup_error" name="signup_error" style="display:none;">
              <label style="font-weight:bold;font-size:14px;" class="label label-danger"><span id="vendorSignupErrorMsgSpan"> Username or Password is incorrect.</span></label>
            </div>
            <div class="form-group " style="color:#687074;">
			  <label class="control-label" for="loginUsrname">Username</label>
			  <div class="input-group">
				<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
				<input type="text" class="form-control" name="email" id="email" placeholder="Enter email">
			</div>
			</div>
            <div class="form-group" style="color:#687074;">
              <label for="password" class="control-label">Password</label>
              <div class="input-group">
				<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
				<input type="password" class="form-control" name="password" id="password" placeholder="Enter password">
			  </div>
            </div>
            <div class="form-group" style="color:#687074;">
              <label for="confirmPassword" class="control-label">Confirm Password</label>
              <div class="input-group">
				<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
				<input type="password" class="form-control" name="confirmPassword"  id="confirmPassword" placeholder="Re-Enter password">
			  </div>
            </div>
            <button type="button" class="btn btn-danger btn-block" id="signup"><span class="glyphicon glyphicon-off"></span> Sign Up</button>
          	<br>
           <div class="modal-footer" style="border-color: #85b213;">
          	<p><span style="color:#687074;">Already a member? </span><a href="#" onclick="showVendorLogin()" style="color:#d9534f">Login</a></p></div>
          </div>
          <!-- Vendor Signup Div Ends -->
          <!-- Vendor Forgot Div Starts -->
          <div id="vendorForgotDiv" style="display:none;">
			<p class="text-center" style="font-size:16px;font-weight:bold;color:#687074" id="">Forgot Password</p>
			<hr style="border-color:#85b213;">
          	<div class="form-group text-center" id="login_error_forgot" name="login_error_forgot" style="display:none;">
              <label style="font-weight:bold;font-size:14px;" class="label label-danger"><span id="vendorForgotErrorMsgSpan"> Username incorrect.</span></label>
            </div>
            <div class="form-group " style="color:#687074;">
			  <label class="control-label" for="loginUsrname">Username</label>
			  <div class="input-group">
				<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
				<input type="text" class="form-control" name="emailForgot" id="emailForgot" placeholder="Enter email">
			</div>
			</div>
            <button type="button" class="btn btn-danger btn-block " onclick="" ><span class="glyphicon glyphicon-log-in"></span> Change Password</button>
          	<br>
           <div class="modal-footer" style="border-color: #85b213;">
          	<p><span style="color:#687074;">Already a member? </span><a href="#" onclick="showVendorLogin()" style="color:#d9534f">Login</a></p>
          	<p><span style="color:#687074;">Not yet Registered? </span><a href="#" onclick="showVendorSignup()" style="color:#d9534f">Sign Up</a></p>
          </div>
          </div>
          <!-- Vendor Forgot Div Ends -->
          <input type="hidden" name="identifier" id="identifier" />
          </form>
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
	<script src="js/VendorLoginSignUp.js" type="text/javascript"></script>
	<style>
		.vehicleDisplay{padding-right:15px;padding-left:15px;margin-right:auto;margin-left:auto}@media (min-width:768px){.vehicleDisplay{width:750px}}@media (min-width:992px){.vehicleDisplay{width:750px}}@media (min-width:1200px){.vehicleDisplay{width:750px}}
	</style>
	
 </body>
</html>