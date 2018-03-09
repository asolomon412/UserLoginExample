<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home Page</title>
</head>
<body>
	<h1>Login</h1>
	<form action="login" method="post">
		UserName:<br> <input type="text" name="username" required><br>
		Password:<br> <input type="password" name="password" required><br>
		<input type="submit" value="submit">
	</form>

	<c:set var="loggedin" value="${fail}" />

	<c:if test="${loggedin == false}">
		<p>${failmsg}
		</p>
	</c:if>

</body>
</html>