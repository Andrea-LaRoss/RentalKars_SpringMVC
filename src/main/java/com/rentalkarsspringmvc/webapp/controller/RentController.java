package com.rentalkarsspringmvc.webapp.controller;

import com.rentalkarsspringmvc.webapp.entities.Car;
import com.rentalkarsspringmvc.webapp.entities.Rent;
import com.rentalkarsspringmvc.webapp.entities.User;
import com.rentalkarsspringmvc.webapp.service.CarService;
import com.rentalkarsspringmvc.webapp.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Controller
@RequestMapping("/reservations_list")
public class RentController {

    @Autowired
    private RentService rentService;

    @Autowired
    private CarService carService;


    @GetMapping//Mostra la lista di tutte le prenotazioni
    public String getAllReservations(Model model) {

        List<Rent> reservations = rentService.getRents();

        model.addAttribute("Titolo", "Lista Prenotazioni");
        model.addAttribute("reservations", reservations);

        return "reservations_list";

    }

}
