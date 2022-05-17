package com.rentalkarsspringmvc.webapp.service;

import com.rentalkarsspringmvc.webapp.entities.User;
import com.rentalkarsspringmvc.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public void saveUser(User user) { userRepository.saveUser(user); }

    @Override
    public void updateUser(User user) { userRepository.updateUser(user); }

    @Override
    public void removeUser(User user) { userRepository.removeUser(user); }

    @Override
    public User selById(Long id) { return userRepository.selById(id); }

    @Override
    public User validateUser(String email) { return userRepository.validateUser(email); }

    @Override
    public List<User> searchByEmail(String email) { return userRepository.searchByEmail(email); }

    @Override
    public List<User> selByFirstName(String firstName) { return userRepository.selByFirstName(firstName); }

    @Override
    public List<User> selByLastName(String lastName) { return userRepository.selByLastName(lastName); }

    @Override
    public List<User> getUsers() { return userRepository.getUsers(); }
}
