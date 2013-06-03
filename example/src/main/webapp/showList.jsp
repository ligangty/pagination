<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="ligangty.common.pagination/pagination" prefix="pagination"%>
<html>
<head>
</head>

<body>
<pagination:pagination action="${pageContext.request.contextPath}/TcmsShowSummary" divCssClass="page" />
</body>
</html>




