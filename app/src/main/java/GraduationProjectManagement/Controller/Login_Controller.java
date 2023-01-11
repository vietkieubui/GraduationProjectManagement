/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller;

import GraduationProjectManagement.View.Login_View;

/**
 *
 * @author BVKieu
 */
public class Login_Controller {
    Login_View view;

    public Login_Controller() {
        this.view = new Login_View(null, true);
        view.show();
    }
}
