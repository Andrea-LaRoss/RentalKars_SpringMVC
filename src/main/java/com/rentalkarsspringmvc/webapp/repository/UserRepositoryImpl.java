package com.rentalkarsspringmvc.webapp.repository;

import com.rentalkarsspringmvc.webapp.config.HibernateConfig;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.rentalkarsspringmvc.webapp.entities.User;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {


    @Override
    public void saveUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public User selById(Long id) {
        return null;
    }

    @Override
    public User validateUser(String email, String password) {
        return null;
    }

    @Override
    public List<User> searchByEmail(String email) {
        return null;
    }

    @Override
    public List<User> selByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<User> selByLastName(String lastName) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }
}
