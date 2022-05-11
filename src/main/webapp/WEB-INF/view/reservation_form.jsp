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
                <form:input type="date" class="form-control" path="startDate"/>
                <label for="startDate">Data Inizio</label>
            </div>

            <div class="form-floating mb-4">
                <form:input type="date" class="form-control" path="endDate"/>
                <label for="endDate">Data Fine</label>
            </div>

        </div>

        <div class="d-grid gap-2">
            <input class="btn btn-lg btn-primary" type="submit" value="Aggiungi">
        </div>
    </form:form>

</div>