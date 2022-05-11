package com.rentalkarsspringmvc.webapp.repository;

import com.rentalkarsspringmvc.webapp.config.HibernateConfig;
import com.rentalkarsspringmvc.webapp.entities.Car;
import com.rentalkarsspringmvc.webapp.entities.Rent;
import com.rentalkarsspringmvc.webapp.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.xml.crypto.dsig.TransformService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentRepositoryImpl implements RentRepository{


    @Override
    public void saveRent(Rent rent) {
        Transaction tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.save(rent);
            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        }
    }


    @Override
    public void updateReservation(Rent rent) {
        Transaction tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.merge(rent);
            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        }
    }


    @Override
    public void removeReservation(Rent rent) {
        Transaction tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.remove(rent);
            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        }
    }


    @Override
    public Rent selById(Long id) {
        Transaction tx = null;
        Rent rent = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            rent = session.get(Rent.class, id);
            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        }

        return rent;
    }


    @Override
    public List<Rent> listUserReservation(User user) {
        Transaction tx = null;
        List<Rent> list = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Rent> query = builder.createQuery(Rent.class);
            Root<Rent> rentSet = query.from(Rent.class);
            CriteriaQuery<Rent> select = query.select(rentSet);

            list = session.createQuery(select.where(builder.equal(rentSet.get("user"), user))).getResultList();

            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        }

        return list;
    }


    @Override
    public List<Car> availableCars(LocalDate startDate, LocalDate endDate) {
        Transaction tx = null;
        List<Long> cars = new ArrayList<>();
        List<Car> available = new ArrayList<>();

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Rent> query = builder.createQuery(Rent.class);
            Root<Rent> rentSet = query.from(Rent.class);
            CriteriaQuery<Rent> select = query.select(rentSet);

            //Query che estrare i noleggi approvati avvenuti durante il periodo deciso
            Predicate between = builder.and(builder.or(builder.between(rentSet.get("startDate"), startDate, endDate),
                            builder.between(rentSet.get("endDate"), startDate, endDate)),
                    builder.equal(rentSet.get("status"), "Approvata"));

            List<Rent> reservations = session.createQuery(select.where(between).distinct(true)).getResultList();

            for(Rent tempReservations : reservations){
                cars.add(tempReservations.getCar().getId());
            }

            CriteriaQuery<Car> query2 = builder.createQuery(Car.class);
            Root<Car> carSet = query2.from(Car.class);
            CriteriaQuery<Car> selectFromCar = query2.select(carSet);

            if(cars.size() == 0) {
                available = session.createQuery(selectFromCar).getResultList();
            } else{
                Predicate notIn = builder.not(carSet.get("id").in(cars));
                available = session.createQuery(selectFromCar.where(notIn)).getResultList();
            }

            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        }

        return available;
    }


    @Override
    public List<Rent> getRents() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Rent",Rent.class).list();
        }
    }

}
