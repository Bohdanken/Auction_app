package com.application.auction.model.lot;

import com.application.auction.model.bid.Bid;
import lombok.Getter;

@Getter
public class LotDTO {
    private final int id;
    private final String name;
    private final double highestBid;

    public LotDTO(int id, Bid highestBid) {
        this.id = id;
        this.highestBid = highestBid == null ? 0 : highestBid.getAmount();
        this.name = highestBid == null ? "" : highestBid.getAccount().getName();
    }

}
