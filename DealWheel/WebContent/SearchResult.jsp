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
<script src="js/SearchResultJS.js" type="text/javascript"></script>

<style>
.navbar-default .navbar-nav>.open>a, .navbar-default .navbar-nav>.open>a:focus,
	.navbar-default .navbar-nav>.open>a:hover {
	background-color: #85b213;
}
.panel-body{padding:0px}
</style>
</head>
<body>
	<% session.setAttribute("currentPage","SearchResult");%>
	<!-- Wrap all page content here -->
	<div id="wrap" style="min-height: 85%;">
		<!-- Nav bar inclusion starts -->
		<%@ include file="commonResources/NavigationBar"%>
		<!-- Nav bar inclusion ends -->
		<!-- Modify Search Div Starts -->
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
		%>
		<div class="container">
			<div class="panel panel-default" style="border-color:#85b213;">
    			<div class="panel-body text-center" id="readOnlySearchDataDivId">
    			<div class="col-sm-3" style="padding:15px">
    				<span class="glyphicon glyphicon-map-marker" style="color:#85b213;font-size:16px;"></span> 
    				<span style="color: #687074;font-size:14.5px; font-weight:600;text-transform:uppercase;"><%=selectedLocation %></span> 
    			</div>	
    			<div class="col-sm-2">
    				<div class="row" >
    					<span style="color: #687074;font-size:10px; font-weight:600;text-transform:uppercase;">Pickup</span>
    				</div>
    			    <div class="row">
    					<div>
    						<span class="glyphicon glyphicon-calendar" style="color:#85b213;font-size:16px;"></span> 
    						<span style="color: #687074; font-weight:500;text-transform:uppercase;">
    							<span style="font-size:12px;"><%=sdtDay%>, </span>
    							<span style="font-size:16px;"><%=sdtDateNum%> </span>
    							<span style="font-size:14px;"><%=sdtMonth%>'<%=sdtYear%></span>
    						</span>
    					</div>
    				</div>
    				
    			</div>	
    			<div class="col-sm-2">
    				<div class="row" style="margin-top:0px;">
    					<span style="color: #687074;font-size:10px; font-weight:600;text-transform:uppercase;">Drop</span>
    				</div>
    			    <div class="row">
    					<div>
    						<span class="glyphicon glyphicon-calendar" style="color:#85b213;font-size:16px;"></span> 
    						<span style="color: #687074; font-weight:500;text-transform:uppercase;">
    							<span style="font-size:12px;"><%=endtDay%>, </span>
    							<span style="font-size:16px;"><%=endtDateNum%> </span>
    							<span style="font-size:14px;"><%=endtMonth%>'<%=endtYear%></span>
    						</span>
    					</div>
    				</div>
    				
    			</div>
    			<div class="col-sm-2">
    				<div class="row" style="margin-top:0px;">
    					<span style="color: #687074;font-size:10px; font-weight:600;text-transform:uppercase;">Days</span>
    				</div>
    			    <div class="row">
    					<div>
    						<span style="color: #687074; font-weight:600;font-size:14px;"><%=noOfDays%></span>
    					</div>
    				</div>
    				
    			</div>
    			<div class="col-sm-3" style="padding:10px">
    				<button type="button" class="btn btn-info btn-md" onclick="modifySearchCriteria('modify')">+ Modify Search</button>
    			</div>
    		</div><!-- End of panel body div - Readonly data-->
    		<div class="panel-body text-center" id="modifySearchDataDivId" style="display:none;padding:10px">
    		  <!-- 
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
				-->
				<%@ include file="commonResources/SearchCriteriaLayout"%>
    		</div><!-- End of panel body div - Modify search-->
  			</div>
  		</div>
		<!-- Modify Search Div Ends -->
		<!-- Begin page content -->
		<div class="container">
			<form method="post" id="bookingFormId" action="${pageContext.request.contextPath}/ConfirmBooking">
				
				<table border="1">
					<%
						Map displaySearchResultMap = (Map) session.getAttribute(GenericConstant.DISPLAYSEARCHRESULTMAP);
						if (displaySearchResultMap != null) {
							Iterator itr = displaySearchResultMap.entrySet().iterator();
							int count = 0;
							while (itr.hasNext()) {
								Map.Entry entry = (Entry) itr.next();
								String key = (String) entry.getKey();
								String value = (String) entry.getValue();
								// String keySplit[] = key.split(GenericConstant.DOLLARFORSPLIT,-1);
								// String valueSplitDollar[] = value.split(GenericConstant.DOLLARFORSPLIT,-1);
								String valueSplitHash[] = value.split("#", -1);
					%>
					<tr>
						<td>
							<div>
								Vehicle Image URL= <%=valueSplitHash[0]%><br> 
								Vehicle Name = <%=valueSplitHash[1]%><br> 
								Vehicle Make = <%=valueSplitHash[2]%><br> 
								Per day cost=<%=valueSplitHash[3]%><br> 
								Security cost= <%=valueSplitHash[4]%><br> 
								Vendor Name= <%=valueSplitHash[5]%><br> 
								Pickup= <%=valueSplitHash[6]%><br> 
								Complete Address= <%=valueSplitHash[7]%><br>
								<button type="button" onclick="selectedVehicle('<%=key%>')">Book</button>
							</div>
						</td>
					</tr>
					<%
						count++;
							}
						}
					%>
				</table>
				<input type="text" id="selectedVehicleDetailsId" name="selectedVehicleDetails" style="display: none;" />
			</form>
		</div>
	</div>
	<!--/wrap-->
	<!-- Footer inclusion starts -->
	<%@ include file="commonResources/Footer"%>
	<!-- Footer inclusion ends -->
	<ul class="nav pull-right scroll-top">
		<li>
			<a href="#" title="Scroll to top">
				<span class="glyphicon glyphicon-menu-up"></span>
			</a>
		</li>
	</ul>

	<!-- Including Modal Windows for Signup and Login -->
	<%@ include file="commonResources/CommonModalDivBlocks"%>

	<!-- Including Common JS -->
	<script>
	var propCities = '<%= CommonUtility.getValuesFromProperties("activeCities")%>';
	</script>
	<script src="js/CommonJS.js" type="text/javascript"></script>
	
</body>
</html>