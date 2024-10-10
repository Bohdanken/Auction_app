package com.application.auction.service;

import com.application.auction.model.auction.Auction;
import com.application.auction.model.auction.AuctionRepository;
import com.application.auction.model.lot.Lot;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionService {

    @Getter
    private Auction currentAuction;

    @Autowired
    private AuctionRepository auctionRepository;

    @Value("${auction.current.id}")
    private int currentAuctionId;

    @PostConstruct
    public void initializeCurrentAuction() {
        this.currentAuction = auctionRepository.findById(currentAuctionId)
                .orElseThrow(() -> new IllegalArgumentException("Auction not found with id: " + currentAuctionId));
    }

    public List<Lot> getLots(Long auctionId) {
        Auction auction = getAuction(auctionId);
        return auction == null || auction.getLotList().isEmpty() ? getDefaultLots() : auction.getLotList();
    }

    public String getLotNameById(int lotId) {
        Lot lot = getLotById(lotId);
        if (lot == null) {
            return "Lot not found";
        }
        String lotName = lot.getName();
        return lotName == null || lotName.trim().isEmpty() ? "None" : lotName;
    }

    private Auction getAuction(Long auctionId) {
        return auctionRepository.findById(auctionId.intValue()).orElse(null);
    }

    public Lot getLotById(int lotId) {
        List<Lot> lots = getLots((long) currentAuctionId);
        if (lots == null || lots.isEmpty()) {
            return null;
        }
        return lots.stream().filter(x -> x.getId() == lotId).findFirst().orElse(null);
    }

    private List<Lot> getDefaultLots() {
        List<Lot> lots = new ArrayList<>();
        lots.add(new Lot(1, "Lot A"));
        lots.add(new Lot(2, "Lot B"));
        lots.add(new Lot(3, "Lot C"));
        return lots;
    }
}