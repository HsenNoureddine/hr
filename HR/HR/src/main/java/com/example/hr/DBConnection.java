package com.example.hr;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "hr";
        String databaseUser = "root";
        String databasePass = "";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePass);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return databaseLink;

    }

}
