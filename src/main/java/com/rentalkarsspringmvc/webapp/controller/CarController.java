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
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping("/form")
    public String saveCarForm(Model model) {
        Car carForm = new Car();
        model.addAttribute("carForm", carForm);
        model.addAttribute("Titolo", "Aggiungi auto");
        return "car_form";
    }


    @PostMapping ("/form")
    public String saveCar(@ModelAttribute("carForm") Car carForm, Model model) {
    /*
        if(carService.selByPlate(carForm.getNumPlate()) != null) {
            model.addAttribute("errorMsg", "Errore. Targa già registrata");
            model.addAttribute("carForm", carForm);
            return "car_form";

        }

        if(carForm.getRegDate().isAfter(LocalDate.now())){
            model.addAttribute("errorMsg", "Errore. La data è nel futuro");
            model.addAttribute("carForm", carForm);
            return "car_form";
        }

        carService.saveCar(carForm);

*/
        System.out.println(carForm.getManufacturer());
        return "redirect:/cars";

    }


    @GetMapping ("/form/{id}")
    public String updateCarForm(@PathVariable("id") String id, Model model) {

        Car car = carService.selById(Long.valueOf(id));

        model.addAttribute("Titolo", "Modifica auto");
        model.addAttribute("car", car);

        return "car_form";
    }


    @PutMapping("/form/update")
    public String updateCar(Long id, String manufacturer, String modello, String type, String numPlate, LocalDate regDate, Model model) {

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
    }


    @DeleteMapping("/remove/{id}")
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
