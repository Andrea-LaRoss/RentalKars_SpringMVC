<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="jumbotron mt-5">
    <div class="img"></div>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h1>${intestazione}</h1>
                <p>${saluti}</p>
                <sec:authorize access="!isAuthenticated()">
                    <a href="<spring:url value="/login/form"/>" id="SignIn" class="btn btn-primary btn-lg">Accedi</a>
                </sec:authorize>
            </div>
        </div>
    </div>

</div>
