package Controller;

import Model.*;
import View.*;
import Model.Course;
import Controller.CourseDescController;



import java.util.ArrayList;

public class SchoolController {

    private School model;
    private SchoolView view;

    public SchoolController(School model,
                            SchoolView view) {

        this.model = model;
        this.view = view;
    }

    public void runSystem () {

        while (true) {

            loadSchool();

            this.view.mainScreen(this.model.getName());

            String usersId = userLogin();

            boolean logOut = false;

            while (true) {

                if (usersId.startsWith("stud")) {

                    studentPage(usersId);

                    saveSchool();
                    break;

                }
                else if (usersId.startsWith("teac")) {

                    teacherPage(usersId);

                    saveSchool();
                    break;
                }
                else if (usersId.startsWith("admi")) {

                    adminPage(usersId);

                    saveSchool();
                    break;
                }
            }
        }

    }

    //Nikolay Kanchev
    private void saveSchool(){
        if(this.model.saveSchool()){
            this.view.successfulSave();
        }
    }

    //Nikolay Kanchev
    private void loadSchool(){

        this.model.loadSchool();
        this.view.schoolLoaded();
    }

    /**
     * Rasmus
     * Loops until user inputs valid password
     * @return
     */
    private String userLogin () {

        String userId = "not found";

        while (userId == "not found") {

            this.view.promptLogin();

            String password = InputController.getString();

            userId = model.getUserIdWithPass(password);
        }

        this.view.loggingIn();

        return userId;
    }

    //Rasmus
    private String newPassword () {

        String newPass;

        passValidLoop : while (true) {

            this.view.promptUserPass();
            newPass = InputController.getString();

            if (newPass.equals("0")){
                return "0";
            }

            String idFromPass = this.model.getUserIdWithPass(newPass);

            if (idFromPass.equals("not found")) {
                break passValidLoop;

            } else {
                this.view.passTaken();
            }
        }

        return newPass;
    }


    //region Admin staff
    //Martin
    private void adminPage(String usersId){

        Admin admin = this.model.getAdminWithId(usersId);

        AdminController thisAdmin =
                new AdminController(admin);

        adminMainLoop : while (true){

            thisAdmin.printMainMenu();

            int choice = InputController.getInt();


            switch (choice){
                case 0 :

                    break adminMainLoop;
                case 1 :

                    //region Manage courses
                    courseListLoop: while(true) {

                        ArrayList<Course> courses = this.model.getCourses();
                        this.view.courseListOptions(courses);

                        int input = InputController.getInt();

                        if(input == 0) {

                            break courseListLoop;

                        }else if(input == 1){

                            //region Create course Nikolay Kanchev
                            createCourseLoop: while(true) {
                                boolean present = true;

                                ArrayList<CourseDesc> courseDescs = this.model.getCourseDescs();
                                this.view.promptListCourseDescriptions(courseDescs);
                                int courseDescNumber = InputController.getInt();

                                switch (courseDescNumber){
                                    case 0:
                                        break createCourseLoop;
                                    default:
                                        if (courseDescNumber > 0 && courseDescNumber < courseDescs.size()+1){
                                            CourseDesc chosenCourseDesc = courseDescs.get(courseDescNumber - 1);
                                            this.model.addCourse(new Course(generateCourseId(), present, chosenCourseDesc));
                                            this.view.printCourseCreated();
                                            break createCourseLoop;
                                        }
                                        break;
                                }
                            }
                            //endregion

                        }else if(input > 1 && input < courses.size() + 2) {

                            //region Course options Nikolay Kanchev-------------------------------------

                            courseOptionsLoop : while (true) {

                                Course courseModel = courses.get(input -2);

                                CourseController thisCourse =
                                        new CourseController(courseModel);

                                thisCourse.printCourseInfo();
                                thisCourse.printAdminsOptions();

                                int courseChoice  = InputController.getInt();

                                switch (courseChoice){

                                    case 0:

                                        break courseOptionsLoop;
                                    case 1:

                                        //region Set past/present

                                        thisCourse.printPastPresentOptions();
                                        boolean pastOrPresent = InputController.getBoolean();
                                        thisCourse.setPastPresentCourse(pastOrPresent);

                                        //endregion

                                        break ;
                                    case 2:
                                        //Nikolay Kanchev
                                        //region assign Teacher -----------------------------------------------------

                                        ArrayList<Teacher> availableTeachers =
                                                this.model.getAvailableTeachers(courseModel);

                                        this.view.availableTeachers(availableTeachers);
                                        int teacherChoice = InputController.getInt();

                                        if (teacherChoice > 0 &&
                                                teacherChoice <= availableTeachers.size())
                                        {

                                            Teacher assignedTeacher =
                                                    availableTeachers.get(teacherChoice - 1);

                                            thisCourse.assignTeacher(assignedTeacher);


                                            //this.model.setCourse(thisCourse.getModel());

                                            //this.model.assignTeacher(courseModel, assignedTeacher);

                                            this.view.assignedTeacher(courseModel, assignedTeacher);

                                        } else {

                                            this.view.inputInvalid();

                                        }

                                        //endregion
                                        break ;

                                    case 3:

                                        //Rolandas
                                        //region assign Student --------------------------------------------

                                        assignStudentLoop: while (true) {
                                            ArrayList<Student> availableStudents =
                                                    this.model.getAvailableStudents(courseModel);

                                            this.view.availableStudents(availableStudents);
                                            int in = InputController.getInt();
                                            switch (in){
                                                case 0:
                                                    break assignStudentLoop;
                                                default:
                                                    if(in > 0 && in <= availableStudents.size()){

                                                        this.model.assignStudent(courseModel,
                                                                availableStudents.get(in-1) );

                                                        this.view.assignedStudent(courseModel,
                                                                availableStudents.get(in-1));



                                                    }break courseOptionsLoop;

                                            }

                                        }

                                        //endregion
                                        break ;

                                    case 4:

                                        //Rasmus
                                        //region Show course details --------------------------------------------

                                        ArrayList<Teacher> assignedTeachers =
                                                this.model.getTeachersInCourse(courseModel);
                                        ArrayList<Student> assignedStudents =
                                                this.model.getStudentsInCourse(courseModel);

                                        String studentText = "";

                                        int studentCount = 1;

                                        for (Student thisStudent:assignedStudents) {

                                            if (thisCourse.checkHandIn(thisStudent)) {

                                                studentText +=
                                                        studentCount + " " +
                                                        thisStudent.getName() + ", handed in\n";
                                                studentCount++;
                                            }
                                            else {

                                                studentText +=
                                                        studentCount + " " +
                                                        thisStudent.getName() + ", handin missing\n";

                                            }
                                        }

                                        thisCourse.printCourseDetails(
                                                assignedTeachers,
                                                studentText,
                                                thisCourse.getCoursePrice(assignedTeachers)
                                        );

                                        promptToContinue();
                                        //endregion
                                        break ;

                                    default:

                                        this.view.inputInvalid();
                                        break;
                                }
                            }

                            //endregion

                        }else {
                            this.view.inputInvalid();
                        }
                    }
                    //endregion
                    break;
                case 2 :

                    //region Check statistics (top 3)

                    ArrayList<Student> topStudents = this.model.getTop3Students();
                    ArrayList<Teacher> topTeachers = this.model.getTop3Teachers();

                    this.view.statistics(topStudents, topTeachers);

                    //endregion
                    break;
                case 3 :

                    //region Manage user accounts

                    accListLoop : while (true) {
                        this.view.accListOptions(
                                this.model.getAdmins(),
                                this.model.getTeachers(),
                                this.model.getStudents());

                        int accsListChoice = InputController.getInt();

                        switch (accsListChoice) {
                            case 0:
                                break accListLoop;
                            case 1:

                                //region Create new user account
                                createUserLoop: while(true) {
                                    this.view.newUserOptions();
                                    int newUser = InputController.getInt();
                                    switch (newUser) {
                                        case 0:

                                            break createUserLoop;
                                        case 1:

                                            createTeacher();
                                            break createUserLoop;
                                        case 2:

                                            createStudent();
                                            break createUserLoop;
                                        case 3:

                                            createAdmin();
                                            break createUserLoop;
                                        default:

                                            this.view.inputInvalid();
                                            break;
                                    }
                                }
                                //endregion
                                break;
                            default:

                                //region Manage account - Rasmus
                                int adminsSize = this.model.getAdmins().size();
                                int teachersSize = this.model.getTeachers().size();
                                int studentsSize = this.model.getStudents().size();

                                if(accsListChoice > 1 &&
                                        accsListChoice <= adminsSize + 1){
                                    int userIndex = accsListChoice - 2;

                                    Admin chosenAccModel = this.model.getAdmins().get(userIndex);

                                    AdminController chosenAcc =
                                            new AdminController(chosenAccModel);

                                    //region Manage admin acc
                                    accEditLoop: while (true) {

                                        chosenAcc.printAccountOptions();
                                        int accEditChoice = InputController.getInt();

                                        switch (accEditChoice) {
                                            case 0:

                                                break accEditLoop;
                                            case 1:

                                                this.view.promptUserName();
                                                String newName = InputController.getString();
                                                if (newName.equals("0")){
                                                    break accEditLoop;
                                                }
                                                this.model.getAdmins().get(userIndex).setName(
                                                        newName
                                                );
                                                break;
                                            case 2:

                                                String newPass =  newPassword();
                                                if (newPass.equals("0")){break accEditLoop;}
                                                this.model.getAdmins().get(userIndex).setPassword(
                                                       newPass
                                                );
                                                break;
                                            default:

                                                this.view.inputInvalid();
                                                break;
                                        }
                                    }
                                    //endregion

                                } else if(accsListChoice > adminsSize + 1 &&
                                        accsListChoice <= adminsSize + teachersSize + 1){
                                    int userIndex = accsListChoice - adminsSize - 2;

                                    Teacher chosenAccModel = this.model.getTeachers().get(userIndex);

                                    TeacherController chosenAcc =
                                            new TeacherController(chosenAccModel);

                                    //region Manage Teacher acc

                                    accEditLoop: while (true) {

                                        chosenAcc.printAccountOptions();
                                        int accEditChoice = InputController.getInt();

                                        switch (accEditChoice) {
                                            case 0:

                                                break accEditLoop;
                                            case 1:

                                                this.view.promptUserAvailability(chosenAcc.getAvailability());
                                                this.model.getTeachers().get(userIndex).setAvailability(
                                                        InputController.getBoolean()
                                                );
                                                break;
                                            case 2:

                                                this.view.promptUserName();
                                                String newName = InputController.getString();
                                                if (newName.equals("0")){
                                                    break accEditLoop;
                                                }
                                                this.model.getTeachers().get(userIndex).setName(
                                                        newName
                                                );
                                                break;
                                            case 3:
                                                String newPass =  newPassword();
                                                if (newPass.equals("0")){break accEditLoop;}
                                                this.model.getTeachers().get(userIndex).setPassword(
                                                        newPass
                                                );
                                                break;
                                            case 4:

                                                this.view.promptUserSalary();
                                                this.model.getTeachers().get(userIndex).setSalary(
                                                        InputController.getDouble()
                                                );
                                                break;
                                            default:

                                                this.view.inputInvalid();
                                                break;
                                        }
                                    }
                                    //endregion

                                } else if(accsListChoice > adminsSize + teachersSize + 1 &&
                                        accsListChoice <= adminsSize + teachersSize + studentsSize + 1){

                                    int userIndex = accsListChoice - adminsSize - teachersSize-2;

                                    Student chosenAccModel = this.model.getStudents().get(userIndex);

                                    StudentController chosenAcc =
                                            new StudentController(chosenAccModel);

                                    //region Manage student acc
                                    //Nikolay Kanchev
                                    accEditLoop: while (true) {

                                        chosenAcc.printAccountOptions();
                                        int accEditChoice = InputController.getInt();

                                        switch (accEditChoice) {
                                            case 0:

                                                break accEditLoop;
                                            case 1:

                                                this.view.promptUserName();
                                                String newName = InputController.getString();
                                                if (newName.equals("0")){
                                                    break accEditLoop;
                                                }
                                                this.model.getStudents().get(userIndex).setName(
                                                        newName
                                                );
                                                break;
                                            case 2:
                                                String newPass =  newPassword();
                                                if (newPass.equals("0")){
                                                    break accEditLoop;
                                                }
                                                this.model.getStudents().get(userIndex).setPassword(
                                                        newPass
                                                );
                                                break;
                                            default:

                                                this.view.inputInvalid();
                                                break;
                                        }
                                    }
                                    //endregion

                                } else {

                                        this.view.inputInvalid();
                                }
                                //endregion
                                break;
                        }
                    }

                    //endregion
                    break;

                case 4 :

                    // region Manage Lectures Rolandas

                    lectureListLoop : while (true) {
                        this.view.lectureOptions();

                        int lectureListChoice = InputController.getInt();

                        switch (lectureListChoice) {
                            case 0:
                                break lectureListLoop;
                            case 1:
                                createLecture();
                                break;
                            default:
                                break;
                        }
                    }

                    // endregion  Rolandas

                    break;

                case 5 :

                    //region List of available teachers

                    ArrayList<Teacher> hiredTeachers =
                            this.model.getHiredTeachers();

                    for (Teacher teacherModel : hiredTeachers) {

                        TeacherController teacher =
                                new TeacherController(teacherModel);

                        ArrayList<Course> teachersCourses =
                                this.model.getTeachersCourses(teacherModel);

                        teacher.printCorrospondingCourses(teachersCourses);
                    }
                    promptToContinue();
                    //endregion

                    break;

                case 6:
                    //region manage course description Nikolay Kanchev
                    manageCourseDescLoop : while (true) {

                        this.view.optionsAndCourseDescs(this.model.getCourseDescs());
//                        this.view.write("" +
//                                "0 Go back" +
//                                "\n1 create course description");
//                        int counter = 2;
//
//
//                        for (CourseDesc courseDesc : this.model.getCourseDescs()) {
//                            this.view.write("" + counter + " ");
//                            CourseDescController courseDescController = new CourseDescController(courseDesc);
//                            courseDescController.printCourseDescription();
//                            counter++;
//                        }

                        int input1 = InputController.getInt();

                        switch (input1){
                            case 0:
                                break manageCourseDescLoop;
                            case 1:
                                //region create course description

                                this.view.write("Enter description name: ");
                                String name = InputController.getString();
                                if (name.equals("0")){
                                    break manageCourseDescLoop;
                                }

                                this.view.write("Enter duration: ");
                                double duration = InputController.getDouble();
                                if (duration == 0){
                                    break manageCourseDescLoop;
                                }

                                CourseDesc courseDesc = new CourseDesc(name, duration);
                                this.model.addCourseDesc(courseDesc);

                                this.view.write("Course description was created successfully !!!");

                                //endregion
                                break;
                            default:
                                //region manage existing description

                                int courseDescSize = this.model.getCourseDescs().size() +2;
                                int courseDescIndex = input1 - 2;

                                if (input1 > 1 && input1 < courseDescSize) {

                                    this.view.write("Choose an option: \n" +
                                            "0 Go back" +
                                            "\n1 Change the name" +
                                            "\n2 Change the duration");

                                    int change = InputController.getInt();

                                    switch (change) {

                                        case 0:
                                            break manageCourseDescLoop;

                                        case 1:
                                            //change name

                                            this.view.write("Enter the new name or press '0' to go back");
                                            String newName = InputController.getString();

                                            if(newName.equals("0")){
                                                break;
                                            }

                                            this.model.courseDescChangeName(courseDescIndex, newName);
                                            break;

                                        case 2:
                                            //change duration

                                            this.view.write("Enter the new duration or press '0' to go back");
                                            double newDuration = InputController.getDouble();

                                            if(newDuration == 0){
                                                break;
                                            }

                                            this.model.courseDescChangeDuration(courseDescIndex, newDuration);
                                            break;
                                    }

                                }
                                //endregion
                                break;
                        }

                    }
                    //endregion
                    break;
                default:

                    this.view.inputInvalid();
                    break;
            }
        }

    }

    private void promptToContinue() {

        continueLoop :
        while (true) {

            BasicView.promptContinue();

            if (InputController.getInt() == 0) {

                break continueLoop;
            }
        }
    }

    // Nikolay
    private void createAdmin(){

        newAdminLoop: while (true){

            this.view.promptUserName();
            String userName = InputController.getString();

            if (userName.equals("0")){
                break newAdminLoop;
            }

            String userPass = newPassword();

            if (userPass.equals("0")){
                break newAdminLoop;
            }

            Admin admin = new Admin (userName, userPass, generateAdminId());
            this.model.addAdmin(admin);
            this.view.newUser(admin.getName(), "Administrator");

            break newAdminLoop;
        }
    }

    private String generateAdminId(){

        String latestId = this.model.getLatestAdmin().getId();

        latestId = latestId.substring(4, latestId.length());

        int idNumber = Integer.parseInt(latestId) + 1;

        return "admi" + idNumber;
    }


    private void teacherPage (String usersId) {

        Teacher teacher = this.model.getTeacherWithId(usersId);

        ArrayList<Course> teachersCourses =
                this.model.getTeachersCourses(teacher);

        TeacherController thisTeacher =
                new TeacherController(teacher);

        //region Teacher's main menu

        teacherMainLoop : while (true) {

            thisTeacher.printMainMenu();

            int mainChoice = InputController.getInt();

            switch (mainChoice) {
                case 0:
                    break teacherMainLoop;

                case 1:

                    //region Choose course

                    chooseCourseLoop : while (true) {

                        thisTeacher.printTeachersCourses(teachersCourses);

                        int courseChoice = InputController.getInt();

                        if (courseChoice == 0) {
                            break chooseCourseLoop;
                        }

                        if (courseChoice < teachersCourses.size()+1
                                && courseChoice > 0) {

                            //region Course options
                            Course courseModel= teachersCourses.get(courseChoice - 1 );

                            CourseController thisCourse =
                                    new CourseController(courseModel);

                            courseOptionsLoop: while (true) {
                                thisCourse.printCourseInfo();//Rolandas
                                thisCourse.printTeachersOptions();


                                int courseOption = InputController.getInt();

                                switch (courseOption) {

                                    case 0:
                                        break courseOptionsLoop;

                                    case 1:

                                        //region Course lectures
                                        lectureListLoop : while (true) {

                                            thisCourse.printCourseLectures();
                                            this.view.printCourseChooseLecture();

                                            int lectureListChoice = InputController.getInt();

                                            if (lectureListChoice == 0) {

                                                break lectureListLoop;

                                            }

                                            if (lectureListChoice < thisCourse.getLectures().size()
                                                    && lectureListChoice > 0) {

                                                thisCourse.printCourseLecture(lectureListChoice);
                                                ArrayList<Student> allSchoolsStudents = this.model.getStudents();
                                                ArrayList<String> attendedStudents = thisCourse.getAttendedStudents(lectureListChoice -1);
                                                ArrayList<Student> attendedStudentsObjs = new ArrayList();

                                                for (Student student: allSchoolsStudents){
                                                    for (String strStudent: attendedStudents) {
                                                        if (student.getId().equals(strStudent)){
                                                            attendedStudentsObjs.add(student);
                                                        }
                                                    }
                                                }

                                                thisCourse.printAttendedStudents(lectureListChoice -1);
                                                thisCourse.printAttendedStudentsObjs(attendedStudentsObjs);

                                            }



                                        }
                                        //endregion
                                        break;
                                    case 2:

                                        //region List of students and who handed in Nikolay Kanchev

                                         ArrayList<Student> studentsInCourse =
                                                 this.model.getStudentsInCourse(thisCourse.getModel());
                                        ArrayList<String> handIns = new ArrayList<>();

                                        for (Student student: studentsInCourse) {
                                            if(thisCourse.checkHandIn(student)){
                                                handIns.add("Handed in");
                                            }else{
                                                handIns.add("did't handed in");
                                            }

                                        }

                                            this.view.studentInCourse(studentsInCourse,handIns);


                                        //endregion
                                        break;
                                    default:

                                        break;
                                }
                            }
                            //endregion
                        }

                    }

                    //endregion
                    break;
                default:

                    break;
            }
        }
        //endregion

        saveSchool();
    }

    //Rasmus
    public void createTeacher () {

        newTeacherLoop: while (true){

            this.view.promptUserName();
            String userName = InputController.getString();

            if (userName.equals("0")){
                break newTeacherLoop;
            }

            String userPass = newPassword();

            if (userPass.equals("0")){
                break newTeacherLoop;
            }

            this.view.promptUserSalary();
            double salary = InputController.getDouble();

            if (salary == 0.0){
                break newTeacherLoop;
            }

            Teacher teacher = new Teacher (userName, userPass, generateTeacherId(), salary);
            this.view.newUser("Teacher", teacher.getName());
            this.model.addTeacher(teacher);

            break newTeacherLoop;
        }
    }

    private String generateTeacherId(){

        String latestId = this.model.getLatestTeacher().getId();

        latestId = latestId.substring(4, latestId.length());

        int idNumber = Integer.parseInt(latestId) + 1;

        return "teac" + idNumber;
    }

    //Rolandas
    public void createLecture() {

        newLectureLoop: while (true) {

            this.view.promptLectureName();
            String subjectName = InputController.getString().toLowerCase();

            if (subjectName.equals("0")) {

                break newLectureLoop;
            }

            ArrayList<Course> allCourse = this.model.getCourses();
            int courseIndex;

            while (true) {

                this.view.coursesForLecture(allCourse);
                courseIndex = InputController.getInt();

                if (courseIndex > 0 &&
                        courseIndex <= allCourse.size()) {
                    break;
                } else if (courseIndex == 0) {

                    break newLectureLoop;
                }
            }

            Course courseChosen = allCourse.get(courseIndex - 1);


            ArrayList<Teacher> allTeachers =
                    this.model.getTeachersInCourse(courseChosen);
            int teacherIndex;

            while (true) {

                this.view.teachersForLecture(allTeachers);
                teacherIndex = InputController.getInt();

                if (teacherIndex > 0 &&
                        teacherIndex <= this.model.getCourses().size()) {

                    break;
                } else if (teacherIndex == 0) {

                    break newLectureLoop;
                }
            }

            Teacher teacherChosen = allTeachers.get(teacherIndex - 1);




            if (subjectName.equals("0")){
                break newLectureLoop;
              }
            Lecture lecture = new Lecture (generateLectureId(),
                    generateLectureNo(subjectName),
                    subjectName,
                    teacherChosen.getId());

            this.view.lectureCreated(
                    lecture.getId(),
                    lecture.getLectureNo(),
                    lecture.getSubjectName(),
                    teacherChosen.getName());

            this.model.addLecture(lecture);
            this.model.assignLecture(courseChosen, lecture);

            break newLectureLoop;
        }
    }
    //Rolandas
    private String generateLectureId(){

        String latestId = this.model.getLatestLecture();

        latestId = latestId.substring(4, latestId.length());

        int idNumber = Integer.parseInt(latestId) + 1;

        return "lect" + idNumber;
    }
    //Rolandas
    private int generateLectureNo(String subjectName){
        int latestNumber = this.model.getLatestSubjectLecture(subjectName);
        return latestNumber++;

    }

    //Nikolay Kanchev
    private void studentPage(String usersId){

        Student studentModel = this.model.getStudentWithId(usersId);

        ArrayList<Course> studentsCourses =
                this.model.getStudentsCourses(studentModel);

        StudentController thisStudent =
                new StudentController(studentModel);

        studentMainLoop: while(true){

            thisStudent.printMainMenu();

            int choice = InputController.getInt();


            switch (choice) {
                case 0:
                    break studentMainLoop;

                case 1:

                    //region Choose course

                    chooseCourseLoop:
                    while (true) {

                        thisStudent.printStudentCourses(studentsCourses);

                        int courseChoice = InputController.getInt();

                        if (courseChoice == 0) {
                            break chooseCourseLoop;
                        }

                        if (courseChoice <= studentsCourses.size()
                                && courseChoice > 0) {

                            //region CourseOptions

                            courseOptionsLoop:
                            while (true) {

                                Course courseModel = studentsCourses.get(courseChoice - 1);

                                CourseController thisCourse =
                                        new CourseController(courseModel);

                                thisCourse.printStudentsOptions();

                                int courseOptionsChoice = InputController.getInt();

                                switch (courseOptionsChoice) {
                                    case 0:

                                        break courseOptionsLoop;

                                    case 1:

                                        //region Check status

                                        boolean handedIn = thisCourse.checkHandIn(studentModel);
                                        thisStudent.checkStatus(thisCourse.getLectures(), handedIn);

                                        //endregion
                                        break;

                                    case 2:

                                        //region Attend lecture

                                        attendLectureLoop : while (true) {

                                            thisCourse.printAttendLecture();
                                            int lectureChoice = InputController.getInt();

                                            ArrayList<Lecture> lectures = thisCourse.getLectures();

                                            if (lectureChoice == 0) {
                                                break attendLectureLoop;
                                            }
                                            else if (lectureChoice > 0 &&
                                                    lectureChoice <= lectures.size()) {

                                                thisCourse.studentAttendLecture(lectureChoice - 1, studentModel);

                                                thisCourse.lectureAttended(lectures.get(lectureChoice -1));


                                            } else {

                                                this.view.inputInvalid();
                                            }
                                        }
                                        //endregion
                                        break;

                                    case 3:

                                        //region Hand in assignment

                                        thisCourse.handIn(studentModel);
                                        this.view.handedIn();
                                        //endregion
                                        break;

                                    default:

                                        this.view.inputInvalid();
                                        break;
                                }
                            }

                            //endregion
                        }
                    }

                    //endregion
                    break;

                case 2:

                    //region View all lectures

                    ArrayList<Lecture> attendedLectures = new ArrayList<>();

                    for (Course thisCourse:studentsCourses) {

                        for (Lecture thisLecture:thisCourse.getLectures()) {

                            if (thisLecture.checkStudentAttendance(studentModel)) {
                                attendedLectures.add(thisLecture);
                            }
                        }
                    }

                    thisStudent.printAllAttendedLectures(attendedLectures);

                    promptToContinue();




                    //endregion
                    break;
            }
        }
    }
    //Martin
    private void createStudent (){
        newStudentLoop: while (true){

            this.view.promptUserName();
            String userName = InputController.getString();

            if (userName.equals("0")){
                break newStudentLoop;
            }

            String userPass = newPassword();

            if (userPass.equals("0")){
                break newStudentLoop;
            }

            Student student = new Student (userName, userPass, generateStudentId());
            this.model.addStudent(student);
            this.view.newUser(student.getName(), "Student");

            break newStudentLoop;
        }

    }
    //Nikolay
    private String generateStudentId(){

        String latestId = this.model.getLatestStudent().getId();

        latestId = latestId.substring(4, latestId.length());

        int idNumber = Integer.parseInt(latestId) + 1;

        return "stud" + idNumber;
    }

    //Nikolay Kanchev
    private String generateCourseId(){

        String latestId = this.model.getLatestCourse().getId();

        latestId = latestId.substring(4, latestId.length());

        int idNumber = Integer.parseInt(latestId) + 1;

        return "cour" + idNumber;
    }

}
