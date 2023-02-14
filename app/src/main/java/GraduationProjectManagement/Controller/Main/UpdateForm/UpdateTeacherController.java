/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller.Main.UpdateForm;

import GraduationProjectManagement.Model.TeacherModel;
import GraduationProjectManagement.Utils.Helpers;
import GraduationProjectManagement.View.Main.UpdateForm.UpdateTeacherForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BVKieu
 */
public class UpdateTeacherController {

    UpdateTeacherForm updateTeacherForm;
    DefaultTableModel table;
    TeacherModel teacherModel;

    public UpdateTeacherController(DefaultTableModel table, TeacherModel teacherModel) {
        this.table = table;
        this.teacherModel = teacherModel;

        updateTeacherForm = new UpdateTeacherForm();
        updateFormButtonController();
        updateTeacherForm.idText.setText(teacherModel.id);
        updateTeacherForm.nameText.setText(teacherModel.name);
        updateTeacherForm.academicRank.setText(teacherModel.academicRank);
        String[] listMajors = Helpers.getMajorsList();
        updateTeacherForm.majorsComboBox.setModel(new DefaultComboBoxModel<>(listMajors));
        updateTeacherForm.majorsComboBox.setSelectedItem(teacherModel.majors);
        updateTeacherForm.phonenumberText.setText(teacherModel.phonenumber);
        updateTeacherForm.emailText.setText(teacherModel.email);
    }

    private void updateFormButtonController() {
        Helpers.addActionListener(updateTeacherForm.updateButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (updateTeacherForm.majorsComboBox.getSelectedIndex() == 0) {
                    Helpers.showMess("Bạn chưa chọn khoa!");
                } else if (!Helpers.isValidPhoneNumber(updateTeacherForm.phonenumberText.getText())) {
                    Helpers.showMess("Số điện thoại không hợp lệ!");
                } else if (!Helpers.isValidEmail(updateTeacherForm.emailText.getText())) {
                    Helpers.showMess("Email không hợp lệ!");
                } else {
                    String majorsId = Helpers.getMajorsId(updateTeacherForm.majorsComboBox.getSelectedItem().toString());
                    TeacherModel teacher = new TeacherModel(
                            updateTeacherForm.nameText.getText(),
                            updateTeacherForm.academicRank.getText(),
                            majorsId,
                            updateTeacherForm.phonenumberText.getText(),
                            updateTeacherForm.emailText.getText()
                    );
                    teacher.id = updateTeacherForm.idText.getText();

                    String[] columnsName = {"name", "academicRank", "majors", "phonenumber", "email"};
                    String[] values = {
                        Helpers.toSQLString(teacher.name, true),
                        Helpers.toSQLString(teacher.academicRank, true),
                        Helpers.toSQLString(teacher.majors),
                        Helpers.toSQLString(teacher.phonenumber),
                        Helpers.toSQLString(teacher.email)
                    };
                    try {
                        if (Helpers.updateData("Teachers", columnsName, values, "id=" + Helpers.toSQLString(teacher.id))) {
                            Helpers.showMess("Cập nhật thành công!");
                            Helpers.getTeacher(table);
                            updateTeacherForm.dispose();
                        }
                    } catch (Exception ex) {
                        Helpers.showMess(ex.toString());
                    }

                }

            }
        }
        );
        Helpers.addActionListener(updateTeacherForm.cancelButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                updateTeacherForm.dispose();
            }
        }
        );
    }

}
