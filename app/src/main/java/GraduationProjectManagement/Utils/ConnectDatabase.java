/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author BVKieu
 */
public class ConnectDatabase {

    public static Connection cnn;

    public ConnectDatabase() {
    }
    
    public static Connection getConnection(){
        return cnn;
    }

    public void connectDatabase(String ipAddress, String username, String password) {
        try {
            String url = "jdbc:sqlserver://" + ipAddress + "\\SQLEXPRESS:1433;databaseName=testDATN;encrypt=true;trustServerCertificate=true;";
            String user = username;
            String pass = password;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cnn = DriverManager.getConnection(url, user, pass);
            JOptionPane.showMessageDialog(null, "Connected!");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
//        return cnn;
    }

}
