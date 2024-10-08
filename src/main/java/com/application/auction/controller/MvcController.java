package com.application.auction.controller;

import com.application.auction.model.account.Account;
import com.application.auction.model.auction.Auction;
import com.application.auction.model.lot.Lot;
import com.application.auction.service.AuctionService;
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

    @Autowired
    public MvcController(AuctionService auctionService) {
        this.auctionService = auctionService;
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
            model.addAttribute("account",getAccount());
        }
        model.addAttribute("lots", auctionService.getLots(0));
        return "bid/bid_form";
    }


    @PostMapping("/submit_bid")
    public String submitBid(@ModelAttribute("account") Account account,
                            @RequestParam("lot") int lotId,
                            @ModelAttribute("bidSize") double bidSize,
                            Model model, SessionStatus sessionStatus) {
        // Process the account, selected lot, and bid size

        // Add the received data to the model for confirmation
        model.addAttribute("account", account);
        model.addAttribute("lotName", auctionService.getLotNameById(lotId));
        model.addAttribute("bidSize", bidSize);

        // Display a confirmation page or redirect to another view
        return "bid/bid_confirm";  // You can create a separate JSP file for confirmation if needed
    }
}