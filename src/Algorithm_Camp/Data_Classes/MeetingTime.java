package Algorithm_Camp.Data_Classes;

public class MeetingTime {

    private String id;
    private String time;

    public MeetingTime(String id, String time) {
        this.id = id;
        this.time = time;

    }

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "MeetingTime{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
