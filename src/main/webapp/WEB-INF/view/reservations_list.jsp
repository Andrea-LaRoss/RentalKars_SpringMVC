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
            <c:url var="delete" value="">
                <c:param name="command" value="DELETE"/>
                <c:param name="rentId" value="${rent.id}"/>
            </c:url>
            <c:url var="approve" value="">
                <c:param name="command" value="APPROVE"/>
                <c:param name="rentId" value="${rent.id}"/>
            </c:url>
            <tr class="table-secondary">
                <td>${rent.user.firstName} ${rent.user.lastName}</td>
                <td>${rent.car.manufacturer} ${rent.car.model}</td>
                <td>${rent.startDate}</td>
                <td>${rent.endDate}</td>
                <td>${rent.status}</td>
                <td></td>
                <td>
                    <c:if test="${loggedUser.admin eq false}">
                        <a class="btn btn-outline-primary" href="<spring:url value="/reservation_form/${rent.id}"/>">Modifica</a>
                    </c:if>
                    <a class="btn btn-outline-danger" href="${delete}" onclick="if(!(confirm('Sei sicuro?'))) return false">Elimina</a>
                    <c:if test="${rent.status == 'In Attesa'}">
                        <c:if test="${loggedUser.admin eq true}">
                            <a class="btn btn-outline-success" href="${approve}">Approva</a>
                        </c:if>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${loggedUser.admin eq false}">
        <div class="d-grid gap-2">
            <a class="btn btn-info" href=""><strong>Nuova prenotazione</strong></a>
        </div>
    </c:if>
</div>

