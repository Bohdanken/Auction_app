package com.application.auction.model;

import com.application.auction.model.account.Account;
import com.application.auction.model.lot.Lot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "bid")
@Getter
@Setter
@NoArgsConstructor
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "time_created")
    private LocalDateTime timeCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lot_id")
    private Lot lot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Account account;

    public Bid(double amount, LocalDateTime timeCreated, Lot lot, Account account) {
        this.amount = amount;
        this.timeCreated = timeCreated;
        this.lot = lot;
        this.account = account;
    }

    public Bid(double amount) {
        this.amount = amount;
    }
}
