/**
 * This file contain changes related to Vendor Home tabs 
 */

//Changes for My Profile tab starts
function editFields() {
	$("#fullName").show();
	$("#fullNameSpan").hide();
	$("#primaryContact").show();
	$("#primaryContactSpan").hide();
	$("#secondaryContact").show();
	$("#secondaryContactSpan").hide();
	$("#addr1").show();
	$("#addr1Span").hide();
	$("#addr2").show();
	$("#addr2Span").hide();
	$("#addr3").show();
	$("#addr3Span").hide();
	$("#locality").show();
	$("#localitySpan").hide();
	$("#city").show();
	$("#citySpan").hide();
	$("#state").show();
	$("#stateSpan").hide();
	$("#pinCode").show();
	$("#pinCodeSpan").hide();
	$("#editBtn").hide();
	$("#saveBtn").removeAttr('disabled');
	$("#cancelBtn").show();
}

function cancelOperation(opCode) {
	
	var emptyField = checkForEmptyFields();

	if (emptyField == "N") {
		if(opCode == 'alert'){
			$("#cancelAlertModalId").modal({
				backdrop : 'static',
				keyboard : false
			});
		}else if(opCode == 'ok'){
		$("#fullName").val($("#fullNameSpan").text());	
		$("#fullName").hide();
		$("#fullNameSpan").show();
		$("#primaryContact").val($("#primaryContactSpan").text());
		$("#primaryContact").hide();
		$("#primaryContactSpan").show();
		$("#secondaryContact").val($("#secondaryContactSpan").text());
		$("#secondaryContact").hide();
		$("#secondaryContactSpan").show();
		$("#addr1").val($("#addr1Span").text());
		$("#addr1").hide();
		$("#addr1Span").show();
		$("#addr2").val($("#addr2Span").text());
		$("#addr2").hide();
		$("#addr2Span").show();
		$("#addr3").val($("#addr3Span").text());
		$("#addr3").hide();
		$("#addr3Span").show();
		$("#locality").val($("#localitySpan").text());
		$("#locality").hide();
		$("#localitySpan").show();
		$("#city").val($("#citySpan").text());
		$("#city").hide();
		$("#citySpan").show();
		$("#state").val($("#stateSpan").text());
		$("#state").hide();
		$("#stateSpan").show();
		$("#pinCode").val($("#pinCodeSpan").text());
		$("#pinCode").hide();
		$("#pinCodeSpan").show();
		$("#editBtn").show();
		$("#saveBtn").attr('disabled', 'true');
		$("#cancelBtn").hide();
		}
	}
}

function saveChanges(){
	var returnVal = checkForEmptyFields();
	if(returnVal=='N'){
		//Submit Form
		$("#updateDetailsFormId").submit();
	}
}

function checkForEmptyFields(){
	$('#fullnameMandate').hide();
	$("#fullName").css("border-color", "");
	$('#primaryContactMandate').hide();
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
	$("#pinCode").css("border-color", "");
	
	var emptyField = "N";
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
	return emptyField;
}
//CHanges for My Profile tab ends