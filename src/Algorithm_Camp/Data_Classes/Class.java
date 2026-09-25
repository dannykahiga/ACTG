package Algorithm_Camp.Data_Classes;

public class Class {
    private int id;
    private Course course;
    private Unit units;
    private Lecturer lecturers;
    private MeetingTime meetingTime;
    private Room room;
    private Departments departments;


    public Class(int id, Course course, Unit units) {
        this.id = id;
        this.course = course;
        this.units = units;
    }

    public void setMeetingTime(MeetingTime meetingTime) {
        this.meetingTime = meetingTime;
    }

    public int getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public Unit getUnit() {
        return units;
    }

    public Lecturer getLecturers() {
        return lecturers;
    }

    public void setLecturers(Lecturer lecturers) {
        this.lecturers = lecturers;
    }

    public MeetingTime getMeetingtime() {
        return meetingTime;
    }

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", course=" + course +
                ", units=" + units +
                ", lecturers=" + lecturers +
                ", meetingTime=" + meetingTime +
                ", room=" + room +
                '}';
    }

}

