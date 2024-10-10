package com.application.auction.model.account;

import com.application.auction.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByEmail(String email);

    Account findByEmail(String email);
}
