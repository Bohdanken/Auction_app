package com.application.auction.service;


import com.application.auction.model.account.Account;
import com.application.auction.model.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccount(Account account) {
        Account existingAccount = accountRepository.findByEmail(account.getEmail());
        if (existingAccount != null) {
            return existingAccount;
        }
        return accountRepository.save(account);
    }
}
