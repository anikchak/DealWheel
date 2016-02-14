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
	$("#vendorOTPPopup").modal({
		backdrop: 'static',
		   keyboard: false
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
		alert("OTP POP-UP TO VERIFY;On Ok submit form else logout");
		//$("#registrationFormId").submit();
	}
}

function verifyVendorOTP(){
	$("#vendorOTPMandate").hide();
	if ( $("#otpVendor").val() == '' || $("#otpVendor").val() == null ) {
		$("#otpVendor").css("border-color", "red");
		$('#vendorOTPMandate').show();
	}else{
		//Trigger AJAX to verify OTP : if successful then execute $("#registrationFormId").submit();
		$("#otpVendor").css("border-color", "");
		$('#vendorOTPMandate').hide();
	}
}

function cancelOTPVerify(pageContext){
	$("#vendorOTPPopup").hide();
	$(location).attr('href', pageContext + "/VendorLoginSignUp.jsp?invoke=vendorFlow");
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