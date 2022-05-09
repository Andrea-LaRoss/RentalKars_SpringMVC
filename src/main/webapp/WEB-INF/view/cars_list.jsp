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
            <th scope="col">Marca</th>
            <th scope="col">Modello</th>
            <th scope="col">Tipo</th>
            <th scope="col">Targa</th>
            <th scope="col">Data Immatricolazione</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="car" items="${carsList}">
            <c:url var="delete" value="">
                <c:param name="command" value="DELETE"/>
                <c:param name="carId" value="${car.id}"/>
            </c:url>
            <tr class="table-secondary">
                <td>${car.manufacturer}</td>
                <td>${car.model}</td>
                <td>${car.type}</td>
                <td>${car.numPlate}</td>
                <td>${car.regDate}</td>
                <td>
                    <c:if test="${loggedUser.admin eq true}">
                        <a class="btn btn-outline-primary" href="<spring:url value="/car_form/${car.id}"/>">Modifica</a>
                        <a class="btn btn-outline-danger" href="${delete}" onclick="if(!(confirm('Sei sicuro?'))) return false">Elimina</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${loggedUser.admin eq true}">
        <div class="d-grid gap-2">
            <a class="btn btn-info" href=""><strong>Aggiungi</strong></a>
        </div>
    </c:if>
</div>
