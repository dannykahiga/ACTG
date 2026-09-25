package Algorithm_Camp.Helper_Classes;

import Algorithm_Camp.Data_Classes.Class;
import Algorithm_Camp.Data_Classes.Course;

import java.util.ArrayList;

public class Schedule {
    private final ArrayList<Class> classes;
    private final Data data;
    private boolean isFitnessChanged = true;
    private double fitness = -1;
    private int classNumb = 0;
    private int numberOfConflicts = 0;

    public Schedule(Data data) {
        this.data = data;
        classes = new ArrayList<Class>(data.getNumberOfClasses());
    }

    public Data getData() {
        return data;
    }

    public Schedule initialize() {
        new ArrayList<Course>(data.getCourses()).forEach(course -> {
            course.getUnits().forEach(units -> {
                Class newClass = new Class(classNumb++, course, units);
                newClass.setMeetingTime(data.getMeetingTimes().get((int) (data.getMeetingTimes().size() * Math.random())));
                newClass.setRoom(data.getRooms().get((int) (data.getRooms().size() * Math.random())));
                newClass.setDepartments(data.getDepartments().get((int) (data.getDepartments().size() * Math.random())));
                newClass.setLecturers(units.getLecturers().get((int) (units.getLecturers().size() * Math.random())));
                classes.add(newClass);
            });
        });

        return this;
    }

    public int getNumberOfConflicts() {
        return numberOfConflicts;
    }

    public ArrayList<Class> getClasses() {
        isFitnessChanged = true;
        return classes;

    }

    public double getFitness() {
        if (isFitnessChanged == true) {
            fitness = calculateFitness();
            isFitnessChanged = false;
        }
        return fitness;
    }

    private double calculateFitness() {
        numberOfConflicts = 0;
        /** classes.forEach((Class x) -> {
         if (x.getCourse().getRooms().indexOf(x.getRoom().getCapacity()) < x.getUnit().getMax_number_of_students()) numberOfConflicts++;
         classes.stream().filter(y -> classes.indexOf(y) >= classes.indexOf(x)).forEach(y ->
         {
         if (x.getMeetingtime() == y.getMeetingtime() && x.getId() != y.getId()) {
         if (x.getCourse().getRooms().indexOf(x.getRoom()) == y.getCourse().getRooms().indexOf(y.getRoom())) numberOfConflicts++;
         if (x.getLecturers() == y.getLecturers()) numberOfConflicts++;
         }
         });
         });*/
        classes.forEach((Class x) -> {
            if (x.getRoom().getCapacity() < x.getUnit().getMax_number_of_students()) numberOfConflicts++;
            classes.stream().filter(y -> classes.indexOf(y) >= classes.indexOf(x)).forEach(y ->
            {
                if (x.getMeetingtime() == y.getMeetingtime() && x.getId() != y.getId()) {
                    if (x.getRoom() == y.getRoom()) numberOfConflicts++;
                    if (x.getLecturers() == y.getLecturers()) numberOfConflicts++;
                }
            });
        });
        return 1 / (double) (numberOfConflicts + 1);
    }

    public String toString() {
        String returnValue = new String();
        for (int x = 0; x < classes.size() - 1; x++) returnValue += classes.get(x) + ",";
        returnValue += classes.get(classes.size() - 1);
        return returnValue;
    }
}
