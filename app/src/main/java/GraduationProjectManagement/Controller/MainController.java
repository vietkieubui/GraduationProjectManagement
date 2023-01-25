/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller;

import GraduationProjectManagement.Utils.Helpers;
import GraduationProjectManagement.View.Main.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;




/**
 *
 * @author BVKieu
 */
public class MainController {
    MainView mainView = new MainView();
    
    ClassManagementPanel classManagementPanel = new ClassManagementPanel();
    CourseManagementPanel courseManagementPanel  = new CourseManagementPanel();
    MajorsManagementPanel majorsManagementPanel = new MajorsManagementPanel();
    ProjectManagementPanel projectManagementPanel = new ProjectManagementPanel();
    SchoolYearManagementPanel schoolYearManagementPanel = new SchoolYearManagementPanel();
    StudentManagementPanel studentManagementPanel = new StudentManagementPanel();
    TeacherManagementPanel teacherManagementPanel = new TeacherManagementPanel();
    JPanel currentPanel;

    public MainController() {  
        mainView.mainPanel.setLayout(new BorderLayout());
        mainView.mainPanel.add(projectManagementPanel,BorderLayout.NORTH);
        mainView.mainPanel.add(projectManagementPanel);        
        projectManagementPanel.setVisible(true);
        currentPanel = projectManagementPanel;
        mainView.setVisible(true);
        mainViewButtonActionListener();
    }
    
    public void mainViewButtonActionListener(){
        Helpers.addActionListener(mainView.projectTopicButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
                currentPanel.setVisible(false);
                mainView.mainPanel.add(projectManagementPanel, BorderLayout.NORTH);
                projectManagementPanel.setVisible(true);
                currentPanel = projectManagementPanel;
            }
        });
        Helpers.addActionListener(mainView.studentButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPanel.setVisible(false);
                mainView.mainPanel.add(studentManagementPanel);
                studentManagementPanel.setVisible(true);
                currentPanel = studentManagementPanel;
            }
        });
        Helpers.addActionListener(mainView.teacherButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPanel.setVisible(false);
                mainView.mainPanel.add(teacherManagementPanel);
                teacherManagementPanel.setVisible(true);
                currentPanel = teacherManagementPanel;
            }
        });
        Helpers.addActionListener(mainView.classButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPanel.setVisible(false);
                mainView.mainPanel.add(classManagementPanel);
                classManagementPanel.setVisible(true);
                currentPanel = classManagementPanel;
            }
        });
        Helpers.addActionListener(mainView.schoolYearButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPanel.setVisible(false);
                mainView.mainPanel.add(schoolYearManagementPanel);
                schoolYearManagementPanel.setVisible(true);
                currentPanel = schoolYearManagementPanel;
            }
        });
        Helpers.addActionListener(mainView.majorsButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPanel.setVisible(false);
                mainView.mainPanel.add(majorsManagementPanel);
                majorsManagementPanel.setVisible(true);
                currentPanel = majorsManagementPanel;
            }
        });
    }
    
    
}
