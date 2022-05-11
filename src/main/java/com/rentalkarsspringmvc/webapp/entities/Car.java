package com.rentalkarsspringmvc.webapp.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "car")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    @NotEmpty(message = "{NotEmpty.Car.manufacturer.validation}")
    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;


    @NotEmpty(message = "{NotEmpty.Car.model.validation}")
    @Column(name = "model", nullable = false)
    private String model;


    @NotEmpty(message = "{NotEmpty.Car.type.validation}")
    @Column(name = "type", nullable = false)
    private String type;


    @NotEmpty(message = "{NotEmpty.Car.numPlate.validation}")
    @Column(name = "num_plate", nullable = false, length = 128, unique = true)
    private String numPlate;


  //  @NotEmpty(message = "{NotEmpty.Car.regDate.validation}")
    //@PastOrPresent(message = "{PastOrPresent.Car.regDate.validation}")
    @Column(name = "reg_date")
    private LocalDate regDate;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rent> reservation;

    public Car(){

    }

    public Car(String manufacturer, String model, String type, String numPlate, LocalDate regDate) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = type;
        this.numPlate = numPlate;
        this.regDate = regDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumPlate() {
        return numPlate;
    }

    public void setNumPlate(String numPlate) {
        this.numPlate = numPlate;
    }

    public LocalDate getRegDate() { return regDate; }

    public void setRegDate(String regDate) {
        this.regDate = LocalDate.parse(regDate);
    }

    public Set<Rent> getReservation() { return reservation; }

    public void setReservation(Set<Rent> reservation) { this.reservation = reservation; }

}
