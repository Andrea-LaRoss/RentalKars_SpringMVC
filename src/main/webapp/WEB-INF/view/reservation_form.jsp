<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
    <h2>${Titolo}</h2>
    <br><br><br>
</div>

<div class="container">

    <form:form method="POST" modelAttribute="rentForm">
        <form:errors path="*" cssClass="alert alert-danger" element="div"/>
        <div class="form-group">

            <div class="form-floating mb-4">
                <form:input type="date" class="form-control" name="startDate" path="startDate"/>
                <label for="startDate">Data Inizio</label>
            </div>

            <div class="form-floating mb-4">
                <form:input type="date" class="form-control" name="endDate" path="endDate"/>
                <label for="endDate">Data Fine</label>
            </div>

        </div>

        <div class="d-grid gap-2">
            <input class="btn btn-primary" type="submit" value="Cerca auto">
        </div>
    </form:form>
        <br>

        <div class="container">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Marca</th>
                    <th scope="col">Modello</th>
                    <th scope="col">Targa</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="car" items="${cars}">
                    <tr class="table-secondary">
                        <td>${car.manufacturer}</td>
                        <td>${car.model}</td>
                        <td>${car.numPlate}</td>
                        <td>
                            <a class="btn btn-outline-warning" href="<spring:url value="/reservations/confirm/${car.id}"/>">Prenota</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

</div>