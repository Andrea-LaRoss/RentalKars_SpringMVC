package com.rentalkarsspringmvc.webapp.service;

import com.rentalkarsspringmvc.webapp.entities.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CustomUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if(email == null) {
            throw new UsernameNotFoundException("Inserisci la mail");
        }

        User user = userService.validateUser(email, "1234");

        if(user == null) {
            throw new UsernameNotFoundException("Utente non trovato");
        }

        UserBuilder builder = null;

        builder = User.withUsername(user.getEmail());
        builder.password(user.getPassword());

        String[] profili =

        builder.authorities(profili);

        return builder.build();
    }
}
