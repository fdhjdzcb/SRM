package com.AMIR.SRM.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Order {

    private long OrderID;
    private String ProductName;
    private String Description;
    private int MaxPrice;
    private int Count;
    private Date ExpectedDate;

    public long getOrderID() {
        return OrderID;
    }

    public void setOrderID(long orderID) {
        OrderID = orderID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getMaxPrice() {
        return MaxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        MaxPrice = maxPrice;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public Date getExpectedDate() {
        return ExpectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        ExpectedDate = expectedDate;
    }
}
