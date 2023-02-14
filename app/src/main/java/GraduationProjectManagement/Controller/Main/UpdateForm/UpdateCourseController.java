/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller.Main.UpdateForm;

import GraduationProjectManagement.Model.CourseModel;
import GraduationProjectManagement.Utils.Helpers;
import GraduationProjectManagement.View.Main.UpdateForm.UpdateCourseForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BVKieu
 */
public class UpdateCourseController {

    UpdateCourseForm updateCourseForm;
    DefaultTableModel table;
    CourseModel courseModel;

    public UpdateCourseController(DefaultTableModel table, CourseModel courseModel) {
        this.courseModel = courseModel;
        this.table = table;
        updateCourseForm = new UpdateCourseForm();
        updateCourseForm.idText.setText(courseModel.id);
        updateCourseForm.nameText.setText(courseModel.name);
        updateCourseForm.studyTimeText.setText(courseModel.studyTime);
        updateCourseForm.descriptionText.setText(courseModel.description);
        String[] listMajors = Helpers.getMajorsList();
        updateCourseForm.majorsComboBox.setModel(new DefaultComboBoxModel<>(listMajors));
        updateCourseForm.majorsComboBox.setSelectedItem(courseModel.majors);
        updateFormButtonController();
    }

    private void updateFormButtonController() {
        Helpers.addActionListener(updateCourseForm.updateButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (updateCourseForm.majorsComboBox.getSelectedIndex() == 0) {
                    Helpers.showMess("Bạn chưa chọn khoa!");
                } else {
                    String majorsId = Helpers.getMajorsId(updateCourseForm.majorsComboBox.getSelectedItem().toString());
                    CourseModel course = new CourseModel(
                            updateCourseForm.nameText.getText(),
                            updateCourseForm.descriptionText.getText(),
                            updateCourseForm.studyTimeText.getText(),
                            majorsId
                    );
                    course.id = updateCourseForm.idText.getText();
                    String[] columnsName = {"name", "description", "studyTime", "majors"};
                    String[] values = {
                        Helpers.toSQLString(course.name, true),
                        Helpers.toSQLString(course.description, true),
                        Helpers.toSQLString(course.studyTime),
                        Helpers.toSQLString(course.majors),};
                    try {
                        if (Helpers.updateData("Courses", columnsName, values, "id=" + Helpers.toSQLString(course.id))) {
                            Helpers.showMess("Cập nhật thành công!");
                            Helpers.getCourse(table);
                            updateCourseForm.dispose();
                        }

                    } catch (Exception ex) {
                        Helpers.showMess(ex.toString());
                    }

                }
            }
        });
        Helpers.addActionListener(updateCourseForm.cancelButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCourseForm.dispose();
            }
        });

    }

}
