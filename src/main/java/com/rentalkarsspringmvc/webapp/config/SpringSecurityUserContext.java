package com.rentalkarsspringmvc.webapp.config;

import com.rentalkarsspringmvc.webapp.entities.User;
import com.rentalkarsspringmvc.webapp.repository.UserRepository;
import com.rentalkarsspringmvc.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityUserContext
{
    @Autowired
    UserService userService;
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();

    public String getCurrentUser()
    {
        String CurrentUser = (authentication != null) ?  authentication.getName() : null;

        if (CurrentUser != null && CurrentUser.equals("anonymousUser"))
            CurrentUser = null;

        return CurrentUser;
    }
}
