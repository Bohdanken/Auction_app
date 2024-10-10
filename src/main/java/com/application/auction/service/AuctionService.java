package com.application.auction.service;

import com.application.auction.model.auction.Auction;
import com.application.auction.model.auction.AuctionRepository;
import com.application.auction.model.lot.Lot;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;

    private Auction currentAuction;

    @Value("${auction.current.id}")
    private int currentAuctionId;

    private final List<SseEmitter> emitters = new ArrayList<>();

    @PostConstruct
    public void initializeCurrentAuction() {
        this.currentAuction = auctionRepository.findById(currentAuctionId)
                .orElseThrow(() -> new IllegalArgumentException("Auction not found with id: " + currentAuctionId));
    }

    public Auction getCurrentAuction() {
        return currentAuction;
    }

    public void setCurrentAuction(Auction auction) {
        this.currentAuction = auction;
    }

    public void addEmitter(SseEmitter emitter) {
        this.emitters.add(emitter);
        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> this.emitters.remove(emitter));
    }

    public void notifyClients() {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        this.emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event().name("auctionUpdate").data("New bid placed"));
            } catch (Exception e) {
                deadEmitters.add(emitter);
                log.error("Error notifying client: ", e);
            }
        });
        this.emitters.removeAll(deadEmitters);
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