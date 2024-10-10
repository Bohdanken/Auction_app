package com.application.auction.model.auction;

import com.application.auction.model.account.Account;
import com.application.auction.model.lot.Lot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
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



    @Column(name = "start_timestamp")
    private LocalDateTime startTimestamp;

    @Column(name = "end_timestamp")
    private LocalDateTime endTimestamp;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private List<Lot> lotList;

    @ManyToMany
    @JoinTable(
            name = "account_auction",
            joinColumns = @JoinColumn(name = "auction_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    private List<Account> accounts;

    public Auction(String name, String description, LocalDateTime endTimestamp,  LocalDateTime startTimestamp) {
        this.name = name;
        this.description = description;
        this.endTimestamp = endTimestamp;
        this.startTimestamp = startTimestamp;
    }
}
