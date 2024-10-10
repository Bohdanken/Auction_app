package com.application.auction.model.auction;

import com.application.auction.model.account.Account;
import com.application.auction.model.lot.Lot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "auction")
@Getter
@Setter
@NoArgsConstructor
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "duration", columnDefinition = "INTERVAL")
    private LocalTime duration;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AuctionStatus status;

    @Column(name = "start_timestamp")
    private LocalDateTime startTimestamp;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private List<Lot> lotList;

    @ManyToMany
    @JoinTable(
            name = "account_auction",
            joinColumns = @JoinColumn(name = "auction_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private List<Account> accounts;

    public Auction(String name, String description, LocalTime duration, AuctionStatus status, LocalDateTime startTimestamp) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.status = status;
        this.startTimestamp = startTimestamp;
    }
}
