package com.application.auction.service;

import com.application.auction.dao.BidDAO;
import com.application.auction.model.Bid;
import com.application.auction.model.account.Account;
import com.application.auction.model.lot.Lot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidService {
    private final BidDAO bidDAO;

    @Autowired
    public BidService(BidDAO bidDAO) {
        this.bidDAO = bidDAO;
    }

    public boolean makeBid(Bid bid, Lot lot, Account account) {
        bid.setLot(lot);
        bid.setAccount(account);
        Bid savedBid = bidDAO.save(bid);
        return savedBid.getId() != null;
    }


}
