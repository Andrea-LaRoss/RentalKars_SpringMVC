<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <h2>${Titolo}</h2>
    <br><br><br>

</div>

<div class="container">

    <div class="container-fluid">
        <form:form class="d-flex" method="GET" action="${pageContext.request.contextPath}/users/search">

            <select name="filter">
                <option value="firstName">Nome</option>
                <option value="lastName">Cognome</option>
                <option value="email">Email</option>
            </select>

            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Cerca.." aria-describedby="button-addon2" name="value" value="${value}"/>
                <button class="btn btn-primary" type="submit">Cerca</button>
            </div>

        </form:form>

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
            <tr class="table-secondary">
                <td>${user.firstName} ${user.lastName}</td>
                <td>${user.birthday}</td>
                <td>${user.email}</td>
                <td>
                    <a class="btn btn-outline-primary" href="<spring:url value="/users/update/${user.email}"/>">Modifica</a>
                    <a class="btn btn-outline-danger" href="<spring:url value="/users/remove/${user.id}"/>" onclick="if(!(confirm('Sei sicuro?'))) return false">Elimina</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

        <div class="d-grid gap-2">
            <a class="btn btn-lg btn-primary" href="<spring:url value="/users/add"/>"><strong>Aggiungi</strong></a>
        </div>

</div>
