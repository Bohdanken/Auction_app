package com.application.auction.controller;

import com.application.auction.model.Bid;
import com.application.auction.model.account.Account;
import com.application.auction.service.AccountService;
import com.application.auction.service.AuctionService;
import com.application.auction.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Arrays;
import java.util.List;


@Controller
@SessionAttributes("account")  // Cache "account" object in session
public class MvcController {
    private final AuctionService auctionService;
    private final BidService bidService;
    private final AccountService accountService;


    @Autowired
    public MvcController(AuctionService auctionService, BidService bidService, AccountService accountService) {
        this.auctionService = auctionService;
        this.bidService = bidService;
        this.accountService = accountService;
    }

    @ModelAttribute("account")
    public Account getAccount() {
        return new Account();

    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        Account user = new Account();
        List<String> professionList = Arrays.asList("Developer", "Designer", "Tester");

        model.addAttribute("user", user);

        model.addAttribute("professionList", professionList);

        return "register_form";
    }

    @GetMapping("/bid")
    public String showBidForm(Model model) {
        if (!model.containsAttribute("account")) {
            model.addAttribute("account", getAccount());
        }
        model.addAttribute("lots", auctionService.getLots(0));
        return "bid/bid_form";
    }


    @PostMapping("/submit_bid")
    public String submitBid(@ModelAttribute("account") Account account,
                            @RequestParam("lot") int lotId,
                            @ModelAttribute("bidSize") double bidSize,
                            Model model, SessionStatus sessionStatus) {

        model.addAttribute("account", account);
        model.addAttribute("lotName", auctionService.getLotNameById(lotId));
        model.addAttribute("bidSize", bidSize);

        //Here it gets the real account object from the input
        //and creates account if it doesnt exits
        Account existingAccount = accountService.getAccount(account);

        //TODO: Add validation of bigger bid
        bidService.makeBid(new Bid(bidSize), auctionService.getLotById(lotId), existingAccount);
        return "bid/bid_confirm";
    }
}