package com.rentalkarsspringmvc.webapp.config;

import com.rentalkarsspringmvc.webapp.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    //@Autowired
    //DataSource dataSource;//DBMS cambiare con hibernate


    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }


    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        User.UserBuilder users = User.builder();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        //Utente1
        manager.createUser(
                users
                        .username("Admin")
                        .password(new BCryptPasswordEncoder().encode("1234"))
                        .roles("USER", "ADMIN")
                        .build());

        //Utente 2
        manager.createUser(
                users
                        .username("Cliente")
                        .password(new BCryptPasswordEncoder().encode("1234"))
                        .roles("USER")
                        .build());

        return manager;

    }


    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception { auth.authenticationProvider(authenticationProvider()); }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;

    }


    public static final String[] ADMIN_URL_MATCHER = {
            "/cars/add/**",
            "/cars/remove/**",
            "/cars/update/**",

            "/users/add/**",
            "/users/remove/**",
            "/users/**",

            "/reservations/approve/**"
    };


    @Override
    public void configure(final HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/").hasAnyRole("ANONYMOUS", "USER")
                .antMatchers(ADMIN_URL_MATCHER).access("hasRole('ADMIN')")
                .antMatchers("/users/update/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/reservations/**").hasRole("USER")
                .and()
                    .formLogin()
                        .loginPage("/login/form")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login/form?error")
                            .usernameParameter("email")
                            .passwordParameter("password")
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/login/form?forbidden")
                .and()
                    .logout()
                    .logoutUrl("/login/form?logout");

    }

}
