package com.application.auction.controller;

import com.application.auction.model.account.Account;
import com.application.auction.model.auction.Auction;
import com.application.auction.model.bid.Bid;
import com.application.auction.model.lot.Lot;
import com.application.auction.service.AccountService;
import com.application.auction.service.AuctionService;
import com.application.auction.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
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

    @GetMapping("/")
    public String home() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String showMainPage(Model model) {
        Auction currentAuction = auctionService.getCurrentAuction();
        if (currentAuction == null) {
            model.addAttribute("error", "No current auction is set.");
            return "error/error_page";
        }

        List<Lot> lots = auctionService.getLots(currentAuction.getId());

        model.addAttribute("auction", currentAuction);
        model.addAttribute("lots", lots);

        model.addAttribute("totalRaised", 1000);   //TODO  напиши оце також!!!

        return "main/main";
    }


    @GetMapping("/auction-updates")
    public SseEmitter getAuctionUpdates() {
        SseEmitter emitter = new SseEmitter();
        auctionService.addEmitter(emitter);
        return emitter;
    }


    @GetMapping("/bid")
    public String showBidForm(Model model) {
        if (!model.containsAttribute("account")) {
            model.addAttribute("account", getAccount());
        }
        Auction currentAuction = auctionService.getCurrentAuction();
        model.addAttribute("lots", auctionService.getLots(currentAuction.getId()));
        return "bid/bid_form";
    }


    @PostMapping("/submit_bid")
    public String submitBid(@ModelAttribute("account") Account account,
                            @RequestParam("lot") int lotId,
                            @ModelAttribute("bidSize") double bidSize,
                            Model model, SessionStatus sessionStatus) {

        Auction currentAuction = auctionService.getCurrentAuction();
        if (currentAuction == null) {
            model.addAttribute("error", "No current auction is set.");
            return "error/error_page";
        }
        account.setAuctions(new ArrayList<>(List.of(new Auction[]{currentAuction})));
        account.setPassword("");

        model.addAttribute("account", account);
        model.addAttribute("lotName", auctionService.getLotNameById(lotId));
        model.addAttribute("bidSize", bidSize);

        Account existingAccount = accountService.getAccount(account);

        Lot lot = auctionService.getLotById(lotId);
        if (lot == null) {
            model.addAttribute("error", "Invalid lot ID. Probably programmer messed up completely");
            return "error/error_page";
        }

        double currentHighestBid = bidService.getCurrentHighestBid(lot);
        if (bidSize <= currentHighestBid || bidSize <= lot.getStartPrice()) {
            NumberFormat formatter = new DecimalFormat("#0.00");
            model.addAttribute("error", "Bid must be greater than the current highest bid of "
                    + formatter.format(currentHighestBid) + " £.\nYour bid is " + formatter.format(bidSize) + " £.");
            return "error/error_page";
        }


        bidService.makeBid(new Bid(bidSize), lot, existingAccount);

        auctionService.notifyClients();

        return "bid/bid_confirm";
    }

    @GetMapping("/lot/{id}")
    public String getLotDetails(@PathVariable("id") Integer id, Model model) {
        Lot lot = auctionService.getLotById(id);  // Assuming a service method to fetch the lot by id
        model.addAttribute("lot", lot);
        return "lot/lot_data";
    }

}
