<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html lang="it">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width = device-width, initial-scale = 1, shrink-to-fit = no">
    <!-- Bootswatch theme  -->
    <link rel="stylesheet" href="https://bootswatch.com/5/zephyr/bootstrap.css">
    <link rel="stylesheet" href="https://bootswatch.com/_vendor/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://bootswatch.com/_vendor/prismjs/themes/prism-okaidia.css">
    <!-- Altri CSS -->
    <link href="<c:url value="/static/css/main.css" />" rel="stylesheet">
    <link rel="stylesheet" href="">
    <title><tiles:insertAttribute name="titolo" /></title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="index">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarColor01">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<spring:url value="cars"/>">Parco Auto</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="reservations">Account</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="users">Admin</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<br><br>
<tiles:insertAttribute name="content"/>
<br><br>
<tiles:insertAttribute name="footer"/>

<!-- Javascritp con JQuery, Popper, BootstrapJS -->
<script src="https://bootswatch.com/_vendor/jquery/dist/jquery.min.js"></script>
<script src="https://bootswatch.com/_vendor/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://bootswatch.com/_vendor/prismjs/prism.js"></script>
</body>
</html>