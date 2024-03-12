package com.example.serverrenteco.Model;

//import javax.persistence.*;

import jakarta.persistence.*;

@Entity
@Table(name = "autovehicles")
public class AutoVehicle {

    @Id
    @GeneratedValue
    private Integer id;
    private String brand;
    private String model;
    private Boolean rented;
    private String linkimg;

    public AutoVehicle() {
    }

    public AutoVehicle(Integer id, String brand, String model, Boolean rented, String linkimg) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.rented = rented;
        this.linkimg = linkimg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", rented=" + rented +
                ", linkImg='" + linkimg + '\'' +
                '}';
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getRented() {
        return rented;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
    }

    public String getLinkimg() {
        return linkimg;
    }

    public void setLinkimg(String linkImg) {
        this.linkimg = linkImg;
    }
}
