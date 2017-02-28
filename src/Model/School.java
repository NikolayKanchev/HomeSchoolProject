package Model;

import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

//Martin Krastev
public class School implements Serializable{

    private String name;

    private ArrayList<CourseDesc> courseDescs;
    private ArrayList<Course> courses;
    private ArrayList<Lecture> lectures;
    private ArrayList<Admin> admins;
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    File filepath = new File(System.getProperty("user.dir") + "\\src\\SaveLoad.ser");

    public School(String name) {
        this.name = name;
        courses = new ArrayList<>();
        lectures = new ArrayList<>();
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        admins = new ArrayList<>();
        courseDescs = new ArrayList<>();


        //region Default users & courses for testing
        if (false) {
            courseDescs.add(new CourseDesc("Computer science", 2.5));
            courseDescs.add(new CourseDesc("Multimedia", 2.5));

            courses.add(new Course("cour1", true,courseDescs.get(0)));
            courses.add(new Course("cour2", false, courseDescs.get(1)));

            teachers.add(new Teacher("Bob", "bobpass", "teac1", 23.2));
            teachers.add(new Teacher("Jed", "jedpass", "teac2", 123.5));
            teachers.add(new Teacher("Martin", "matpass", "teac3", 100000.0));
            teachers.add(new Teacher("Bert", "nerpass", "teac4", 123.5));
            teachers.add(new Teacher("Kim", "kimpass", "teac5", 100000.0));

            students.add(new Student("Sly", "slypass", "stud1"));
            students.add(new Student("Poh", "pohpass", "stud2"));
            students.add(new Student("Brock", "bropass", "stud3"));
            students.add(new Student("Scamble", "scapass", "stud4"));

            admins.add(new Admin("Las", "laspass", "admi1"));
            admins.add(new Admin("Nikolay", "nikpass", "admi2"));
            admins.add(new Admin("Marius", "maipass", "admi3"));
            admins.add(new Admin("Rolandes", "rolpass", "admi4"));
            admins.add(new Admin("Rasmus", "raspass", "admi5"));


            courses.get(0).assignTeacher(teachers.get(0));
            courses.get(0).assignStudent(students.get(0));
            courses.get(0).assignStudent(students.get(1));

            courses.get(1).assignTeacher(teachers.get(0));
            courses.get(1).assignTeacher(teachers.get(1));
            courses.get(1).assignStudent(students.get(0));

            //Rolandas
            //courses.get(1).addLectures();
        }
        //endregion
    }

    //Rolandas
    public Course getCourse(int index) {
        return courses.get(index);
    }

    //Nikolay Kanchev
    public ArrayList<Teacher> getAvailableTeachers(Course course){
        ArrayList<Teacher> availableTeachers = new ArrayList<>();


        for (Teacher teacher: teachers) {

            if(!course.isTeacherAssigned(teacher) &&
                    teacher.getAvailability()){
                availableTeachers.add(teacher);

            }

        }
        return availableTeachers;
    }

    //Rasmus
    public ArrayList<Teacher> getTeachersInCourse(Course course){

        ArrayList<Teacher> availableTeachers = new ArrayList<>();


        for (Teacher teacher: teachers) {

            if(course.isTeacherAssigned(teacher)){

                availableTeachers.add(teacher);

            }

        }
        return availableTeachers;
    }

    //Rasmus
    public ArrayList<Student> getStudentsInCourse(Course course){

        ArrayList<Student> studentsFound = new ArrayList<>();


        for (Student student:students) {

            if(course.isStudentAssigned(student)){

                studentsFound.add(student);

            }

        }
        return studentsFound;
    }

    //Rolandas
    public ArrayList<Student> getAvailableStudents(Course course){
        ArrayList<Student> availableStudents = new ArrayList<>();


        for (Student student: students) {
            if(!(course.isStudentAssigned(student))){
                availableStudents.add(student);

            }

        }
        return availableStudents;
    }

    public ArrayList<Teacher> getHiredTeachers(){
        ArrayList<Teacher> hiredTeachers = new ArrayList<>();


        for (Teacher teacher: teachers) {

            if(teacher.getAvailability()){
                hiredTeachers.add(teacher);

            }

        }
        return hiredTeachers;
    }



    //region Save and Load Nikolay Kanchev
    public boolean saveSchool(){
        boolean valueReturn = true;
        try {
            FileOutputStream fos = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);

            oos.close();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            valueReturn = false;
        } catch (IOException e) {
            e.printStackTrace();
            valueReturn = false;
        }

        return valueReturn;
    }

    public void loadSchool(){
        File filepath = new File(System.getProperty("user.dir") + "\\src\\SaveLoad.ser");
        School school = null;

        try {
            FileInputStream fis = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            school = (School) ois.readObject();

            fis.close();
            ois.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        name = school.name;
        courses = school.courses;
        students = school.students;
        teachers = school.teachers;
        admins = school.admins;
        courseDescs = school.courseDescs;

    }
    //endregion

    //region Special getters & setters
    //Rasmus
    public String getUserIdWithPass(String password) {

        String userId = "not found";
        for (Admin admin : admins){
            if(password.equals(admin.getPassword())){
                userId = admin.getId();
            }
        }

        if (userId.equals("not found")) {

            for (Teacher teacher:teachers) {

                if (password.equals(teacher.getPassword())) {

                    userId = teacher.getId();
                }
            }
        }

        if (userId.equals("not found")) {

            for (Student student:students) {

                if (password.equals(student.getPassword())) {

                    userId = student.getId();
                }
            }
        }

        return userId;
    }

    public Admin getAdminWithId(String adminId){

        Admin admin = null;
        for(Admin currentAdmin: admins){
            if(currentAdmin.getId().equals(adminId)){
                admin = currentAdmin;
            }
        }
        return admin;
    }

    public Admin getLatestAdmin() {

        return admins.get(admins.size()-1);
    }

    public void addAdmin(Admin admin){
        admins.add(admin);
    }


    //Rasmus
    public int getCourseIndex (Course course) {

        int courseIndex = 0;

        String courseId = course.getId();

        for (Course thisCourse : courses) {

            if (thisCourse.getId().equals(courseId)){

                return courseIndex;
            }

            courseIndex++;
        }
        return -1;
    }

    public ArrayList<Course> getTeachersCourses (Teacher teacher) {

        ArrayList<Course> teachersCourses = new ArrayList<>();

        for (Course course: courses) {

            if (course.isTeacherAssigned(teacher)) {

                teachersCourses.add(course);

            }

        }

        return teachersCourses;

    }

    //Martin
    public Teacher getTeacherWithId(String teacherId){

        Teacher teacher = null;
        for(Teacher currentTeacher: teachers){
            if(currentTeacher.getId().equals(teacherId)){
                teacher = currentTeacher;
            }
        }
        return teacher;
    }

    public Teacher getLatestTeacher() {

        return teachers.get(teachers.size()-1);
    }

    //Rolandas
    public String getLatestLecture() {
        if (lectures.isEmpty() ) {

            return "lect000";
        }
        return lectures.get(lectures.size()-1).getId();
    }
    //Rolandas

    public int getLatestSubjectLecture(String subjectName) {

        ArrayList<Integer> lectureNumbers = new ArrayList();
        if (lectures.isEmpty()) {

            return 1;
        }

        for (Lecture lecture : lectures) {
            if (lecture.getSubjectName().equals(subjectName)) {
                lectureNumbers.add(lecture.getLectureNo());
            }
        }

        int number = lectureNumbers.size() + 1;
        return number++;

    }
    //Rolandas
    public void addLecture(Lecture lecture){
        lectures.add(lecture);

    }


    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }

    //Nikolay Kanchev
    public ArrayList<Course> getStudentsCourses(Student student) {

        ArrayList<Course> studentsCourses = new ArrayList<>();

        for (Course course: courses) {

            if (course.isStudentAssigned(student)) {

                studentsCourses.add(course);

            }

        }

        return studentsCourses;

    }

    //Nikolay Kanchev
    public Student getStudentWithId(String studentId){

        Student student = null;
        for(Student currentStudent: students){
            if(currentStudent.getId().equals(studentId)){
                student = currentStudent;
            }
        }
        return student;
    }

    public Student getLatestStudent() {

        return students.get(students.size()-1);
    }

    public void addStudent(Student student){
        students.add(student);
    }

    //Rolandas
    public void assignLecture (Course course, Lecture lecture) {

        String courseId = course.getId();

        for (Course thisCourse : courses) {
            if (thisCourse.getId().equals(courseId)){
                thisCourse.assignLecture(lecture);
            }
        }

    }

    //Rasmus
    public void assignStudent (Course course, Student student){

        String courseId = course.getId();

        for (Course thisCourse : courses) {
            if (thisCourse.getId().equals(courseId)){
                thisCourse.assignStudent(student);
                System.out.println(student.getName());
            }
        }

    }

    //Rasmus
    public void setCourse(Course course) {

        int courseIndex = getCourseIndex(course);

        if (courseIndex >= 0) {

            courses.set(courseIndex, course);
        }
    }
    //Nikolay Kanchev
    public ArrayList<CourseDesc> getCourseDescs() {
        return courseDescs;
    }

    //Nikolay Kanchev
    public Course getLatestCourse() {

        return courses.get(courses.size()-1);
    }

    public void addCourse(Course course){

        courses.add(course);
    }



    //Rasmus
    public ArrayList<Student> getTop3Students () {

        int record = 0;

        Student userNo1 = new Student("", "", "none");
        Student userNo2 = userNo1;
        Student userNo3 = userNo1;

        ArrayList<Student> top3Users = new ArrayList<>();

        ArrayList<Lecture> allLectures = new ArrayList<>();

        for (Course thisCourse:courses) {

            for (Lecture thisLecture:thisCourse.getLectures()) {

                allLectures.add(thisLecture);
            }
        }

        //region 1st place

        getNo1Record :
        for (Student thisUser:students) {

            int count = 0;

            for (Lecture thisLecture:allLectures) {

                if (thisLecture.checkStudentAttendance(thisUser)) {

                    count++;

                    if (count > record) {
                        record  = count;
                    }
                }
            }

        }

        getNo1User :
        for (Student thisUser:students) {

            int count = 0;

            for (Lecture thisLecture:allLectures) {

                if (thisLecture.checkStudentAttendance(thisUser)) {

                    count++;

                    if (count == record) {

                        top3Users.add(thisUser);
                    }
                }
            }

        }

        record = 0;

        //endregion

        //region 2nd place

        getNo2Record :
        for (Student thisUser:students) {

            int count = 0;
            boolean isTopUser = false;
            String userId = thisUser.getId();

            for (Student thisTopUser: top3Users) {


                if (userId.equals(thisTopUser.getId())) {

                    isTopUser = true;
                }
            }

            for (Lecture thisLecture:allLectures) {

                if (thisLecture.checkStudentAttendance(thisUser)) {

                    count++;

                    if (count > record && !isTopUser) {
                        record  = count;
                    }
                }
            }

        }

        getNo2User :
        for (Student thisUser:students) {

            int count = 0;
            boolean isTopUser = false;
            String userId = thisUser.getId();

            for (Student thisTopUser: top3Users) {


                if (userId.equals(thisTopUser.getId())) {

                    isTopUser = true;
                }
            }

            for (Lecture thisLecture:allLectures) {

                if (thisLecture.checkStudentAttendance(thisUser)) {

                    count++;

                    if (count == record && !isTopUser) {

                        top3Users.add(thisUser);
                    }
                }
            }

        }

        //endregion

        //region 3rd place

        record = 0;

        getNo3Record :
        for (Student thisUser:students) {

            int count = 0;
            boolean isTopUser = false;
            String userId = thisUser.getId();

            for (Student thisTopUser: top3Users) {


                if (userId.equals(thisTopUser.getId())) {

                    isTopUser = true;
                }
            }

            for (Lecture thisLecture:allLectures) {

                if (thisLecture.checkStudentAttendance(thisUser)) {

                    count++;

                    if (count > record && !isTopUser) {
                        record  = count;
                    }
                }
            }

        }

        getNo3User :
        for (Student thisUser:students) {

            int count = 0;
            boolean isTopUser = false;
            String userId = thisUser.getId();

            for (Student thisTopUser: top3Users) {


                if (userId.equals(thisTopUser.getId())) {

                    isTopUser = true;
                }
            }

            for (Lecture thisLecture:allLectures) {

                if (thisLecture.checkStudentAttendance(thisUser)) {

                    count++;

                    if (count == record && !isTopUser) {

                        top3Users.add(thisUser);
                    }
                }
            }
        }

        //endregion

        return top3Users;
    }

    //Rasmus
    public ArrayList<Teacher> getTop3Teachers () {

        int record = 0;

        Teacher userNo1 = new Teacher("", "", "none", 0);
        Teacher userNo2 = userNo1;
        Teacher userNo3 = userNo1;

        ArrayList<Teacher> top3Users = new ArrayList<>();

        ArrayList<Lecture> allLectures = new ArrayList<>();

        for (Course thisCourse:courses) {

            for (Lecture thisLecture:thisCourse.getLectures()) {

                allLectures.add(thisLecture);
            }
        }

        //region 1st place

        getNo1Record :
        for (Teacher thisUser:teachers) {

            int count = 0;

            for (Lecture thisLecture:allLectures) {

                if (thisLecture.isTeacherAssigned(thisUser)) {

                    count++;

                    if (count > record) {
                        record  = count;
                    }
                }
            }

        }

        getNo1User :
        for (Teacher thisUser:teachers) {

            int count = 0;

            for (Lecture thisLecture:allLectures) {

                if (thisLecture.isTeacherAssigned(thisUser)) {

                    count++;

                    if (count == record) {

                        top3Users.add(thisUser);
                    }
                }
            }

        }

        record = 0;

        //endregion

        //region 2nd place

        getNo2Record :
        for (Teacher thisUser:teachers) {

            int count = 0;
            boolean isTopUser = false;
            String userId = thisUser.getId();

            for (Teacher thisTopUser: top3Users) {


                if (userId.equals(thisTopUser.getId())) {

                    isTopUser = true;
                }
            }

            for (Lecture thisLecture:allLectures) {

                if (thisLecture.isTeacherAssigned(thisUser)) {

                    count++;

                    if (count > record && !isTopUser) {
                        record  = count;
                    }
                }
            }

        }

        getNo2User :
        for (Teacher thisUser:teachers) {

            int count = 0;
            boolean isTopUser = false;
            String userId = thisUser.getId();

            for (Teacher thisTopUser: top3Users) {


                if (userId.equals(thisTopUser.getId())) {

                    isTopUser = true;
                }
            }

            for (Lecture thisLecture:allLectures) {

                if (thisLecture.isTeacherAssigned(thisUser)) {

                    count++;

                    if (count == record && !isTopUser) {

                        top3Users.add(thisUser);
                    }
                }
            }

        }

        //endregion

        //region 3rd place

        record = 0;

        getNo3Record :
        for (Teacher thisUser:teachers) {

            int count = 0;
            boolean isTopUser = false;
            String userId = thisUser.getId();

            for (Teacher thisTopUser: top3Users) {


                if (userId.equals(thisTopUser.getId())) {

                    isTopUser = true;
                }
            }

            for (Lecture thisLecture:allLectures) {

                if (thisLecture.isTeacherAssigned(thisUser)) {

                    count++;

                    if (count > record && !isTopUser) {
                        record  = count;
                    }
                }
            }

        }

        getNo3User :
        for (Teacher thisUser:teachers) {

            int count = 0;
            boolean isTopUser = false;
            String userId = thisUser.getId();

            for (Teacher thisTopUser: top3Users) {


                if (userId.equals(thisTopUser.getId())) {

                    isTopUser = true;
                }
            }

            for (Lecture thisLecture:allLectures) {

                if (thisLecture.isTeacherAssigned(thisUser)) {

                    count++;

                    if (count == record && !isTopUser) {

                        top3Users.add(thisUser);
                    }
                }
            }
        }

        //endregion

        return top3Users;
    }

    //Nikolay Kanchev
    public void addCourseDesc(CourseDesc courseDesc){
        courseDescs.add(courseDesc);
    }

    //Nikolay Kanchev
    public void courseDescChangeName(int index, String name){
        courseDescs.get(index).setName(name);
    }

    //Nikolay Kanchev
    public void courseDescChangeDuration(int index, double duration){
        courseDescs.get(index).setDuration(duration);
    }
    //endregion

    //region Getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Teacher> getTeachers() {
        return this.teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public ArrayList<Admin> getAdmins() {
        return this.admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }
    //endregion
}
