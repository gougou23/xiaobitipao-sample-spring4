<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="<s:url value="/resources/css/login/login.css" />">
<div class="container">
    <c:url var="loginUrl" value="/login" />
    <form action="${loginUrl}" method="post" class="form-signin">
        <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                <p>Invalid username and password.-${param.error}-</p>
            </div>
        </c:if>
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="username" class="sr-only">Email address</label>
        <input type="email" id="username" class="form-control" name="username" placeholder="Enter Email address" required autofocus>
        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password" class="form-control" name="password" placeholder="Enter Password" required>
        <div class="checkbox">
            <label> <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
</div>