package com.example.studaid;
import java.sql.Connection;
import java.sql.DriverManager;

public class database {
    public static Connection connectionDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/studentaid","root","");
            return connect;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
