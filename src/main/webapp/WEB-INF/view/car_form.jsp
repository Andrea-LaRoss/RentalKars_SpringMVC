<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
    <h2>${Titolo}</h2>
    <br><br><br>
</div>

<div class="container">

    <form:form method="POST" modelAttribute="car">
        <form:errors path="*" cssClass="alert alert-danger" element="div"/>
        <div class="form-group">

            <div class="form-floating mb-4">
                <form:input type="text" class="form-control" name="manufacturer" path="manufacturer"/>
                <label for="manufacturer">Marca</label>
            </div>

            <div class="form-floating mb-4">
                <form:input type="text" class="form-control" name="model" path="model"/>
                <label for="model">Modello</label>
            </div>

            <div class="form-floating mb-4">
                <form:input type="text" class="form-control" name="type" path="type"/>
                <label for="type">Tipo</label>
            </div>

            <div class="form-floating mb-4">
                <form:input type="text" class="form-control" name="numPlate" path="numPlate"/>
                <label for="numPlate">Targa</label>
            </div>

            <div class="form-floating mb-4">
                <form:input type="date" class="form-control" name="regDate" path="regDate"/>
                <label for="regDate">Immatricolazione</label>
            </div>

        </div>

        <div class="d-grid gap-2">
            <input class="btn btn-lg btn-primary" type="submit" value="Salva">
        </div>
    </form:form>

</div>