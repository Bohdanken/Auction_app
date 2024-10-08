package com.application.auction.service;

import com.application.auction.dao.BidDAO;
import com.application.auction.model.Bid;
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

    public void makeBid(Bid bid, Lot lot) {
        bid.setLot(lot);
        bidDAO.save(bid);
    }


}
