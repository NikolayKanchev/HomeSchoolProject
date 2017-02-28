package View;

import Interface.AccountView;
import Model.Course;

import java.util.ArrayList;

/**
 * Created by ${NikolayKanchev} on 11/7/2016.
 */
public class TeacherView implements AccountView {

    //region Shit for creating new teacher...
//    public void getNameInput() {
//        System.out.println("Please enter the teacher's name below.");
//    }
//    public void getSubjectInput() {
//        System.out.println("Please enter the teacher's subject below.");
//    }
//    public void getPaymentInput() {
//        System.out.println("Please enter the teacher's payment below.");
//    }
//    public void getAvailabilityInput() {
//        System.out.println("Is the teacher available?\n1 Yes\n2 No\n");
//    }
    //endregion


    @Override
    public void mainMenu(String usersName) {

        System.out.println("\nTeacher, " + usersName +
                ".\n\nPlease choose an option:" +
                "\n0 Log out" +
                "\n1 Choose Course");
    }

    //Rasmus
    @Override
    public void usersCourses(ArrayList<Course> courses) {

        System.out.println("\nThese are the teachers past and present corresponding courses:\n");
        int i = 1;

        for (Course course:courses){

            System.out.println(i + " " + course + "\n");
            i++;
        }

        System.out.println("\nChoose a course by it's corresponding number or type 0 to go back.");

    }

    public void corresCourses(String name, ArrayList<Course> courses) {

        System.out.println(name + "'s past and present corresponding courses:");
        BasicView.courseList(1, "", courses);
        System.out.println("");
    }


    public void оptionsАndTeachersInfo(String name, String password, double salary, boolean available) {

        System.out.println("\nTeacher" +
                "\nName: " + name +
                "\nPassword: " + password +
                "\nSalary per lecture: " + salary + " kr" +
                "\nAvailable: " + available);

        System.out.println("\nPlease choose an option:" +
                "\n0 Go back" +
                "\n1 Set availability" +
                "\n2 Change name" +
                "\n3 Change password" +
                "\n4 Change salary");
    }
}
