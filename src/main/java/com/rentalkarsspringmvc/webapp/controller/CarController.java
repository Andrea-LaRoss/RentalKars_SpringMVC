package com.rentalkarsspringmvc.webapp.controller;

import com.rentalkarsspringmvc.webapp.config.SpringSecurityUserContext;
import com.rentalkarsspringmvc.webapp.entities.Car;
import com.rentalkarsspringmvc.webapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;



    @GetMapping("/add")
    public String saveCarForm(@ModelAttribute("carForm") Car carForm, Model model) {

        model.addAttribute("carForm", carForm);
        model.addAttribute("Titolo", "Aggiungi auto");
        model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());

        return "car_form";

    }


    @PostMapping ("/add")
    public String saveCar(@Valid @ModelAttribute("carForm") Car carForm, BindingResult result, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());
            return "car_form";
        }

        carService.saveCar(carForm);
        return "redirect:/cars";

    }


    @GetMapping ("/update/{id}")
    public String updateCarForm(@PathVariable("id") String id, Model model) {

        Car car = carService.selById(Long.valueOf(id));

        model.addAttribute("Titolo", "Modifica auto");
        model.addAttribute("carForm", car);
        model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());

        return "car_form";

    }


    @PostMapping("/update/{id}")
    public String updateCar(@Valid @ModelAttribute("carForm") Car carForm, BindingResult result, @PathVariable("id") String id, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());
            return "car_form";
        }

        Car car = carService.selById(Long.valueOf(id));
        car.setManufacturer(carForm.getManufacturer());
        car.setModel(carForm.getModel());
        car.setType(carForm.getType());
        car.setNumPlate(carForm.getNumPlate());
        car.setNumPlate(carForm.getNumPlate());
        car.setRegDate(carForm.getRegDate());

        carService.updateCar(car);

        return "redirect:/cars";

    }


    @GetMapping("/remove/{id}")
    public String deleteCar(@PathVariable("id") String id, Model model) {

        Car car = carService.selById(Long.valueOf(id));
        carService.removeCar(car);

        return "redirect:/cars";

    }


    @GetMapping
    public String getAllCars(Model model) {

        List<Car> cars = carService.getCars();

        model.addAttribute("Titolo", "Listino Auto");
        model.addAttribute("carsList", cars);
        model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());

        return "cars_list";

    }


}
