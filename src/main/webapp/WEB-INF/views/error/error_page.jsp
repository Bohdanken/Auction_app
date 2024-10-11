<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
    <style>
        <%@include file="/WEB-INF/views/error/error_page.css"%>
        <%@include file="/WEB-INF/views/css/style.css" %>
    </style>
</head>
<body>
    <div class="error-container">
        <h2>Kurwa!</h2>
        <p>${error}</p>
        <a href="/bid" class="back-btn">Try again</a>
    </div>
</body>
</html>
