package Model;

import java.io.Serializable;

public class Admin extends User implements Serializable{

    public Admin(String name,
                 String password,
                 String id) {

        super(name, password, id);
    }

}
