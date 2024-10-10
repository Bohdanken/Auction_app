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
    <div class="items-grid" id="lots-container">
        <c:forEach items="${lots}" var="lot">
            <div class="item" id="lot-${lot.id}">
                <div class="item-content">
                    <p><strong>Item Name:</strong> ${lot.description}</p>
                    <p><strong>Starting Price:</strong> &pound; ${lot.startPrice}</p>
                    <p>
                        <strong>
                            Current Highest Bid:
                        </strong> &pound;
                        <span class="highest-bid" id="highest-bid-${lot.id}">
                            <c:choose>
                                <c:when test="${not empty lot.highestBid}">
                                    ${lot.highestBid.amount}
                                </c:when>
                                <c:otherwise>
                                    0.00
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </p>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- Total Raised Money -->
    <div class="total-raised">
        <p>Total Raised Money: &pound; <span id="total-raised">${totalRaised}</span></p>
    </div>
</div>

<script>
    // SSE: Listening for auction updates
    const eventSource = new EventSource("/auction-updates");

    // Listen for 'auctionUpdate' event
    eventSource.addEventListener("auctionUpdate", function(event) {
        console.log("Received auction update: ", event.data);

        // Fetch updated auction data (highest bids and total raised) via AJAX
        fetch("/main-data")
            .then(response => response.json())
            .then(data => {
                // Update the highest bid and total raised dynamically
                updateAuctionData(data);
            });
    });

    // Function to update the highest bids and total raised
    function updateAuctionData(data) {
        // Update total raised
        console.log(data);
        document.getElementById("total-raised").textContent = data.totalRaised.toFixed(2);

        // Update each lot's highest bid
        data.lots.forEach(lot => {
            let bidElement = document.getElementById("highest-bid-" + lot.id);
            if (bidElement) {
                bidElement.textContent = lot.highestBid ? lot.highestBid.toFixed(2) : "0.00";
            }
        });
    }
</script>

</body>
</html>
