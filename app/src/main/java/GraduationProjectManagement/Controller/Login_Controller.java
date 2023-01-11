/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller;

import GraduationProjectManagement.View.Login_View;
import java.awt.event.ActionEvent;
import java.sql.Connection;


/**
 *
 * @author BVKieu
 */
public class Login_Controller {
    Login_View view = new Login_View();
    Connection cnn = ConnectDB.getConnectDB();

    public Login_Controller() {
        loginButtonActionListener();
    }

   
    
    private void loginButtonActionListener(){
        view.addActionListener((ActionEvent e) -> {
            System.out.println("Hello");
        }, view.loginButton);
    }
}
