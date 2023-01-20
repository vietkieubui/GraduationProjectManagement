/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller;

import GraduationProjectManagement.Model.Auth.Login_Model;
import GraduationProjectManagement.Model.Auth.Register_Model;
import GraduationProjectManagement.Model.Auth.User;
import GraduationProjectManagement.Utils.Helpers;
import GraduationProjectManagement.View.Auth.Auth_View;
import GraduationProjectManagement.View.Auth.Login_Panel;
import GraduationProjectManagement.View.Auth.Register_Panel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author BVKieu
 */
public class Auth_Controller {

    Auth_View authView = new Auth_View();
    Login_Panel loginPanel = new Login_Panel();
    Register_Panel registerPanel = new Register_Panel();

    public Auth_Controller() {
        authView.setLayout(new BorderLayout());
        authView.add(loginPanel, BorderLayout.CENTER);
        authView.setVisible(true);
        loginPanel.usernameText.setText("admin");
        loginPanel.passwordText.setText("1");
        registerPanel.nameText.setText("Bùi Việt Kiều");
        registerPanel.phonenumberText.setText("0123456789");
        registerPanel.usernameText.setText("admin");
        registerPanel.passwordText.setText("1");
        registerPanel.confirmPasswordText.setText("1");

        loginViewActionListener();
        registerViewActionListener();
    }

    private void loginViewActionListener() {
        Helpers.addActionListener(loginPanel.loginButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = loginPanel.usernameText.getText();
                String password = loginPanel.passwordText.getText();
                Login_Model loginModel = new Login_Model(userName, password);
                if(Helpers.login(loginModel.username, loginModel.password)){
                    Helpers.showMess("Đăng nhập thành công");
                    System.out.println(User.name);
                }
            }
        });
        Helpers.addActionListener(loginPanel.registerLabel, new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loginPanel.setVisible(false);
                authView.add(registerPanel, BorderLayout.CENTER);
                registerPanel.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                loginPanel.registerLabel.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginPanel.registerLabel.setForeground(Color.BLACK);
            }
        });
    }

    private void registerViewActionListener() {
        Helpers.addActionListener(registerPanel.registerButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = registerPanel.nameText.getText();
                String phonenumber = registerPanel.phonenumberText.getText();
                String username = registerPanel.usernameText.getText();
                String password = registerPanel.passwordText.getText();
                String confirmPassword = registerPanel.confirmPasswordText.getText();
                if(Helpers.register(name, username, phonenumber, password, confirmPassword)){
                    registerPanel.setVisible(false);
                    authView.add(loginPanel, BorderLayout.CENTER);
                    loginPanel.setVisible(true);
                }
            }
        });
        Helpers.addActionListener(registerPanel.loginLabel, new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                registerPanel.setVisible(false);
                authView.add(loginPanel, BorderLayout.CENTER);
                loginPanel.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                registerPanel.loginLabel.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerPanel.loginLabel.setForeground(Color.BLACK);
            }
        });
    }

}
