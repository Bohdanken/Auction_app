package com.application.auction.model.lot;

import com.application.auction.model.auction.Auction;
import com.application.auction.model.bid.Bid;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "lot")
@Getter
@Setter
@NoArgsConstructor
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "start_price")
    private double startPrice;

    @JoinColumn(name = "highest_bid")
    @OneToOne
    private Bid highestBid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    private Auction auction;

    @OneToMany(mappedBy = "lot", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bid> bids;

    public Lot(String name, String description, double startPrice, Auction auction) {
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.auction = auction;
    }

    public Lot(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
