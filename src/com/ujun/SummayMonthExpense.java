/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ujun;

/**
 *
 * @author user
 */
public class SummayMonthExpense {

    private Integer year;
    private Integer month;
    private Integer transactionTotal;
    private Double expenseTotal;
    private Double expenseEverange;

    public SummayMonthExpense(Integer year, Integer month, Integer transactionTotal, Double expenseTotal, Double expenseEverange) {
        this.year = year;
        this.month = month;
        this.transactionTotal = transactionTotal;
        this.expenseTotal = expenseTotal;
        this.expenseEverange = expenseEverange;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getTransactionTotal() {
        return transactionTotal;
    }

    public void setTransactionTotal(Integer transactionTotal) {
        this.transactionTotal = transactionTotal;
    }

    public Double getExpenseTotal() {
        return expenseTotal;
    }

    public void setExpenseTotal(Double expenseTotal) {
        this.expenseTotal = expenseTotal;
    }

    public Double getExpenseEverange() {
        return expenseEverange;
    }

    public void setExpenseEverange(Double expenseEverange) {
        this.expenseEverange = expenseEverange;
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
            this.getMonthName(),
            this.year.toString(),
            this.transactionTotal.toString(),
            "%.2f".formatted(this.expenseTotal),
            "%.2f".formatted(this.expenseEverange)
        };
    }

}
