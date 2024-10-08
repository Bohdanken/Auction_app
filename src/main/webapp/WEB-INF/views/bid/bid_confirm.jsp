<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Bid Confirmation</title>
    <link rel="stylesheet" type="text/css">
    <style>
        <%@include file="/WEB-INF/views/bid/bid_confirm.css" %>
    </style>

</head>
<body>
<div class="container">
    <h2>Bid Submitted Successfully!</h2>
    <p>Thank you for submitting your bid. Your bid has been successfully placed.</p>

    <!-- Display some details if needed -->
    <p>Bid Amount: <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${bidSize}"/> £</p>
    <p>Selected Lot: ${lotName}</p>

    <!-- Button to go back to bid form -->
    <a href="/bid" class="back-btn">Go Back to Bid Form</a>
</div>
</body>
</html>
