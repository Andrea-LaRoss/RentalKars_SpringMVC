package com.rentalkarsspringmvc.webapp.controller;

import com.rentalkarsspringmvc.webapp.entities.Car;
import com.rentalkarsspringmvc.webapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;


    @InitBinder
    public void initialiseBinder(WebDataBinder binder){
        //Riempire il metodo per la data
    }


    @GetMapping("/add")
    public String saveCarForm(@ModelAttribute("carForm") Car carForm, Model model) {

        model.addAttribute("carForm", carForm);
        model.addAttribute("Titolo", "Aggiungi auto");

        return "car_form";

    }


    @PostMapping ("/add")
    public String saveCar(@Valid @ModelAttribute("carForm") Car carForm, BindingResult result, Model model) {

        if(result.hasErrors()) {
            return "car_form";
        }

       /* Aggiungere dopo che mi rendo conto di come gestire l'eccezione
       if(carService.selByPlate(carForm.getNumPlate()) != null) {
            model.addAttribute("errorMsg", "Errore. Targa già registrata");
            model.addAttribute("carForm", carForm);
            return "car_form";

        }*/

        carService.saveCar(carForm);
        return "redirect:/cars";

    }


    @GetMapping ("/form/{id}")
    public String updateCarForm(@ModelAttribute("carForm") Car carForm, @PathVariable("id") String id, Model model) {

        Car car = carService.selById(Long.valueOf(id));

        carForm.setManufacturer(car.getManufacturer());
        carForm.setModel(car.getModel());
        carForm.setType(car.getType());
        carForm.setNumPlate(car.getNumPlate());
        carForm.setRegDate(String.valueOf(car.getRegDate()));

        model.addAttribute("Titolo", "Modifica auto");
        model.addAttribute("carForm", carForm);

        return "car_form";

    }


    @PostMapping("/form/update")
    public String updateCar(@Valid @ModelAttribute("carForm") Car carForm, BindingResult result, Model model) {

       /* if(carService.selByPlate(numPlate) != null) {
            model.addAttribute("errorMsg", "Errore. Targa già registrata");
            return listCars(model);
        }*/

        if(result.hasErrors()) {
            return "car_form";
        }

        carService.updateCar(carForm);

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

        return "cars_list";

    }


}
