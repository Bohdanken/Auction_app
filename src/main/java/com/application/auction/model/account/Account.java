package com.application.auction.model.account;

import com.application.auction.model.Bid;
import com.application.auction.model.auction.Auction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private AccountRole accountRole;

    @OneToMany(mappedBy = "account")
    private List<Bid> bids;

    @ManyToMany
    @JoinTable(
            name = "account_auction",
            joinColumns = @JoinColumn(name="account_id"),
            inverseJoinColumns = @JoinColumn(name="auction_id")
    )
    private List<Auction> auctions;


    public Account(String name, String email, String password, AccountRole accountRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountRole = accountRole;
    }

    public Account(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
