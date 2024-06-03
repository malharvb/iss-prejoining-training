<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<nav class="nav bg-primary p-3">
  <a class="nav-link text-light" href="<%= request.getContextPath()%>/index.jsp">Home</a>
  <a class="nav-link text-light" href="<%= request.getContextPath()%>/pages/login.jsp">Login</a>
  <a class="nav-link text-light" href="<%= request.getContextPath()%>/pages/register.jsp">Register</a>
</nav>