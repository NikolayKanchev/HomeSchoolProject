package Model;

import java.io.Serializable;

//Nikolay Kanchev
abstract   class User implements Serializable{

    protected String name;
    protected String password;
    protected String id;

    public User(String name,
                String password,
                String id){
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


}
