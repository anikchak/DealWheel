$(function() {
	$("#signUp").on( "click", function() {
		  $(".signUpContent").show();
		  $("#submitBtnId").text("Register");
	});
	$("#signIn").on( "click", function() {
		  $(".signUpContent").hide();
		  $("#submitBtnId").text("Register");
	});
	$( "#dateFrom" ).datepicker();
});

function selectRadio(value) {
	if (value) {
		$("#signIn").attr('checked', true);
	} else {
		$("#signUp").attr('checked', true);
	}
}
function registeredUserFunc() {
	//alert("registeredUserFunc called");
	var x = document.getElementById("confirmPswdSpan");
	//alert(x);
	x.style.display = 'none';
	var x = document.getElementById("radId2");
	var y = document.getElementById("radId1");

}

function newUserFunc() {
	//alert("newUserFunc called");
	var x = document.getElementById("confirmPswdSpan");

	x.style.display = 'block';
	var x = document.getElementById("radId2");
	var y = document.getElementById("radId1");
	if (x != null) {

	}
	//alert(x.checked);
}

function verifyPswd() {
	//alert("foo called");
	var x = document.getElementById("radId2");
	var y = document.getElementById("radId1");
	var a = document.getElementById("userId");
	var b = document.getElementById("pswdId");
	var c = document.getElementById("confirmPswdId");
	var form = document.getElementById("submitFormId");
	if (x != null || y!=null) {
		if (x.checked) {
			if (a.value == "" || b.value == ""
					|| (c != null && c.value == "")) {
				alert("Enter all the values");

				return false;
			}
		}else if(a.value == "" || b.value == ""){
			alert("Enter all the values");

			return false;
		}
	}
	if (x != null) {
		if (x.checked) {
		//	alert("inside if");
			if (c.value != b.value) {
				alert("Password and Confirm Password mismatch");
				c.value = "";
				return false;
			}
		}
	}
	form.submit();
}