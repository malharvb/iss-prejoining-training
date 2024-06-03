<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<nav class="nav bg-primary p-3">
	  <span class="nav-link text-light my-auto"><strong>Welcome ${user.email}</strong></span>
	  <a class="nav-link text-light" href="<%= request.getContextPath()%>/logout"><button class="btn btn-light">Logout</button></a>
	</nav>
	
	<p class="px-5 pt-5">Server side project with authentication</p>
	
	<p class="px-5">Hope you have a great day, ${user.email}!</p>
</body>
</html>