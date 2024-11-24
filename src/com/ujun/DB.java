/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ujun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.sqlite.JDBC;

public class DB {
    private static Connection connection = null;
    private static String database = DB.class.getResource("./../../keuangan.db").toString();
    private static String jdbcUrl = "jdbc:sqlite:" + DB.database;
    
    public static Connection getConnection() throws SQLException{
        if(DB.connection == null || DB.connection.isClosed()){
            DB.connection = DriverManager.getConnection(DB.jdbcUrl);
            return DB.connection;
        } else {
            return DB.connection;
        }
    }
    
    public static void registerSqliteDriver() throws SQLException {
        DriverManager.registerDriver(new JDBC());
    }
}
