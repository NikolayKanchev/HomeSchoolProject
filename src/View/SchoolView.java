package View;

import Model.*;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;

/**
 * Created by ${NikolayKanchev} on 11/23/2016.
 */
public class SchoolView {

    //region All round messages
    public void mainScreen(String schoolName) {
        System.out.println("\n\nWelcome to " + schoolName + ".\n");
    }

    public void successfulSave(){

        System.out.println("\t\t\t\t\tSchool data saved successfully!!!\n");
    }

    public void schoolLoaded() {
        System.out.println("School data loaded...");
    }

    public void inputInvalid(){
        System.out.println(
                "That's not a valid option. " +
                        "Choose a number from the list!!!");
    }

    public void promptLogin() {
        System.out.println("Please login to the system. Enter your password.");
    }

    public void loggingIn() {
        System.out.println("Logging in...\n");
    }
    //endregion



    //Martin
    public void courseListOptions(ArrayList<Course> courses){

        System.out.println("\nPlease choose an option:" +
                "\n0 Go back" +
                "\n1 Create new course");

        BasicView.courseList(2, "Manage ", courses);
    }

    //Rolandas
    public void coursesForLecture(ArrayList<Course> courses){

        System.out.println("\nChoose the course to add the lecture or enter 0 to go back");

        BasicView.courseList(1 , "", courses);
    }

    public void teachersForLecture(ArrayList<Teacher> teachers) {

        System.out.println("\nChoose the teacher to add the lecture or enter 0 to go back");
        BasicView.teacherList(1 , "", teachers);
    }

    //Rasmus
    public void accListOptions(
            ArrayList<Admin> admins,
            ArrayList<Teacher> teachers,
            ArrayList<Student> students){

        System.out.println("\nPlease choose an option:" +
                        "\n0 Go back" +
                        "\n1 Create new user account");

        int optionNo = BasicView.adminList(2, "Manage administrator ", admins);

        optionNo = BasicView.teacherList(optionNo, "Manage teacher ", teachers);

        BasicView.studentList(optionNo, "Manage student ", students);
    }

    //Nikolay Kanchev
    public void newUserOptions(){
        System.out.println("\nPlease choose an option:" +
                "\n0 Go back" +
                "\n1 Teacher" +
                "\n2 Student" +
                "\n3 Admin");
    }

    public void promptUserAvailability (boolean availability) {
        System.out.println(
                "\nUser's current availability: "+ availability +
                "Press '0' to cancel or type 'true' or 'false' to set availability."
        );
    }

    public void promptUserName() {
        System.out.println("Enter users name or press '0' to cancel");
    }

    public void promptUserPass() {
        System.out.println("Enter users password or press '0' to cancel");
    }

    public void passTaken() {
        System.out.println("\nThe password is already taken. You must choose a different password.");
    }

    public void promptUserSalary() {
        System.out.println("Enter users salary or press '0' to cancel");
    }

    public void newUser(String userProfession, String userName){
        System.out.println("\n" + userProfession + " " + userName + " is created.");
    }

    //Rolandas
    public void lectureOptions() {
        System.out.println("\n\n0.Go back\n1.Create Lecture");
    }

    public void promptLectureName() {
        System.out.println("Enter lecture name or press '0' to cancel");
    }

    public void lectureCreated(
            String lectureId,
            int lectureNumber,
            String subjectName,
            String teacherName){

        System.out.println("You have created lecture: " +
                " lecture Id " + lectureId +
                ", lecture Number " + lectureNumber +
                " , subject Name " + subjectName +
                " , teacher Name " + teacherName
        );
    }

    public void availableTeachers(ArrayList<Teacher> availableTeachers){
        int i = 1;
        System.out.println("Choose an option: " +
                "\n0 Go back");

        BasicView.teacherList(1, "", availableTeachers);
    }

    public void assignedTeacher(Course course, Teacher teacher){
        System.out.println("\nTeacher, " + teacher.getName() + ", is assigned to:"+ course);
    }

    public void printAssignedTeacher(Course course, Teacher teacher){
        System.out.println("teacher " + teacher + " is assigned to "+ course);
    }

    //Rolandas
    public void availableStudents(ArrayList<Student> availableStudents){
        int i = 1;
        System.out.println("Choose an option: " +
                "\n0 Go back");
        for (Student student: availableStudents) {
            System.out.println(i +
                    "" + student);
            i++;
        }
    }
    //Rolandas
    public void assignedStudent(Course course, Student student){
        System.out.println("student " + student + " is assigned to "+ course);
    }

    //Rolandas
    public void printCourseChooseLecture() {
        System.out.println("Enter lecture number to choose lecture or enter 0 to go back");
    }

    public void handedIn () {

        System.out.println("Assignment successfully handed in.");
    }

    public void statistics (ArrayList<Student> students, ArrayList<Teacher> teachers) {

        System.out.println("\nThese are the top 3 most active students\n");
        BasicView.studentList(1, "", students);
        System.out.println("\nThese are the top 3 most active teachers\n");
        BasicView.teacherList(1, "", teachers);
    }

    //Nikolay Kanchev
    public void promptListCourseDescriptions(ArrayList<CourseDesc> courseDescs){
        int i = 1;
        System.out.println("Choose a course description from the list: " +
                "\n0 Go back");
        for (CourseDesc courseDesc : courseDescs) {
            System.out.println(i + "" + courseDesc);
            i++;
        }

    }

    public void printCourseCreated(){
        System.out.println("Course was created successfully");
    }

    public void studentInCourse(ArrayList<Student> studentsInCourse, ArrayList<String> handIns){
        int i=1;
        for (Student student: studentsInCourse) {
            System.out.println(i + "" + student + " " + handIns.get(i-1) + "\n");
            i++;
        }
    }

    public void write(Object line){
        System.out.println(line);
    }

    public void optionsAndCourseDescs(ArrayList<CourseDesc> courseDescs){
        int i = 2;
        System.out.println("" +
                "0 Go back\n" +
                "1 create course description");
        for (CourseDesc courseDesc: courseDescs) {
            System.out.println(i + " Manage " + courseDesc.getName() +
                    " " + courseDesc.getDuration() +" years");
            i++;

        }
    }
}

