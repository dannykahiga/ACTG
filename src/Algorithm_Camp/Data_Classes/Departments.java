package Algorithm_Camp.Data_Classes;

public class Departments {
    private String id;
    private String department_name;

    public Departments(String id, String department_name) {
        this.id = id;
        this.department_name = department_name;
    }

    public String getId() {
        return id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    @Override
    public String toString() {
        return "Departments{" +
                "id='" + id + '\'' +
                ", department_name='" + department_name + '\'' +
                '}';
    }

}
