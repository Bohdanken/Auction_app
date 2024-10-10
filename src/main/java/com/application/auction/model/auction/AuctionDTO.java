package com.application.auction.model.auction;

import com.application.auction.model.lot.Lot;
import com.application.auction.model.lot.LotDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class AuctionDTO {
    private final double totalRaised;
    private final List<LotDTO> lots;

    public AuctionDTO(double totalRaised, List<LotDTO> lots) {
        this.totalRaised = totalRaised;
        this.lots = lots;
    }
}
