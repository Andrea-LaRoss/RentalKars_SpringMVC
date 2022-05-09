package com.rentalkarsspringmvc.webapp.repository;

import com.rentalkarsspringmvc.webapp.entities.Car;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.rentalkarsspringmvc.webapp.config.HibernateConfig;

import java.util.List;

@Repository
public class CarRepositoryImpl implements CarRepository {

    @Override
    public void saveCar(Car car) {

    }

    @Override
    public void updateCar(Car car) {

    }

    @Override
    public void removeCar(Car car) {

    }

    @Override
    public Car selById(Long id) {
        return null;
    }

    @Override
    public Car selByPlate(String numPlate) {
        return null;
    }

    @Override
    public List<Car> getCars() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Car", Car.class).list();
        }
    }
}
