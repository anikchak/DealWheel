<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="commonResources/CommonViewImports"%>
<!DOCTYPE html>
<html>

<head>
<link href="css/LandingPageCSS.css" rel="stylesheet" type="text/css" />
<title>DealWheel: Your Deal to self-drive your Wheels</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="commonResources/CommonJSCSSInclude"%>
<script type="text/javascript">
var pageContext = '<%=request.getContextPath()%>';
</script>
<script src="js/LandingPageJS.js" type="text/javascript"></script>
</head>

<body>

	<!-- Wrap all page content here -->
	<div id="wrap" style="min-height: 85%;">

		<header>
			<h1 class="text-center">DealWheel logo goes here</h1>
		</header>

		<!-- Fly-in navbar -->
		<div class="navbar navbar-default navbar-static-top" id="nav">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>

				<div class="collapse navbar-collapse navbar-left">
					<ul class="nav navbar-nav">
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#"
							style="color: #fff; font-size: 16px; font-weight: bold;">Location<span
								class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#" style="color: #101f40; font-weight: bold;">Bangalore</a></li>
								<li><a href="#" style="color: #101f40; font-weight: bold;">Pune</a></li>
							</ul></li>
					</ul>
				</div>

				<div class="collapse navbar-collapse navbar-right">
					<ul class="nav navbar-nav">
						<li><a
							href="${pageContext.request.contextPath}/LandingPage.jsp"
							style="color: #fff; font-size: 16px; font-weight: bold;"><span
								class="glyphicon glyphicon-home"></span></a></li>
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color: #fff; font-size: 16px; font-weight: bold;">
								New to DealWheel?<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="#" style="color: #101f40; font-weight: bold;">How
										DealWheel works?</a></li>
								<li><a href="#" style="color: #101f40; font-weight: bold;">Tariff</a></li>
								<li><a href="#" style="color: #101f40; font-weight: bold;">Policies</a></li>
								<li><a href="#" style="color: #101f40; font-weight: bold;">Offers</a></li>
							</ul>
						</li>
						<li><a href="${pageContext.request.contextPath}/VendorLoginSignUp.jsp"
							style="color: #fff; font-size: 16px; font-weight: bold;">List
								With Us</a></li>
						
						<!-- Verify if the user is logged in or not -->
						<%
							new CustomerControllerService().cleanBookings();
							session.setAttribute(GenericConstant.COMINGFROMPAGE, "LandingPage");
							if(session.getAttribute("LoggedInUserDetailsObject")!=null){
								List<User> validUserDetails = (List<User>)session.getAttribute("LoggedInUserDetailsObject");
								if(validUserDetails!=null & validUserDetails.size()>0){
									for(User u : validUserDetails){
						%>
						<!-- User section post signin -->		
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color: #fff; font-size: 16px;">
								<span class="glyphicon glyphicon-user"></span> 
								<%=u.getUserEmail() %><span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="#" style="color: #101f40; font-weight: bold;">
										<span class="glyphicon glyphicon-cog"></span> My Profile
									</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/MyBookings" style="color: #101f40; font-weight: bold;">
										<span class="glyphicon glyphicon-list"></span> My Bookings
									</a>
								</li>
								<li>
									<a href="#" style="color: #101f40; font-weight: bold;" onclick="logoutUser()">
										<span class="glyphicon glyphicon-log-out"></span> Logout
									</a>
								</li>
							</ul>
						</li>
					   <!-- User section post signin ends-->	

						<%		
									}
								}
							}
							else{
						%>
						<!-- When user is not logged in -->
						<li><a href="#"
							style="color: #fff; font-size: 16px; font-weight: bold;"
							onclick="openSignUpPopUp()"><span
								class="glyphicon glyphicon-user"></span> SignUp</a></li>
						<li><a href="#"
							style="color: #fff; font-size: 16px; font-weight: bold;"
							onclick="openLoginPopUp()"><span
								class="glyphicon glyphicon-log-in"></span> Login</a></li>
						
						<!-- When user is not logged in ends-->
						<%} %>
						<!-- Verify if the user is logged in or not ends-->
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container -->
		</div>
		<!--/.navbar -->

		<!-- Begin page content -->

		<div class="container">
			<div class="col-sm-9 col-sm-offset-2">
				<form class="form-inline" role="form" action="${pageContext.request.contextPath}/Search" method="post">
					<div>
 						<div class="form-group has-success has-feedback date">
							<label for="usr">Pickup Date:</label> <input type="text"
								class="form-control" id="pickupDate" name="fromDate"
								placeholder="Enter Pickup Date" onchange="defaultDropDate()"> <span
								class="glyphicon glyphicon-calendar form-control-feedback"></span>
						</div>
						<div class="form-group has-success has-feedback date">
							<label for="usr"> Dropoff Date:</label> <input type="text"
								class="form-control" id="dropoffDate" name="toDate"
								placeholder="Enter Dropoff Date"> 
								<span class="glyphicon glyphicon-calendar form-control-feedback"></span>
						</div>
						<div class="form-group ">
							<button type="submit" class="btn btn-md btn-primary"
								style="background-color: #101f40;">
								<span class="glyphicon glyphicon-search"></span> Search
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!--/wrap-->

	<div id="footer">
		<div class="container" style="margin-top: 0px;">

			<hr style="border-color: #101f40; margin-bottom: 0px;">
			<table width="100%">
				<tr>
					<td><span style="">Copyright ©2014 DealWheel</span></td>
					<td>
						<div class="dropup" style="float: right;">
							<ul class="nav navbar-nav">
								<li class="dropdown"><a class="dropdown-toggle"
									data-toggle="dropdown" href="#" style="color: #101f40;">Know
										us more<span class="caret"></span>
								</a>
									<ul class="dropdown-menu">
										<li><a href="#" style="color: #101f40;">Contact Us</a></li>
										<li><a href="#" style="color: #101f40;">About Us</a></li>
									</ul></li>
							</ul>
						</div>
					</td>
				</tr>
			</table>
		</div>

	</div>

	<ul class="nav pull-right scroll-top">
		<li><a href="#" title="Scroll to top"><span
				class="glyphicon glyphicon-menu-up"></span></a></li>
	</ul>

	<!-- Including Modal Windows for Signup and Login -->
	<%@ include file="commonResources/CommonLoginSignUpDivBlocks"%>

	<!-- Including Common JS -->

	<script src="js/CommonJS.js" type="text/javascript"></script>
	
</body>
</html>