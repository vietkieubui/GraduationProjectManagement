/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package GraduationProjectManagement.Controller;

import GraduationProjectManagement.Controller.Main.MainController;
import GraduationProjectManagement.Services.ConnectDatabase;

public class App {

    public static void main(String[] args) {
//        new Auth_Controller();
//          new ConnectDBController();
        var connectDB = new ConnectDatabase();
        connectDB.connectDatabase("192.168.0.102", "sa", "123");
        new MainController();

    }
}
