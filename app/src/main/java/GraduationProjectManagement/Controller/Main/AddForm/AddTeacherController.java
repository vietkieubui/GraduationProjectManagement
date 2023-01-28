/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller.Main.AddForm;

import GraduationProjectManagement.Model.CourseModel;
import GraduationProjectManagement.Model.TeacherModel;
import GraduationProjectManagement.Utils.Helpers;
import GraduationProjectManagement.View.Main.AddForm.AddTeacherForm;
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
public class AddTeacherController {

    DefaultTableModel table;
    AddTeacherForm addTeacherForm;

    public AddTeacherController(DefaultTableModel table) {
        this.table = table;
        addTeacherForm = new AddTeacherForm();
        addFormButtonController();
        String[] listMajors = Helpers.getMajorsList();
        addTeacherForm.majorsComboBox.setModel(new DefaultComboBoxModel<>(listMajors));
    }

    void addFormButtonController() {
        Helpers.addActionListener(addTeacherForm.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addTeacherForm.majorsComboBox.getSelectedIndex() == 0) {
                    Helpers.showMess("Bạn chưa chọn khoa!");
                } else {
                    TeacherModel teacher = new TeacherModel(addTeacherForm.teacherNameText.getText(),
                            addTeacherForm.teacherAcademicRank.getText(), addTeacherForm.majorsComboBox.getSelectedItem().toString(), addTeacherForm.teacherPhoneNumberText.getText(), addTeacherForm.teacherEmailText.getText());
                    String majorsId = Helpers.getMajorsId(teacher.majors);
                    Statement stm = null;
                    ResultSet rs = null;
                    String[] columnsName = {"name", "academicRank", "majors", "phonenumber","email"};
                    String[] values = {Helpers.toSQLString(teacher.name, true), Helpers.toSQLString(teacher.academicRank, true), 
                        Helpers.toSQLString(majorsId), Helpers.toSQLString(teacher.phonenumber),Helpers.toSQLString(teacher.email)};
                    if (Helpers.insertIntoDatabase("Teachers", columnsName, values)) {
                        Helpers.showMess("Thêm thành công!");
                    }
                    Helpers.getTeacher(table);
                    addTeacherForm.dispose();
                }
            }
        });
        Helpers.addActionListener(addTeacherForm.cancelButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTeacherForm.dispose();
            }
        });
    }

}
