package Algorithm_Camp.Data_Classes;

import java.util.ArrayList;

public class Course {

    private String department;
    private String code;
    private String name;
    private String Semester;
    private String Year_Of_Study;
    private String Academic_Year;
    private int maxNumberOfStudents;
    private ArrayList<Unit> units;

    public Course(String department, String code, String name, String semester, String year_Of_Study, String academic_Year, int maxNumberOfStudents, ArrayList<Unit> units) {
        this.department = department;
        this.code = code;
        this.name = name;
        Semester = semester;
        Year_Of_Study = year_Of_Study;
        Academic_Year = academic_Year;
        this.maxNumberOfStudents = maxNumberOfStudents;
        this.units = units;
    }

    public String getDepartment() {
        return department;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSemester() {
        return Semester;
    }

    public String getYear_Of_Study() {
        return Year_Of_Study;
    }

    public String getAcademic_Year() {
        return Academic_Year;
    }

    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    @Override
    public String toString() {
        return "Course{" +
                "department='" + department + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", Semester='" + Semester + '\'' +
                ", Year_Of_Study='" + Year_Of_Study + '\'' +
                ", Academic_Year='" + Academic_Year + '\'' +
                ", maxNumberOfStudents=" + maxNumberOfStudents +
                ", units=" + units +
                '}';
    }
}
