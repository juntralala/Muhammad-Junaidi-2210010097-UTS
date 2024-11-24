/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ujun.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author user
 */
public class Expense {

    private Integer id = null;
    private String type = null;
    private Double amount = 0d;
    private String description;
    private LocalDate date;

    public Expense(Integer id, String type, Double amount, String description, LocalDate date) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public Expense(String type, Double amount, String description, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public java.sql.Date getSqlDate() {
        return java.sql.Date.valueOf(this.date);
    }

    public String[] toArray() {
        return new String[]{
            this.id.toString(),
            this.type,
            "%.2f".formatted(this.amount),
            this.description,
            this.date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        };
    }
}
