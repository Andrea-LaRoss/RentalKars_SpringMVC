package com.rentalkarsspringmvc.webapp.controller;

import com.rentalkarsspringmvc.webapp.entities.Car;
import com.rentalkarsspringmvc.webapp.entities.Rent;
import com.rentalkarsspringmvc.webapp.entities.User;
import com.rentalkarsspringmvc.webapp.service.CarService;
import com.rentalkarsspringmvc.webapp.service.RentService;
import com.rentalkarsspringmvc.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
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


    @InitBinder
    public void initialiseBinder(WebDataBinder binder){
        //Riempire il metodo per la data
    }


    @GetMapping("/add")
    public String addReservationForm(@ModelAttribute ("rentForm") Rent rentForm, Model model) {

        model.addAttribute(rentForm);
        model.addAttribute("Titolo", "Aggiungi prenotazione");

        return "reservation_form";

    }


    @PostMapping ("/add")
    public String checkCars(@Valid @ModelAttribute("rentForm") Rent rentForm, BindingResult result, Model model) {

        if(result.hasErrors()) {
            return "reservation_form";
        }

        rent = new Rent();
        List<Car> cars = rentService.availableCars(rentForm.getStartDate(), rentForm.getEndDate());
        rent.setStartDate(rentForm.getStartDate());
        rent.setEndDate(rentForm.getEndDate());
        model.addAttribute("rentForm", rentForm);
        model.addAttribute("cars", cars);

        return "reservation_form";
    }


    @GetMapping("/confirm/{carId}")
    public String addReservation(@PathVariable("carId") String id, Model model) {

            Car car = carService.selById(Long.valueOf(id));
            //User user = Prendi l'user dalla sessione

            rent.setCar(car);
            rent.setUser(userService.selById(1l));
            rentService.saveRent(rent);

        return "redirect:/reservations";

    }


    @GetMapping("/update/{id}")
    public String updateReservationForm(@PathVariable("id") String id, Model model) {

        Rent rent = rentService.selById(Long.valueOf(id));

        model.addAttribute("Titolo", "Modifica prenotazione");
        model.addAttribute("rentForm", rent);

        return "reservation_form";
    }


    @PostMapping("/update/{id}")
    public String updateReservation(@Valid @ModelAttribute("rentForm") Rent rentForm, BindingResult result, @PathVariable("id") String id, Model model) {

        if(result.hasErrors()) {
            return "reservation_form";
        }

        Rent rent = rentService.selById(Long.valueOf(id));
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


    @GetMapping("/remove/{id}")
    public String deleteReservation(@PathVariable("id") String id, Model model) {

        Rent rent = rentService.selById(Long.valueOf(id));
        rentService.removeReservation(rent);

        return "redirect:/reservations";

    }

}
