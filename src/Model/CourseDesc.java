package Model;

import java.io.Serializable;

/**
 * Created by ${NikolayKanchev} on 11/23/2016.
 */
public class CourseDesc implements Serializable{

    private String name;
    private double duration;

    public CourseDesc(String name,
                      double duration) {

        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public double getDuration() {
        return duration;
    }

    @Override
    public String toString() {

        return "\n" + name
                + "\nDuration: " + duration + " years";
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDuration(Double duration){
        this.duration = duration;
    }
}
