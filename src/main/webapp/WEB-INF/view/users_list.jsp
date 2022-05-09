<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <h2>${titolo}</h2>
    <br><br><br>

    <div>
        <b-alert show variant="danger">${errorMsg}</b-alert>
    </div>
</div>

<div class="container">
    <div class="container-fluid">

        <form class="d-flex" action="">
            <input type="hidden" name="command" value="SEARCH">
            <select name="input">
                <option value="firstName">Nome</option>
                <option value="lastName">Cognome</option>
                <option value="email">Email</option>
            </select>
            <div class="input-group mb-3">
                <input type="text" name="toSearch" class="form-control" placeholder="Cerca.." aria-label="Cerca.." aria-describedby="button-addon2">
                <button class="btn btn-primary" type="submit" value="cerca" >Button</button>
            </div>
        </form>
    </div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Nome</th>
            <th scope="col">Data di Nascita</th>
            <th scope="col">Email</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${usersList}">
            <c:url var="delete" value="">
                <c:param name="command" value="DELETE"/>
                <c:param name="userId" value="${user.id}"/>
            </c:url>
            <tr class="table-secondary">
                <td>${user.firstName} ${user.lastName}</td>
                <td>${user.birthday}</td>
                <td>${user.email}</td>
                <td>
                    <a class="btn btn-outline-primary" href="<spring:url value="/user_form/${user.id}"/>">Modifica</a>
                    <a class="btn btn-outline-danger" href="${delete}" onclick="if(!(confirm('Sei sicuro?'))) return false">Elimina</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${loggedUser.admin eq true}">
        <div class="d-grid gap-2">
            <a class="btn btn-lg btn-info" href=""><strong>Aggiungi</strong></a>
        </div>
    </c:if>
</div>
