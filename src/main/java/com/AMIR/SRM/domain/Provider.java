package com.AMIR.SRM.domain;

import com.AMIR.SRM.domain.Order;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Provider {
    private String name;
    private String new_date;
    private int new_price;
    private int new_count;
    public Provider() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNew_date() {
        return new_date;
    }

    public void setNew_date(String new_date) {
        this.new_date = new_date;
    }

    public int getNew_price() {
        return new_price;
    }

    public void setNew_price(int new_price) {
        this.new_price = new_price;
    }

    public int getNew_count() {
        return new_count;
    }

    public void setNew_count(int new_count) {
        this.new_count = new_count;
    }

    void createProvider(Order order) {
        Random random = new Random();
        int countOfProviders = random.nextInt(5) + 1;
        //for (int i = 0; i < countOfProviders; i++) {
            int j = random.nextInt(10);
            if (j < 10) {
                order.setProvider("Поставщик" + 1);
                order.setReal_price((random.nextInt(10)+91) * order.getMax_price() / 100);
                /*order.setReal_date(order.getExpected_date());*/
                /*Calendar cal = Calendar.getInstance();
                LocalDate date = LocalDate.parse(order.getExpected_date());
                cal.setTime(java.sql.Date.valueOf(date));
                cal.add(Calendar.DATE, -random.nextInt(6)); //todo может быть такое что доставят раньше сегодняшнего
                order.setReal_date(cal.toString());*/
            }
            //}
    }

}
