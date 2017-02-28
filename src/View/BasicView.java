package View;

import Model.*;

import java.util.ArrayList;

/**
 * Created by Rasmus on 01-12-2016.
 */
public class BasicView {

    public static void wrongInputInt() {

        System.out.println("Input has to be a whole number. Please try again.");

    }

    public static void wrongInputDouble() {

        System.out.println("Input has to be a number. Please try again.");

    }

    public static void wrongInputBoolean() {

        System.out.println("Input has to be 'true' or 'false'. Please try again.");

    }

    public static void promptContinue() {

        System.out.println("Press '0' to continue...");
    }

    //region Printing lists... Rasmus
    public static void courseList(int startNumber,
                                  String startText,
                                  ArrayList<Course> courses){

        int optionNo = startNumber;

        for (Course course: courses) {

            System.out.println(optionNo + " " +
                    startText +
                    course.getName() +
                    ", present: " + course.getPresent()
            );

            optionNo++;
        }
    }

    public static int adminList(int startNumber,
                                String startText,
                                ArrayList<Admin> admins) {

        int optionNo = startNumber;

        for (Admin admin : admins) {

            System.out.println(optionNo + " " +
                    startText +
                    admin.getName()
            );

            optionNo++;
        }

        return optionNo;
    }

    public static int teacherList(int startNumber,
                                  String startText,
                                  ArrayList<Teacher> teachers) {

        int optionNo = startNumber;

        for (Teacher teacher : teachers) {

            System.out.println(optionNo + " " +
                    startText +
                    teacher.getName() +
                    ", available: " + teacher.getAvailability()
            );

            optionNo++;
        }

        return optionNo;
    }

    public static int studentList(int startNumber,
                                  String startText,
                                  ArrayList<Student> students) {

        int optionNo = startNumber;

        for (Student student : students) {

            System.out.println(optionNo + " " +
                    startText + student.getName()
            );

            optionNo++;
        }

        return optionNo;
    }

    public static int lectureList(int startNumber,
                                  String startText,
                                  ArrayList<Lecture> lectures) {

        int optionNo = startNumber;

        for (Lecture lecture : lectures) {

            System.out.println(optionNo + " " +
                    startText +
                    lecture.getSubjectName() +
                    ", no: " + lecture.getLectureNo()
            );

            optionNo++;
        }

        return optionNo;
    }
    //endregion
}
