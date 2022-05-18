package com.rentalkarsspringmvc.webapp.controller;


import com.rentalkarsspringmvc.webapp.config.SpringSecurityUserContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping
    public String showDashboard(Model model) {

        model.addAttribute("Titolo", "La tua dashboard");
        model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());

        return "dashboard";
    }

}
