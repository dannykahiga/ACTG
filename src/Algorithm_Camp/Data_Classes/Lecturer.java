package Algorithm_Camp.Data_Classes;

public class Lecturer {
    private String lecturerId;
    private String lecturerName;

    public Lecturer(String lecturerId, String lecturerName) {
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
    }

    public String getLecturerId() {
        return this.lecturerId;

    }

    public String getLecturerName() {
        return this.lecturerName;
    }

    public String toString() {
        return lecturerName;
    }

}
