package com.application.auction.service;

import com.application.auction.model.account.Account;
import com.application.auction.model.bid.Bid;
import com.application.auction.model.bid.BidRepository;
import com.application.auction.model.lot.Lot;
import jakarta.persistence.PessimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BidService {
    private final BidRepository bidRepository;

    @Autowired
    public BidService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Retryable(
            value = PessimisticLockException.class,
            maxAttempts = 5,
            backoff = @Backoff(delay = 300)  // Delay of 1 second between retries
    )
    public Bid makeBid(Bid bid, Lot lot, Account account) {
        bid.setLot(lot);
        bid.setTimeCreated(LocalDateTime.now());
        bid.setAccount(account);
        makeCoolSound();
        return bidRepository.save(bid);
    }

    private void makeCoolSound() {
        
    }

}
