package com.rentalkarsspringmvc.webapp.entities;

import com.rentalkarsspringmvc.webapp.validator.NotMinor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    @NotEmpty(message = "{NotEmpty.User.email.validation}")
    @Column(name = "email", nullable = false, length = 128, unique = true)
    private String email;


    @NotEmpty(message = "{NotEmpty.User.password.validation}")
    @Column(name = "password", nullable = false)
    private String password;


    @NotEmpty(message = "{NotEmpty.User.firstName.validation}")
    @Column(name = "first_name", nullable = false)
    private String firstName;


    @NotEmpty(message = "{NotEmpty.User.lastName.validation}")
    @Column(name = "last_name", nullable = false)
    private String lastName;


    @NotMinor
    @NotNull(message = "{NotNull.User.birthday.validation}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "is_admin", nullable = false)
    private boolean admin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rent> reservations;

    public User(){

    }

    public User(String email, String password, String firstName, String lastName, LocalDate birthday, boolean isAdmin) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.admin = isAdmin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() { return birthday; }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        admin = admin;
    }

    public Set<Rent> getReservations() { return reservations; }

    public void setReservations(Set<Rent> reservations) { this.reservations = reservations; }

}
