package com.rentalkarsspringmvc.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if(!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Metodo di autenticazione non supportato: " + request.getMethod());
        }

        UsernamePasswordAuthenticationToken authRequest = getAuthRequest(request);

        return this.getAuthenticationManager().authenticate(authRequest);

    }

    private UsernamePasswordAuthenticationToken getAuthRequest (HttpServletRequest request) {

        String email = request.getParameter("email");
        String password = obtainPassword(request);

        email = (email == null) ? "" : email;
        password = (password == null) ? "" : password;

        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
