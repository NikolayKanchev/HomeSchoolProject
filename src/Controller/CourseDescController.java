package Controller;

import Model.CourseDesc;
import View.CourseDescView;

/**
 * Created by ${NikolayKanchev} on 11/23/2016.
 */
public class CourseDescController {

    private CourseDesc model;
    private CourseDescView view;

    public CourseDescController(CourseDesc model){
        this.model = model;
    }

    public void printCourseDescription(){
        this.view.printCourseDesc(this.model);
    }
}
