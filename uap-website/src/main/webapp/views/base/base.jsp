<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><tiles:getAsString name="title"/></title>
    <script src="/js/bootstrap.min.js"></script>
    <link href="/css/bootstrap.min.css" rel="stylesheet"/>
</head>

<body>
<div class="navbar navbar-fixed-top">
    <div class="container">
        <div class="header">
            <tiles:insertAttribute name="header"/>
        </div>
    </div>
</div>

<div id="bg">
    <div id="outer">
        <div id="main">
            <div id="sidebar">
                <tiles:insertAttribute name="sidebar"/>
            </div>
            <div id="content">
                <tiles:insertAttribute name="body"/>
            </div>
            <br class="clear"/>
        </div>
    </div>
    <tiles:insertAttribute name="footer"/>
</div>
</body>
</html>