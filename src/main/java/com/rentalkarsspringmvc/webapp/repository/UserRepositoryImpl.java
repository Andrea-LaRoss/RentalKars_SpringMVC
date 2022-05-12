package com.rentalkarsspringmvc.webapp.repository;

import com.rentalkarsspringmvc.webapp.config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import com.rentalkarsspringmvc.webapp.entities.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public void saveUser(User user) {
        Transaction tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.save(user);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }


    @Override
    public void updateUser(User user) {
        Transaction tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.merge(user);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }


    @Override
    public void removeUser(User user) {
        Transaction tx = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            session.remove(user);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }


    @Override
    public User selById(Long id) {
        Transaction tx = null;
        User user = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            user = session.get(User.class, id);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public User validateUser(String email, String password) {
        Transaction tx = null;
        User user = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> userSet = query.from(User.class);
            CriteriaQuery<User> select = query.select(userSet);

            user = session.createQuery(select.where(builder.and(builder.equal(userSet.get("email"), email),
                    builder.equal(userSet.get("password"), password)))).getSingleResult();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public List<User> searchByEmail(String email) {
        Transaction tx = null;
        List<User> users = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> userSet = query.from(User.class);
            CriteriaQuery<User> select = query.select(userSet);

            users = session.createQuery(select.where(builder.like(userSet.get("email"), "%"+email+"%"))).getResultList();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public List<User> selByFirstName(String firstName) {
        Transaction tx = null;
        List<User> users = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> userSet = query.from(User.class);
            CriteriaQuery<User> select = query.select(userSet);

            users = session.createQuery(select.where(builder.like(userSet.get("firstName"), "%"+firstName+"%"))).getResultList();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public List<User> selByLastName(String lastName) {
        Transaction tx = null;
        List<User> users = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> userSet = query.from(User.class);
            CriteriaQuery<User> select = query.select(userSet);

            users = session.createQuery(select.where(builder.like(userSet.get("lastName"), "%"+lastName+"%"))).getResultList();

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public List<User> getUsers() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

}
