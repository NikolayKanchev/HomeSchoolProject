package Controller;

import Model.Admin;
import View.AdminView;

/**
 * Created by ${NikolayKanchev} on 11/24/2016.
 */
public class AdminController {

    Admin model ;
    AdminView view = new AdminView();

    public AdminController(Admin model) {
        this.model = model;
    }

    public void printMainMenu(){
        this.view.mainMenu(this.model.getName());
    }

    public void printAccountOptions () {

        this.view.optionsAndAdminInfo(
                this.model.getName(), this.model.getPassword());
    }

}
