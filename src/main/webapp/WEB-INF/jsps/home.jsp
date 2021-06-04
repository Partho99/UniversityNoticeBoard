<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Classic Spring</title>
</head>
<body>

	<p>
		<a href="${pageContext.request.contextPath}/notices">Show current
			notice</a>
	</p>
	<p>
		<a href="${pageContext.request.contextPath}/notice/create">Create
			notice</a>
	</p>
	<sec:authorize access="!isAuthenticated()">
		<p>
			<a href="<c:url value='/login'/>">Log in</a>
		</p>
	</sec:authorize>

	<sec:authorize access="isAuthenticated()">
		<p>
			<a href="<c:url value='/j_spring_security_logout'/>">Log out</a>
		</p>
	</sec:authorize>

	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<p>
			<a href="<c:url value='/admin'/>">Admin Page</a>
		</p>
	</sec:authorize>

</body>
</html>




<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

