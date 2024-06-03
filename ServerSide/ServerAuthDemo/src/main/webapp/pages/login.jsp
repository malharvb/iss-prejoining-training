<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="../styles.css" rel="stylesheet">
<script src="../scripts/signin-form-validation.js"></script>
<script src="../scripts/login-script.js" defer></script>
</head>
<body>

	<%@ include file="/includes/navbar.jsp" %>
	
	<form class="container-sm py-5" id="login-form" action="<%= request.getContextPath()%>/login">
	  <div class="mb-3">
	    <label for="email" class="form-label">Email address</label>
	    <input type="text" class="form-control" id="email" name="email" required>
	  </div>
	  <div class="mb-3">
	    <label for="password" class="form-label">Password</label>
	    <input type="password" class="form-control" id="password" name="password" required>
	  </div>
	  <div class="error" aria-live="polite"></div>
	  <button type="submit" class="btn btn-primary">Login</button>
	</form>
	
	<!-- Displaying errors occurring on the back-end -->
	<script>
		let errorMsg = `<%= session.getAttribute("loginErrorMsg") %>`;
		if (errorMsg != 'null') {
			document.querySelector('.error').classList.add('show-error');
			document.querySelector('.error').textContent = errorMsg;
		}
	</script>

</body>
</html>