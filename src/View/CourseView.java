package View;

import Model.Course;
import Model.Lecture;
import Model.Student;
import Model.Teacher;

import java.util.ArrayList;

/**
 * Created by ${NikolayKanchev} on 11/23/2016.
 */
public class CourseView {
    //Rolandas
    public void teacherOptions() {
        System.out.println("\n" +
                "\n0.Go back" +
                "\n1.Show course lectures" +
                "\n2.Show Student list and Hand-Ins"/* +
                "\n3.Upload file" +
                "\n4.Delete File"*/);

    }

    public void studentAttendance() {

    }

    public void chooseLecture() {

    }
    //Rolandas
    public void courseInfo(String name, boolean present){
        System.out.println(name + " present: " + present);
    }
    //Rolandas
    public void courseLectures(ArrayList<Lecture> lectures){
        int i=1;
        for (Lecture lecture:lectures){

            System.out.println(i + " " + lecture + "\n");
            i++;
        }


    }
    //Rolandas
    public void courseLecture(Lecture lecture){
            System.out.println("You have choosed lecture: " + lecture + "\n");
    }

    //Rolandas
    public void printAttendedStudentsObjs( ArrayList<Student> attendedStudentsObjs){
        System.out.println("List of student names that attended this lecture: ");
        for (Student student: attendedStudentsObjs){
            System.out.println("Name " + student.getName());
        }
    }

    public void adminsOptions(){
        System.out.println("\n" +
                "\n0 Go back" +
                "\n1 Set past or present" +
                "\n2 Assign teacher" +
                "\n3 Assign student" +
                "\n4 Show course details");
    }

    //Marius
    public void studentsOptions(Course course) {

        System.out.println(
                "\nCourse chosen: " + course.getName() + ", present: " + course.getPresent() +
                "\n\nPlease choose an option:" +
                "\n0 Go back" +
                "\n1 Check Status" +
                "\n2 Attend Lecture" +
                "\n3 Hand in Assignment");

    }

    public void attendLecture(ArrayList<Lecture> lectures){

        BasicView.lectureList(1, "Attend ", lectures);
        System.out.println("Choose lecture to attend or press '0' to go back.");
    }

    public void lectureAttended (Lecture lecture) {

        System.out.println("\nLecture attended: " + lecture.getSubjectName());
    }

    public void courseDetails (String name,
                               ArrayList<Teacher> teachers,
                               String students, double coursePrice) {

        System.out.println(name +
                "\nCourse price: " + coursePrice +
                "\nTeachers:");

        BasicView.teacherList(1, "", teachers);

        System.out.println("\nStudents:" +
                students);
    }
    //Nikolay Kanchev
    public void pastPresent(){
        System.out.println("Type 'true' for present or 'false' for past");
    }
}
