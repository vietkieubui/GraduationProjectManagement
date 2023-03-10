/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller;

import GraduationProjectManagement.Services.ConnectDatabase;
import GraduationProjectManagement.Services.Services;
import GraduationProjectManagement.View.ConnectDBView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author BVKieu
 */
public class ConnectDBController {

    ConnectDBView view = new ConnectDBView();
    Services helpers = new Services();

    public ConnectDBController() {
        view.ipAddressText.setText("192.168.0.103");
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
                view.dispose();
                AuthController auth_Controller = new AuthController();

            }
        });
    }

}
