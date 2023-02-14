/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller.Main;

import GraduationProjectManagement.Controller.Main.AddForm.AddClassController;
import GraduationProjectManagement.Controller.Main.AddForm.AddCourseController;
import GraduationProjectManagement.Controller.Main.AddForm.AddMajorsController;
import GraduationProjectManagement.Controller.Main.AddForm.AddSchoolYearController;
import GraduationProjectManagement.Controller.Main.AddForm.AddStudentController;
import GraduationProjectManagement.Controller.Main.AddForm.AddTeacherController;
import GraduationProjectManagement.Controller.Main.UpdateForm.UpdateClassController;
import GraduationProjectManagement.Controller.Main.UpdateForm.UpdateCourseController;
import GraduationProjectManagement.Controller.Main.UpdateForm.UpdateMajorsController;
import GraduationProjectManagement.Controller.Main.UpdateForm.UpdateSchoolYearController;
import GraduationProjectManagement.Controller.Main.UpdateForm.UpdateTeacherController;
import GraduationProjectManagement.Model.ClassModel;
import GraduationProjectManagement.Model.CourseModel;
import GraduationProjectManagement.Model.MajorsModel;
import GraduationProjectManagement.Model.SchoolYearModel;
import GraduationProjectManagement.Model.TeacherModel;
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
    DefaultTableModel teacherTable = (DefaultTableModel) teacherManagementPanel.teacherTable.getModel();
    DefaultTableModel studentTable = (DefaultTableModel) studentManagementPanel.studentTable.getModel();

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
        teacherPanelActionListener();
        studentPanelActionListener();
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
                Helpers.getStudent(studentTable);
                currentPanel.setVisible(false);
                mainView.mainPanel.add(studentManagementPanel);
                studentManagementPanel.setVisible(true);
                currentPanel = studentManagementPanel;
            }
        });

        Helpers.addActionListener(mainView.teacherButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Helpers.getTeacher(teacherTable);
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

    public void studentPanelActionListener() {
        Helpers.addActionListener(studentManagementPanel.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudentController(studentTable);
            }
        });
    }

    public void teacherPanelActionListener() {
        Helpers.addActionListener(teacherManagementPanel.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTeacherController(teacherTable);
            }
        });
        Helpers.addActionListener(teacherManagementPanel.updateButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int updateRow = teacherManagementPanel.teacherTable.getSelectedRow();
                    TeacherModel teacherModel = new TeacherModel(
                            (String) teacherTable.getValueAt(updateRow, 1),
                            (String) teacherTable.getValueAt(updateRow, 2),
                            (String) teacherTable.getValueAt(updateRow, 3),
                            (String) teacherTable.getValueAt(updateRow, 4),
                            (String) teacherTable.getValueAt(updateRow, 5)
                    );
                    
                    teacherModel.id = (String) teacherTable.getValueAt(updateRow, 0);
                    new UpdateTeacherController(teacherTable, teacherModel);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                    Helpers.showMess("Bạn phải chọn 1 hàng!");
                }
            }
        });
    }

    public void classPanelActionListener() {
        Helpers.addActionListener(classManagementPanel.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddClassController(classTable);
            }
        });
        Helpers.addActionListener(classManagementPanel.updateButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int updateRow = classManagementPanel.classTable.getSelectedRow();
                    ClassModel classModel = new ClassModel(
                            (String) classTable.getValueAt(updateRow, 1),
                            (String) classTable.getValueAt(updateRow, 5),
                            (String) classTable.getValueAt(updateRow, 2)
                    );
                    classModel.id = (String) classTable.getValueAt(updateRow, 0);
                    new UpdateClassController(classTable, classModel);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                    Helpers.showMess("Bạn phải chọn 1 hàng!");
                }
            }
        });
    }

    public void coursePanelActionListener() {
        Helpers.addActionListener(courseManagementPanel.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCourseController(courseTable);
            }
        });
        Helpers.addActionListener(courseManagementPanel.updateButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int updateRow = courseManagementPanel.courseTable.getSelectedRow();
                    CourseModel courseModel = new CourseModel(
                            (String) courseTable.getValueAt(updateRow, 1),
                            (String) courseTable.getValueAt(updateRow, 3),
                            (String) courseTable.getValueAt(updateRow, 4),
                            (String) courseTable.getValueAt(updateRow, 2)
                    );
                    courseModel.id = (String) courseTable.getValueAt(updateRow, 0);
                    new UpdateCourseController(courseTable, courseModel);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                    Helpers.showMess("Bạn phải chọn 1 hàng!");
                }

            }
        });

    }

    public void majorsPanelActionListener() {
        Helpers.addActionListener(majorsManagementPanel.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMajorsController(majorsTable);
            }
        });
        Helpers.addActionListener(majorsManagementPanel.updateButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int updateRow = majorsManagementPanel.majorsTable.getSelectedRow();
                    MajorsModel majorsModel = new MajorsModel(
                            (String) majorsTable.getValueAt(updateRow, 0),
                            (String) majorsTable.getValueAt(updateRow, 1),
                            (String) majorsTable.getValueAt(updateRow, 2));
                    new UpdateMajorsController(majorsTable, majorsModel);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                    Helpers.showMess("Bạn phải chọn 1 hàng!");
                }
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
        Helpers.addActionListener(schoolYearManagementPanel.updateButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int updateRow = schoolYearManagementPanel.schoolYearTable.getSelectedRow();
                    SchoolYearModel schoolYearModel = new SchoolYearModel((String) schoolYearTable.getValueAt(updateRow, 1));
                    schoolYearModel.id = (String) schoolYearTable.getValueAt(updateRow, 0);
                    new UpdateSchoolYearController(schoolYearTable, schoolYearModel);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                    Helpers.showMess("Bạn phải chọn 1 hàng!");
                }

            }
        });
    }

    public void setupTable() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        schoolYearManagementPanel.schoolYearTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
    }
}
