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
<title>DealWheel: Your Deal to self-drive your Wheels</title>
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
	<div id="wrap" style="min-height:85%;" >
		<!-- Nav bar inclusion starts -->
		<%@ include file="commonResources/NavigationBar"%>
		<!-- Nav bar inclusion ends -->
		<!-- Begin page content -->
		<%
		String s = request.getParameter("confirmationurl");
		CustomerControllerService cs = new CustomerControllerService();
		String s1 = cs.ConfirmationUserRegisteration(s);
				
		%>
    	<div class="container">
			<br><br><br><br>
  				<div>
  				<%if ("Success".equals(s1)) { %>
  						<div class="container">
			<span style="color:#687074;text-transform: uppercase;font-size: 12px;"><span style="font-size: 14px;font-weight:bold;color:rgba(83,217,79, 1);">Congratulations!!!</span> Your registration confirmed.Login to avail our services</span> <br>
			<br>
			
  		</div>
  				<% }
  				else {%>
  					<div class="container">
			         <span style="color:#687074;text-transform: uppercase;font-size: 12px;"><span style="font-size: 14px;font-weight:bold;color:rgba(217, 83, 79, 1);">Opps..!! </span>Your registration is expired or confirmed already. Login or Signup to avail our services.</span> <br>
			        <br>
			       
  		            </div>
  				<% } %>
  				</div>
  				<div >
  				<br><br><br><br><br>
  				<img alt="How we work" src="https://s3-us-west-2.amazonaws.com/dealwheel/How+we+work.png" style="width:40%;margin-left:28%;">
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
	<style>
		.vehicleDisplay{padding-right:15px;padding-left:15px;margin-right:auto;margin-left:auto}@media (min-width:768px){.vehicleDisplay{width:750px}}@media (min-width:992px){.vehicleDisplay{width:750px}}@media (min-width:1200px){.vehicleDisplay{width:750px}}
	</style>
</body>
</html>