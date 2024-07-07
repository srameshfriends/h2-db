package com.batch.demo;

import java.util.Date;

public class Payment {
    private String user;
    private int id;

    private Date date;
    private double amount;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "user='" + user + '\'' +
                ", id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }
}
