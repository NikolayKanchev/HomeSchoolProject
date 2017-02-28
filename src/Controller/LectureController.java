package Controller;

import Model.Lecture;
import View.LectureView;

/**
 * Created by ${Rolandas} on 25/11/2016.
 */
public class LectureController {

    private Lecture model;
    private LectureView view = new LectureView();

    public LectureController(Lecture model) {

        this.model = model;

    }


    public void printAttendedStudents (){
        this.view.printAttendedStudents(this.model.getAttendedStudents());
    }
}