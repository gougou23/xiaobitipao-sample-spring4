<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<h1><s:message code="user.register.head" /></h1>
<sf:form method="POST" commandName="userForm" enctype="multipart/form-data">
    <sf:errors path="*" element="div" cssClass="errors" />
    <sf:label path="username" cssErrorClass="error">
        <s:message code="username" />
    </sf:label>
    <s:message code="mark.colon.half" />
    <sf:input path="username" cssErrorClass="error" />
    <sf:errors path="username" cssClass="error" />
    <br />
    <sf:label path="password" cssErrorClass="error">
        <s:message code="password" />
    </sf:label>
    <s:message code="mark.colon.half" />
    <sf:password path="password" cssErrorClass="error" />
    <sf:errors path="password" cssClass="error" />
    <br />
    <sf:label path="email" cssErrorClass="error">
        <s:message code="email" />
    </sf:label>
    <s:message code="mark.colon.half" />
    <sf:input path="email" type="email" cssErrorClass="error" />
    <sf:errors path="email" cssClass="error" />
    <br />
    <sf:label path="age" cssErrorClass="error">
        <s:message code="age" />
    </sf:label>
    <s:message code="mark.colon.half" />
    <sf:input path="age" cssErrorClass="error" />
    <sf:errors path="age" cssClass="error" />
    <br />
    <sf:label path="birthday" cssErrorClass="error">
        <s:message code="birthday" />
    </sf:label>
    <s:message code="mark.colon.half" />
    <sf:input path="birthday" cssErrorClass="error" />
    <sf:errors path="birthday" cssClass="error" />
    <br />
    <sf:label path="profilePicture" cssErrorClass="error">
        <s:message code="profilePicture" />
    </sf:label>
    <s:message code="mark.colon.half" />
    <sf:input path="profilePicture" type="file" accept="image/jpeg,image/png,image/gif" cssErrorClass="error" />
    <sf:errors path="profilePicture" cssClass="error" />
    <br />
    <input type="submit" value="Register" />
</sf:form>
