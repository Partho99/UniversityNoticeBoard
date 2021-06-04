<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<h3>you must be admin to see this page</h3>
	<h5>Authorize users only</h5>
	<div class="col-md-6 col-md-offset-3">
		<table class="table table-striped">
			<tr>
				<th>Username</th>
				<th>Email</th>
				<th>Authority</th>
				<th>Enabled</th>
			</tr>
			<c:forEach var="user" items="${users}">
				<tr>
					<td><c:out value="${user.username}"></c:out></td>
					<td><c:out value="${user.email}"></c:out></td>
					<td><c:out value="${user.authority}"></c:out></td>
					<td><c:out value="${user.enabled}"></c:out></td>
				</tr>


			</c:forEach>
		</table>
	</div>

</body>
</html>