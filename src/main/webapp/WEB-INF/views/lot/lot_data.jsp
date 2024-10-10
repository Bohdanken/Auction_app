<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${lot.name}</title>
    <!-- Link the external CSS file -->

    <style>
        <%@include file="/WEB-INF/views/css/style.css"%>
        <%@include file="/WEB-INF/views/lot/lot_data.css"%>
    </style>
</head>
<body>
    <div class="lot-container">
        <h1 class="lot-name">${lot.name}</h1>

        <div class="lot-image">
            <img src="${lot.imageUrl}" alt="${lot.name}" class="lot-image">
        </div>

        <div class="lot-description">
            <p>${lot.description}</p>
        </div>
    </div>
</body>
</html>
