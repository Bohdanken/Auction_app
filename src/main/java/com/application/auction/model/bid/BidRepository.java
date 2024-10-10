package com.application.auction.model.bid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    @Query("SELECT MAX(b.amount) FROM Bid b WHERE b.lot.id = :lotId")
    Double findHighestBidForLot(int lotId);
}