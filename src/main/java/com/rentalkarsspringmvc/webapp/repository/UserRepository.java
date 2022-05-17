package com.rentalkarsspringmvc.webapp.repository;

import com.rentalkarsspringmvc.webapp.entities.User;

import java.util.List;

public interface UserRepository {

    void saveUser(User user);

    public void updateUser(User user);

    public void removeUser(User user);

    public User selById(Long id);

    public User validateUser(String email);

    public List<User> searchByEmail(String email);

    public List<User> selByFirstName(String firstName);

    public List<User> selByLastName(String lastName);

    public List <User> getUsers();

}
