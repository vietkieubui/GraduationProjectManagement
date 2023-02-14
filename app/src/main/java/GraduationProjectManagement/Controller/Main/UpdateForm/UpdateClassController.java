package GraduationProjectManagement.Controller.Main.UpdateForm;

import GraduationProjectManagement.Model.ClassModel;
import GraduationProjectManagement.Utils.Helpers;
import GraduationProjectManagement.View.Main.UpdateForm.UpdateClassForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author BVKieu
 */
public class UpdateClassController {

    UpdateClassForm updateClassForm;
    DefaultTableModel table;
    ClassModel classModel;

    public UpdateClassController(DefaultTableModel table, ClassModel classModel) {
        this.table = table;
        this.classModel = classModel;

        updateClassForm = new UpdateClassForm();
        updateFormButtonController();
        updateClassForm.idText.setText(classModel.id);
        updateClassForm.nameText.setText(classModel.name);
        updateClassForm.descriptionText.setText(classModel.description);
        String[] listMajors = Helpers.getMajorsList();
        updateClassForm.majorsComboBox.setModel(new DefaultComboBoxModel<>(listMajors));
        String[] listCourse = Helpers.getCourseList("");
        updateClassForm.courseComboBox.setModel(new DefaultComboBoxModel<>(listCourse));
        updateClassForm.courseComboBox.setSelectedItem(classModel.course);
    }

    private void updateFormButtonController() {
        Helpers.addActionListener(updateClassForm.majorsComboBox, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] listCourse = null;
                if (updateClassForm.majorsComboBox.getSelectedIndex() == 0) {
                    listCourse = Helpers.getCourseList("");
                } else {
                    listCourse = Helpers.getCourseList(updateClassForm.majorsComboBox.getSelectedItem().toString());
                }
                updateClassForm.courseComboBox.setModel(new DefaultComboBoxModel<>(listCourse));
            }
        });
        Helpers.addActionListener(updateClassForm.courseComboBox, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (updateClassForm.courseComboBox.getSelectedIndex() != 0 && updateClassForm.majorsComboBox.getSelectedIndex() == 0) {
                    String courseName = updateClassForm.courseComboBox.getSelectedItem().toString();
                    updateClassForm.majorsComboBox.setSelectedItem(Helpers.setSelectedMajors("", updateClassForm.courseComboBox.getSelectedItem().toString()));
                    updateClassForm.courseComboBox.setSelectedItem(courseName);
                }
            }
        });
        Helpers.addActionListener(updateClassForm.updateButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (updateClassForm.courseComboBox.getSelectedIndex() == 0) {
                    Helpers.showMess("Bạn chưa chọn khoá!");
                } else {
                    String courseId = Helpers.getCourseId(updateClassForm.courseComboBox.getSelectedItem().toString());
                    ClassModel classMd = new ClassModel(
                            updateClassForm.nameText.getText(),
                            updateClassForm.descriptionText.getText(),
                            courseId
                    );
                    classMd.id = updateClassForm.idText.getText();
                    String[] columnsName = {"name", "description", "course"};
                    String[] values = {
                        Helpers.toSQLString(classMd.name, true),
                        Helpers.toSQLString(classMd.description, true),                        
                        Helpers.toSQLString(classMd.course)};
                    try {
                        if (Helpers.updateData("Classes", columnsName, values, "id=" + Helpers.toSQLString(classMd.id))) {
                            Helpers.showMess("Cập nhật thành công!");
                            Helpers.getClass(table);
                            updateClassForm.dispose();
                        }
                    } catch (Exception ex) {
                        Helpers.showMess(ex.toString());
                    }
                }
            }
        });
        Helpers.addActionListener(updateClassForm.cancelButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClassForm.dispose();
            }
        });
    }

}
