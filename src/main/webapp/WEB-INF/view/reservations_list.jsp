<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="container">
    <h2>${titolo}</h2>
    <br><br><br>

    <div>
        <b-alert show variant="danger">${errorMsg}</b-alert>
    </div>
</div>

<div class="container">

    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Cliente</th>
            <th scope="col">Auto</th>
            <th scope="col">Data Inizio</th>
            <th scope="col">Data Fine</th>
            <th scope="col">Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="rent" items="${reservations}">
            <tr class="table-secondary">
                <td>${rent.user.firstName} ${rent.user.lastName}</td>
                <td>${rent.car.manufacturer} ${rent.car.model}</td>
                <td>${rent.startDate}</td>
                <td>${rent.endDate}</td>
                <td>${rent.status}</td>
                <td></td>
                <td>
                    <a class="btn btn-outline-primary" href="<spring:url value="/reservations/update/${rent.id}"/>">Modifica</a>
                    <a class="btn btn-outline-danger" href="<spring:url value="/reservations/remove/${rent.id}"/>" onclick="if(!(confirm('Sei sicuro?'))) return false">Elimina</a>
                    <c:if test="${rent.status == 'In Attesa'}">
                            <a class="btn btn-outline-success" href="<spring:url value="/reservations/approve/${rent.id}"/>">Approva</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
        <sec:authorize access="hasRole('USER')">
            <div class="d-grid gap-2">
                <a class="btn btn-primary" href="<spring:url value="/reservations/add"/>"><strong>Nuova prenotazione</strong></a>
            </div>
        </sec:authorize>
</div>

