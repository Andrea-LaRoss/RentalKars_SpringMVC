package com.rentalkarsspringmvc.webapp.repository;

import com.rentalkarsspringmvc.webapp.entities.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import com.rentalkarsspringmvc.webapp.config.HibernateConfig;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CarRepositoryImpl implements CarRepository {

    private Transaction tx;


    @Override
    public void saveCar(Car car) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.save(car);
            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        }
    }


    @Override
    public void updateCar(Car car) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.merge(car);
            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        }
    }


    @Override
    public void removeCar(Car car) {
        tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.remove(car);
            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        }
    }


    @Override
    public Car selById(Long id) {
        tx = null;
        Car car = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            car = session.get(Car.class, id);
            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        }
        return car;
    }


    @Override
    public Car selByPlate(String numPlate) {
        tx = null;
        Car car = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Car> query = builder.createQuery(Car.class);
            Root<Car> carSet = query.from(Car.class);
            CriteriaQuery<Car> select = query.select(carSet);
            car = session.createQuery(select.where(builder.equal(carSet.get("numPlate"), numPlate))).getSingleResult();
            tx.commit();

        } catch (Exception e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();

        }
        return car;
    }


    @Override
    public List<Car> getCars() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Car", Car.class).list();
        }
    }
}
