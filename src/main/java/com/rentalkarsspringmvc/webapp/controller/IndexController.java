package com.rentalkarsspringmvc.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = "index")
    public String getWelcome(Model model) {
        model.addAttribute("intestazione", "Benvenuti su RentalKars");
        model.addAttribute("saluti", "Prenota le tue auto preferite");

        return "index";
    }

    @RequestMapping
    public String getWelcome2(Model model) {
        model.addAttribute("intestazione", "Benvenuti su RentalKars");
        model.addAttribute("saluti", "Prenota le tue auto preferite");

        return "index";
    }

}
