<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<div id="logo">
    <h1>
        <a href="#">Serious Face</a>
    </h1>
</div>
<div id="nav">
    <ul>
        <li class="first active">
            <a href="#">Home</a>
        </li>
        <li>
            <a href="<c:url value="/user" />">User</a>
        </li>
        <li>
            <a href="<c:url value="/user/register" />">Register</a>
        </li>
        <li>
            <a href="#">Support</a>
        </li>
        <li>
            <a href="#">Blog</a>
        </li>
        <li>
            <a href="#">About</a>
        </li>
        <li class="last">
            <a href="#">Contact</a>
        </li>
    </ul>
    <br class="clear">
</div>