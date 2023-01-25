/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller.Main.AddForm;

import GraduationProjectManagement.Model.CourseModel;
import GraduationProjectManagement.Model.SchoolYearModel;
import GraduationProjectManagement.Utils.ConnectDatabase;
import GraduationProjectManagement.Utils.Helpers;
import GraduationProjectManagement.View.Main.AddForm.AddCourseForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        String[] listMajors = getMajors();
        addCourseForm.majorsComboBox.setModel(new DefaultComboBoxModel<>(listMajors));
    }

    void addFormButtonController() {
        Helpers.addActionListener(addCourseForm.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addCourseForm.majorsComboBox.getSelectedIndex() == 0) {
                    Helpers.showMess("Bạn chưa chọn khoa!");
                } else {
                    CourseModel course = new CourseModel(addCourseForm.courseNameText.getText(), addCourseForm.courseDescriptionText.getText(), addCourseForm.majorsComboBox.getSelectedItem().toString());
                    String getMajorsIdSQL = "SELECT * FROM Majors";
                    String majorsId = "";
                    Statement stm = null;
                    ResultSet rs = null;
                    try {
                        stm = ConnectDatabase.cnn.createStatement();
                        rs = stm.executeQuery(getMajorsIdSQL);
                        while (rs.next()) {
                            if (rs.getString("name").equals(course.majors)) {
                                majorsId = rs.getString("id");
                            }
                        }
                        String[] columnsName = {"name", "description", "majors"};
                        String[] values = {Helpers.toSQLString(course.name, true), Helpers.toSQLString(course.description, true), Helpers.toSQLString(majorsId)};
                        Helpers.insertIntoDatabase("Courses", columnsName, values);
                        Helpers.showMess("Thêm thành công!");
                        Helpers.getCourse(table);
                        addCourseForm.dispose();
                    } catch (SQLException ex) {
                        System.out.println(ex.toString());
                        Helpers.showMess("SQL Error!");
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

    String[] getMajors() {
        String[] listMajors = null;
        ArrayList<String> list = new ArrayList<String>();
        list.add("--Chọn khoa--");
        String sql = "SELECT*FROM Majors";
        try {
            Statement stm = ConnectDatabase.cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString("name"));
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            Helpers.showMess("Lỗi đọc dữ liệu từ SQL Server!");
        }
        listMajors = list.toArray(new String[list.size()]);
        return listMajors;
    }

}
