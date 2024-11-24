/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ujun.service;

import com.ujun.SummayMonthExpense;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class AnalysistService {

    private Connection connection = null;

    public AnalysistService(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<SummayMonthExpense> summaryMonthlyExpense() throws SQLException {
        var summary = new ArrayList<SummayMonthExpense>();
        String query = """
                       SELECT 
                           strftime('%Y', datetime(tanggal / 1000, 'unixepoch')) AS tahun,  
                           strftime('%m', datetime(tanggal / 1000, 'unixepoch')) AS bulan,
                           COUNT(id) AS total_transaksi,
                           SUM(jumlah) AS total_pengeluaran,
                           AVG(jumlah) AS rata_rata_pengeluaran
                       FROM 
                           pengeluaran
                       GROUP BY 
                           tahun, bulan
                       ORDER BY 
                           tahun DESC, bulan DESC;""";
        try (Statement statement = this.connection.createStatement()) {
            var result = statement.executeQuery(query);
            while (result.next()) {
                summary.add(new SummayMonthExpense(
                        result.getInt(1),
                        result.getInt(2),
                        result.getInt(3),
                        result.getDouble(4),
                        result.getDouble(5)
                ));
            }
        }
        return summary;
    }

    public boolean isOverBudget() throws SQLException {
        boolean isOver = false;
        String query = """
                      SELECT 
                          p.tahun,
                          p.bulan,
                          CASE 
                              WHEN SUM(p.jumlah) > a.jumlah_batasan THEN 1
                              ELSE 0
                          END AS melebihi_anggaran
                      FROM 
                          (SELECT 
                              strftime('%Y', datetime(tanggal / 1000, 'unixepoch')) AS tahun, 
                              strftime('%m', datetime(tanggal / 1000, 'unixepoch')) AS bulan, 
                              jumlah
                          FROM pengeluaran) p
                      JOIN anggaran a
                          ON p.tahun = a.tahun AND p.bulan = a.bulan
                      GROUP BY 
                          p.tahun, p.bulan
                      ORDER BY 
                          p.tahun DESC, p.bulan DESC;""";
        try (Statement statement = this.connection.createStatement()) {
            var result = statement.executeQuery(query);
            if (result.next()) {
                isOver = result.getInt(3) > 0;
            }
        }
        return isOver;
    }

    public Double remaingBudgetThisMonth() throws SQLException {
        String query = """
                       WITH pengeluaran_bulan_ini AS (
                           SELECT 
                               SUM(jumlah) AS total_pengeluaran
                           FROM pengeluaran
                           WHERE strftime('%m', datetime(tanggal / 1000, 'unixepoch')) = strftime('%m', 'now') 
                             AND strftime('%Y', datetime(tanggal / 1000, 'unixepoch')) = strftime('%Y', 'now')
                       ),
                       anggaran_bulan_ini AS (
                           SELECT 
                               jumlah_batasan
                           FROM anggaran
                           WHERE bulan = CAST(strftime('%m', 'now') AS INTEGER)
                             AND tahun = CAST(strftime('%Y', 'now') AS INTEGER)
                       )
                       SELECT 
                           anggaran_bulan_ini.jumlah_batasan - COALESCE(pengeluaran_bulan_ini.total_pengeluaran, 0) AS sisa_anggaran
                       FROM anggaran_bulan_ini
                       INNER JOIN pengeluaran_bulan_ini ON 1=1;""";
        try (var statement = this.connection.createStatement()) {
            var result = statement.executeQuery(query);
            if (result.next()) {
                return result.getDouble(1);
            } else {
                return null;
            }
        }
    }
}
