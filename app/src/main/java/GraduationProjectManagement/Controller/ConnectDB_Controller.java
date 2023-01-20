/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller;

import GraduationProjectManagement.Utils.ConnectDatabase;
import GraduationProjectManagement.Utils.Helpers;
import GraduationProjectManagement.View.ConnectDB_View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author BVKieu
 */
public class ConnectDB_Controller {

    ConnectDB_View view = new ConnectDB_View();
    Helpers helpers = new Helpers();

    public ConnectDB_Controller() {
        view.ipAddressText.setText("192.168.0.102");
        view.usernameText.setText("sa");
        view.passwordText.setText("123");
        loginButtonActionListener();
    }

    private void loginButtonActionListener() {
        helpers.addActionListener(view.connectButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ipAddress = view.ipAddressText.getText();
                String username = view.usernameText.getText();
                String password = view.passwordText.getText();
                var connectDB = new ConnectDatabase();
                connectDB.connectDatabase(ipAddress, username, password);
                var cnn = ConnectDatabase.getConnection();
                Statement stm = null;
                ResultSet rs = null;
                String sql = "select*from Persons";
                try {
                    stm = cnn.createStatement();
                    rs = stm.executeQuery(sql);
                    while (rs.next()) {
                        System.out.println(rs.getString(3));
                    }
                } catch (SQLException ex) {
                    System.out.println("ERROR");
                }
                view.dispose();
                Auth_Controller auth_Controller = new Auth_Controller();

            }
        });
    }

}
