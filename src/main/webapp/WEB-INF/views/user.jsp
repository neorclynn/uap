<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<c:out value="${a}" />
    <c:forEach items="${users}" var="user">
        <c:out value="${user.username}" />
    </c:forEach>
</body>
</html>