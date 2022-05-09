package com.rentalkarsspringmvc.webapp.controller;

import com.rentalkarsspringmvc.webapp.entities.Car;
import com.rentalkarsspringmvc.webapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/cars_list")
public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping//Mostra la lista di tutte le auto
    public String getAllCars(Model model) {

        List<Car> cars = carService.getCars();

        model.addAttribute("titolo", "Listino Auto");
        model.addAttribute("carsList", cars);

        return "cars_list";

    }

}
