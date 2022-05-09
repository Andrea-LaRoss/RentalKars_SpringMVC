package com.rentalkarsspringmvc.webapp.repository;

import com.rentalkarsspringmvc.webapp.entities.Car;
import com.rentalkarsspringmvc.webapp.entities.Rent;
import com.rentalkarsspringmvc.webapp.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface RentRepository {

    public void saveRent(Rent rent);

    public void updateReservation(Rent rent);

    public void removeReservation(Rent rent);

    public Rent selById(Long id);

    public List<Rent> listUserReservation(User user);

    public List<Car> availableCars(LocalDate startDate, LocalDate endDate);

    public List <Rent> getRents();
}
