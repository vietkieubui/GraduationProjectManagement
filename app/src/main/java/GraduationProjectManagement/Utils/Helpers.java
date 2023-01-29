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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
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

    public static void addActionListener(JComboBox cbb, ActionListener al) {
        cbb.addActionListener(al);
    }

    public static boolean login(String username, String password) {
        String hashedPassword = Helpers.hashPassword(password);
        String sql = "SELECT * FROM Users WHERE username = " + toSQLString(username) + " AND password = " + toSQLString(hashedPassword);
        try {
            Statement stm = cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                User.setUser(rs.getString("id"), rs.getString(3), rs.getString(2), rs.getString(4));
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
                if (Helpers.insertIntoDatabase("Users", columnsName, values)) {
                    Helpers.showMess("Đăng ký thành công");
                    return true;
                }
            } catch (Exception ex) {
            }
        }
        return false;
    }

    public static boolean insertIntoDatabase(String tableName, String[] columnsName, String[] values) {
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
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Helpers.class.getName()).log(Level.SEVERE, null, ex);
            showMess(ex.toString());
            return false;
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
     * get ComboBox data
     */
    public static String[] getMajorsList() {
        String sql = "SELECT DISTINCT Majors.name FROM Classes, Courses, Majors WHERE Courses.id = Classes.course and Majors.id = Courses.majors";
//        if(!className.equals("") && courseName.equals("")){
//            sql = "SELECT DISTINCT Majors.name FROM Classes, Courses, Majors WHERE Courses.id = Classes.course and Majors.id = Courses.majors and Classes.name = " + toSQLString(className, true);
//        }else if(className.equals("") && !courseName.equals("")){
//            sql = "SELECT DISTINCT Majors.name FROM Classes, Courses, Majors WHERE Courses.id = Classes.course and Majors.id = Courses.majors and Course.name = " + toSQLString(courseName, true);
//        }else if(!className.equals("") && !courseName.equals("")){
//            sql = "SELECT DISTINCT Majors.name FROM Classes, Courses, Majors WHERE Courses.id = Classes.course and Majors.id = Courses.majors and Course.name = " + toSQLString(courseName, true) + " and Classes.name = " + toSQLString(className, true);
//        }
        
        String[] listMajors = null;
        ArrayList<String> list = new ArrayList<String>();
        list.add("--Chọn khoa--");
        
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

    public static String[] getCourseList(String majorsName) {
        String sql = "SELECT*FROM Courses";
        if (!majorsName.equals("")) {
            String majorsId = getMajorsId(majorsName);
            sql = "SELECT * FROM Courses WHERE majors = " + toSQLString(majorsId);
        }
        String[] courseList = null;
        ArrayList<String> list = new ArrayList<String>();
        list.add("--Chọn khóa--");

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
        courseList = list.toArray(new String[list.size()]);
        return courseList;
    }
    
    public static String[] getClassList(String majorsName, String courseName) {
        String sql = "SELECT DISTINCT Classes.name FROM Classes, Courses, Majors WHERE Courses.id = Classes.course and Majors.id = Courses.majors";
        if (!majorsName.equals("") && courseName.equals("")) {
            String majorsId = getMajorsId(majorsName);
            sql = "SELECT DISTINCT Classes.name FROM Classes, Courses, Majors WHERE Courses.id = Classes.course and Majors.id = Courses.majors and Majors.id = " + toSQLString(majorsId);
        }else if(majorsName.equals("") && !courseName.equals("")){
            String courseId = getCourseId(courseName);
            sql = "SELECT DISTINCT Classes.name FROM Classes, Courses, Majors WHERE Courses.id = Classes.course and Majors.id = Courses.majors and Courses.id = " + toSQLString(courseId);
        }else if(!majorsName.equals("") && !courseName.equals("")){
            String courseId = getCourseId(courseName);
            String majorsId = getMajorsId(majorsName);
            sql = "SELECT DISTINCT Classes.name FROM Classes, Courses, Majors WHERE Courses.id = Classes.course and Majors.id = Courses.majors "
                    + "and Courses.id = " + toSQLString(courseId) + " and Majors.id = " + toSQLString(majorsId);            
        }
        String[] courseList = null;
        ArrayList<String> list = new ArrayList<String>();
        list.add("--Chọn lớp--");

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
        courseList = list.toArray(new String[list.size()]);
        return courseList;
    }
    
    public static String setSelectedMajors(String className, String courseName){
        String majors = "";
        String sql = "SELECT DISTINCT Majors.name FROM Classes, Courses, Majors WHERE Courses.id = Classes.course and Majors.id = Courses.majors";
        if(!className.equals("") && courseName.equals("")){
            sql = "SELECT DISTINCT Majors.name FROM Classes, Courses, Majors WHERE Courses.id = Classes.course and Majors.id = Courses.majors and Classes.name = " + toSQLString(className);
        }else if(className.equals("") && !courseName.equals("")){
            sql = "SELECT DISTINCT Majors.name FROM Courses, Majors WHERE Majors.id = Courses.majors and Courses.name = " + toSQLString(courseName);
        }else if(!className.equals("") && !courseName.equals("")){
            sql = "SELECT DISTINCT Majors.name FROM Classes, Courses, Majors WHERE Courses.id = Classes.course and Majors.id = Courses.majors and Courses.name = " + toSQLString(courseName) + " and Classes.name = " + toSQLString(className);
        }
        try {
            Statement stm = ConnectDatabase.cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                majors = rs.getString("name");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            Helpers.showMess("Lỗi đọc dữ liệu từ SQL Server!");
        }
        return majors;
    }
    
    public static String setSelectedCourses(String className){
        String course = "";
        String sql = "SELECT DISTINCT Courses.name FROM Classes, Courses, Majors WHERE Courses.id = Classes.course and Majors.id = Courses.majors";
        if(!className.equals("")){
            sql = "SELECT DISTINCT Courses.name FROM Classes, Courses, Majors WHERE Courses.id = Classes.course and Majors.id = Courses.majors and Classes.name = " + toSQLString(className);
        }
        try {
            Statement stm = ConnectDatabase.cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                course = rs.getString("name");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            Helpers.showMess("Lỗi đọc dữ liệu từ SQL Server!");
        }
        return course;
    }

    /**
     *
     * get ComboBox data ID
     */
    public static String getMajorsId(String name) {
        String majorsId = "";
        String getMajorsIdSQL = "SELECT * FROM Majors";
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = ConnectDatabase.cnn.createStatement();
            rs = stm.executeQuery(getMajorsIdSQL);
            while (rs.next()) {
                if (rs.getString("name").equals(name)) {
                    majorsId = rs.getString("id");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            Helpers.showMess("SQL Error!");
        }
        return majorsId;
    }

    public static String getCourseId(String name) {
        String majorsId = "";
        String getCourseIdSQL = "SELECT * FROM Courses";
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = ConnectDatabase.cnn.createStatement();
            rs = stm.executeQuery(getCourseIdSQL);
            while (rs.next()) {
                if (rs.getString("name").equals(name)) {
                    majorsId = rs.getString("id");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            Helpers.showMess("SQL Error!");
        }
        return majorsId;
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
        String sql = "SELECT Courses.id, Courses.name, Courses.description, Majors.name as majors, Courses.studyTime FROM Courses, Majors WHERE Courses.majors = Majors.id ORDER BY name ASC";
        try {
            Statement stm = cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                courseTable.addRow(new Object[]{rs.getString("name"), rs.getString("majors"), rs.getString("description"), rs.getString("studyTime")});
            }
        } catch (Exception ex) {
            Logger.getLogger(Helpers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getClass(DefaultTableModel classTable) {
        classTable.setRowCount(0);
        String sql = "SELECT Classes.id, Classes.name, Courses.name as course, Majors.name as majors, Courses.studyTime, Classes.description FROM Classes, Courses, Majors WHERE Classes.course = Courses.id and Courses.majors = Majors.id ORDER BY Courses.studyTime, name";
        try {
            Statement stm = cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                classTable.addRow(new Object[]{rs.getString("name"), rs.getString("course"), rs.getString("majors"), rs.getString("studyTime"), rs.getString("description")});
            }
        } catch (Exception ex) {
            Logger.getLogger(Helpers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void getTeacher(DefaultTableModel teacherTable) {
        teacherTable.setRowCount(0);
        String sql = "SELECT Teachers.id, Teachers.name, Teachers.academicRank, Majors.name as majors, Teachers.phonenumber, Teachers.email "
                + "FROM Teachers, Majors WHERE Teachers.majors = Majors.id ORDER BY Teachers.name";
        try {
            Statement stm = cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                teacherTable.addRow(new Object[]{rs.getString("id"), rs.getString("name"), rs.getString("academicRank"), rs.getString("majors"), rs.getString("phonenumber"), rs.getString("email")});
            }
        } catch (Exception ex) {
            Logger.getLogger(Helpers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
