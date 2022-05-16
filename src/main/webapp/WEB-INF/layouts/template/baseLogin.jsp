<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
    <head>
        <title>RentalKars:Login</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width = device-width, initial-scale = 1, shrink-to-fit = no">
        <!-- Bootswatch theme  -->
        <link rel="stylesheet" href="https://bootswatch.com/5/zephyr/bootstrap.css">
        <link rel="stylesheet" href="https://bootswatch.com/_vendor/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://bootswatch.com/_vendor/prismjs/themes/prism-okaidia.css">
        <!-- Altri CSS -->
        <link href="<c:url value="/static/css/main.css" />" rel="stylesheet">
    </head>

    <body>


        <tiles:insertAttribute name="content"/>
    </body>
</html>