package Controller;

import Model.Course;
import Model.Lecture;
import Model.Student;
import Model.Teacher;
import View.CourseView;

import java.util.ArrayList;

/**
 * Created by ${NikolayKanchev} on 11/23/2016.
 */
public class CourseController {

    private Course model;
    private CourseView view = new CourseView();

    public CourseController(Course model) {

        this.model = model;
    }

    public void printTeachersOptions() {

        this.view.teacherOptions();

    }

    //Nikolay Kanchev
    public void printAdminsOptions(){
        this.view.adminsOptions();
    }

    public void printStudentsOptions () {

        this.view.studentsOptions(this.model);
    }

    public void checkStudentStatus () {

    }

    public boolean checkHandIn (Student student) {

        return this.model.checkHandIn(student);
    }

    public void showAttendance() {

    }
    //Rolandas
    public void printCourseInfo(){
        this.view.courseInfo(this.model.getName(), this.model.getPresent());
    }

    public void printCourseLectures(){
        this.view.courseLectures(this.model.getLectures());
    }
    //Rolandas
    public ArrayList<String> getAttendedStudents(int lectureIndex){
            return this.model.getStudentsAttendedLecture(lectureIndex);
    }

    //Rolandas
    public void printCourseLecture(int lectureChoice){

        this.view.courseLecture(this.model.getLectures().get(lectureChoice-1));
    }
    //Rolandas
    public void printAttendedStudents (int lectureIndex){
        this.model.printAttendedStudents(lectureIndex);
    }

    public void printAttendedStudentsObjs( ArrayList<Student> attendedStudentsObjs){
        this.view.printAttendedStudentsObjs(attendedStudentsObjs);
    }

   public ArrayList<Lecture> getLectures(){
     return  this.model.getLectures();
   }

   public void setPastPresentCourse(boolean pastOrPresent){
       this.model.setPresent(pastOrPresent);
   }

   public Course getModel () {

       return this.model;
   }

    public void assignTeacher (Teacher teacher) {

        this.model.assignTeacher(teacher);
    }

    public void assignStudent (Student student) {

        this.model.assignStudent(student);
    }

    public void printAttendLecture () {

        this.view.attendLecture(this.model.getLectures());
    }

    public double getCoursePrice (ArrayList<Teacher> teachers) {

        double coursePrice = 0;

        for (Lecture thisLecture: this.model.getLectures()) {

            String lectureId = thisLecture.getTeacherId();

            for (Teacher thisTeacher:teachers) {

                if (thisTeacher.getId().equals(lectureId)) {
                    coursePrice += thisTeacher.getSalary();
                    break;
                }
            }

        }

        return coursePrice;
    }

    public void printCourseDetails (ArrayList<Teacher> teachers, String students, double coursePrice) {

        this.view.courseDetails(this.model.getName(), teachers, students, coursePrice);
    }

    public void studentAttendLecture (int lectureIndex, Student student) {

        this.model.studentAttendLecture(lectureIndex, student);
    }
    //Rolandas
    public void getStudentAttendedLecture (int lectureIndex) {

        this.model.getStudentsAttendedLecture(lectureIndex);
    }

    public void lectureAttended (Lecture lecture) {

        this.view.lectureAttended(lecture);
    }

    public void handIn (Student student) {
        this.model.setHandIn(student, true);
    }

    public void printPastPresentOptions(){
        this.view.pastPresent();
    }

    public boolean isStudentAssigned(Student student){
        return this.model.isStudentAssigned(student);
    }

}
