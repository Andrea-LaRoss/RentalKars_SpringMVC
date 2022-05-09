package com.rentalkarsspringmvc.webapp.controller;

import com.rentalkarsspringmvc.webapp.entities.User;
import com.rentalkarsspringmvc.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users_list")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping //Mostra lista utenti
    public String getAllUsers(Model model) {

        List<User> users = userService.getUsers();
        model.addAttribute("usersList", users);

        return "users_list";
    }

}
