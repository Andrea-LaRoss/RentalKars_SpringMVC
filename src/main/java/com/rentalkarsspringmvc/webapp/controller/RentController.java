package com.rentalkarsspringmvc.webapp.controller;

import com.rentalkarsspringmvc.webapp.entities.Car;
import com.rentalkarsspringmvc.webapp.entities.Rent;
import com.rentalkarsspringmvc.webapp.entities.User;
import com.rentalkarsspringmvc.webapp.service.CarService;
import com.rentalkarsspringmvc.webapp.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class RentController {

    @Autowired
    private RentService rentService;

    @Autowired
    private CarService carService;


    @InitBinder
    public void initialiseBinder(WebDataBinder binder){
        //Riempire il metodo per la data
    }


    @GetMapping("/add")
    public String addReservationForm(@ModelAttribute("rentForm") Rent rentForm, Model model) {

        model.addAttribute("rentForm", rentForm);
        model.addAttribute("Titolo", "Aggiungi prenotazione");

        return "reservation_form";

    }


    @PostMapping("/add")
    public void getAvailableCars(@ModelAttribute ("rentForm") Rent rentForm, Model model) {

        List<Car> cars = rentService.availableCars(rentForm.getStartDate(), rentForm.getEndDate());
        model.addAttribute("cars", cars);

    }


    @GetMapping("/save/{id}")
    public String addReservation(@Valid @ModelAttribute("rentForm") Rent rentForm, BindingResult result, @PathVariable("id") String id, Model model) {

        if(result.hasErrors()) {
            return "reservation_form";
        }

        Car car = carService.selById(Long.valueOf(id));
        //User user = Prendi l'user dalla sessione

        //Rent rent = new Rent(rentForm.getStartDate(), rentForm.getEndDate(), car, "", "In Attesa");
        rentService.saveRent(rentForm);

        return "redirect:/reservations";
    }


    @GetMapping("/approve/{id}")
    public String approveReservation(@PathVariable("id") String id, Model model) {

        Rent rent = rentService.selById(Long.valueOf(id));
        rent.setStatus("Approvata");

        return "redirect:/reservations";

    }


    @GetMapping//Mostra la lista di tutte le prenotazioni
    public String getAllReservations(Model model) {

        List<Rent> reservations = new ArrayList<>();

        //if loggedUser is admin
        //reservations = rentService.listUserReservation(loggedUser);
        //else
        reservations = rentService.getRents();

        model.addAttribute("Titolo", "Lista Prenotazioni");
        model.addAttribute("reservations", reservations);

        return "reservations_list";

    }

}
