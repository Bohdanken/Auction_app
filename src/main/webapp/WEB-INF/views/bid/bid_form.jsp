<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Account Form</title>
    <style>
        <%@include file="/WEB-INF/views/bid/bid_form.css"%>
        <%@include file="/WEB-INF/views/css/style.css" %>
    </style>
</head>

<body>
<div class="container">
    <h2>Bid Information</h2>
    <form action="submit_bid" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${account.name}" placeholder="Enter your name" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${account.email}" placeholder="Enter your email"
                   required>
        </div>

        <div class="form-group">
            <label for="lot">Select Lot:</label>
            <select id="lot" name="lot">
                <c:forEach var="lot" items="${lots}">
                    <option value="${lot.id}">${lot.name}</option>
                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <label for="bidSize">Bid:</label>
            <input type="number" step="0.01" id="bidSize" name="bidSize" placeholder="Enter bid size" required>
        </div>

        <div class="form-group">
            <button type="submit">Submit</button>
        </div>
    </form>
</div>
</body>
</html>
