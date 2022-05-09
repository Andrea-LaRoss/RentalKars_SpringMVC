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


    @GetMapping("/form/save")
    public void saveCar(String manufacturer, String modello, String type, String numPlate, LocalDate regDate, Model model) {

        if(carService.selByPlate(numPlate) != null) {
            model.addAttribute("errorMsg", "Errore. Targa già registrata");
            //return "car_form";
        }

        if(regDate.isAfter(LocalDate.now())){
            model.addAttribute("errorMsg", "Errore. La data è nel futuro");
            //return "car_form";
        }

        carService.saveCar(new Car(manufacturer, modello, type, numPlate, regDate));


        getAllCars(model);
    }


    @PutMapping("/form/update")
    public void updateCar(Long id, String manufacturer, String modello, String type, String numPlate, LocalDate regDate, Model model) {

        if(carService.selByPlate(numPlate) != null) {
            model.addAttribute("errorMsg", "Errore. Targa già registrata");
           // return "car_form"; o comunque ritorna alla pagina car_form in qualche maniera. spero. non so ancora se ho capito il metodo da usare. aiut.
        }

        if(regDate.isAfter(LocalDate.now())){
            model.addAttribute("errorMsg", "Errore. La data è nel futuro");
           // return "car_form";
        }

        Car car = carService.selById(id);

        car.setManufacturer(manufacturer);
        car.setModel(modello);
        car.setType(type);
        car.setNumPlate(numPlate);
        car.setRegDate(regDate);

        carService.updateCar(car);

        getAllCars(model);//Dovrebbe restituire la lista auto
    }


    @GetMapping("/remove/{id}")
    public void deleteCar(@PathVariable("id") String id, Model model) {

        Car car = carService.selById(Long.valueOf(id));
        carService.removeCar(car);

        getAllCars(model);
    }


    @GetMapping
    public String getAllCars(Model model) {

        List<Car> cars = carService.getCars();

        model.addAttribute("Titolo", "Listino Auto");
        model.addAttribute("carsList", cars);

        return "cars_list";
    }


    @GetMapping("/form/{id}")
    public String getCarForm(@RequestParam("id") String id, Model model) {

        Car car = carService.selById(Long.valueOf(id));

        model.addAttribute("Titolo", "Modifica auto");
        model.addAttribute("car", car);

        return "car_form";
    }

}
