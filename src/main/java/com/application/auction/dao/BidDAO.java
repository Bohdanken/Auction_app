package com.application.auction.dao;

import com.application.auction.model.Bid;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidDAO extends CrudRepository<Bid, Integer> {
}
