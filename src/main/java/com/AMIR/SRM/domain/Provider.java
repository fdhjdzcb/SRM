package com.AMIR.SRM.domain;

import com.AMIR.SRM.domain.Order;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;

public class Provider {
    private String name;
    private Date new_date;
    private double new_price;
    private int new_count;
    public Provider() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getNew_date() {
        return new_date;
    }

    public void setNew_date(Date new_date) {
        this.new_date = new_date;
    }

    public double getNew_price() {
        return new_price;
    }

    public void setNew_price(double new_price) {
        this.new_price = new_price;
    }

    public int getNew_count() {
        return new_count;
    }

    public void setNew_count(int new_count) {
        this.new_count = new_count;
    }

}
