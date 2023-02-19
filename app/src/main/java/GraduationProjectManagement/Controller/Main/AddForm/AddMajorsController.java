/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller.Main.AddForm;

import GraduationProjectManagement.Model.MajorsModel;
import GraduationProjectManagement.Model.SchoolYearModel;
import GraduationProjectManagement.Services.Services;
import GraduationProjectManagement.View.Main.AddForm.AddMajorsForm;
import GraduationProjectManagement.View.Main.AddForm.AddSchoolYearForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BVKieu
 */
public class AddMajorsController {

    AddMajorsForm addMajorsForm;
    DefaultTableModel table;

    public AddMajorsController(DefaultTableModel table) {
        this.table = table;
        addMajorsForm = new AddMajorsForm();
        addFormButtonController();
    }

    void addFormButtonController() {
        Services.addActionListener(addMajorsForm.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MajorsModel majors = new MajorsModel(addMajorsForm.majorsIdText.getText(), addMajorsForm.majorsNameText.getText(), addMajorsForm.majorsDescriptionText.getText());
                String[] columnsName = {"majorsId", "name", "description"};
                String[] values = {Services.toSQLString(majors.majorsId), Services.toSQLString(majors.name, true), Services.toSQLString(majors.description, true)};
                try {
                    if (Services.insertIntoDatabase("Majors", columnsName, values)) {
                        Services.showMess("Thêm thành công!");
                        Services.getMajors(table);
                        addMajorsForm.dispose();
                    }

                } catch (Exception ex) {
                    Services.showMess(ex.toString());
                }
            }
        });
        Services.addActionListener(addMajorsForm.cancelButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMajorsForm.dispose();
            }
        });
    }
}
