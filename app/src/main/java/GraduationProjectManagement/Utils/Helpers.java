package GraduationProjectManagement.Utils;

import java.awt.event.ActionListener;
import javax.swing.JButton;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author BVKieu
 */
public class Helpers {
    public void addActionListener(ActionListener al, JButton btn) {
        btn.addActionListener(al);
    }
    
}
