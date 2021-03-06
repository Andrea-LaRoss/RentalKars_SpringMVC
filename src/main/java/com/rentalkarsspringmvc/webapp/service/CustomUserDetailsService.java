package com.rentalkarsspringmvc.webapp.service;

import com.rentalkarsspringmvc.webapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if(email == null) {
            throw new UsernameNotFoundException("Inserisci la mail");
        }

        User user = userService.validateUser(email);

        if(user == null) {
            throw new UsernameNotFoundException("Utente non trovato");
        }

        UserBuilder builder = null;

        builder = org.springframework.security.core.userdetails.User.withUsername(user.getEmail());
        builder.password(user.getPassword());

        if(user.isAdmin()) {
            builder.roles("ADMIN");
        } else {
            builder.roles("USER");
        }

        return builder.build();

    }
}
