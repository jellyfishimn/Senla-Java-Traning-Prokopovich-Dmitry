package com.training.senla.model;

import com.training.senla.enums.ServicesSection;

import java.util.Date;

/**
 * Created by dmitry on 1.12.16.
 */
public class Service extends Base {
    private String name;
    private double price;
    private ServicesSection section;
    private Date startDate;
    private Date finalDate;

    public Service() {
    }

    public Service(String name, double price, ServicesSection section, Date startDate, Date finalDate) {
        this.name = name;
        this.price = price;
        this.section = section;
        this.startDate = startDate;
        this.finalDate = finalDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ServicesSection getSection() {
        return section;
    }

    public void setSection(ServicesSection section) {
        this.section = section;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
}