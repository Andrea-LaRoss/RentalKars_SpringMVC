package com.rentalkarsspringmvc.webapp.service;

import com.rentalkarsspringmvc.webapp.entities.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void updateUser(User user, String email);

    void removeUser(User user);

    User selById(Long id);

    User validateUser(String email);

    List<User> searchByEmail(String email);

    List<User> selByFirstName(String firstName);

    List<User> selByLastName(String lastName);

    List<User> getUsers();
}
