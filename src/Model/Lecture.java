package Model;

import Controller.LectureController;

import java.io.Serializable;
import java.util.ArrayList;

//Nikolay Kanchev
public class Lecture implements Serializable{

    private String lectureId;
    private int lectureNumber;
    private String subjectName;
    private String teacherId;
    private ArrayList<String> studentsAttended = new ArrayList<>();
    //Martin Krastev
    //Rolandas

    public Lecture(String lectureId,
                   int lectureNumber,
                   String subjectName,
                   String teacherId
    ) {

        this.lectureId = lectureId;
        this.lectureNumber = lectureNumber;
        this.subjectName = subjectName;
        this.teacherId = teacherId;
    }

    public String getId() {
        return lectureId;
    }

    public int getLectureNo() {
        return lectureNumber;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void studentAttend (Student student) {

        studentsAttended.add(student.getId());
    }

    public ArrayList<String> getAttendedStudents() {

        return this.studentsAttended;
    }

    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }

    public int getLectureNumber() {
        return lectureNumber;
    }

    public void setLectureNumber(int lectureNumber) {
        this.lectureNumber = lectureNumber;
    }

    public boolean checkStudentAttendance (Student student) {

        for (String studendId:studentsAttended) {

            if (studendId.equals(student.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean isTeacherAssigned (Teacher teacher) {

        return this.teacherId.equals(teacher.getId());
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public ArrayList<String> getStudentsAttended() {
        return studentsAttended;
    }

    public void setStudentsAttended(ArrayList<String> studentsAttended) {
        this.studentsAttended = studentsAttended;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return this.getSubjectName() + ", no: " + lectureNumber;
    }
}
