package com.rentalkarsspringmvc.webapp.controller;

import com.rentalkarsspringmvc.webapp.config.SpringSecurityUserContext;
import com.rentalkarsspringmvc.webapp.entities.Car;
import com.rentalkarsspringmvc.webapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/form")
    public String carForm(@ModelAttribute("car") Car car, @RequestParam (value = "id", required = false) Long id, Model model) {

        if(id != null) {
            car = carService.selById(id);
        }

        model.addAttribute("car", car);
        model.addAttribute("Titolo", "Form auto");
        model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());

        return "car_form";
    }


    @PostMapping("/form")
    public String carFormSubmit(@Valid @ModelAttribute("car") Car car, BindingResult result, @PathVariable (value = "id", required = false) Long id, Model model) {

        if(result.hasErrors()) {
            model.addAttribute("User", new SpringSecurityUserContext().getCurrentUser());
            return "car_form";
        }

        if(id != null) {
            carService.updateCar(car);
        } else {
            carService.saveCar(car);
        }

        return "redirect:/cars";

    }


    @GetMapping("/remove/{id}")
    public String deleteCar(@PathVariable("id") Long id) {

        Car car = carService.selById(id);
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
