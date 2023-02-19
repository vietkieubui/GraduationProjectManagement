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
import GraduationProjectManagement.Controller.Main.UpdateForm.UpdateStudentController;
import GraduationProjectManagement.Controller.Main.UpdateForm.UpdateTeacherController;
import GraduationProjectManagement.Model.ClassModel;
import GraduationProjectManagement.Model.CourseModel;
import GraduationProjectManagement.Model.MajorsModel;
import GraduationProjectManagement.Model.SchoolYearModel;
import GraduationProjectManagement.Model.StudentModel;
import GraduationProjectManagement.Model.TeacherModel;
import GraduationProjectManagement.Services.Services;
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
        Services.addActionListener(mainView.projectTopicButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentPanel.setVisible(false);
                mainView.mainPanel.add(projectManagementPanel, BorderLayout.NORTH);
                projectManagementPanel.setVisible(true);
                currentPanel = projectManagementPanel;
            }
        });

        Services.addActionListener(mainView.studentButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Services.getStudent(studentTable);
                currentPanel.setVisible(false);
                mainView.mainPanel.add(studentManagementPanel);
                studentManagementPanel.setVisible(true);
                currentPanel = studentManagementPanel;
            }
        });

        Services.addActionListener(mainView.teacherButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Services.getTeacher(teacherTable);
                currentPanel.setVisible(false);
                mainView.mainPanel.add(teacherManagementPanel);
                teacherManagementPanel.setVisible(true);
                currentPanel = teacherManagementPanel;
            }
        });

        Services.addActionListener(mainView.classButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Services.getClass(classTable);
                currentPanel.setVisible(false);
                mainView.mainPanel.add(classManagementPanel);
                classManagementPanel.setVisible(true);
                currentPanel = classManagementPanel;
            }
        });

        Services.addActionListener(mainView.courseButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Services.getCourse(courseTable);
                currentPanel.setVisible(false);
                mainView.mainPanel.add(courseManagementPanel);
                courseManagementPanel.setVisible(true);
                currentPanel = courseManagementPanel;
            }
        });

        Services.addActionListener(mainView.schoolYearButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Services.getSchoolYears(schoolYearTable);
                currentPanel.setVisible(false);
                mainView.mainPanel.add(schoolYearManagementPanel);
                schoolYearManagementPanel.setVisible(true);
                currentPanel = schoolYearManagementPanel;
            }
        });

        Services.addActionListener(mainView.majorsButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Services.getMajors(majorsTable);
                currentPanel.setVisible(false);
                mainView.mainPanel.add(majorsManagementPanel);
                majorsManagementPanel.setVisible(true);
                currentPanel = majorsManagementPanel;
            }
        });
    }

    public void studentPanelActionListener() {
        Services.addActionListener(studentManagementPanel.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudentController(studentTable);
            }
        });

        Services.addActionListener(studentManagementPanel.updateButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int updateRow = studentManagementPanel.studentTable.getSelectedRow();
                    StudentModel studentModel = new StudentModel(
                            (String) studentTable.getValueAt(updateRow, 0),
                            (String) studentTable.getValueAt(updateRow, 1),
                            (String) studentTable.getValueAt(updateRow, 2),
                            (String) studentTable.getValueAt(updateRow, 3),
                            (String) studentTable.getValueAt(updateRow, 4),
                            (String) studentTable.getValueAt(updateRow, 5),
                            (String) studentTable.getValueAt(updateRow, 6)
                    );
                    new UpdateStudentController(studentTable, studentModel);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                    Services.showMess("Bạn phải chọn 1 hàng!");
                }
            }
        });

    }

    public void teacherPanelActionListener() {
        Services.addActionListener(teacherManagementPanel.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTeacherController(teacherTable);
            }
        });
        Services.addActionListener(teacherManagementPanel.updateButton, new ActionListener() {
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
                    Services.showMess("Bạn phải chọn 1 hàng!");
                }
            }
        });
    }

    public void classPanelActionListener() {
        Services.addActionListener(classManagementPanel.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddClassController(classTable);
            }
        });
        Services.addActionListener(classManagementPanel.updateButton, new ActionListener() {
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
                    Services.showMess("Bạn phải chọn 1 hàng!");
                }
            }
        });
    }

    public void coursePanelActionListener() {
        Services.addActionListener(courseManagementPanel.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCourseController(courseTable);
            }
        });
        Services.addActionListener(courseManagementPanel.updateButton, new ActionListener() {
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
                    Services.showMess("Bạn phải chọn 1 hàng!");
                }

            }
        });

    }

    public void majorsPanelActionListener() {
        Services.addActionListener(majorsManagementPanel.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddMajorsController(majorsTable);
            }
        });
        Services.addActionListener(majorsManagementPanel.updateButton, new ActionListener() {
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
                    Services.showMess("Bạn phải chọn 1 hàng!");
                }
            }
        });

    }

    public void schoolYearPanelActionListener() {
        Services.addActionListener(schoolYearManagementPanel.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddSchoolYearController(schoolYearTable);
            }
        });
        Services.addActionListener(schoolYearManagementPanel.updateButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int updateRow = schoolYearManagementPanel.schoolYearTable.getSelectedRow();
                    SchoolYearModel schoolYearModel = new SchoolYearModel((String) schoolYearTable.getValueAt(updateRow, 1));
                    schoolYearModel.id = (String) schoolYearTable.getValueAt(updateRow, 0);
                    new UpdateSchoolYearController(schoolYearTable, schoolYearModel);
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                    Services.showMess("Bạn phải chọn 1 hàng!");
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
