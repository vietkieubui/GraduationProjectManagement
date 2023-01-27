/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller.Main;

import GraduationProjectManagement.Controller.Main.AddForm.AddClassController;
import GraduationProjectManagement.Controller.Main.AddForm.AddCourseController;
import GraduationProjectManagement.Controller.Main.AddForm.AddMajorsController;
import GraduationProjectManagement.Controller.Main.AddForm.AddSchoolYearController;
import GraduationProjectManagement.Utils.Helpers;
import GraduationProjectManagement.View.Main.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BVKieu
 */
public final class MainController {

    public MainView mainView;

    ClassManagementPanel classManagementPanel = new ClassManagementPanel();
    CourseManagementPanel courseManagementPanel = new CourseManagementPanel();
    MajorsManagementPanel majorsManagementPanel = new MajorsManagementPanel();
    ProjectManagementPanel projectManagementPanel = new ProjectManagementPanel();
    SchoolYearManagementPanel schoolYearManagementPanel = new SchoolYearManagementPanel();
    StudentManagementPanel studentManagementPanel = new StudentManagementPanel();
    TeacherManagementPanel teacherManagementPanel = new TeacherManagementPanel();
    JPanel currentPanel;

    /**
     * Table
     */
    DefaultTableModel schoolYearTable = (DefaultTableModel) schoolYearManagementPanel.schoolYearTable.getModel();
    DefaultTableModel majorsTable = (DefaultTableModel) majorsManagementPanel.majorsTable.getModel();
    DefaultTableModel courseTable = (DefaultTableModel) courseManagementPanel.courseTable.getModel();
    DefaultTableModel classTable = (DefaultTableModel) classManagementPanel.classTable.getModel();
 
    public MainController() {
        this.mainView = new MainView();
        setupTable();
        mainView.mainPanel.setLayout(new BorderLayout());
        mainView.mainPanel.add(projectManagementPanel, BorderLayout.NORTH);
        mainView.mainPanel.add(projectManagementPanel);
        projectManagementPanel.setVisible(true);
        currentPanel = projectManagementPanel;
        mainView.setVisible(true);
        mainViewButtonActionListener();
        classPanelActionListener();
        coursePanelActionListener();        
        majorsPanelActionListener();
        schoolYearPanelActionListener();
        
    }

    public void mainViewButtonActionListener() {
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
                Helpers.getClass(classTable);
                currentPanel.setVisible(false);
                mainView.mainPanel.add(classManagementPanel);
                classManagementPanel.setVisible(true);
                currentPanel = classManagementPanel;
            }
        });
        
        Helpers.addActionListener(mainView.courseButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Helpers.getCourse(courseTable);
                currentPanel.setVisible(false);
                mainView.mainPanel.add(courseManagementPanel);
                courseManagementPanel.setVisible(true);
                currentPanel = courseManagementPanel;
            }
        });
        
        Helpers.addActionListener(mainView.schoolYearButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Helpers.getSchoolYears(schoolYearTable);
                currentPanel.setVisible(false);
                mainView.mainPanel.add(schoolYearManagementPanel);
                schoolYearManagementPanel.setVisible(true);
                currentPanel = schoolYearManagementPanel;
            }
        });
        
        Helpers.addActionListener(mainView.majorsButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Helpers.getMajors(majorsTable);
                currentPanel.setVisible(false);
                mainView.mainPanel.add(majorsManagementPanel);
                majorsManagementPanel.setVisible(true);
                currentPanel = majorsManagementPanel;
            }
        });
    }
    
    public void classPanelActionListener(){
        Helpers.addActionListener(classManagementPanel.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddClassController(classTable);
            }
        });
    }
    
    public void coursePanelActionListener(){
        Helpers.addActionListener(courseManagementPanel.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCourseController(courseTable);
            }
        });
        
    }
    
    public void majorsPanelActionListener(){
        Helpers.addActionListener(majorsManagementPanel.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMajorsController(majorsTable);
            }
        });
    }
    
    public void schoolYearPanelActionListener() {
        Helpers.addActionListener(schoolYearManagementPanel.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddSchoolYearController(schoolYearTable);                
            }
        });
    }

    public void setupTable(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        schoolYearManagementPanel.schoolYearTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
    }
}
