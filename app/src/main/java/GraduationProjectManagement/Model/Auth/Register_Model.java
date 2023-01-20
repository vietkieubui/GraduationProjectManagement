/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Model.Auth;

/**
 *
 * @author BVKieu
 */
public class Register_Model extends Login_Model {

    public String confirmPassword;
    public String name;
    public String phonenumber;

    public Register_Model(String name, String phonenumber, String userName, String password, String confirmPassword) {
        super(userName, password);
        this.confirmPassword = confirmPassword;
        this.name = name;
        this.phonenumber = phonenumber;
    }    
}
