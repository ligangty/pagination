<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="ligangty.common.pagination/pagination" prefix="page"%>
<html>
<head>
</head>

<body>
<table border="1" cellpadding="1" cellspacing="0" >
<thead>
	<tr>
		<th>id</th>
		<th>name</th>
		<th>email</th>
	</tr>
</thead>
<tbody>
	<c:forEach items="${showList}" var="showBean">
		<tr>
			<td>${showBean.id}</td>
			<td>${showBean.userName}</td>
			<td>${showBean.email}</td>
		</tr>
	</c:forEach>
</tbody>
</table>
<page:pagination action="${pageContext.request.contextPath}/ShowSummary" divCssClass="page" pagesize="30" />
</body>
</html>




