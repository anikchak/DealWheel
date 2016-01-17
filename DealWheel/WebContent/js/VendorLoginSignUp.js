/**
 * This JS file contains functions related to VendorLoginSignUp.jsp
 */

function showVendorLogin(){
		$("#loginEmail").val("");
		$("#loginPassword").val("");
		$("#login_Error_Login").hide();
		$("#vendorForgotDiv").hide();
		$("#vendorSignupDiv").hide();
		$("#vendorLoginDiv").show();
	}
function showVendorSignup(){
		$("#email").val("");
		$("#password").val("");
		$("#confirmPassword").val("");
		$("#signup_error").hide();
		$("#vendorLoginDiv").hide();
		$("#vendorForgotDiv").hide();
		$("#vendorSignupDiv").show();
	}
function showVendorForgotPassword(){
		$("#emailForgot").val("");
		$("#login_error_forgot").hide();
		$("#vendorLoginDiv").hide();
		$("#vendorForgotDiv").show();
	}

$(document).ready(function(){
	$("#login_Error_Login").hide();
	$("#signup_error").hide();
	var preventFormSubmit = "N";
	
	$("#login").click(function(){
		preventFormSubmit = "N";
		validateLogin();
		$("#identifier").val("login");
		submitForm();
	});
	
	$("#signup").click(function(){
		preventFormSubmit = "N";
		validateSignUp();
		$("#identifier").val("signup");
		submitForm();
	});
	
	function validateLogin(){
		var loginEmail = $("input[name='loginEmail']").val();
		var loginPassword = $("input[name='loginPassword']").val();
		
		if(loginEmail == "" || loginPassword == ""){
			$("#login_Error_Login").hide();
			$("#vendorLoginErrorMsgSpan").text("Email or Password cannot be blank");
			$("#login_Error_Login").show();
			preventFormSubmit = "Y";
			return false;
		}
		
		if(loginEmail != null && loginEmail != ""){
			var emailValid = isValidEmailAddress(loginEmail);
			if(!emailValid){
				$("#login_Error_Login").hide();
				$("#vendorLoginErrorMsgSpan").text("Please enter correct email Id");
				$("#login_Error_Login").show();
				preventFormSubmit = "Y";
				return false;
			}
		}
	}
	
	function validateSignUp(){
		var signupEmail = $("input[name='email']").val();
		var signupPassword = $("input[name='password']").val();
		var signupConfirmPassword = $("input[name='confirmPassword']").val();
		
		if(signupEmail == "" || signupPassword == ""  || signupConfirmPassword == "" ){
			$("#signup_error").hide();
			$("#vendorSignupErrorMsgSpan").text("Email or Password cannot be blank");
			$("#signup_error").show();
			preventFormSubmit = "Y";
			return false;
		}
		
		if(signupEmail != null && signupEmail != ""){
			var emailValid = isValidEmailAddress(signupEmail);
			if(!emailValid){
				$("#signup_error").hide();
				$("#vendorSignupErrorMsgSpan").text("Please enter correct email Id");
				$("#signup_error").show();
				preventFormSubmit = "Y";
				return false;
			}
		}
		
		if(signupPassword != signupConfirmPassword){
			$("#signup_error").hide();
			$("#vendorSignupErrorMsgSpan").text("Passwords do not match");
			$("#signup_error").show();
			preventFormSubmit = "Y";
			return false;
		}
	}
	
	function isValidEmailAddress(emailAddress) {
	    var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
	    return pattern.test(emailAddress);
	}
	
	function submitForm(){
		if(preventFormSubmit == "N"){
			$("#loginFormId").submit();
		}
	}
}); 