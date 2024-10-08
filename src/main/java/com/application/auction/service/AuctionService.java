package com.application.auction.service;

import com.application.auction.dao.AuctionDAO;
import com.application.auction.model.auction.Auction;
import com.application.auction.model.lot.Lot;
import com.application.auction.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionService {
    private final AuctionDAO auctionDAO;

    private List<Lot> getLots() {
        List<Lot> lots = new ArrayList<>();
        lots.add(new Lot(1L, "Lot A"));
        lots.add(new Lot(2L, "Lot B"));
        lots.add(new Lot(3L, "Lot C"));
        return lots;
    }

    @Autowired
    public AuctionService(AuctionDAO auctionDAO) {
        this.auctionDAO = auctionDAO;
    }


    public List<Lot> getLots(int auctionId) {
        Auction auction = getAuction(Constants.AUCTION_ID);
        return auction == null || auction.getLotList().isEmpty() ? getLots() : auction.getLotList();
    }

    public String getLotNameById(int lotId) {
        List<Lot> lots = getLots(Constants.AUCTION_ID);
        if (lots == null || lots.isEmpty()) {
            return "None";
        }
        return lots.stream().filter(x -> x.getId() == lotId).map(Lot::getName).findFirst().orElse("None");
    }



    private Auction getAuction(int auctionId) {
        return auctionDAO.findById(auctionId).orElse(null);
    }
}
