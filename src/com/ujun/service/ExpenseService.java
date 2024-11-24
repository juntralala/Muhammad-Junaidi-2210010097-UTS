/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ujun.service;

import com.ujun.model.Expense;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
//import java.time.*;

public class ExpenseService {

    private Connection connection;

    public ExpenseService(Connection connection) {
        this.connection = connection;
    }

    public boolean add(Expense expense) throws SQLException {
        String query = "INSERT INTO pengeluaran(tipe, jumlah, deskripsi, tanggal) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, expense.getType());
            statement.setDouble(2, expense.getAmount());
            statement.setString(3, expense.getDescription());
            statement.setDate(4, expense.getSqlDate());
            return statement.execute();
        }
    }

    public Expense find(Integer id) throws SQLException {
        String query = "SELECT id, tipe, jumlah, deskripsi, tanggal FROM pengeluaran WHERE id=?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id);
            var result = statement.executeQuery();
            if (result.next()) {
                return new Expense(
                        result.getInt(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getString(4),
                        result.getDate(5).toLocalDate()
                );
            } else {
                return null;
            }
        }
    }

    public ArrayList<Expense> getAll() throws SQLException {
        ArrayList<Expense> expenses = new ArrayList<>();
        var statement = this.connection.createStatement();
        var result = statement.executeQuery("SELECT id, tipe, jumlah, deskripsi, tanggal FROM pengeluaran");

        while (result.next()) {
            expenses.add(new Expense(
                    result.getInt(1),
                    result.getString(2),
                    result.getDouble(3),
                    result.getString(4),
                    result.getDate(5).toLocalDate()
            ));
        }
        
        return expenses;
    }

    public int update(Expense expense) throws SQLException {
        String query = "UPDATE pengeluaran SET tipe=?, jumlah=?, deskripsi=?, tanggal=? WHERE id=?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, expense.getType());
            statement.setDouble(2, expense.getAmount());
            statement.setString(3, expense.getDescription());
            statement.setDate(4, expense.getSqlDate());
            statement.setInt(5, expense.getId());
            return statement.executeUpdate();
        }
    }

    public int delete(Expense expense) throws SQLException {
        return this.delete(expense.getId());
    }
    
    public int delete(Integer id) throws SQLException {
        String query = "DELETE FROM pengeluaran WHERE id=?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        }
    }
}
