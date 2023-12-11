package com.example.fourfourtwopedia.DBHandler;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class dbConnection {
        private  static String username="root";
        private  static String userpass="12345678Pp";
        private  static String DBConnectionStr="jdbc:mysql://localhost:3306/scorecompetition";

    public static Connection getDBConnection() {
        try {
            Connection con = DriverManager.getConnection(DBConnectionStr, username, userpass);
            return con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
