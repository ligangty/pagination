<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="ligangty.common.pagination/pagination" prefix="page"%>
<html>
<head>
</head>

<body>
<page:pagination action="${pageContext.request.contextPath}/ShowSummary" divCssClass="page" />
</body>
</html>




