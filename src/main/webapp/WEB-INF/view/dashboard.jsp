<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="jumbotron">
    <h2>${Titolo}</h2>
</div>

<div class="container">
    <sec:authorize access="hasRole('ADMIN')">

        <a href="<spring:url value="/cars/add/"/>">Aggiungi Auto</a>
        <a href="<spring:url value="/users/add/"/>">Aggiungi Utente</a>
        <a href="<spring:url value="/users/"/>">Lista Utenti</a>
        <a href="<spring:url value="/reservations/"/>">Lista Prenotazioni</a>
    </sec:authorize>

    <sec:authorize access="hasRole('USER')">
        <a href="<spring:url value="/users/update/${User}"/>">Modifica il tuo profilo</a>
        <a href="<spring:url value="/reservations/"/>">Lista Prenotazioni</a>
    </sec:authorize>
</div>