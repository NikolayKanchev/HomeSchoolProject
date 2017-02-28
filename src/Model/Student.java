package Model;

import java.io.Serializable;

/**
 * Created by ${NikolayKanchev} on 11/7/2016.
 */
public class Student extends User implements Serializable{

    public Student(String name,
                   String password,
                   String id) {

        super(name, password, id);

    }

    @Override
    public String toString() {
        return " " + name;
    }
}
