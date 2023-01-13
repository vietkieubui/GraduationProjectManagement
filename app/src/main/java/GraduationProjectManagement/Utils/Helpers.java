package GraduationProjectManagement.Utils;

import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author BVKieu
 */
public final class Helpers {

    /**
     *
     * @param al
     * @param btn
     */
    public static void addActionListener(ActionListener al, JButton btn) {
        btn.addActionListener(al);
    }
    public static Component getComponent(JPanel jp, String name){
        Component[] components = jp.getComponents();
        Component component = null;
        for(int i=0;i<components.length;i++){
            if(components[i].getName()==name){
                component = components[i];
            }
        }
        return component;        
    }
    
}
