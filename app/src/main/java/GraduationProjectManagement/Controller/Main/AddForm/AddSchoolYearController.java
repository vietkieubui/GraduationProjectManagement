/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Controller.Main.AddForm;

import GraduationProjectManagement.Controller.Main.MainController;
import GraduationProjectManagement.Model.SchoolYearModel;
import GraduationProjectManagement.Utils.Helpers;
import GraduationProjectManagement.View.Main.AddForm.AddSchoolYearForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BVKieu
 */
public final class AddSchoolYearController {

    AddSchoolYearForm addSchoolYearForm;
    DefaultTableModel table;

    public AddSchoolYearController(DefaultTableModel table) {
        this.table = table;
        addSchoolYearForm = new AddSchoolYearForm();
        addFormButtonController();
    }

    void addFormButtonController() {
        Helpers.addActionListener(addSchoolYearForm.addButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SchoolYearModel schoolYear = new SchoolYearModel(addSchoolYearForm.schoolYearText.getText());
                String[] columnsName = {"name"};
                String[] values = {Helpers.toSQLString(schoolYear.name)};
                try {
                    Helpers.insertIntoDatabase("SchoolYears", columnsName, values);
                    Helpers.showMess("Thêm thành công!");
                    Helpers.getSchoolYears(table);
                    addSchoolYearForm.dispose();
                } catch (Exception ex) {
                    Helpers.showMess(ex.toString());
                }
            }
        });
        Helpers.addActionListener(addSchoolYearForm.cancelButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSchoolYearForm.dispose();
            }
        });
    }

}
