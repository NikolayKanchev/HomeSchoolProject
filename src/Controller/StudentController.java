package Controller;

import Model.Course;
import Model.Lecture;
import Model.Student;
import View.StudentView;

import java.util.ArrayList;


public class StudentController {

    private Student model;
    private StudentView view = new StudentView();

    //Nikolay Kanchev
    public StudentController(Student model) {

        this.model = model;
    }

    public void printMainMenu(){
        this.view.mainMenu(this.model.getName());
    }

    //Nikolay Kanchev
    public void printStudentCourses(ArrayList<Course> courses) {

        this.view.usersCourses(courses);
    }

    public void printAllAttendedLectures (ArrayList<Lecture> lectures) {

        this.view.allAttendedLectures(lectures);
    }

    public void printAccountOptions () {

        this.view.optionsAndStudentInfo(
                this.model.getName(), this.model.getPassword());
    }

    public void checkStatus(ArrayList<Lecture>lectures, boolean handedIn){

        int attendedLectures = 0;

        for (Lecture lecture: lectures)
        {

            if (lecture.checkStudentAttendance(this.model)) {
                attendedLectures++;
            }

        }
        this.view.checkStatus(attendedLectures, lectures.size(), handedIn);
    }
}
