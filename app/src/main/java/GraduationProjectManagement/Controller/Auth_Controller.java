/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller;

import GraduationProjectManagement.Utils.Helpers;
import GraduationProjectManagement.View.Auth.Auth_View;
import GraduationProjectManagement.View.Auth.Login_Panel;
import GraduationProjectManagement.View.Auth.Register_Panel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author BVKieu
 */
public class Auth_Controller {

    Auth_View view = new Auth_View();
    JPanel loginPanel = new Login_Panel();
    JPanel registerPanel = new Register_Panel();

    public Auth_Controller() {
        view.setLayout(new BorderLayout());
        view.add(loginPanel, BorderLayout.CENTER);
        view.setVisible(true);
        loginButtonActionListener();
        registerButtonActionListener();
    }

    private void loginButtonActionListener() {
        Helpers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField usernameText = (JTextField) Helpers.getComponent(loginPanel, "usernameText");
                JTextField passwordText = (JTextField) Helpers.getComponent(loginPanel, "passwordText");

                System.out.println(usernameText.getText() + passwordText.getText());
            }
        }, (JButton) Helpers.getComponent(loginPanel, "loginButton"));
    }

    private void registerButtonActionListener() {
        Helpers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.add(registerPanel, BorderLayout.CENTER);

                view.remove(loginPanel);
            }
        }, (JButton) Helpers.getComponent(loginPanel, "registerButton"));
    }

}
