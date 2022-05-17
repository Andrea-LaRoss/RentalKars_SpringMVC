package com.rentalkarsspringmvc.webapp.controller;

import com.rentalkarsspringmvc.webapp.config.SpringSecurityUserContext;
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


    @GetMapping("/add")
    public String saveUserForm(@ModelAttribute("userForm") User userForm, Model model) {

        model.addAttribute("userForm", userForm);
        model.addAttribute("Titolo", "Aggiungi utente");
        model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());

        return "user_form";

    }

    @PostMapping("/add")
    public String saveUser(@Valid @ModelAttribute("userForm") User userForm, BindingResult result, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());
            return "user_form";
        }

        User user = new User();
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setEmail(userForm.getEmail());
        user.setBirthday(userForm.getBirthday());
        userService.saveUser(user);

        return "redirect:/users";

    }


    @GetMapping("/update/{id}")
    public String updateUserForm(@PathVariable("id") String id, Model model) {

        User user = userService.selById(Long.valueOf(id));

        model.addAttribute("Titolo", "Modifica utente");
        model.addAttribute("userForm", user);
        model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());

        return "user_form";
    }


    @PostMapping("/update/{id}")
    public String updateUser(@Valid @ModelAttribute("userForm") User userForm, BindingResult result, @PathVariable("id") String id, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());
            return "user_form";
        }

        User user = userService.selById(Long.valueOf(id));
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setEmail(userForm.getEmail());
        user.setBirthday(userForm.getBirthday());
        userService.updateUser(user);

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
    public String searchUserFirstName(@RequestParam("filter") String filter, @RequestParam("value") String value, Model model) {

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
