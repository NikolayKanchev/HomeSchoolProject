package Model;

import java.io.Serializable;

/**
 * Created by ${NikolayKanchev} on 11/7/2016.
 */
public class Teacher extends User implements Serializable{

    private double salary;
    private boolean available = true;

    public Teacher(String name,
                   String password,
                   String id,
                   double salary) {

        super(name, password, id);
        this.salary = salary;
    }

    //region Setters & getters

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean getAvailability() {
        return available;
    }

    public void setAvailability(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return " " + name;
    }

    //endregion
}
