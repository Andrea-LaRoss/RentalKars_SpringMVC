<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <h2>${Titolo}</h2>
    <br><br><br>
</div>


<div class="container">

    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Marca</th>
            <th scope="col">Modello</th>
            <th scope="col">Tipo</th>
            <th scope="col">Targa</th>
            <th scope="col">Data Immatricolazione</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="car" items="${carsList}">
            <tr class="table-secondary">
                <td>${car.manufacturer}</td>
                <td>${car.model}</td>
                <td>${car.type}</td>
                <td>${car.numPlate}</td>
                <td>${car.regDate}</td>
                <td>
                    <sec:authorize access="hasRole('ADMIN')">
                        <a class="btn btn-outline-primary" href="<spring:url value="/cars/form?id=${car.id}" />" >Modifica</a>
                        <a class="btn btn-outline-danger" href="<spring:url value="/cars/remove/${car.id}" />" onclick="if(!(confirm('Sei sicuro?'))) return false">Elimina</a>
                    </sec:authorize>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <sec:authorize access="hasRole('ADMIN')">
        <div class="d-grid gap-2">
            <a class="btn btn-lg btn-primary" href="<spring:url value="/cars/form" />"><strong>Aggiungi</strong></a>
        </div>
    </sec:authorize>
</div>
