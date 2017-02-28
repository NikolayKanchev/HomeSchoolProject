package View;

import java.util.ArrayList;

/**
 * Created by ${NikolayKanchev} on 11/23/2016.
 */
public class LectureView {

    //Rolandas
    public void printAttendedStudents (ArrayList<String> attendedStudents){
        int i = 0;
        System.out.println("Id list of students that attended this lecture: ");
        for(String student: attendedStudents ){
            System.out.println("Id: " + attendedStudents.get(i));
            i++;
        }
    }
}
