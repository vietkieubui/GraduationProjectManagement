/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller.Main.AddForm;

import GraduationProjectManagement.Model.CourseModel;
import GraduationProjectManagement.Utils.Helpers;
import GraduationProjectManagement.View.Main.AddForm.AddCourseForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BVKieu
 */
public class AddCourseController {

    DefaultTableModel table;
    AddCourseForm addCourseForm;

    public AddCourseController(DefaultTableModel table) {
        this.table = table;
        addCourseForm = new AddCourseForm();
        addFormButtonController();
        String[] listMajors = Helpers.getMajorsList();
        addCourseForm.majorsComboBox.setModel(new DefaultComboBoxModel<>(listMajors));
    }

    void addFormButtonController() {
        Helpers.addActionListener(addCourseForm.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addCourseForm.majorsComboBox.getSelectedIndex() == 0) {
                    Helpers.showMess("Bạn chưa chọn khoa!");
                } else {
                    CourseModel course = new CourseModel(addCourseForm.courseNameText.getText(), addCourseForm.courseDescriptionText.getText(), addCourseForm.studyTimeText.getText(), addCourseForm.majorsComboBox.getSelectedItem().toString());
                    String majorsId = Helpers.getMajorsId(course.majors);
                    String[] columnsName = {"name", "description", "studyTime", "majors"};
                    String[] values = {Helpers.toSQLString(course.name, true), Helpers.toSQLString(course.description, true), Helpers.toSQLString(course.studyTime), Helpers.toSQLString(majorsId)};
                    if (Helpers.insertIntoDatabase("Courses", columnsName, values)) {
                        Helpers.showMess("Thêm thành công!");
                        Helpers.getCourse(table);
                        addCourseForm.dispose();
                    }

                }
            }
        });
        Helpers.addActionListener(addCourseForm.cancelButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCourseForm.dispose();
            }
        });
    }
}
