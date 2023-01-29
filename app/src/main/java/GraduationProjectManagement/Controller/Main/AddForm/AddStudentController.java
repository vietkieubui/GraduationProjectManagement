/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller.Main.AddForm;

import GraduationProjectManagement.Utils.Helpers;
import GraduationProjectManagement.View.Main.AddForm.AddSchoolYearForm;
import GraduationProjectManagement.View.Main.AddForm.AddStudentForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BVKieu
 */
public class AddStudentController {

    AddStudentForm addStudentForm;
    DefaultTableModel table;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public AddStudentController(DefaultTableModel table) {
        this.table = table;
        addStudentForm = new AddStudentForm();
        String[] listMajors = Helpers.getMajorsList();
        addStudentForm.studentMajorsComboBox.setModel(new DefaultComboBoxModel<>(listMajors));
        String[] listCourse = Helpers.getCourseList("");
        addStudentForm.studentCourseComboBox.setModel(new DefaultComboBoxModel<>(listCourse));
        String[] listClass = Helpers.getClassList("", "");
        addStudentForm.studentClassComboBox.setModel(new DefaultComboBoxModel<>(listClass));

        addFormButtonController();
    }

    void addFormButtonController() {
        Helpers.addActionListener(addStudentForm.studentMajorsComboBox, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] listCourse = null;
                String[] listClass = null;
                if (addStudentForm.studentMajorsComboBox.getSelectedIndex() == 0) {
                    listCourse = Helpers.getCourseList("");
                    listClass = Helpers.getClassList("", "");
                } else {
                    listCourse = Helpers.getCourseList(addStudentForm.studentMajorsComboBox.getSelectedItem().toString());
                    listClass = Helpers.getClassList(addStudentForm.studentMajorsComboBox.getSelectedItem().toString(), "");
                }
                addStudentForm.studentCourseComboBox.setModel(new DefaultComboBoxModel<>(listCourse));
                addStudentForm.studentClassComboBox.setModel(new DefaultComboBoxModel<>(listClass));
            }
        });

        Helpers.addActionListener(addStudentForm.studentCourseComboBox, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] listClass = null;
                if (addStudentForm.studentCourseComboBox.getSelectedIndex() == 0
                        && addStudentForm.studentMajorsComboBox.getSelectedIndex() == 0) {
                    listClass = Helpers.getClassList("", "");
                } else if (addStudentForm.studentCourseComboBox.getSelectedIndex() == 0
                        && addStudentForm.studentMajorsComboBox.getSelectedIndex() != 0) {
                    listClass = Helpers.getClassList(addStudentForm.studentMajorsComboBox.getSelectedItem().toString(), "");
                } else if(addStudentForm.studentCourseComboBox.getSelectedIndex() != 0){
                    String courseName = addStudentForm.studentCourseComboBox.getSelectedItem().toString();
                    addStudentForm.studentMajorsComboBox.setSelectedItem(Helpers.setSelectedMajors("", courseName));
                    addStudentForm.studentCourseComboBox.setSelectedItem(courseName);
                    listClass = Helpers.getClassList(addStudentForm.studentMajorsComboBox.getSelectedItem().toString(), courseName);

                }
                addStudentForm.studentClassComboBox.setModel(new DefaultComboBoxModel<>(listClass));
            }
        });

        Helpers.addActionListener(addStudentForm.studentClassComboBox, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addStudentForm.studentClassComboBox.getSelectedIndex() != 0) {
                    String className = addStudentForm.studentClassComboBox.getSelectedItem().toString();
                    addStudentForm.studentMajorsComboBox.setSelectedItem(Helpers.setSelectedMajors(className, ""));
                    addStudentForm.studentCourseComboBox.setSelectedItem(Helpers.setSelectedCourses(className));
                    addStudentForm.studentClassComboBox.setSelectedItem(className);
                }
            }
        });

        Helpers.addActionListener(addStudentForm.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Helpers.addActionListener(addStudentForm.cancelButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudentForm.dispose();
            }
        });
    }
}
