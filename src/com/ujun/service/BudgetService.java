package com.ujun.service;

import com.ujun.model.Budget;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class BudgetService {

    private Connection connection;

    public BudgetService(Connection connection) {
        this.connection = connection;
    }

    public boolean add(Budget budget) throws SQLException {
        String query = "INSERT INTO anggaran(jumlah_batasan, bulan, tahun) VALUES (?, ?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDouble(1, budget.getAmountBoundary());
            statement.setInt(2, budget.getMonth());
            statement.setInt(3, budget.getYear());
            return statement.execute();
        }
    }

    public Budget find(Integer id) throws SQLException {
        String query = "SELECT id, jumlah_batasan, bulan, tahun FROM anggaran WHERE id=?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id);
            var result = statement.executeQuery();
            if (result.next()) {
                return new Budget(
                        result.getInt(1),
                        result.getDouble(2),
                        result.getInt(3),
                        result.getInt(4)
                );
            } else {
                return null;
            }
        }
    }

    public ArrayList<Budget> getAll() throws SQLException {
        ArrayList<Budget> budgets = new ArrayList<>();
        var statement = this.connection.createStatement();
        var result = statement.executeQuery("SELECT id, jumlah_batasan, bulan, tahun FROM anggaran");
        while (result.next()) {
            budgets.add(new Budget(
                    result.getInt(1),
                    result.getDouble(2),
                    result.getInt(3),
                    result.getInt(4)
            ));
        }

        return budgets;
    }

    public int update(Budget budget) throws SQLException {
        String query = "UPDATE anggaran SET jumlah_batasan=?, bulan=?, tahun=? WHERE id=?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDouble(1, budget.getAmountBoundary());
            statement.setInt(2, budget.getMonth());
            statement.setInt(3, budget.getYear());
            statement.setInt(4, budget.getId());
            return statement.executeUpdate();
        }
    }

    public int delete(Budget budget) throws SQLException {
        return this.delete(budget.getId());
    }

    public int delete(Integer id) throws SQLException {
        String query = "DELETE FROM anggaran WHERE id=?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        }
    }

    
}
