package com.application.auction;

import com.application.auction.model.account.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MvcController {
     
    @RequestMapping("/")
    public String home() {
        System.out.println("Going home...");
        return "index";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        Account user = new Account();
        List<String> professionList = Arrays.asList("Developer", "Designer", "Tester");

        model.addAttribute("user", user);

        model.addAttribute("professionList", professionList);

        return "register_form";
    }
}