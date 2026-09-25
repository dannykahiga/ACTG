package Algorithm_Camp.Helper_Classes;

import Algorithm_Camp.Data_Classes.*;

import Database_Controller.Database_Handler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static Database_Controller.Database_Handler.database_handler;

public class Data {

    public ObservableList roomList = FXCollections.observableArrayList();
    public ObservableList lectuerList = FXCollections.observableArrayList();
    public ObservableList unitsList = FXCollections.observableArrayList();
    public ObservableList courseList = FXCollections.observableArrayList();
    public ObservableList meetingTimeList = FXCollections.observableArrayList();
    public ObservableList departmentsList = FXCollections.observableArrayList();
    public ResultSet resultSet_departments;
    public ResultSet resultSet_rooms;
    public ResultSet resultSet_meetingTimes;

    public ResultSet resultSet_units;
    private ArrayList<Room> rooms;
    private ArrayList<MeetingTime> meetingTimes;
    private ArrayList<Lecturer> lecturers;
    private ArrayList<Course> course;
    private ArrayList<Unit> units;
    private ArrayList<Departments> departments;
    private int NumberOfClasses = 0;
    private int numberOfConflicts = 0;

    public Database_Handler database_Handler;
    public Data() {
        database_Handler = Database_Handler.getInstance();
        initialize();
    }

    public Data initialize() {
/**
        Room room1 = new Room("A02", 60);
        Room room2 = new Room("B02", 60);
        Room room3 = new Room("C02", 65);
        rooms = new ArrayList<Room>(Arrays.asList(room1, room2, room3));

        MeetingTime meetingTime1 = new MeetingTime("1", "Monday (0700-1000)");
        MeetingTime meetingTime2 = new MeetingTime("2", "Monday (1000-1300)");
        MeetingTime meetingTime3 = new MeetingTime("3", "Monday (1300-1600)");
        MeetingTime meetingTime4 = new MeetingTime("4", "Monday (1600-1900)");

        meetingTimes = new ArrayList<MeetingTime>(Arrays.asList(meetingTime1, meetingTime2, meetingTime3, meetingTime4));

        Lecturer lecturer1 = new Lecturer("L1", "Dr Bruce Wayne");
        Lecturer lecturer2 = new Lecturer("L2", "Mr Clark Kent");

        Lecturer lecturer3 = new Lecturer("L3", "Mr Oliver Queen");
        Lecturer lecturer4 = new Lecturer("L4", "Dr Barry Allen");
        Lecturer lecturer5 = new Lecturer("L5", "Dr Leo Snart");
        Lecturer lecturer6 = new Lecturer("L6", "Mr Mike Lorrie");
        Lecturer lecturer7 = new Lecturer("L7", "Ms Thea Queen");
        Lecturer lecturer8 = new Lecturer("L8", "Ms Iris West");
        lecturers = new ArrayList<Lecturer>(Arrays.asList(lecturer1, lecturer2, lecturer3, lecturer4, lecturer5, lecturer6, lecturer7, lecturer8));

        Unit unit1 = new Unit("SMA 2424", "Complex Analysis", 50, new ArrayList<Lecturer>(Arrays.asList(lecturer1)));
        Unit unit2 = new Unit("SMA 2465", "Real Analysis", 60, new ArrayList<Lecturer>(Arrays.asList(lecturer1)));
        Unit unit3 = new Unit("ICS 2462", "Computer Networks", 50, new ArrayList<Lecturer>(Arrays.asList(lecturer2)));
        Unit unit4 = new Unit("ICS 2432", "DBMS", 60, new ArrayList<Lecturer>(Arrays.asList(lecturer3)));
        Unit unit5 = new Unit("SMA 2415", "Fluid Mechanics", 50, new ArrayList<Lecturer>(Arrays.asList(lecturer4)));
        units = new ArrayList<Unit>(Arrays.asList(unit1, unit2, unit3, unit4, unit5));

        Course course1 = new Course("CIT", "BCS", "Computer Science", "Two", "1", "1", 40, new ArrayList<Unit>(Arrays.asList(unit1, unit2)));
        Course course2 = new Course("PAS", "BMCS", "Mathematics and Computer Science", "Two", "1", "1", 60, new ArrayList<Unit>(Arrays.asList(unit3, unit4)));
        Course course3 = new Course("CIT", "IT", "Information Technology", "Two", "1", "1", 50, new ArrayList<Unit>(Arrays.asList(unit3, unit5)));
        Course course4 = new Course("CIT", "BCOM", "Bachelor of Commerce", "Two", "1", "1", 63, new ArrayList<Unit>(Arrays.asList(unit4, unit1)));

        course = new ArrayList<Course>(Arrays.asList(course1, course2, course3, course4));

        Departments department1 = new Departments("PAS", "Pure and Applied Sciences");
        Departments department2 = new Departments("CIT", "Communication and Information Technology");
        departments = new ArrayList<Departments>(Arrays.asList(department1, department2));

        course.forEach(x -> NumberOfClasses += x.getUnits().size());
        return this;
        
      */
          //FETCHING ROOMS
        String query_rooms = "SELECT * FROM room";
        resultSet_rooms = database_handler.execQuery(query_rooms);
        try {
            while (resultSet_rooms.next()) {
                String id = resultSet_rooms.getString("ID");
                String room_number = resultSet_rooms.getString("Number");
                int capacity = resultSet_rooms.getInt("Capacity");
                Room room = new Room(id, capacity);
                roomList.addAll(room);
                rooms = new ArrayList<Room>(roomList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //FETCHING DEPARTMENTS
        String query_departments = "SELECT * FROM department";
        resultSet_departments = database_handler.execQuery(query_departments);
        try {
            while (resultSet_departments.next()) {
                String id = resultSet_departments.getString("ID");
                String name = resultSet_departments.getString("Name");
                Departments department = new Departments(id, name);
                departmentsList.addAll(department);
                departments = new ArrayList<Departments>(departmentsList);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //FETCHING MEETING TIMES
        String query_meeting_times = "SELECT * FROM meeting_time";
        resultSet_meetingTimes = database_handler.execQuery(query_meeting_times);
        try {
            while (resultSet_meetingTimes.next()) {
                String id = resultSet_meetingTimes.getString("ID");
                String time = resultSet_meetingTimes.getString("Time");
                MeetingTime meetingTime = new MeetingTime(id, time);
                meetingTimeList.addAll(meetingTime);
                meetingTimes = new ArrayList<MeetingTime>(meetingTimeList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
/**
        String query_units = "SELECT Unit.Lecturer_Name ,Unit.Course_Name,\n"
                + " Course.Number_Of_Students, Course.Semester, \n"
                + " Course.Year_Of_Study, Course.Academic_Year,\n"
                + " Course.Course_Code, Course.Department,\n"
                + " Unit.Unit_Code ,Unit.Unit_Name,\n"
                + " Unit.Number_Of_Students, Lecturer.ID\n"
                + "FROM ((Unit INNER JOIN Lecturer ON Unit.Lecturer_Name = Lecturer.Lecturer_Name)\n"
                + "INNER JOIN Course ON Unit.Course_Name = Course.Course_Name);";
 */
   String query_units = "SELECT unit.Lecturer_Name ,unit.Course_Name,\n" +
           "\t   course.Number_Of_Students, course.Semester,\n" +
           "\t   course.Year_Of_Study, course.Academic_Year,\n" +
           "\t   course.Course_Code, course.Department,\n" +
           "\t   unit.Unit_Code ,unit.Unit_Name,\n" +
           "\t   unit.Number_Of_Students, Lecturer.ID\n" +
           "\t   FROM ((Unit INNER JOIN lecturer ON unit.Lecturer_Name = lecturer.Lecturer_Name)\n" +
           "\t   INNER JOIN course ON unit.Course_Name = course.Course_Name)";
        resultSet_units = database_handler.execQuery(query_units);
        try {
            while (resultSet_units.next()) {
                //LECTURERS
                String lecturer_id = resultSet_units.getString("ID");
                String lecturer_name = resultSet_units.getString("Lecturer_Name");
                Lecturer lecturer = new Lecturer(lecturer_id, lecturer_name);
                lectuerList.addAll(lecturer);
                lecturers = new ArrayList<Lecturer>(lectuerList);

                //UNITS
                String unit_name = resultSet_units.getString("Unit_Name");
                String unit_code = resultSet_units.getString("Unit_Code");
                int number_of_students = resultSet_units.getInt("Number_Of_Students");
                Unit unit = new Unit(unit_code, unit_name, number_of_students, new ArrayList<Lecturer>(Arrays.asList(lecturer)));
                unitsList.addAll(unit);
                units = new ArrayList<Unit>(unitsList);

                //COURSES
                String department = resultSet_units.getString("Department");
                String course_code = resultSet_units.getString("Course_Code");
                String course_name = resultSet_units.getString("Course_Name");
                String course_semester = resultSet_units.getString("Semester");
                String course_YOS = resultSet_units.getString("Year_Of_Study");
                String course_Academic_YR = resultSet_units.getString("Academic_Year");
                int course_NOS = resultSet_units.getInt("Number_Of_Students");
                Course courses = new Course(department, course_code, course_name, course_semester, course_YOS, course_Academic_YR, course_NOS, new ArrayList<Unit>(Arrays.asList(unit)));
                courseList.addAll(courses);
                course = new ArrayList<Course>(courseList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        course.forEach(x -> NumberOfClasses += x.getUnits().size());
        return this;

    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<MeetingTime> getMeetingTimes() {
        return meetingTimes;
    }

    public ArrayList<Lecturer> getLecturer() {
        return lecturers;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public ArrayList<Course> getCourses() {
        return course;
    }

    public ArrayList<Departments> getDepartments() {
        return departments;
    }

    public int getNumberOfClasses() {
        return this.NumberOfClasses;
    }
}
