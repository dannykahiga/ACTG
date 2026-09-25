package Algorithm_Camp.Helper_Classes;

import Algorithm_Camp.Data_Classes.Class;
import Database_Controller.Database_Handler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Driver {
    public static final int POPULATION_SIZE = 9;
    public static final double MUTATION_RATE = 0.1;
    public static final double CROSSOVER_RATE = 0.9;
    public static final int TOURNAMENT_SELECTION_SIZE = 3;
    public static final int NUMB_OF_ELITE_SCHEDULES = 1;
    public String course_code;
    public String course_name;
    public String course_semester;
    public String course_YOS;
    public String course_AcademicYR;
    public String rooms;
    public String lecturers;
    public String time;
    public String unit_name;
    public String unit_code;
    public String departments;
    public int id = 0;
    public ObservableList<String> timetable_data_list = FXCollections.observableArrayList();
    public ResultSet resultSet;
    public String query;
    Database_Handler database_handler = Database_Handler.getInstance();
    private int scheduleNumb = 0;
    private int classNumb = 1;
    private Data data;

    public static void Generate_Timetable() {
        Driver driver = new Driver();
        driver.data = new Data();
        int generationNumber = 0;
        driver.printAvailableData();
        System.out.println("> Generation #" + generationNumber);
        System.out.print("  Schedule # |");
        System.out.print("Classes [dept,class,room,lecturer,time slot]      ");
        System.out.println("                                                                                                                                                                         |  Fitness | Conflicts");
        System.out.print(".....................................................................");
        System.out.println("................................................................................");
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(driver.data);
        Population population = new Population(Driver.POPULATION_SIZE, driver.data).sortByFitness();
        population.getSchedules().forEach(schedule -> System.out.println("      " + driver.scheduleNumb++ +
                "      | " + schedule + " | " +
                String.format("%.5f", schedule.getFitness()) +
                " | " + schedule.getNumberOfConflicts()));
        driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
        driver.classNumb = 1;
        while (population.getSchedules().get(0).getFitness() != 1.0) {
            System.out.println("> Generation # " + ++generationNumber);
            System.out.print(" Schedule # |");
            System.out.print("Classes [department,    class,      room,     lecturer,      time-slot]        ");
            System.out.println("                                        |   Fitness | Conflicts");
            System.out.print(".........................................................................");
            System.out.println("................................................................................");
            population = geneticAlgorithm.evolve(population).sortByFitness();
            driver.scheduleNumb = 0;
            population.getSchedules().forEach(schedule -> System.out.println("      " + driver.scheduleNumb++ +
                    "       |" + schedule + " | " +
                    String.format("%.5f", schedule.getFitness()) +
                    "   | " + schedule.getNumberOfConflicts()));
            driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
            driver.classNumb = 1;
        }

    }

    private void printScheduleAsTable(Schedule schedule, int generation) {
        ArrayList<Class> classes = schedule.getClasses();
        System.out.print("\n        ");
        System.out.println("Class number | Department | Course(capacity) | Unit(ID(Number of students) | Lecturer(ID) | Room(Capacity) | Meeting Time(ID)");
        System.out.print("                ");
        System.out.print("...........................................................");
        System.out.println("..................................................................................");
        classes.forEach(x -> {

            int departmentIndex = data.getDepartments().indexOf(x.getDepartments());
            int majorIndex = data.getCourses().indexOf(x.getCourse());
            int unitsIndex = data.getUnits().indexOf(x.getUnit());
            int roomsIndex = data.getRooms().indexOf(x.getRoom());
            int lecturersIndex = data.getLecturer().indexOf(x.getLecturers());
            int meetingTimeIndex = data.getMeetingTimes().indexOf(x.getMeetingtime());

            departments = data.getCourses().get(departmentIndex).getDepartment();
            course_code = data.getCourses().get(majorIndex).getCode();
            course_name = data.getCourses().get(majorIndex).getName();
            course_semester = data.getCourses().get(majorIndex).getSemester();
            course_YOS = data.getCourses().get(majorIndex).getYear_Of_Study();
            course_AcademicYR = data.getCourses().get(majorIndex).getAcademic_Year();
            unit_code = data.getUnits().get(unitsIndex).getCode();
            unit_name = data.getUnits().get(unitsIndex).getName();
            rooms = data.getRooms().get(roomsIndex).getRoomNumber();
            lecturers = data.getLecturer().get(lecturersIndex).getLecturerName();
            time = data.getMeetingTimes().get(meetingTimeIndex).getTime();

            System.out.print("                  ");
            System.out.print(String.format(" %1$02d  ", classNumb) + " | ");
            System.out.print(String.format(" %1$15s", data.getDepartments().get(departmentIndex).getId() + " " +
                    " (" + x.getDepartments().getDepartment_name()) + ")  |");
            System.out.print(String.format(" %1$21s", data.getCourses().get(majorIndex).getName()) + "(" +
                    x.getCourse().getMaxNumberOfStudents() + ")       | ");
            System.out.print(String.format(" %1$21s", data.getUnits().get(unitsIndex).getName()) + " " +
                    "               (" + data.getUnits().get(unitsIndex).getCode() + "(" + x.getUnit().getMax_number_of_students() + ")       | ");
            System.out.print(String.format(" %1$10s", data.getRooms().get(roomsIndex).getRoomNumber() + " " +
                    " (" + x.getRoom().getCapacity()) + ")  |");
            System.out.print(String.format(" %1$15s", data.getLecturer().get(lecturersIndex).getLecturerName() + " " +
                    " (" + data.getLecturer().get(lecturersIndex).getLecturerId()) + ")  |");
            System.out.println(data.getMeetingTimes().get(meetingTimeIndex).getTime() +
                    " (" + data.getMeetingTimes().get(meetingTimeIndex).getId() + ")");

            classNumb++;

            id++;

            //WRITE DATABASE INSERTION QUERIES HERE.
            if (schedule.getFitness() == 1 && schedule.getNumberOfConflicts() == 0) {
                timetable_data_list.addAll("DEPARTMENT: " + departments, "COURSE [(Code: " + course_code + " )", "(Name: " + course_name + " )]", "Semester: " + course_semester, "YOS: " + course_YOS, "AcademicYR: " + course_AcademicYR, "UNIT [(Code: " + unit_code, ")" + "(Name: " + unit_name + ")]", "ROOM: " + rooms, "LECTURER: " + lecturers, "MEETING TIME: " + time + "\n");
                saveTimetableData();
            }
        });
        if (schedule.getFitness() == 1) {
            System.out.println("Hurray!! Solution Found in " + (generation + 1) + " generations");
            System.out.print("\nWANTED TIMETABLE DATA:\n" + timetable_data_list);
            System.out.print("..............................................");
            System.out.println("........................................................................");
        }
    }

    public void saveTimetableData() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E dd/MM/yyyy 'at' hh:mm:ss a zzz");

        String generation_date = new String(ft.format(dNow).toString());

        query = "INSERT INTO Timetable_Data VALUES("
                + "'" + departments + "',"
                + "'" + course_name + "',"
                + "'" + unit_name + "',"
                + "'" + unit_code + "',"
                + "'" + course_semester + "',"
                + "'" + course_YOS + "',"
                + "'" + course_AcademicYR + "',"
                + "'" + rooms + "',"
                + "'" + lecturers + "',"
                + "'" + time + "',"
                + "'" + generation_date + "'"
                + ")";
        if (database_handler.executeAction(query)) {
            System.out.println("Timetable Data Saved Successfully");
            /*
            TrayNotification notification = new TrayNotification();
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setMessage("Timetable Data Saved Successfully...");
            notification.showAndDismiss(Duration.seconds(1.5));
            */
        } else {
            System.out.println("Error saving Timetable Data");
            /*
            TrayNotification notification = new TrayNotification();
            notification.setNotificationType(NotificationType.ERROR);
            notification.setMessage("Error saving Timetable Data...");
            notification.showAndDismiss(Duration.seconds(1.5));
            */
        }
    }

    private void printAvailableData() {
        System.out.println("Available Departments ==>");
        data.getDepartments().forEach(x -> System.out.println("Department Id: " + x.getId() + ", Name: " + x.getDepartment_name()));
        System.out.println("Available Courses ==>");
        data.getCourses().forEach(x -> System.out.println("course: " + x.getName() + "\tNumber of Students: " + x.getMaxNumberOfStudents() + ", units: " + x.getUnits()));
        System.out.println("\nAvailable Units ==>");
        data.getUnits().forEach(x -> System.out.println("Unit code: " + x.getCode() + ", Name: " + x.getName()
                + ", Lecturers:" + x.getLecturers()));
        System.out.println("\nAvailable Rooms ==>");
        data.getRooms().forEach(x -> System.out.println("Room number: " + x.getRoomNumber() + ", Max Capacity: " + x.getCapacity()));
        System.out.println("\nAvailable Lecturers ==>");
        data.getLecturer().forEach(x -> System.out.println("Lec Id: " + x.getLecturerId() + ", Lec Name: " + x.getLecturerName()));
        System.out.println("\nAvailable Timeslots==>");
        data.getMeetingTimes().forEach(x -> System.out.println("id: " + x.getId() + ",Time slot: " + x.getTime()));
        System.out.print(".................................................................");
        System.out.println("..................................................................................");
    }
}
