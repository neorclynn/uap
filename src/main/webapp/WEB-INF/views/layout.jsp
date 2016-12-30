<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>

<html>
<head>
    <title><tiles:insertAttribute name="title"/></title>
    <link href="<c:url value="/static/css/style.css" />" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="bg">
    <div id="outer">
        <div id="header">
            <tiles:insertAttribute name="header"/>
        </div>
        <div id="banner">
            <img src="<c:url value="/static/images/pic1.jpg" />" width="932" height="172" alt="">
        </div>
        <div id="main">
            <tiles:insertAttribute name="body"/>
        </div>
    </div>
    <div id="copyright">
        <tiles:insertAttribute name="footer"/>
    </div>
</div>
</body>
</html>