package Controller;


import Model.Course;
import Model.Teacher;
import View.TeacherView;

import java.util.ArrayList;

public class TeacherController {

    private Teacher model;
    private TeacherView view = new TeacherView();

    public TeacherController(Teacher model) {

        this.model = model;
    }

    //Marius
    public void printMainMenu(){
        this.view.mainMenu(this.model.getName());
    }

    public void printTeachersCourses(ArrayList<Course> courses) {

        this.view.usersCourses(courses);
    }

    public void printAccountOptions () {

        this.view.оptionsАndTeachersInfo(
                this.model.getName(), this.model.getPassword(),
                this.model.getSalary(), this.model.getAvailability());
    }

    public boolean getAvailability() {
        return this.model.getAvailability();
    }

    public void setAvailability(boolean available) {
        this.model.setAvailability(available);
    }

    public void printCorrospondingCourses(ArrayList<Course> courses) {

        this.view.corresCourses(this.model.getName(), courses);
    }
}
