<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<h1><s:message code="user.list.head" /></h1>
<ul class="userList">
    <c:forEach items="${userList}" var="user">
        <li id="user_<c:out value="user.id"/>">
            <c:out value="${user.username}" />
            <c:out value="${user.email}" />
            <c:out value="${user.age}" />
            <c:out value="${user.birthday}" />
        </li>
    </c:forEach>
</ul>
