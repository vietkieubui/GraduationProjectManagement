/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GraduationProjectManagement.Model;

/**
 *
 * @author BVKieu
 */
public class CourseModel {

    public String id;
    public String name;
    public String description;
    public String majors ;

    public CourseModel(String name, String description, String majors) {
        this.name = name;
        this.description = description;
        this.majors = majors;
    }
    
}
