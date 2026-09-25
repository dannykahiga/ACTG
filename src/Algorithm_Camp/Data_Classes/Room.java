package Algorithm_Camp.Data_Classes;


public class Room {
    private String roomNumber;
    private int capacity;

    public Room(String roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;

    }


    public String getRoomNumber() {
        return roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
