package com.application.auction.dao;

import com.application.auction.model.auction.Auction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionDAO extends CrudRepository<Auction, Integer> {
}
