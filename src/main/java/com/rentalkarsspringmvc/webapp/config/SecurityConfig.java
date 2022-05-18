package com.rentalkarsspringmvc.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }


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
            "/users",

            "/reservations/approve/**"
    };


    @Override
    public void configure(final HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers(ADMIN_URL_MATCHER).access("hasRole('ADMIN')")
                .antMatchers("/users/update/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/reservations/**").hasAnyRole("USER","ADMIN")
                .and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
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


    public AuthenticationFilter authenticationFilter() throws Exception {

        AuthenticationFilter filter = new AuthenticationFilter();

        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationFailureHandler(failureHandler());
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());

        return filter;

    }


    public SimpleUrlAuthenticationFailureHandler failureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/login/form?error");
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
        auth.setTargetUrlParameter("targetUrl");

        return auth;
    }

}
