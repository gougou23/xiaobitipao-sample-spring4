<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<h1><s:message code="user.profile.head" /></h1>
<c:out value="${user.username}" />
<br />
<c:out value="${user.email}" />
<br />
<c:out value="${user.age}" />
<br />
<c:out value="${user.birthday}" />
