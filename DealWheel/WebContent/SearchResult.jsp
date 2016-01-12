<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="commonResources/CommonViewImports"%>

<!DOCTYPE html>
<html>
<head>
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
    		  <%@ include file="commonResources/SearchCriteriaLayout"%>
    		</div><!-- End of panel body div - Modify search-->
  			</div>
  		</div>
		<!-- Modify Search Div Ends -->
		<!-- Begin page content -->
		
			<form method="post" id="bookingFormId" action="${pageContext.request.contextPath}/ConfirmBooking">
				
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
						<br>
						<div class="vehicleDisplay" style="border-color:#85b213;border:1px solid #85b213;border-radius:4px;">
							<!-- Start of row 1 div -->
							<div class="row">

								<div class="col-md-4" style="padding:10px;float:left;">
									<%if(valueSplitHash[0]==null || "null".equalsIgnoreCase(valueSplitHash[0]) ){ %>
									<img class="" src="https://s3-us-west-2.amazonaws.com/dealwheel/EmptyImage.png" 
									alt="Logo" style="width:150px;height:150px;border:1px solid #85b213;border-radius:4px;"/>
									<%}else{
										System.out.println("value 0 = "+valueSplitHash[0]);
									 %>
										<img class="" src="http://www.kcls.org/images/loaders/inspiroo_logo_loader_pop.gif" 
												alt="Logo" style="width:150px;height:150px;border:1px solid #85b213;border-radius:4px;"/>
									<%
										}
									%>
									
								</div> <!-- end of image div -->

								<!-- Start of vehicle detail div -->
								<div class="col-md-8">

									<!-- Vehicle details -->
									<div class="col-md-8 col-sm-8 col-xs-8" style="padding:10px;float:left">
										<div class="row">
											<span style="font-size:16.5px;font-weight:400;color: #687074;"><%=valueSplitHash[2]%></span> 
											<span style="font-size:17.5px;font-weight:500;color: #687074;text-transform:uppercase;"><%=valueSplitHash[1]%></span>
										</div>
										<div class="row">
											<span style="font-size:14px;font-weight:400;color: #687074;"><%=valueSplitHash[5]%></span>
										</div>

										<div class="row">
											<br/>
											<span style="font-size:17px;font-weight:500;color: #687074;" data-toggle="tooltip" 
											title="<%=valueSplitHash[7]%>" data-placement="bottom">
											<span class="glyphicon glyphicon-map-marker" style="color: rgba(217, 83, 79, 1);"></span>
											<%=valueSplitHash[6]%>
											</span>
										</div>

									</div>
									<!-- Vehicle details ends-->

									<!-- Price div starts-->
									<div class="col-md-4 col-sm-4 col-xs-4 pull-right" style="padding:10px;float:left;">

									<!--Start of per day cost-->
										<div class="row">
											<span style="font-size:11px;font-weight:600;color: rgba(217, 83, 79, 1);">Per Day Cost:</span> 
											<span style="font-size:15px;font-weight:600;color: #687074;">
												<span style='font-family:Arial;'>&#8377;</span> 
												<span id="perDayCostId_<%=key%>"><%=valueSplitHash[3]%></span>
											</span>
										</div>

									<!--End of per day cost-->

									<!--Start of Security-->
										<div class="row">
											<span style="font-size:11px;font-weight:600;color: rgba(217, 83, 79, 1);">Security Deposit**:</span> 
											<span style="font-size:15px;font-weight:600;color: #687074;"><span style='font-family:Arial;'>&#8377;</span> 
												<span id="securityDepositId_<%=key%>"><%=valueSplitHash[4]%></span>
											</span>
										</div>

									<!--End of Security-->

									<!--Start of Total payable amnt-->
										<div class="row">
											<span style="font-size:11px;font-weight:600;color: rgba(217, 83, 79, 1);" data-toggle="tooltip" 
											title="Payable Amount = Days * Per Day Cost" data-placement="bottom">Payable amount:</span> 
											<span style="font-size:15px;font-weight:600;color: #687074;" data-toggle="tooltip" 
											title="Payable Amount = <%=noOfDays%> * <%=Long.parseLong(valueSplitHash[3]) %>" data-placement="bottom">
												<span style='font-family:Arial;'>&#8377;</span> <span id="payableAmntId_<%=key%>"><%=(noOfDays*Long.parseLong(valueSplitHash[3])) %></span>
											</span>
										</div>

									<!--End of Total payable amnt-->

									<!--Start of Book-->
										<div class="row">
											<br/>
											<button type="button" class="btn btn-md btn-primary"
											style="background-color: #85b213;" onclick="selectedVehicle('<%=key%>')">
											<span class="glyphicon glyphicon-ok"></span> Book
											</button>
										</div>

									<!--End of Book-->

									</div><!-- Price div end --> 
								</div> <!-- End of vehicle detail div -->
							</div> <!-- End of row 1 div -->

							<div class="row"><!-- Start of row 2 div -->
								<span class="pull-right" style="color: rgba(217, 83, 79, 1);font-size:12px;">** - Security deposit to be collected/refunded by vendor during vehicle pickup/dropoff . </span>
							</div> <!-- End of row 2 div -->

						</div>
					<%
						count++;
							}
						}else{
					%>
					<div class = "container text-center">
						<h3 style="color: rgba(217, 83, 79, 1);">Oops..!!</h3>
						<span style="font-size:15px;font-weight:600;color: #687074;"> All our vehicles are busy for the specified date range. Kindly modify your search criteria to view other available vehicles .</span>
					</div>
					<%
						}
					%>
				<input type="text" id="selectedVehicleDetailsId" name="selectedVehicleDetails" style="display: none;" />
			</form>
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
	<!-- Alert for users who are not logged in starts-->
	<div class="modal fade" id="userNotLoggedInModalId" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content" >
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title"><img class="" src="http://htmlstream.com/preview/unify-v1.8/assets/img/logo1-default.png" alt="Logo"></h4>
        </div>
        <div class="modal-body text-center">
          <p >
          	<span style="font-size:16px;font-weight:600;color: rgba(217, 83, 79, 1);">Machaa...!!!</span>
          	<span style="font-size:14px;font-weight:600;color: #687074;"> You are not logged in. Kindly log-in to proceed.</span>
          </p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-md btn-primary" style="background-color: #85b213;" data-dismiss="modal" onclick="openLoginPopUp();">Ok</button>
        </div>
      </div>
      
    </div>
  </div>
	<!-- Alert for users who are not logged in ends -->
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