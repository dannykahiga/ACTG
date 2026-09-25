package Algorithm_Camp.Data_Classes;

import java.util.ArrayList;

public class Unit {
    private final ArrayList<Lecturer> lecturers;
    private String code = null;
    private String name = null;
    private int max_number_of_students;

    public Unit(String code, String name, int max_number_of_students, ArrayList<Lecturer> lecturers) {
        this.code = code;
        this.name = name;
        this.max_number_of_students = max_number_of_students;
        this.lecturers = lecturers;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getMax_number_of_students() {
        return max_number_of_students;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public String toString() {
        return name;
    }

}

