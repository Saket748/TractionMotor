package com.Traction.motor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
    static Connection con;
    public static Connection createC()throws IOException {
        try{
            //loading the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //create connection
            String url = "jdbc:mysql://localhost:3306/VandeBharatTractionMotor?useSSL=false";
            String user = "Saket-yadav";
            String password = "example-password";
            con = DriverManager.getConnection(url,user,password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
