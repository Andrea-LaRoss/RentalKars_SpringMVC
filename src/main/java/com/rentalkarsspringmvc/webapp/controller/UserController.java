package com.rentalkarsspringmvc.webapp.controller;

import com.rentalkarsspringmvc.webapp.config.SpringSecurityUserContext;
import com.rentalkarsspringmvc.webapp.entities.Car;
import com.rentalkarsspringmvc.webapp.entities.User;
import com.rentalkarsspringmvc.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/form/{email}/")
    public String userForm(@ModelAttribute("user") User user, @PathVariable (value = "email") String email, Model model) {

        if(!email.equals("new")) {
            user = userService.validateUser(email);
            user.setPassword("");
        } else {
            user.setEmail("");
        }

        model.addAttribute("user", user);
        model.addAttribute("Titolo", "Form Utente");
        model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());

        return "user_form";
    }


    @PostMapping("/form/{email}/")
    public String userFormSubmit(@Valid @ModelAttribute("user") User user, @PathVariable("email") String email, BindingResult result, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());
            return "user_form";
        }

        if(!email.equals("new")) {
            userService.updateUser(user, email);
        } else {
            userService.saveUser(user);
        }

        return "redirect:/users";

    }


    @GetMapping("/remove/{id}")
    public String deleteUser(@PathVariable("id") String id, Model model) {

        User user = userService.selById(Long.valueOf(id));
        userService.removeUser(user);

        return "redirect:/users";

    }


    @GetMapping //Mostra lista utenti
    public String getAllUsers(Model model) {

        List<User> users = userService.getUsers();
        model.addAttribute("usersList", users);
        model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());

        return "users_list";

    }

    @GetMapping("/search")
    public String searchUser(@RequestParam("filter") String filter, @RequestParam("value") String value, Model model) {

        List<User> users = new ArrayList<>();

        switch(filter) {
            case "firstName":
                users = userService.selByFirstName(value);
                break;

            case "lastName":
                users = userService.selByLastName(value);
                break;

            case "email":
                users = userService.searchByEmail(value);
        }

        model.addAttribute("usersList", users);
        model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());

        return "users_list";

    }

}
