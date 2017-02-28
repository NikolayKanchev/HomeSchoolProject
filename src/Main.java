import Controller.SchoolController;
import Model.School;
import View.SchoolView;

/**
 * Created by ${NikolayKanchev} on 11/7/2016.
 */
public class Main {


    public static void main(String[] args) {

        School school = new School("KEA");
        SchoolView schoolView = new SchoolView();
        SchoolController schoolController =
                new SchoolController(school, schoolView);

        schoolController.runSystem();

    }
}
