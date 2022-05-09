package com.rentalkarsspringmvc.webapp.repository;


import com.rentalkarsspringmvc.webapp.entities.Car;

import java.util.List;

public interface CarRepository {

    void saveCar(Car car);

    void updateCar(Car car);

    void removeCar(Car car);

    public Car selById(Long id);

    public Car selByPlate(String numPlate);

    public List<Car> getCars();
}
