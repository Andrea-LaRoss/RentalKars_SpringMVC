package com.rentalkarsspringmvc.webapp.service;

import com.rentalkarsspringmvc.webapp.entities.Car;
import com.rentalkarsspringmvc.webapp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    CarRepository carRepository;

    @Override
    public void saveCar(Car car) {
        carRepository.saveCar(car);
    }

    
    @Override
    public void updateCar(Car car) {

        carRepository.updateCar(car);
    
    }

    
    @Override
    public void removeCar(Car car) { carRepository.removeCar(car); }

    @Override
    public Car selById(Long id) {
        return carRepository.selById(id);
    }

    @Override
    public Car selByPlate(String numPlate) { return carRepository.selByPlate(numPlate); }

    @Override
    public List<Car> getCars() {
        return carRepository.getCars();
    }

}
