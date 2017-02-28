package View;
//Martin
import Interface.AccountView;
import Model.Course;

import java.util.ArrayList;


public class AdminView  implements AccountView{

    @Override
    public void mainMenu(String usersName) {
        System.out.println("\nAdminstrator, " + usersName +
                ".\n\nPlease choose an option:" +
                "\n0 Log out" +
                "\n1 Manage Courses" +
                "\n2 Check statistics" +
                "\n3 Manage users accounts" +
                "\n4 Manage Lectures" +
                "\n5 View available teachers and their courses" +
                "\n6 Manage course descriptions");

    }

    @Override
    public void usersCourses(ArrayList<Course> courses) {

        BasicView.courseList(1, "", courses);

        System.out.println("\nChoose a course by it's corresponding number or type 0 to go back.");

    }

    public void optionsAndAdminInfo(String name, String password) {

        System.out.println("\nAdministrator" +
                "\nName: " + name +
                "\nPassword: " + password);

        System.out.println("\nPlease choose an option:" +
                "\n0 Go back" +
                "\n1 Change name" +
                "\n2 Change password");
    }
}
