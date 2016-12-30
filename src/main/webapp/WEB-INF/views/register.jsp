<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ page session="false" %>
<html>
<head>
    <title>Register</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<s:url value="/static/css/style.css" />">
    <style>
        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1>Register</h1>
<sf:form method="POST" commandName="user">
    Username <sf:input path="username"/><sf:errors path="username" cssClass="error"/><br/>
    Password <sf:password path="password"/><sf:errors path="password" cssClass="error"/><br/>
    <input type="submit" value="Save">
</sf:form>
</body>
</html>