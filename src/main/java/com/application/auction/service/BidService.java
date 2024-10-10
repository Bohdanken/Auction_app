package com.application.auction.service;

import com.application.auction.model.bid.Bid;
import com.application.auction.model.bid.BidRepository;
import com.application.auction.model.account.Account;
import com.application.auction.model.lot.Lot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BidService {
    private final BidRepository bidRepository;

    @Autowired
    public BidService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public boolean makeBid(Bid bid, Lot lot, Account account) {
        bid.setLot(lot);
        bid.setTimeCreated(LocalDateTime.now());
        bid.setAccount(account);
        Bid savedBid = bidRepository.save(bid);
        return savedBid.getId() != null;
    }


    public Double getCurrentHighestBid(Lot lot) {
        return bidRepository.findHighestBidForLot(lot.getId());
    }


}
