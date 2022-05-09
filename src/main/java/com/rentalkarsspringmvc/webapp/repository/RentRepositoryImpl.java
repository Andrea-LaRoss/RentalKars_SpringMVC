package com.rentalkarsspringmvc.webapp.repository;

import com.rentalkarsspringmvc.webapp.config.HibernateConfig;
import com.rentalkarsspringmvc.webapp.entities.Car;
import com.rentalkarsspringmvc.webapp.entities.Rent;
import com.rentalkarsspringmvc.webapp.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RentRepositoryImpl implements RentRepository{

    private Transaction tx;

    @Override
    public void saveRent(Rent rent) {

    }


    @Override
    public void updateReservation(Rent rent) {

    }


    @Override
    public void removeReservation(Rent rent) {

    }


    @Override
    public Rent selById(Long id) {
        return null;
    }


    @Override
    public List<Rent> listUserReservation(User user) {
        return null;
    }


    @Override
    public List<Car> availableCars(LocalDate startDate, LocalDate endDate) {
        return null;
    }


    @Override
    public List<Rent> getRents() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Rent",Rent.class).list();
        }
    }

}
