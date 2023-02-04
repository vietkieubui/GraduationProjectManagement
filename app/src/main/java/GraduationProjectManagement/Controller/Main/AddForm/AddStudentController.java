/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller.Main.AddForm;

import GraduationProjectManagement.Model.StudentModel;
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
        addStudentForm.majorsComboBox.setModel(new DefaultComboBoxModel<>(listMajors));
        String[] listCourse = Helpers.getCourseList("");
        addStudentForm.courseComboBox.setModel(new DefaultComboBoxModel<>(listCourse));
        String[] listClass = Helpers.getClassList("", "");
        addStudentForm.classComboBox.setModel(new DefaultComboBoxModel<>(listClass));

        addFormButtonController();
    }

    void addFormButtonController() {
        Helpers.addActionListener(addStudentForm.majorsComboBox, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] listCourse = null;
                String[] listClass = null;
                if (addStudentForm.majorsComboBox.getSelectedIndex() == 0) {
                    listCourse = Helpers.getCourseList("");
                    listClass = Helpers.getClassList("", "");
                } else {
                    listCourse = Helpers.getCourseList(addStudentForm.majorsComboBox.getSelectedItem().toString());
                    listClass = Helpers.getClassList(addStudentForm.majorsComboBox.getSelectedItem().toString(), "");
                }
                addStudentForm.courseComboBox.setModel(new DefaultComboBoxModel<>(listCourse));
                addStudentForm.classComboBox.setModel(new DefaultComboBoxModel<>(listClass));
            }
        });

        Helpers.addActionListener(addStudentForm.courseComboBox, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] listClass = null;
                if (addStudentForm.courseComboBox.getSelectedIndex() == 0
                        && addStudentForm.majorsComboBox.getSelectedIndex() == 0) {
                    listClass = Helpers.getClassList("", "");
                } else if (addStudentForm.courseComboBox.getSelectedIndex() == 0
                        && addStudentForm.majorsComboBox.getSelectedIndex() != 0) {
                    listClass = Helpers.getClassList(addStudentForm.majorsComboBox.getSelectedItem().toString(), "");
                } else if (addStudentForm.courseComboBox.getSelectedIndex() != 0) {
                    String courseName = addStudentForm.courseComboBox.getSelectedItem().toString();
                    addStudentForm.majorsComboBox.setSelectedItem(Helpers.setSelectedMajors("", courseName));
                    addStudentForm.courseComboBox.setSelectedItem(courseName);
                    listClass = Helpers.getClassList(addStudentForm.majorsComboBox.getSelectedItem().toString(), courseName);

                }
                addStudentForm.classComboBox.setModel(new DefaultComboBoxModel<>(listClass));
            }
        });

        Helpers.addActionListener(addStudentForm.classComboBox, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addStudentForm.classComboBox.getSelectedIndex() != 0) {
                    String className = addStudentForm.classComboBox.getSelectedItem().toString();
                    addStudentForm.majorsComboBox.setSelectedItem(Helpers.setSelectedMajors(className, ""));
                    addStudentForm.courseComboBox.setSelectedItem(Helpers.setSelectedCourses(className));
                    addStudentForm.classComboBox.setSelectedItem(className);
                }
            }
        });

        Helpers.addActionListener(addStudentForm.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addStudentForm.phonenumberText.getText().equals("")
                        || sdf.format(addStudentForm.birthday.getDate()).equals("")
                        || addStudentForm.classComboBox.getSelectedIndex() == 0
                        || addStudentForm.genderComboBox.getSelectedIndex() == 0
                        || addStudentForm.idText.getText().equals("")
                        || addStudentForm.emailText.getText().equals("")
                        || addStudentForm.nameText.getText().equals("")) {
                    Helpers.showMess("Bạn phải điền đầy đủ thông tin!");
                } else {
                    if (!Helpers.isValidPhoneNumber(addStudentForm.phonenumberText.getText())) {
                        Helpers.showMess("Số điện thoại không hợp lệ!");
                    } else if (!Helpers.isValidEmail(addStudentForm.emailText.getText())) {
                        Helpers.showMess("Email không hợp lệ!");
                    }
                    StudentModel student = new StudentModel(addStudentForm.idText.getText(), addStudentForm.nameText.getText(),
                            addStudentForm.genderComboBox.getSelectedItem().toString(), sdf.format(addStudentForm.birthday.getDate()),
                            addStudentForm.classComboBox.getSelectedItem().toString(), addStudentForm.phonenumberText.getText(),
                            addStudentForm.emailText.getText());
                    student.classId = Helpers.getClassId(student.classId);
                    String[] columnsName = {"id", "name", "gender", "birthday", "class", "phonenumber", "email"};
                    String[] values = {Helpers.toSQLString(student.id), Helpers.toSQLString(student.name, true),
                        Helpers.toSQLString(student.gender, true), Helpers.toSQLString(student.birthday, false),
                        Helpers.toSQLString(student.classId), Helpers.toSQLString(student.phonenumber),
                        Helpers.toSQLString(student.email)};
                    if (Helpers.insertIntoDatabase("Students", columnsName, values)) {
                        Helpers.showMess("Thêm thành công!");
                        Helpers.getStudent(table);
                        addStudentForm.dispose();
                    }
                }

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
