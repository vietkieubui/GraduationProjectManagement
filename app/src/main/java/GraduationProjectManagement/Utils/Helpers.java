package GraduationProjectManagement.Utils;

import GraduationProjectManagement.Model.Auth.RegisterModel;
import GraduationProjectManagement.Model.Auth.User;
import static GraduationProjectManagement.Utils.ConnectDatabase.cnn;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.security.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author BVKieu
 */
public final class Helpers {

    public static void addActionListener(JButton btn, ActionListener al) {
        btn.addActionListener(al);
    }

    public static void addActionListener(JLabel lb, MouseListener ml) {
        lb.addMouseListener(ml);
    }

    public static boolean login(String username, String password) {
        String hashedPassword = Helpers.hashPassword(password);
        String sql = "SELECT * FROM Users WHERE username = " + toSQLString(username) + " AND password = " + toSQLString(hashedPassword);
        try {
            Statement stm = cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                User.setUser(rs.getString(3), rs.getString(2), rs.getString(4));
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Helpers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean register(String name, String username, String phonenumber, String password, String confirmPassword) {

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Mật khẩu không trùng khớp!");
            return false;
        }
        if (checkExist("Users", "username", username)) {
            showMess("Tên đăng nhập đã được sử dụng!");
            return false;
        } else {
            String hashedPassword = Helpers.hashPassword(password);
            RegisterModel registerModel = new RegisterModel(name, phonenumber, username, hashedPassword, confirmPassword);

            String[] columnsName = {"name", "username", "phonenumber", "password"};
            String[] values = {Helpers.toSQLString(registerModel.name, true), Helpers.toSQLString(registerModel.username), Helpers.toSQLString(registerModel.phonenumber), Helpers.toSQLString(registerModel.password)};
            try {
                Helpers.insertIntoDatabase("Users", columnsName, values);
                Helpers.showMess("Đăng ký thành công");
                return true;
            } catch (Exception ex) {
            }
        }
        return false;
    }

    public static void insertIntoDatabase(String tableName, String[] columnsName, String[] values) {
        String columnsString = "";
        String valuesString = "";
        for (int i = 0; i < columnsName.length; i++) {
            columnsString += columnsName[i];
            if (i + 1 < columnsName.length) {
                columnsString += ",";
            }
        }
        for (int i = 0; i < values.length; i++) {
            valuesString += values[i];
            if (i + 1 < values.length) {
                valuesString += ",";
            }
        }
        String sql = "INSERT INTO " + tableName + "(" + columnsString + ")" + "VALUES(" + valuesString + ")";
        System.out.println(sql);
        try {
            Statement stm = cnn.createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Helpers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static boolean checkExist(String tableName, String column, String value) {
        boolean flag = false;
        String sql = "SELECT * FROM " + tableName + " where " + column + " = " + toSQLString(value);
        try {
            Statement stm = cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Helpers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public static String toSQLString(String s, boolean... vietnamese) {
        if (vietnamese.length > 0) {
            if (vietnamese[0]) {
                return "N'" + s + "'";
            }
        }
        return "'" + s + "'";
    }

    public static String hashPassword(String password) {
        String hashedPassword = "";
        try {
            byte[] bytesOfPassword = password.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] theMD5digest = md.digest(bytesOfPassword);
            BigInteger no = new BigInteger(1, theMD5digest);
            hashedPassword = no.toString(16);
            return hashedPassword;
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Helpers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hashedPassword;
    }

    public static void showMess(String st) {
        JOptionPane.showMessageDialog(null, st);
    }

    /**
     *
     * get table data
     */
    public static void getSchoolYears(DefaultTableModel schoolYearTable) {
        schoolYearTable.setRowCount(0);
        String sql = "SELECT * FROM SchoolYears ORDER BY name ASC";
        try {
            Statement stm = cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                schoolYearTable.addRow(new Object[]{rs.getString("name")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Helpers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getMajors(DefaultTableModel majorsTable) {
        majorsTable.setRowCount(0);
        String sql = "SELECT * FROM Majors ORDER BY name ASC";
        try {
            Statement stm = cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                majorsTable.addRow(new Object[]{rs.getString("majorsId"), rs.getString("name"), rs.getString("description")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Helpers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getCourse(DefaultTableModel courseTable) {
        courseTable.setRowCount(0);
        String sql = "SELECT Courses.id, Courses.name, Courses.description, Majors.name as majors FROM Courses, Majors WHERE Courses.majors = Majors.id ORDER BY name ASC";
        try {
            Statement stm = cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                courseTable.addRow(new Object[]{rs.getString("name"), rs.getString("majors"), rs.getString("description")});
            }
        } catch (Exception ex) {
            Logger.getLogger(Helpers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
