package com.rentalkarsspringmvc.webapp.controller;

import com.rentalkarsspringmvc.webapp.config.SpringSecurityUserContext;
import com.rentalkarsspringmvc.webapp.entities.Car;
import com.rentalkarsspringmvc.webapp.entities.Rent;
import com.rentalkarsspringmvc.webapp.entities.User;
import com.rentalkarsspringmvc.webapp.service.CarService;
import com.rentalkarsspringmvc.webapp.service.RentService;
import com.rentalkarsspringmvc.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class RentController {

    private Rent rent;

    @Autowired
    private RentService rentService;

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;


    @GetMapping("/add")
    public String addReservationForm(@ModelAttribute ("rentForm") Rent rentForm, Model model) {

        model.addAttribute(rentForm);
        model.addAttribute("Titolo", "Aggiungi prenotazione");
        model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());

        return "reservation_form";

    }


    @PostMapping ("/add")
    public String checkCars(@Valid @ModelAttribute("rentForm") Rent rentForm, BindingResult result, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());
            return "reservation_form";
        }

        rent = new Rent();
        List<Car> cars = rentService.availableCars(rentForm.getStartDate(), rentForm.getEndDate());
        rent.setStartDate(rentForm.getStartDate());
        rent.setEndDate(rentForm.getEndDate());
        model.addAttribute("rentForm", rentForm);
        model.addAttribute("cars", cars);
        model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());

        return "reservation_form";
    }


    @GetMapping("/confirm/{carId}")
    public String addReservation(@PathVariable("carId") Long id, Model model) {

            Car car = carService.selById(id);
            User user = userService.validateUser(new SpringSecurityUserContext().getCurrentUser());

            rent.setCar(car);
            rent.setUser(user);
            rentService.saveRent(rent);

        return "redirect:/reservations";

    }


    @GetMapping("/update/{id}")
    public String updateReservationForm(@PathVariable("id") Long id, Model model) {

        Rent rent = rentService.selById(id);

        model.addAttribute("Titolo", "Modifica prenotazione");
        model.addAttribute("rentForm", rent);
        model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());

        return "reservation_form";
    }


    @PostMapping("/update/{id}")
    public String updateReservation(@Valid @ModelAttribute("rentForm") Rent rentForm, BindingResult result, @PathVariable("id") Long id, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());
            return "reservation_form";
        }

        Rent rent = rentService.selById(id);
        rent.setStartDate(rentForm.getStartDate());
        rent.setEndDate(rentForm.getEndDate());
        rent.setStatus("In attesa");
        rentService.updateReservation(rent);

        return "redirect:/reservations";

    }


    @GetMapping("/approve/{id}")
    public String approveReservation(@PathVariable("id") Long id, Model model) {

        Rent rent = rentService.selById(id);
        rent.setStatus("Approvata");
        rentService.updateReservation(rent);
        model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());

        return "redirect:/reservations";

    }


    @GetMapping//Mostra la lista di tutte le prenotazioni
    public String getAllReservations(Model model) {

        List<Rent> reservations = new ArrayList<>();

        User user = userService.validateUser(new SpringSecurityUserContext().getCurrentUser());

        if(user.isAdmin()) {
            reservations = rentService.getRents();
        }
        else {
            reservations = rentService.listUserReservation(user);
        }


        model.addAttribute("Titolo", "Lista Prenotazioni");
        model.addAttribute("reservations", reservations);
        model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());

        return "reservations_list";

    }


    @GetMapping("/remove/{id}")
    public String deleteReservation(@PathVariable("id") String id, Model model) {

        Rent rent = rentService.selById(Long.valueOf(id));
        rentService.removeReservation(rent);

        return "redirect:/reservations";

    }

}
