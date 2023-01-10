/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author BVKieu
 */
public class ConnectDB {
    static Connection cnn;
    
    public static Connection getConnectDB() {
        try {
            String url = "jdbc:sqlserver://192.168.0.103\\SQLEXPRESS:1433;databaseName=testDATN;encrypt=true;trustServerCertificate=true;";
            String user = "sa";
            String pass = "123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cnn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e);
        }
        return cnn;
    }
    
}
