/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller.Main.AddForm;

import GraduationProjectManagement.Model.ClassModel;
import GraduationProjectManagement.Utils.Helpers;
import GraduationProjectManagement.View.Main.AddForm.AddClassForm;
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
public class AddClassController {

    DefaultTableModel table;
    AddClassForm addClassForm;

    public AddClassController(DefaultTableModel table) {
        this.table = table;
        addClassForm = new AddClassForm();
        addFormButtonActionListener();
        String[] listMajors = Helpers.getMajorsList();
        String[] listCourse = Helpers.getCourseList("");
        addClassForm.majorsComboBox.setModel(new DefaultComboBoxModel<>(listMajors));
        addClassForm.courseComboBox.setModel(new DefaultComboBoxModel<>(listCourse));
    }

    void addFormButtonActionListener() {
        Helpers.addActionListener(addClassForm.majorsComboBox, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] listCourse = null;
                if (addClassForm.majorsComboBox.getSelectedIndex() == 0) {
                    listCourse = Helpers.getCourseList("");
                } else {
                    listCourse = Helpers.getCourseList(addClassForm.majorsComboBox.getSelectedItem().toString());

                }
                addClassForm.courseComboBox.setModel(new DefaultComboBoxModel<>(listCourse));
            }
        });
        Helpers.addActionListener(addClassForm.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(addClassForm.courseComboBox.getSelectedIndex() ==0){
                    Helpers.showMess("Bạn chưa chọn khóa!");
                }else if(addClassForm.classNameText.getText().equals("")){
                    Helpers.showMess("Tên lớp không được để trống!");
                }else{
                    ClassModel classModel = new ClassModel(addClassForm.classNameText.getText(),
                            addClassForm.classDescriptionText.getText(), addClassForm.courseComboBox.getSelectedItem().toString());
                    System.out.println(classModel.course);
                    String courseId = Helpers.getCourseId(classModel.course);
                    Statement stm = null;
                    ResultSet rs = null;
                    String[] columnsName = {"name", "description", "course"};
                    String[] values = {Helpers.toSQLString(classModel.name, true), Helpers.toSQLString(classModel.description, true),Helpers.toSQLString(courseId)};
                    if(Helpers.insertIntoDatabase("Classes", columnsName, values)){
                        Helpers.showMess("Thêm thành công!");
                    }
                    Helpers.getClass(table);
                    addClassForm.dispose();
                }
            }
        });
        Helpers.addActionListener(addClassForm.cancelButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addClassForm.dispose();
            }
        });
    }
}
