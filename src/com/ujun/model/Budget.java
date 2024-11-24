/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ujun.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author user
 */
public class Budget {

    private Integer id = null;
    private Double amountBoundary = null;
    private Integer month = null;
    private Integer year = null;

    public Budget(Integer id, Double amountBoundary, Integer month, Integer year) {
        this.id = id;
        this.amountBoundary = amountBoundary;
        this.month = month;
        this.year = year;
    }

    public Budget(Double amountBoundary, Integer month, Integer year) {
        this.amountBoundary = amountBoundary;
        this.month = month;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public Double getAmountBoundary() {
        return amountBoundary;
    }

    public void setAmountBoundary(Double amountBoundary) {
        this.amountBoundary = amountBoundary;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMonthName() {
        return switch (this.month.intValue()) {
            case 1 ->
                "Januari";
            case 2 ->
                "Februari";
            case 3 ->
                "Maret";
            case 4 ->
                "April";
            case 5 ->
                "Mei";
            case 6 ->
                "Juni";
            case 7 ->
                "Juli";
            case 8 ->
                "Agustus";
            case 9 ->
                "September";
            case 10 ->
                "Oktober";
            case 11 ->
                "November";
            case 12 ->
                "Desember";
            default ->
                "INVALID MONTH";
        };
    }

    public String[] toArray() {
        return new String[]{
            this.id.toString(),
            "%.2f".formatted(this.amountBoundary),
            this.getMonthName(),
            this.year.toString()
        };
    }
}
