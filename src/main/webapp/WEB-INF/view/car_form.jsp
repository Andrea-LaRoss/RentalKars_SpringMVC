<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <h2>${Titolo}</h2>
    <br><br><br>

    <div>
        <b-alert show variant="danger">${errorMsg}</b-alert>
    </div>
</div>

<div class="container">

    <form:form method="POST" modelAttribute="carForm">
        <div class="form-group">

            <div class="form-floating mb-4">
                <form:input type="text" class="form-control" id="manufacturer" required="required" path="manufacturer"/>
                <label for="manufacturer">Marca</label>
            </div>

            <div class="form-floating mb-4">
                <form:input type="text" class="form-control" id="model" required="required" path="model"/>
                <label for="model">Modello</label>
            </div>

            <div class="form-floating mb-4">
                <form:input type="text" class="form-control" id="type" required="required" path="type"/>
                <label for="type">Tipo</label>
            </div>

            <div class="form-floating mb-4">
                <form:input type="text" class="form-control" id="numPlate" required="required" path="numPlate"/>
                <label for="numPlate">Targa</label>
            </div>

            <div class="form-floating mb-4">
                <form:input type="date" class="form-control" id="regDate" required="required" path="regDate" value="dateString"/>
                <label for="regDate">Immatricolazione</label>
            </div>

        </div>

        <div class="d-grid gap-2">
            <input class="btn btn-lg btn-primary" type="submit" value="Aggiungi">
        </div>
    </form:form>

</div>