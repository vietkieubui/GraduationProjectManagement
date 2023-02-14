package GraduationProjectManagement.Controller.Main.UpdateForm;

import GraduationProjectManagement.Model.MajorsModel;
import GraduationProjectManagement.Utils.Helpers;
import GraduationProjectManagement.View.Main.UpdateForm.UpdateMajorsForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author BVKieu
 */
public class UpdateMajorsController {

    UpdateMajorsForm updateMajorsForm;
    DefaultTableModel table;
    MajorsModel majorsModel;

    public UpdateMajorsController(DefaultTableModel table, MajorsModel majorsModel) {
        this.table = table;
        this.majorsModel = majorsModel;
        updateMajorsForm = new UpdateMajorsForm();
        updateMajorsForm.idText.setText(majorsModel.majorsId);
        updateMajorsForm.nameText.setText(majorsModel.name);
        updateMajorsForm.descriptionText.setText(majorsModel.description);
        updateFormButtonController();
    }

    void updateFormButtonController() {
        Helpers.addActionListener(updateMajorsForm.updateButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MajorsModel majors = new MajorsModel(updateMajorsForm.idText.getText(),
                        updateMajorsForm.nameText.getText(), updateMajorsForm.descriptionText.getText());
                String[] columnsName = {"name", "description"};
                String[] values = {Helpers.toSQLString(majors.name,true), Helpers.toSQLString(majors.description,true)};
                try {
                    if (Helpers.updateData("Majors", columnsName, values, "majorsId="+Helpers.toSQLString(majors.majorsId))) {
                        Helpers.showMess("Cập nhật thành công!");
                        Helpers.getMajors(table);
                        updateMajorsForm.dispose();
                    }

                } catch (Exception ex) {
                    Helpers.showMess(ex.toString());
                }
            }
        });
        Helpers.addActionListener(updateMajorsForm.cancelButton, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateMajorsForm.dispose();
            }
        });
    }

}
