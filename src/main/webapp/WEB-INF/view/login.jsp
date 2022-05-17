<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <c:url var="loginUrl" value="/login" />
    <form action="${loginUrl}" method="POST">
        <div class="form-group">

            <c:if test="${param.error != null}">
                <div class="alert alert-danger">
                    <p><spring:message code="login.form.errmsg"/></p>
                </div>
            </c:if>

            <c:if test="${param.forbidden != null}">
                <div class="alert alert-danger">
                    <p><spring:message code="login.form.forbiddenmsg"/></p>
                </div>
            </c:if>

            <c:if test="${param.logout != null}">
                <div class="alert alert-success">
                    <p><spring:message code="login.form.logoutmsg"/></p>
                </div>
            </c:if>

            <div class="form-floating mb-4">
                <input type="text" class="form-control"  name="email" id="email" placeholder="Inserisci la mail" required/>
                <label for="email">Email</label>
            </div>

            <div class="form-floating mb-4">
                <input type="password" class="form-control" name="password" id="password" placeholder="Inserisci la password" required/>
                <label for="password">Password</label>
            </div>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="form-actions">
                <input type="submit" class="btn btn-lg btn-primary" value="Accedi">
                <a href="<spring:url value="/index/" />" class="btn btn-lg btn-primary">Torna Indietro</a>
            </div>
        </>
    </form>
</div>
