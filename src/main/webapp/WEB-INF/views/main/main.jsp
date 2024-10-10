<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page - Current Auction</title>
    <style>
        <%@include file="/WEB-INF/views/main/main.css"%>
        <%@include file="/WEB-INF/views/css/style.css" %>
    </style>
</head>

<body>
<div class="container">
    <!-- Auction Title -->
    <h2>Current Auction: ${auction.description}</h2>

    <!-- Items Table -->
    <div class="items-grid">
        <c:forEach items="${lots}" var="lot">
            <div class="item">
                <!-- Placeholder for lot fields -->
                <div class="item-content">
                    <p><strong>Item Name:</strong> ${lot.description}</p>
                    <p><strong>Starting Price:</strong> &pound; ${lot.startPrice}</p>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- Total Raised Money -->
    <div class="total-raised">
        <p>Total Raised Money: &pound; ${totalRaised}</p>
    </div>
</div>
</body>
</html>