/**
 * This file contains jquery/javascript functions that are common accross the
 * application
 */
// Global variables
var city,ip,region;

/* affix the navbar after scroll below header */
$('#nav').affix({
	offset : {
		top : $('header').height() - $('#nav').height()
	}
});

/* highlight the top nav as scrolling occurs */
$('body').scrollspy({
	target : '#nav'
})

/* smooth scrolling for scroll to top */
$('.scroll-top').click(function() {
	$('body,html').animate({
		scrollTop : 0
	}, 1000);
})

/* smooth scrolling for nav sections */

$('#nav .navbar-nav li>a').click(function() {
	var link = $(this).attr('href');
	var posi = $(link).offset().top;
	$('body,html').animate({
		scrollTop : posi
	}, 700);
});
$(document).ready(function() {
	$('#pickupDate').datepicker({
		format : "dd/mm/yyyy",
		startDate : "0",
		autoclose : "true",
		clearBtn : "true",
		title : "pickup",
	});
	$('#dropoffDate').datepicker({
		format : "dd/mm/yyyy",
		autoclose : "true",
		clearBtn : "true",
		title : "pickup",
	});
	 $('[data-toggle="tooltip"]').tooltip();   
	 
});

//This function is used to default the drop-off date to (start-date + 1)
function defaultDropDate(){
	var startDate = $('#pickupDate').datepicker('getDate', '+1d'); 
	startDate.setDate(startDate.getDate()+1);
	$('#dropoffDate').datepicker('setStartDate', startDate);
}

function openLoginPopUp() {
	$('#loginErrorMsgDiv').css('display', 'none');
	$('#loginUsrname').val("");
	$('#loginPassword').val("");
	$("#signupCancelBtn").click();
	$("#loginModal").modal();
}
function openSignUpPopUp() {
	$('#signupErrorMsgDiv').css('display', 'none');
	$('#signupUsername').val("");
	$('#signupPassword').val("");
	$('#signupmobileNumber').val("");
	$("#loginCancelBtn").click();
	$("#signupModal").modal();
}

function submitForAuthentication(authType, pageContext) {
	if (authType == 'login') {
		if (($('#loginUsrname') != null && $('#loginUsrname').val() == '')
				|| ($('#loginPassword') != null && $('#loginPassword').val() == '')) {
			$('#loginErrorMsgSpan').html("Enter value for all fields");
			$('#loginErrorMsgDiv').css('display', 'block');
		} else {
			$.post("Login", {
				username : $('#loginUsrname').val(),
				password : $('#loginPassword').val(),
				authType : 'login'
			}, function(responseText) {
				//alert("ResponseText=" + responseText);
				if (responseText != null && responseText != ''
						&& responseText == 'authenticationFailed') {
					$('#loginErrorMsgSpan').html(
							"Invalid username or password entered.");
					$('#loginErrorMsgDiv').css('display', 'block');
					$('#loginPassword').val("");
				} else if(responseText == 'emptyFields'){
					$('#loginErrorMsgSpan').html("Enter value for all fields");
					$('#loginErrorMsgDiv').css('display', 'block');
				} else if (responseText == 'invalidEmail'){
					$('#loginErrorMsgSpan').html("Email address invalid.");
					$('#loginErrorMsgDiv').css('display', 'block');
					$('#loginUsrname').val("");
					$('#loginPassword').val("");
				}
				else {
					//alert("Response=" + responseText);
					$(location).attr('href', pageContext + responseText);
				}
			});
		}
	} else if (authType == 'signup') {
		if (($('#signupUsername') != null && $('#signupUsername').val() == '')
				|| ($('#signupPassword') != null && $('#signupPassword').val() == '')
				|| ($('#signupmobileNumber') != null && $(
						'#signupmobileNumber').val() == '')) {
			$('#signupErrorMsgSpan').html("Enter value for all fields");
			$('#signupErrorMsgDiv').css('display', 'block');
		} else {
			$
					.post(
							"Login",
							{
								username : $('#signupUsername').val(),
								password : $('#signupPassword').val(),
								mobileNumber : $('#signupmobileNumber')
										.val(),
								authType : 'signup'
							},
							function(responseText) {
								//alert("ResponseText=" + responseText);
								if (responseText != null && responseText != '') {
									if (responseText == 'userNameExists') {
										$('#signupErrorMsgSpan')
												.html(
														'Username already exists.');
										$('#signupErrorMsgDiv').css('display',
												'block');
										$('#signupUsername').val("");
										$('#signupPassword').val("");
										$('#signupmobileNumber').val("");
									} else if (responseText == 'mobileNaN') {
										$('#signupErrorMsgSpan')
												.html(
														'Invalid Mobile Number.');
										$('#signupErrorMsgDiv').css('display',
												'block');
										$('#signupPassword').val("");
										$('#signupmobileNumber').val("");
									} else if(responseText == 'invalidEmail'){
										$('#signupErrorMsgSpan').html("Email address invalid.");
										$('#signupErrorMsgDiv').css('display', 'block');
										$('#signupUsername').val("");
										$('#signupPassword').val("");
										$('#signupmobileNumber').val("");
									}
									else if(responseText == 'emptyFields'){
										$('#signupErrorMsgSpan').html("Enter value for all fields");
										$('#signupErrorMsgDiv').css('display', 'block');
									} else {
										//alert("Response=" + responseText);
										$(location).attr('href',
												pageContext + responseText);
									}
								} else {

									$('#loginErrorMsgSpan')
											.html(
													'Something went terribly wrong with our system. Please try again after sometime.');
									$('#signupErrorMsgDiv').css('display',
											'block');
									$('#signupUsername').val("");
									$('#signupPassword').val("");
									$('#signupmobileNumber').val("");
								}
							});

		}
	}
}


function logoutUser(){
	$('#logoutFormId').submit();
}

function selectedLocation(selectedLoc){
	$('#locationId').text(" "+selectedLoc+ " ");
	setLocationToSession(selectedLoc);
	$('#selectedLocationId').val(selectedLoc);
	$("#selectedLocationCancelBtn").click();
	$("#landingPageNavId").click();
}

function setLocationToSession(selectedLoc){
	$.post(
			"LocationSetting",
			{
				locationSelected : selectedLoc
			},
			function(responseText) {
				
			});
	
}

function modifySearchCriteria(operation){
	if(operation=='modify'){
		$('#readOnlySearchDataDivId').hide();
		$('#modifySearchDataDivId').show();
	}else if (operation=='cancel'){
		$('#readOnlySearchDataDivId').show();
		$('#modifySearchDataDivId').hide();
		$("#modifySearchDivId").hide();
		$("#bookingSummaryMainBlockId").show();
		$("#modifyBtnId").show();
		$("#paymentBtnId").show();
		$("#reviewModifyDivId").hide();
		$('#pickupDate').val("");
		$('#dropoffDate').val("");
	}
}


function verifyLocationSetting(){
	var locationIdText = $('#locationId').text();
	if(' Location ' == locationIdText){
		$("#chooseLocationDivId").modal();
	}else{
		var pickup = $("#pickupDate").val();
		var dropoff = $("#dropoffDate").val();
		if(pickup=="" || pickup == null){
			$("#searchCriteriaErrorSpan").text("** Pickup date cannot be empty");
		}else if(dropoff=="" || dropoff == null){
			$("#searchCriteriaErrorSpan").text("** Dropoff date cannot be empty");
		}else{
			$("#searchCriteriaDivId").submit();
		}
		
	}
}