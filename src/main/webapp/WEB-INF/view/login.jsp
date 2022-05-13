<form action="/login" method="POST">
    <div class="form-group">

        <div class="form-floating mb-4">
            <input type="text" class="form-control"  name="email" id="email" placeholder="Inserisci la mail" required/>
            <label for="email">Email</label>
        </div>

        <div class="form-floating mb-4">
            <input type="password" class="form-control" name="password" id="password" placeholder="Inserisci la password" required/>
            <label for="password">Password</label>
        </div>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        
        <div class="form-actions">
            <input type="submit" class="btn btn-lg btn-primary" value="Accedi">
        </div>
    </div>
</form>
