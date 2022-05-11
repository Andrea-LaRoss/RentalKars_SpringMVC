package com.rentalkarsspringmvc.webapp.controller;

import com.rentalkarsspringmvc.webapp.entities.User;
import com.rentalkarsspringmvc.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @InitBinder
    public void initialiseBinder(WebDataBinder binder){
        //Riempire il metodo per la data
    }


    @GetMapping("/add")
    public String saveUserForm(@ModelAttribute("userForm") User userForm, Model model) {

        model.addAttribute("userForm", userForm);
        model.addAttribute("Titolo", "Aggiungi utente");

        return "user_form";

    }

    @PostMapping("/add")
    public String saveUser(@Valid @ModelAttribute("userForm") User userForm, BindingResult result, Model model) {

        if(result.hasErrors()) {
            return "user_form";
        }

        userService.saveUser(userForm);
        return "redirect:/users";
    }


    @GetMapping("/form/{id}")
    public String updateUserForm(@ModelAttribute("userForm") User userForm, @PathVariable("id") String id, Model model) {

        User user = userService.selById(Long.valueOf(id));

        userForm.setEmail(user.getEmail());
        userForm.setPassword(user.getPassword());
        userForm.setFirstName(user.getFirstName());
        userForm.setLastName(user.getLastName());
        //userForm.setBirthday(user.getBirthday());

        model.addAttribute("Titolo", "Modifica utente");
        model.addAttribute("userForm", userForm);

        return "user_form";
    }


    @PostMapping("/form")
    public String updateUser(@Valid @ModelAttribute("userForm") User userForm, BindingResult result, Model model) {

        if(result.hasErrors()) {
            return "user_form";
        }

        userService.updateUser(userForm);

        return "redirect: /users";

    }


    @GetMapping("/remove/{id}")
    public String deleteUser(@PathVariable("id") String id, Model model) {

        User user = userService.selById(Long.valueOf(id));
        userService.removeUser(user);

        return "redirect: /users";

    }


    @GetMapping //Mostra lista utenti
    public String getAllUsers(Model model) {

        List<User> users = userService.getUsers();
        model.addAttribute("usersList", users);

        return "users_list";

    }

}
