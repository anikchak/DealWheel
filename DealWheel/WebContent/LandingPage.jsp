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

<style>
.navbar-default .navbar-nav > .open > a, .navbar-default .navbar-nav > .open > a:focus, .navbar-default .navbar-nav > .open > a:hover
	{
	background-color:#85b213;
	}

</style>
</head>

<body>

	<!-- Wrap all page content here -->
	<div id="wrap" style="min-height: 85%;">
		<!-- Nav bar inclusion starts -->
		<%@ include file="commonResources/NavigationBar"%>
		<!-- Nav bar inclusion ends -->
		<!-- Begin page content -->

		<div class="container">
			<h3 class="text-center">Some content/image goes here</h3>
  				<br>
				<form class="form-inline" role="form" action="${pageContext.request.contextPath}/Search" method="post">
					<div class="text-center">
 						<div class="form-group has-success has-feedback date">
							<label for="usr" style="color: #687074;">Pickup Date:</label> <input type="text"
								class="form-control" id="pickupDate" name="fromDate"
								placeholder="Enter Pickup Date" onchange="defaultDropDate()"> <span
								class="glyphicon glyphicon-calendar form-control-feedback"></span>
						</div>
						<div class="form-group has-success has-feedback date">
							<label for="usr" style="color: #687074;"> Dropoff Date:</label> <input type="text"
								class="form-control" id="dropoffDate" name="toDate"
								placeholder="Enter Dropoff Date"> 
								<span class="glyphicon glyphicon-calendar form-control-feedback"></span>
						</div>
						<div class="form-group ">
							<button type="submit" class="btn btn-md btn-info">
								<span class="glyphicon glyphicon-search"></span> Search
							</button>
						</div>
					</div>
					<input type="text" style="display:none;" name="selectedLocationName" id="selectedLocationId" />
				</form>
			
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

	<script src="js/CommonJS.js" type="text/javascript"></script>
	<script>
	var propCities = '<%= CommonUtility.getValuesFromProperties("activeCities")%>';

	function matchLocation(){
		var locationIdText = $('#locationId').text();
		var cityMatchFound = 0;
		if(' Location ' == locationIdText){
			
		if(city=='Bengaluru'){
			city = "Bangalore";
		}
		if(propCities!=null){
			var activeCities = propCities.split("#",-1);
			for(i =0; i<activeCities.length;i++)
				{
					if(activeCities[i]==city){
						cityMatchFound = 1;
						$('#locationId').text(" "+activeCities[i]+ " ");
						$('#selectedLocationId').val(activeCities[i]);
						setLocationToSession(activeCities[i]);
						break;
					}else{
						cityMatchFound = 0;
					}
				}
		}
		if(cityMatchFound==0){
			$("#chooseLocationDivId").modal();
		}
	}
		
	}
	
	</script>
</body>
</html>