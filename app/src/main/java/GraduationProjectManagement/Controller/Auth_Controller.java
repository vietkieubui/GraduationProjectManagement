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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author BVKieu
 */
public class Auth_Controller {

    Auth_View view = new Auth_View();
    Helpers helpers = new Helpers();
    JPanel loginPanel = new Login_Panel();
    JPanel registerPanel = new Register_Panel();

    public Auth_Controller() {        
        view.setLayout(new BorderLayout());
        view.add(loginPanel, BorderLayout.CENTER);
        view.setVisible(true);
    }
    
    private void loginButtonActionListener(){
        helpers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello");
            }
        }, new Login_Panel().loginButton);
    }

}
