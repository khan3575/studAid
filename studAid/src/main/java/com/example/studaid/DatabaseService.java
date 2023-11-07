package com.example.studaid;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseService {

    public static  DatabaseService instance = new DatabaseService();

    private Connection dbConnection;
    private Statement statement;

    private DatabaseService() {
        String databaseName ="studentaid";
        String databaseUser ="root";
        String databasePassword = "";
        String url= "jdbc:mysql://localhost:3306/"+databaseName;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection= DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

    public Connection getConnection() {
        return dbConnection;
    }

    public Statement getStatement() {
        try {
            return dbConnection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
