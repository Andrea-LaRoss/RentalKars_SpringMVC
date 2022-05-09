<div class="container">
    <h2>${Titolo}</h2>
    <br><br><br>

    <div>
        <b-alert show variant="danger">${errorMsg}</b-alert>
    </div>
</div>

<div class="container">
    <form action="CarServlet" method="GET">
        <div class="form-group">
            <div class="form-floating mb-4">
                <input type="text" class="form-control" name="manufacturer" id="manufacturer" required value="${car.manufacturer}">
                <label for="manufacturer">Marca</label>
            </div>
            <div class="form-floating mb-4">
                <input type="text" class="form-control" name="model" id="model" required value="${car.model}">
                <label for="model">Modello</label>
            </div>
            <div class="form-floating mb-4">
                <input type="text" class="form-control" name="type" id="type" required value="${car.type}">
                <label for="type">Tipo</label>
            </div>
            <div class="form-floating mb-4">
                <input type="text" class="form-control" name="numPlate" id="numPlate" required value="${car.numPlate}">
                <label for="numPlate">Targa</label>
            </div>
        </div>
        <div class="form-floating mb-4">
            <input type="date" class="form-control" name="regDate" id="regDate" required value="${car.regDate}">
            <label for="regDate">Immatricolazione</label>
        </div>

        <input type="hidden" name="command" value="ADDorUPDATE">
        <input type="hidden" name="carId" value="${car.id}">

        <div class="d-grid gap-2">
            <input class="btn btn-lg btn-primary" type="submit" value="Aggiungi">
        </div>

    </form>
</div>