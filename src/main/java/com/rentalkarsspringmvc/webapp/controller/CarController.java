package com.rentalkarsspringmvc.webapp.controller;

import com.rentalkarsspringmvc.webapp.entities.Car;
import com.rentalkarsspringmvc.webapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;


    @InitBinder
    public void initialiseBinder(WebDataBinder binder){

    }


    @GetMapping("/add")
    public String saveCarForm(@ModelAttribute("carForm") Car carForm, Model model) {
        model.addAttribute("carForm", carForm);
        model.addAttribute("Titolo", "Aggiungi auto");
        return "car_form";
    }


    @PostMapping ("/add")
    public String saveCar(@ModelAttribute("carForm") Car carForm, Model model) {

       /* Aggiungere dopo che mi rendo conto di come gestire l'eccezione
       if(carService.selByPlate(carForm.getNumPlate()) != null) {
            model.addAttribute("errorMsg", "Errore. Targa già registrata");
            model.addAttribute("carForm", carForm);
            return "car_form";

        }

        if(carForm.getRegDate().isAfter(LocalDate.now())){
            model.addAttribute("errorMsg", "Errore. La data è nel futuro");
            model.addAttribute("carForm", carForm);
            return "car_form";
        }*/

        carService.saveCar(carForm);

        System.out.println(carForm.getRegDate());
        return "redirect:/cars";

    }


    @GetMapping ("/form/{id}")
    public String updateCarForm(@PathVariable("id") String id, Model model) {

        Car car = carService.selById(Long.valueOf(id));

        model.addAttribute("Titolo", "Modifica auto");
        model.addAttribute("car", car);

        return "car_form";
    }


   /* @PostMapping("/form/update")
    public String updateCar(, Model model) {

        if(carService.selByPlate(numPlate) != null) {
            model.addAttribute("errorMsg", "Errore. Targa già registrata");
            return listCars(model);
        }

        if(regDate.isAfter(LocalDate.now())){
            model.addAttribute("errorMsg", "Errore. La data è nel futuro");
            return listCars(model);
        }

        Car car = carService.selById(id);

        car.setManufacturer(manufacturer);
        car.setModel(modello);
        car.setType(type);
        car.setNumPlate(numPlate);
        car.setRegDate(regDate);

        carService.updateCar(car);

        return listCars(model);
    }*/


    @GetMapping("/remove/{id}")
    public String deleteCar(@PathVariable("id") String id, Model model) {

        Car car = carService.selById(Long.valueOf(id));
        carService.removeCar(car);

        return listCars(model);

    }


    @GetMapping
    public String getAllCars(Model model) {
        return listCars(model);
    }


    public String listCars(Model model) {
        List<Car> cars = carService.getCars();

        model.addAttribute("Titolo", "Listino Auto");
        model.addAttribute("carsList", cars);

        return "cars_list";
    }
}
