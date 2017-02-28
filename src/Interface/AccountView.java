package Interface;

import Model.Course;

import java.util.ArrayList;

/**
 * Created by ${NikolayKanchev} on 11/24/2016.
 */
public interface AccountView {

    public void mainMenu(String usersName);
    public void usersCourses(ArrayList<Course> courses);

}