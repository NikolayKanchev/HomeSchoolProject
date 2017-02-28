package Model;

import Controller.LectureController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ${NikolayKanchev} on 11/23/2016.
 */
//Martin Krastev
public class Course implements Serializable{

    private String id ;
    private boolean present;
    private double price;
    private ArrayList<String> teacherId = new ArrayList<>();
    private ArrayList<String> studentId = new ArrayList<>();
    private CourseDesc desc;
    private ArrayList<Lecture> lectures = new ArrayList();
    private HashMap<String, Boolean> handIns = new HashMap<>();

    public Course(String id,
                  boolean present,
                  CourseDesc desc) {

        this.id = id;
        this.present = present;
        this.desc = desc;
        //addLectures();
    }

//    //Rolandas
//   public void addLectures() {
//        lectures.add(new Lecture ("lect001", 1, "ITO", "teac2"));
//        lectures.add(new Lecture ("lect002", 2, "ITO", "teac2"));
//        lectures.add(new Lecture ("lect003", 1, "Software Construction", "teac2"));
//        lectures.add(new Lecture ("lect004", 2, "Software Construction", "teac2"));
//        lectures.add(new Lecture ("lect005", 1, "Software Design", "teac1"));
//        lectures.add(new Lecture ("lect006", 2, "Software Design", "teac1"));
//    }

    public boolean isTeacherAssigned (Teacher teacher) {

        String checkId = teacher.getId();

        boolean isAssigned = false;

        for (String currentId: teacherId) {
            if (currentId.equals(checkId)) {
                isAssigned = true;
            }
        }

        return isAssigned;
    }

    //Nikolay Kanchev
    public boolean isStudentAssigned (Student student) {

        String checkId = student.getId();

        boolean isAssigned = false;

        for (String currentId: studentId) {
            if (currentId.equals(checkId)) {
                isAssigned = true;
            }
        }

        return isAssigned;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<String> getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(ArrayList<String> teacherId) {
        this.teacherId = teacherId;
    }

    public void assignTeacher (Teacher teacher) {

        if (!isTeacherAssigned(teacher)) {

            teacherId.add(teacher.getId());
        }
    }

    public void assignStudent(Student student) {

        if (!isStudentAssigned(student)) {

            studentId.add(student.getId());
            handIns.put(student.getId(), false);
        }
    }

      //Rolandas
    public void assignLecture (Lecture lecture) {

        lectures.add(lecture);

    }

    public ArrayList<Lecture> getLectures() {
        return this.lectures;
    }

    public boolean checkHandIn (Student student) {

        if (isStudentAssigned(student)) {

            if (handIns.get(student.getId())) {

                return true;

            }
        }

        return false;
    }

    @Override
    public String toString() {
        return desc +
                "\nCourse present: " + present;
    }

    //Rolandas
    public String getName(){
        return this.desc.getName();
    }

    public void studentAttendLecture(int lectureIndex, Student student) {

        lectures.get(lectureIndex).studentAttend(student);
    }
    //Rolandas
    public ArrayList<String> getStudentsAttendedLecture(int lectureIndex) {

        return lectures.get(lectureIndex).getAttendedStudents();

    }

    //Rolandas
    public void printAttendedStudents ( int lectureIndex){
        LectureController lectureController = new LectureController(lectures.get(lectureIndex));
        lectureController.printAttendedStudents();
    }

    public void setHandIn (Student student, boolean handedIn) {

        handIns.put(student.getId(), true);
    }

    public HashMap<String, Boolean> getHandIns() {
        return handIns;
    }
}


