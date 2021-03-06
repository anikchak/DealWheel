/**
 * This file contains JS realted to Vendor Registration page
 */

$(document).ready(function() {
	$('#dob').datepicker({
		format : "dd/mm/yyyy",
		autoclose : "true",
		clearBtn : "true",
		title : "dob",
	});

	 $("#primaryContact").keypress(function (e) {
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	        $("#errmsgContact1").show().fadeOut(2000);
	               return false;
	    }
	   });
	 
	 $("#secondaryContact").keypress(function (e) {
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	        $("#errmsgContact2").show().fadeOut(2000);
	               return false;
	    }
	   });
	 
	 $("#pinCode").keypress(function (e) {
	     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
	        $("#errmsgPin").show().fadeOut(2000);
	               return false;
	    }
	   });
	
	$('[data-toggle="tooltip"]').tooltip();

});

function checkRequiredFieldsCondition() {
	var emptyField = 'N';
	$('#fullnameMandate').hide();
	$("#fullName").css("border-color", "");
	$('#primaryContactMandate').hide();
	$('#incompleteContactNo1').hide();
	$('#incompleteContactNo2').hide();
	$("#primaryContact").css("border-color", "");
	$('#addr1Mandate').hide();
	$("#addr1").css("border-color", "");
	$('#addr2Mandate').hide();
	$("#addr2").css("border-color", "");
	$('#localityMandate').hide();
	$("#locality").css("border-color", "");
	$('#cityMandate').hide();
	$("#city").css("border-color", "");
	$('#stateMandate').hide();
	$("#state").css("border-color", "");
	$('#pinCodeMandate').hide();
	$('#incompletePinCode').hide();
	$("#pinCode").css("border-color", "");

	if ($("#fullName").val() == '' || $("#fullName").val() == null) {
		$("#fullName").css("border-color", "red");
		$('#fullnameMandate').show();
		emptyField = 'Y';
	}
	if ($("#primaryContact").val() == '' || $("#primaryContact").val() == null) {
		$("#primaryContact").css("border-color", "red");
		$('#primaryContactMandate').show();
		emptyField = 'Y';
	}
	if ( $("#primaryContact").val().length > 0  &&  $("#primaryContact").val().length < 10){
		$("#primaryContact").css("border-color", "red");
		$('#incompleteContactNo1').show();
		emptyField = 'Y';
	}
	if ( $("#secondaryContact").val().length > 0  &&  $("#secondaryContact").val().length < 10){
		$("#secondaryContact").css("border-color", "red");
		$('#incompleteContactNo2').show();
		emptyField = 'Y';
	}
	
	if ($("#addr1").val() == '' || $("#addr1").val() == null) {
		$("#addr1").css("border-color", "red");
		$('#addr1Mandate').show();
		emptyField = 'Y';
	}
	if ($("#addr2").val() == '' || $("#addr2").val() == null) {
		$("#addr2").css("border-color", "red");
		$('#addr2Mandate').show();
		emptyField = 'Y';
	}
	if ($("#locality").val() == '' || $("#locality").val() == null) {
		$("#locality").css("border-color", "red");
		$('#localityMandate').show();
		emptyField = 'Y';
	}
	if ($("#city").val() == '' || $("#city").val() == null) {
		$("#city").css("border-color", "red");
		$('#cityMandate').show();
		emptyField = 'Y';
	}
	if ($("#state").val() == '' || $("#state").val() == null) {
		$("#state").css("border-color", "red");
		$('#stateMandate').show();
		emptyField = 'Y';
	}
	if ($("#pinCode").val() == '' || $("#pinCode").val() == null) {
		$("#pinCode").css("border-color", "red");
		$('#pinCodeMandate').show();
		emptyField = 'Y';
	}
	if ($("#pinCode").val().length > 0 && $("#pinCode").val().length < 6) {
		$("#pinCode").css("border-color", "red");
		$('#incompletePinCode').show();
		emptyField = 'Y';
	}
	if (emptyField == 'N') {
		submitForm();
		 $("#vendorOTPPopup").modal({
			backdrop: 'static',
			   keyboard: false
		});
	}
}

function submitForm(){
	var formData = $("#registrationFormId").serialize();
	$.ajax({
        url: "VendorRegistration" ,
        type: "post",
        data : formData,
        success: function(response){
        	
        	var list = response.split(",");
        	$.post("TriggerEmail",
        			{
        				emailType : "VERIFY_VENDOR",
        				emailAddress : list[0],
        				list : response				
        			},
        			function(responseText) {});
        }
      });
}

function verifyVendorOTP(){
	$("#vendorOTPMandate").hide();
	$('#vendorOTPFormat').hide();
	$('#vendorOTPIncorrect').hide();
	$('#vendorOTPWait').hide();
	$('#vendorOTPResend').hide();
	$("#otpVendor").css("border-color", "");
	
	if ( $("#otpVendor").val() == '' || $("#otpVendor").val() == null ) {
		$("#otpVendor").css("border-color", "red");
		$('#vendorOTPMandate').show();
	}else if ( $("#otpVendor").val().length > 0 && $("#otpVendor").val().length < 6){
		$("#otpVendor").css("border-color", "red");
		$('#vendorOTPFormat').show();
	}else{
		$('#vendorOTPWait').show();
		var formData = $("#verifyOTPForm").serialize();
		$.ajax({
	        url: "VerifyOTP" ,
	        type: "post",
	        data : formData,
	        success: function(response){
	        	if(response == "OTPWRONG"){
	        		$('#vendorOTPWait').hide();
	        		$('#vendorOTPIncorrect').show();
	        		$("#otpVendor").css("border-color", "red");
	        	}else{
	        		$(location).attr('href', pageContext + "/VendorHome.jsp");
	        		$('#vendorOTPWait').hide();
	        	}
	        }
	      });
		
	}
}

function resendOTP(email){
	$('#vendorOTPWait').show();
	$.ajax({
        url: "VerifyOTP" ,
        type: "post",
        data : {identifier:"ResendOTP",email:email},
        success: function(response){
        	var list = response.split(",");
        	$.post("TriggerEmail",
        			{
        				emailType : "VERIFY_VENDOR",
        				emailAddress : list[0],
        				list : response				
        			},
        			function(responseText) {
        				$('#vendorOTPWait').hide();
        				$('#vendorOTPResend').show();
        			});
        }
	});
	$('#vendorOTPResend').show();
}

function cancelOTPVerify(email){
	$('#vendorOTPWait').show();
	$.ajax({
        url: "VerifyOTP" ,
        type: "post",
        data : {identifier:"Cancel",email:email},
        success: function(response){
        	$("#vendorOTPPopup").hide();
        	$(location).attr('href', pageContext + "/VendorLoginSignUp.jsp?invoke=vendorFlow&cancel=cancel");
        }
        
	});
	
	
}
function resetFields() {
	$("#fullName").val('');
	$("input[value='Male']").removeAttr('checked');
	$("input[value='Female']").removeAttr('checked');
	$("#dob").val('');
	$("#primaryContact").val('');
	$("#secondaryContact").val('');
	$("#addr1").val('');
	$("#addr2").val('');
	$("#addr3").val('');
	$("#locality").val('');
	$("#city").val('');
	$("#state").val('');
	$("#pinCode").val('');
}