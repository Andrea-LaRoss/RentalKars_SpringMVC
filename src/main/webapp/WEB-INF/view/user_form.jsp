<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <h2>${Titolo}</h2>
    <br><br><br>
</div>

<div class="container">

    <form:form method="POST" modelAttribute="userForm">
        <form:errors path="*" cssClass="alert alert-danger" element="div"/>
        <div class="form-group">

            <div class="form-floating mb-4">
                <form:input type="text" class="form-control" path="email"/>
                <label for="email">Email</label>
            </div>

            <div class="form-floating mb-4">
                <form:input type="password" class="form-control" path="password"/>
                <label for="password">Password</label>
            </div>

            <div class="form-floating mb-4">
                <form:input type="text" class="form-control" path="firstName"/>
                <label for="firstName">Nome</label>
            </div>

            <div class="form-floating mb-4">
                <form:input type="text" class="form-control" path="lastName"/>
                <label for="lastName">Cognome</label>
            </div>
            <!--
            <div class="form-floating mb-4">
                <form:input type="date" class="form-control" path="birthday"/>
                <label for="birthday">Data di nascita</label>
            </div> -->


        </div>

        <div class="d-grid gap-2">
            <input class="btn btn-lg btn-primary" type="submit" value="Aggiungi">
        </div>
    </form:form>

</div>