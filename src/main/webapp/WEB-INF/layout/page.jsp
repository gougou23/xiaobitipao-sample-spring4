<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ page session="false"%>
<html lang="ja-JP">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title><s:message code="project.name" /></title>
<link rel="icon" href="/resources/imgs/favicon.ico">
<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/bootstrap/bootstrap.css" />">
<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/style.css" />">
</head>
<body>
    <div id="header">
        <t:insertAttribute name="header" />
    </div>
    <div id="navi">
        <t:insertAttribute name="navi" />
    </div>
    <div id="content">
        <t:insertAttribute name="body" />
    </div>
    <div id="footer">
        <t:insertAttribute name="footer" />
    </div>
</body>
</html>
