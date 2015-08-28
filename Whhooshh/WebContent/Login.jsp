<%@page import="services.utility.GenericConstant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<script type="text/javascript">
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
</script>
</head>
<body>
<%
String errormsg = (String)session.getAttribute("LOGIN_ERROR");
%>
<h1>Welcome Guest</h1>
	<br>
	<h3>Select any option</h3>

	<form method="post" id="submitFormId" action="${pageContext.request.contextPath}/Login">
		 
		Already Registered..!!!<input type="radio" value="oldRegistration" id="radId1"
			name="option" onclick="registeredUserFunc()" checked /><br> New
		User? Sign Up<input type="radio" value="newRegistration" id="radId2" name="option"
			onclick="newUserFunc()" /><br> <br> <br>
		<div>
		<%if(errormsg!=null){ %>
			<span style="color: RED; font-weight:bold;font-size: 16px;"><%=errormsg %></span>
			<br/>
			<%
			session.removeAttribute(GenericConstant.LOGINERROR);
		} %>
			Username: <input type="text" name="username" id="userId"><br>
			Password: <input type="password" name="password" id="pswdId"><br>
			<span style="display: none;" id="confirmPswdSpan">Confirm
				Password: <input type="password" name="confirmPassword"
				id="confirmPswdId">
			</span>
		</div>
		<input type="button" value="click me" onclick="verifyPswd()"
			id="submitBtnId" />
			
			
	</form>
</body>
</html>