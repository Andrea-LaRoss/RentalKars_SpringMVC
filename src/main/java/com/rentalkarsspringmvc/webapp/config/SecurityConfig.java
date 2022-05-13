/*package com.rentalkarsspringmvc.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        User.UserBuilder users = User.builder();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        //Utente1
        manager.createUser(
                users
                        .username("")
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
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());

    }

    public static final String[] ADMIN_URL_MATCHER = {
            "/cars/add/**",
            "/cars/remove/**",
            "/cars/update/**",

            "/users/add/**",
            "/users/remove/**",

            "/reservations/approve/**"
    };

    @Override
    public void configure(final HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/login/**").permitAll()
               // .antMatchers("/").hasAnyRole("ANONYMOUS", "USER")
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
*/