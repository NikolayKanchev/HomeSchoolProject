package View;

import Interface.AccountView;
import Model.Course;
import Model.Lecture;

import java.util.ArrayList;

/**
 * Created by ${NikolayKanchev} on 11/7/2016.
 */
public class StudentView implements AccountView {
//Martin Krastev

    @Override
    public void mainMenu(String usersName) {
        System.out.println("\nStudent, " + usersName +
                ".\n\nPlease choose an option:" +
                "\n0 Log out" +
                "\n1 Choose Course" +
                "\n2 View all attended lectures");
    }

    @Override
    public void usersCourses(ArrayList<Course> courses) {

        System.out.println("\nThese are the students  courses:\n");
        int i = 1;

        for (Course course:courses){

            System.out.println(i + " " + course + "\n");
            i++;
        }

        System.out.println("\nChoose a course by it's corresponding number or type 0 to go back.");

    }

    public void optionsAndStudentInfo(String name, String password) {

        System.out.println("\nStudent" +
                "\nName: " + name +
                "\nPassword: " + password);

        System.out.println("\nPlease choose an option:" +
                "\n0 Go back" +
                "\n1 Change name" +
                "\n2 Change password");
    }

    //Martin
    public void checkStatus(int attendedLectures, int amountOfLectures, boolean handedIn){
        System.out.println("Attended " + attendedLectures + " out of " + amountOfLectures + " lectures.");
        System.out.println("Assignment handed in: " + handedIn);
    }

    public void allAttendedLectures (ArrayList<Lecture> lectures) {

        System.out.println("These are all lectures the student ever attended:");
        BasicView.lectureList(1, "", lectures);

    }
}
