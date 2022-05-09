package com.rentalkarsspringmvc.webapp.service;

import com.rentalkarsspringmvc.webapp.entities.Car;
import com.rentalkarsspringmvc.webapp.entities.Rent;
import com.rentalkarsspringmvc.webapp.entities.User;
import com.rentalkarsspringmvc.webapp.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentServiceImpl implements RentService{

    @Autowired
    private RentRepository rentRepository;

    @Override
    public void saveRent(Rent rent) { rentRepository.saveRent(rent); }

    @Override
    public void updateReservation(Rent rent) { rentRepository.updateReservation(rent); }

    @Override
    public void removeReservation(Rent rent) { rentRepository.removeReservation(rent); }

    @Override
    public Rent selById(Long id) { return rentRepository.selById(id); }

    @Override
    public List<Rent> listUserReservation(User user) { return rentRepository.listUserReservation(user); }

    @Override
    public List<Car> availableCars(LocalDate startDate, LocalDate endDate) { return rentRepository.availableCars(startDate, endDate); }

    @Override
    public List<Rent> getRents() { return rentRepository.getRents(); }

}
