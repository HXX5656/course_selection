package main.entity;

public class Classroom {
    private String room_id;
    private String capacity;

    public Classroom(String room_id, String capacity) {
        this.room_id = room_id;
        this.capacity = capacity;
    }

    public Classroom() {
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
}
