package com.AMIR.SRM.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String product_name;
    private String description;
    private int max_price;
    private int count;
    private Date expected_date;

    public Order(){

    }

    public Order(String product_name, String description, int max_price, int count, Date expected_date) {
        this.product_name = product_name;
        this.description = description;
        this.max_price = max_price;
        this.count = count;
        this.expected_date = expected_date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMax_price() {
        return max_price;
    }

    public void setMax_price(int max_price) {
        this.max_price = max_price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getExpected_date() {
        return expected_date;
    }

    public void setExpected_date(Date expected_date) {
        this.expected_date = expected_date;
    }
}
